package iwostaq.yppj;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * A class to handle messages.
 * 
 */
public final class Message {

  protected static ResourceBundle bundle;

  static {
    Message.bundle = ResourceBundle.getBundle("iwostaq.yppj.message");
  }

  public static String getById(String id, Object... args) {
    String format;

    if (Message.bundle.containsKey(id)) {
      format = Message.bundle.getString(id);
    } else {
      format = "unknown error: " + id;
      if (0 < args.length) {
        format = format + " " + Arrays.toString(args);
      }
    }

    return MessageFormat.format(format, args);
  }
}
