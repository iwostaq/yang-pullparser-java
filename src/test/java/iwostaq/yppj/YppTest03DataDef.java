package iwostaq.yppj;

import static org.junit.Assert.*;
import java.io.FileReader;
import org.junit.Test;
import iwostaq.yppj.YangPullParser.StatementType;

public class YppTest03DataDef {

  @Test
  public void parseLeaf() {
    try (FileReader fromFile = Util.getFileReader("testmodule-03leaf.yang")) {
      YangPullParser ypp = new YangPullParserImpl(fromFile);

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.MODULE, null, "testmodule-03leaf");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-03leaf");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-03leaf");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.LEAF, null, "l-01");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPE, null, "string");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPE, null, "string");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.LEAF, null, "l-01");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.LEAF, null, "l-02");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.WHEN, "when-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.WHEN, "when-01");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPE, null, "string");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPE, null, "string");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.UNITS, "u-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.UNITS, "u-01");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.MUST, "must-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.MUST, "must-01");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.DEFAULT, "def-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.DEFAULT, "def-01");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.STATUS, "current");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.STATUS, "current");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.MANDATORY, "true");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.MANDATORY, "true");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.IF_FEATURE, null, "iff-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.IF_FEATURE, null, "iff-01");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-01");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.IF_FEATURE, null, "iff-02");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.IF_FEATURE, null, "iff-02");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.CONFIG, "true");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.CONFIG, "true");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.LEAF, null, "l-02");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.LEAF, null, "l-03");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.WHEN, "when-02");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-02");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-02");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-02");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-02");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.WHEN, "when-02");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPE, null, "string");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPE, null, "string");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.MUST, "must-02");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.ERROR_MESSAGE, "em-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.ERROR_MESSAGE, "em-01");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.ERROR_APP_TAG, "eat-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.ERROR_APP_TAG, "eat-01");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-03");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-03");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-03");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-03");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.MUST, "must-02");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.MUST, "must-03");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.ERROR_MESSAGE, "em-02");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.ERROR_MESSAGE, "em-02");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.ERROR_APP_TAG, "eat-02");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.ERROR_APP_TAG, "eat-02");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-04");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-04");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-04");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-04");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.MUST, "must-03");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.LEAF, null, "l-03");

      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.MODULE, null, "testmodule-03leaf");
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }
}
