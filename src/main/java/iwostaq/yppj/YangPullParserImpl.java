package iwostaq.yppj;

import java.io.IOException;
import java.io.Reader;
import java.util.Optional;
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

    Optional<StatementType> statementType;
    if (token.getType() == YangLexer.EOF) {
      this.currentEvent = Event.END_DEFINITION;

      return this.currentEvent.getEventType();
    }

    if (this.syntax.isEndStatement(token)) {
      this.currentEvent = this.endStatement();
      return this.currentEvent.getEventType();
    }

    statementType = this.syntax.searchStatementsExpectingIdFor(token);
    if (statementType.isPresent()) {
      Event startEventExpectingId = this.createStartStatementEvent(statementType.get(), token);
      this.readIdentifier(startEventExpectingId);
      this.closeStartStatement(startEventExpectingId);
      this.currentEvent = startEventExpectingId;
      return this.currentEvent.getEventType();
    }

    statementType = this.syntax.searchStatementsExpectingArgumentsFor(token);
    if (statementType.isPresent()) {
      Event startEventExpectingArgument =
          this.createStartStatementEvent(statementType.get(), token);
      String argumentString = this.readString();
      if (argumentString == null) {
        throw new YangPullParserException();
      }
      startEventExpectingArgument.setArgument(argumentString);
      this.closeStartStatement(startEventExpectingArgument);
      this.currentEvent = startEventExpectingArgument;
      return this.currentEvent.getEventType();

    }

    statementType = this.syntax.searchStatementsExpectingNoArgFor(token);
    if (statementType.isPresent()) {
      Event startEventExpectingNoArg = this.createStartStatementEvent(statementType.get(), token);
      this.closeStartStatement(startEventExpectingNoArg);
      this.currentEvent = startEventExpectingNoArg;
      return this.currentEvent.getEventType();
    }

    if (this.syntax.isUnknownStatement(token)) {
      Event startUnknownEvent = new Event(EventType.STATEMENT_START, StatementType.UNKNOWN);
      this.procUnknownStatement(startUnknownEvent);
      this.closeStartStatement(startUnknownEvent);
      this.currentEvent = startUnknownEvent;
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
   * Create the start event from the given token.
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

  protected Event endStatement() throws YangPullParserException {
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

  protected void procUnknownStatement(Event event)
      throws YangPullParserException {
    Token token = this.context.getCurrentToken();
    this.context.consume();
  
    Matcher m = YangPullParserImpl.PATTERN_IDTF.matcher(token.getText());
    if (!m.find()) {
      throw new YangPullParserException("err.unexpected_token", token.getText());
    }
  
    event.setNamespace(m.group("ns"));
    event.setIdentifier(m.group("id"));
 
    Token nextToken = this.context.getCurrentToken();
    if (nextToken.getType() == YangLexer.S_SEMICOLON) {
      return;
    }
    
    String arg = this.readString();
    if (arg != null) {
      event.setArgument(arg);
    }
  }

  protected void readIdentifier(Event event) throws YangPullParserException {
    assert (context != null);
    assert (event != null);
    assert (event.getEventType() == EventType.STATEMENT_START);

    String prefixOrIdtring = this.readString();
    if (prefixOrIdtring == null) {
      throw new YangPullParserException();
    }

    Matcher m = PATTERN_IDTF.matcher(prefixOrIdtring);
    if (!m.find()) {
      throw new YangPullParserException();
    }

    event.setNamespace(m.group("ns"));
    event.setIdentifier(m.group("id"));
  }

  protected String readDateString() throws YangPullParserException {
    String dateString = this.readString();
    if (dateString == null) {
      throw new YangPullParserException();
    }

    Matcher m = PATTERN_DATE.matcher(dateString);
    if (!m.matches()) {
      throw new YangPullParserException();
    }
    return dateString;
  }

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
      if (nextToken.getType() != YangLexer.S_PLUS) {
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
