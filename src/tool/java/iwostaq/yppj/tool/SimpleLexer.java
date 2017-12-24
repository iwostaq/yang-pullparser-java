package iwostaq.yppj.tool;

import java.io.FileReader;
import java.io.IOException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenFactory;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.UnbufferedCharStream;
import iwostaq.yppj.exception.YangPullParserException;
import iwostaq.yppj.g.YangLexer;

public class SimpleLexer extends AppMain.AppFunction {

  @Override
  public void start() throws IOException, YangPullParserException {

    if (super.args.length < 2) {
      new RuntimeException("no file name specified");
    }

    System.out.println("file=" + super.args[0]);
    try (FileReader fromReader = new FileReader(super.args[0])) {
      CharStream fromCharStream = new UnbufferedCharStream(fromReader);
      YangLexer lexer = new YangLexer(fromCharStream);
      lexer.setTokenFactory(new CommonTokenFactory(true));
      CommonTokenStream tokenStream = new CommonTokenStream(lexer);
      // this.tokenStream.seek(0);

      while (true) {
        Token tok = tokenStream.LT(1);
        if (tok.getType() == YangLexer.EOF) {
          break;
        }
        String tokName = YangLexer.VOCABULARY.getDisplayName(tok.getType());
        String line = tok.getText().replace("\\n", "\n").replace("\\r", "\r");
        System.out.println(String.format("%03d:%s:%s", tok.getType(), tokName, line));
        tokenStream.consume();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
