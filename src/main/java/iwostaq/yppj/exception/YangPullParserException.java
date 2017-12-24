package iwostaq.yppj.exception;

import iwostaq.yppj.Message;

/**
 * An exception for the yang-pullparser-java.
 * 
 */
public class YangPullParserException extends Exception {

  /**
   * Default constructor.
   */
  public YangPullParserException() {
    super();
  }

  /**
   * Constructor.
   * 
   * @param e the underlying exception
   */
  public YangPullParserException(Throwable e) {
    super(e);
  }

  /**
   * Constructor.
   * 
   * @param messageId the message ID
   * @param args argument array for the message
   */
  public YangPullParserException(String messageId, Object... args) {
    super(Message.getById(messageId, args));
  }
}
