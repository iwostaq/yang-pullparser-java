package iwostaq.yppj;

import java.util.EmptyStackException;
import java.util.Stack;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

/**
 * A class holding the state of the parser.
 *
 *
 */
public class ParserContext {

  private enum YangVersion {
    V10, V11
  }

  protected YangVersion yangVersion;

  protected Stack<Event> eventStack;

  protected CommonTokenStream tokenStream;

  /**
   * Constructor.
   * 
   * @param tokenStream the CommonTokenStream object of ANTLR v4.
   */
  protected ParserContext(CommonTokenStream tokenStream) {
    if (tokenStream == null) {
      throw new IllegalArgumentException();
    }
    this.yangVersion = YangVersion.V10; // by default
    this.eventStack = new Stack<>();
    this.tokenStream = tokenStream;
  }

  /**
   * Return the Yang version.
   * 
   * @return the version enum
   */
  public YangVersion getYangVersion() {
    return this.yangVersion;
  }

  /**
   * Return the size of the event stack.
   * 
   * @return the size of the stack
   */
  protected int getEventStackSize() {
    return this.eventStack.size();
  }

  /**
   * Return the current lexical token.
   * 
   * @return the current lexical token.
   */
  public Token getCurrentToken() {
    return this.tokenStream.LT(1);
  }

  /**
   * Consume the current lexical token and prepares the next token in the stream.
   */
  public void consume() {
    this.tokenStream.consume();
  }

  /**
   * Return the top on the event stack. The event is removed from the stack.
   *
   * @return the top event
   */
  public Event popTopEvent() {
    try {
      return this.eventStack.pop();
    } catch (EmptyStackException e) {
      return null;
    }
  }

  /**
   * Put the event on the stack.
   * 
   * @param event the event
   */
  public void pushEvent(Event event) {
    if (event != null) {
      this.eventStack.push(event);
    }
  }
}
