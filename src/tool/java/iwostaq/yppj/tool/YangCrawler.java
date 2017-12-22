package iwostaq.yppj.tool;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.util.Stack;

import iwostaq.yppj.YangPullParser;
import iwostaq.yppj.YangPullParser.EventType;
import iwostaq.yppj.YangPullParser.StatementType;
import iwostaq.yppj.YangPullParserImpl;
import iwostaq.yppj.exception.YangPullParserException;

/**
 * A crawler that lists all the statements in a given Yang model.
 * 
 */
public final class YangCrawler extends AppMain.AppFunction {

  private Stack<String> stmtStack;

  /**
   * Constructor.
   * 
   */
  public YangCrawler() {
    this.stmtStack = new Stack<>();
  }

  /**
   * Starts the crawling.
   * 
   * @throws IOException
   *           raised when problems related to I/O are occurred.
   * @throws YangPullParserException
   *           raised when parsing problems are occurred.
   */
  @Override
  protected void start()
      throws IOException, YangPullParserException {

    Reader fromFileReader = null;
    try {
      if (super.args.length == 0) {
        fromFileReader = new InputStreamReader(System.in);
      } else {
        fromFileReader = new FileReader(super.args[0]);
      }

      this.crawlThroughFile(new YangPullParserImpl(fromFileReader));
    } finally {
      if (fromFileReader != null) {
        fromFileReader.close();
      }
    }
  }

  private void crawlThroughFile(YangPullParser ypp) throws YangPullParserException {
    assert (ypp != null);

    PrintStream toStream = System.out;
    try {
      while (true) {
        EventType eventType = ypp.next();
        if (eventType == EventType.END_MODULE) {
          break;
        }

        switch (eventType) {
        case STATEMENT_START:
          StatementType stmt = ypp.getStatementType();
          String namespace = ypp.getNamespace();
          String identifier = ypp.getIdentifier();
          String argument = ypp.getArgument();
          String elem = null;
          if (identifier == null) {
            elem = String.format("%s(%s)", stmt.name(), argument);
          } else {
            if (namespace == null) {
              elem = String.format("%s[%s]", stmt.name(), identifier);
            } else {
              elem = String.format("%s[%s:%s]", stmt.name(), namespace, identifier);
            }
          }
          this.stmtStack.push(elem);

          for (String s : this.stmtStack) {
            toStream.print('/');
            toStream.print(s);
          }
          toStream.println();

          break;
        case STATEMENT_END:
          this.stmtStack.pop();
          break;
        case END_MODULE:
          throw new YangPullParserException();
        }
      }
    } catch (IOException e) {
      throw new YangPullParserException(e);
    }
  }
}
