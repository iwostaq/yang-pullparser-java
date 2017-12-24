package iwostaq.yppj;

import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenFactory;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.UnbufferedCharStream;
import iwostaq.yppj.exception.YangPullParserException;
import iwostaq.yppj.g.YangLexer;

/**
 * A Yang pull parser implementation.
 *
 */
public class YangPullParserImpl implements YangPullParser {

  public static final String VERSION = "1.1.0";

  protected static Pattern PATTERN_DATE = Pattern.compile("\\d{4}\\-\\d\\d\\-\\d\\d");
  protected static Pattern PATTERN_IDTF =
      Pattern.compile("(?:(?<ns>[A-Za-z_][\\w_\\-\\.]*):)?(?<id>[A-Za-z_][\\w_\\-\\.]*)");

  protected CommonTokenStream tokenStream;

  protected ParserContext context;
  protected YangSyntax syntax;

  protected Event currentEvent;

  /**
   * Constructor.
   *
   * @param fromReader the Reader object to read data.
   */
  public YangPullParserImpl(Reader fromReader) {
    if (fromReader == null) {
      throw new IllegalArgumentException();
    }

    CharStream fromCharStream = new UnbufferedCharStream(fromReader);

    YangLexer lexer = new YangLexer(fromCharStream);
    lexer.setTokenFactory(new CommonTokenFactory(true));
    this.tokenStream = new CommonTokenStream(lexer);
    // this.tokenStream.seek(0);

    this.context = new ParserContext(this.tokenStream); // by default
    this.syntax = new YangSyntax();
    this.currentEvent = null;
  }

  /**
   * Returns the next parser event.
   * 
   * @return the next parser event
   * @throws IOException
   * @throws YangPullParserException
   */
  @Override
  public EventType next() throws IOException, YangPullParserException {

    Token token = this.context.getCurrentToken();

    if (token.getType() == YangLexer.EOF) {
      this.currentEvent = Event.END_DEFINITION;

      return this.currentEvent.getEventType();
    }

    if (this.syntax.isEndStatement(token)) {
      this.currentEvent = this.createEndStatementEvent();
      return this.currentEvent.getEventType();
    }

    StatementType statementType;

    // check if the token is a statement followed by an ID.
    statementType = this.syntax.searchStatementsExpectingIdFor(token);
    if (statementType != null) {
      Event event = this.createStartStatementEvent(statementType, token);
      this.parseIdentifier(event);
      this.closeStartStatement(event);
      this.currentEvent = event;
      return this.currentEvent.getEventType();
    }

    // check if the token is a statemnt followed by arguments.
    statementType = this.syntax.searchStatementsExpectingArgumentsFor(token);
    if (statementType != null) {
      Event event = this.createStartStatementEvent(statementType, token);
      event.setArgument(this.readString());
      this.closeStartStatement(event);
      this.currentEvent = event;
      return this.currentEvent.getEventType();

    }

    // check if the token is a statement followed by none.
    statementType = this.syntax.searchStatementsExpectingNoArgFor(token);
    if (statementType != null) {
      Event event = this.createStartStatementEvent(statementType, token);
      this.closeStartStatement(event);
      this.currentEvent = event;
      return this.currentEvent.getEventType();
    }

    // check if the token is an unknown statement.
    if (this.syntax.isUnknownStatement(token)) {
      Event event = new Event(EventType.STATEMENT_START, StatementType.UNKNOWN);
      this.parseUnknownStatement(event);
      this.closeStartStatement(event);
      this.currentEvent = event;
      return this.currentEvent.getEventType();
    }

    throw new YangPullParserException("unknown token:" + token.getText());
  }

  /**
   * Returns the depth of the current statement from the root module statement.
   * 
   * @return the depth of the current statement
   */
  @Override
  public int getDepth() {
    return this.context.getEventStackSize();
  }

  /**
   * Returns the type of the current event.
   * 
   * @return the type of the current event.
   */
  @Override
  public EventType getEventType() {
    if (this.currentEvent == null) {
      return null;
    } else {
      return this.currentEvent.getEventType();
    }
  }

  /**
   * Returns the type of the current statement.
   *
   * @return the type of the current statement
   */
  @Override
  public StatementType getStatementType() {
    if (this.currentEvent == null) {
      return null;
    } else {
      return this.currentEvent.getStatementType();
    }
  }

  /**
   * Returns the namespace of the current statement.
   * 
   * @return the namespace of the current statement
   */
  public String getNamespace() {
    if (this.currentEvent == null) {
      return null;
    } else {
      return this.currentEvent.getNamespace();
    }
  }

  /**
   * Returns the identifier of the current statement.
   * 
   * @return the identifier of the current statement
   */
  @Override
  public String getIdentifier() {
    if (this.currentEvent == null) {
      return null;
    } else {
      return this.currentEvent.getIdentifier();
    }
  }

  /**
   * Returns the argument of the current statement.
   *
   * @return the argument of the current statement.
   */
  @Override
  public String getArgument() {
    if (this.currentEvent == null) {
      return null;
    } else {
      return this.currentEvent.getArgument();
    }
  }

  /**
   * Create a start event from the given token.
   *
   * @param statemntType the statement type
   * @param token the token given
   * @return the start event created from the token parameter.
   */
  protected Event createStartStatementEvent(StatementType statementType, Token token) {
    assert (statementType != null);
    assert (token != null);

    Event startEvent = new Event(EventType.STATEMENT_START, statementType);
    this.context.consume();

    return startEvent;
  }

  /**
   * Create an end event.
   * 
   * @return the created end event
   * @throws YangPullParserException
   */
  protected Event createEndStatementEvent() throws YangPullParserException {
    Event startEvent = this.context.popTopEvent();
    if (startEvent == null) {
      throw new YangPullParserException();
    }

    Event endEvent = new Event(EventType.STATEMENT_END, startEvent.getStatementType());
    endEvent.setNamespace(startEvent.getNamespace());
    endEvent.setIdentifier(startEvent.getIdentifier());
    endEvent.setArgument(startEvent.getArgument());

    this.context.consume();

    return endEvent;
  }

  protected void closeStartStatement(Event event) throws YangPullParserException {
    this.context.pushEvent(event);

    Token nextToken = this.context.getCurrentToken();
    switch (nextToken.getType()) {
      case YangLexer.S_LBR:
        this.context.consume();
        break;
      case YangLexer.S_SEMICOLON:
        break;
      default:
        throw new YangPullParserException("err.unexpected_token", nextToken.getText());
    }
  }

  protected void parseUnknownStatement(Event event) throws YangPullParserException {
    assert (event != null);

    this.parseIdentifier(event);

    Token nextToken = this.context.getCurrentToken();
    if (nextToken.getType() == YangLexer.QUOTED_STRING
        || nextToken.getType() == YangLexer.UNQUOTED_STRING) {
      event.setArgument(this.readString());
    }
  }

  protected void parseIdentifier(Event event) throws YangPullParserException {
    assert (event != null);
    assert (event.getEventType() == EventType.STATEMENT_START);

    String prefixOrIdtring = this.readString();

    Matcher m = PATTERN_IDTF.matcher(prefixOrIdtring);
    if (!m.find()) {
      throw new YangPullParserException("");
    }

    event.setNamespace(m.group("ns"));
    event.setIdentifier(m.group("id"));
  }

  protected String readDateString() throws YangPullParserException {
    String dateString = this.readString();

    Matcher m = PATTERN_DATE.matcher(dateString);
    if (!m.matches()) {
      throw new YangPullParserException("err.unexpected_token", dateString);
    }
    return dateString;
  }

  /**
   * Reads a string from the stream.
   * 
   * @return the string
   * @throws YangPullParserException when meets a token that is not expected.
   */
  protected String readString() throws YangPullParserException {
    Token stringToken = this.context.getCurrentToken();
    if (stringToken.getType() == YangLexer.UNQUOTED_STRING) {
      this.context.consume();
      return stringToken.getText();
    }

    if (stringToken.getType() != YangLexer.QUOTED_STRING) {
      throw new YangPullParserException("err.unexpected_token", stringToken.getText());
    }

    StringBuilder sb = new StringBuilder();
    sb.append(stringToken.getText());
    this.context.consume();

    while (true) {
      Token nextToken = this.context.getCurrentToken();
      if (nextToken.getType() != YangLexer.UNQUOTED_STRING || !("+".equals(nextToken.getText()))) {
        break;
      }
      this.context.consume();

      stringToken = this.context.getCurrentToken();
      if (stringToken.getType() != YangLexer.QUOTED_STRING) {
        throw new YangPullParserException("err.unexpected_token", stringToken.getText());
      }
      sb.append(stringToken.getText());
      this.context.consume();
    }

    return sb.toString();
  }
}
