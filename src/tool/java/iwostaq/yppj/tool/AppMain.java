package iwostaq.yppj.tool;

import java.io.IOException;
import java.util.Arrays;

import iwostaq.yppj.exception.YangPullParserException;

public final class AppMain {

  private static final String[] HELP_MESSAGES = new String[] {
      "usage: java -jar yang-pull-parser-tool.jar <command> [<arg>]",
      "",
      "COMMAND:",
      "",
      "help",
      "\tDisplays this messages",
      "crawl <file path>",
      "\tcrawls a YANG file given as the argument.",
  };

  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("no command given");
      return;
    }

    try {
      AppFunction func;
      switch (args[0]) {
      case "crawl":
        func = new YangCrawler();
        break;
      case "lex":
        func = new SimpleLexer();
        break;
      default:
        func = new AppMain.HelpDisplayer();
      }

      func.setArguments(Arrays.copyOfRange(args, 1, args.length));
      func.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected static abstract class AppFunction {
    protected String[] args;

    protected AppFunction() {
      this.args = new String[0];
    }

    protected void setArguments(String[] args) {
      if (args == null) {
        args = new String[0];
      }

      this.args = args;
    }

    protected abstract void start() throws YangPullParserException, IOException;
  }

  /**
   * A class responsible for printing help messages.
   */
  protected static class HelpDisplayer extends AppFunction {
    protected void start() {
      for (String helpMessage : AppMain.HELP_MESSAGES) {
        System.out.println(helpMessage);
      }
    }
  }
}
