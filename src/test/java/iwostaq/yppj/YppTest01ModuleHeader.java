package iwostaq.yppj;

import static org.junit.Assert.*;
import java.io.FileReader;
import org.junit.Ignore;
import org.junit.Test;
import iwostaq.yppj.YangPullParser.StatementType;

public class YppTest01ModuleHeader {

  @Test
  public void parseModuleHeader() {
    try (FileReader fromFile = Util.getFileReader("testmodule-01moduleheader.yang")) {
      YangPullParser ypp = new YangPullParserImpl(fromFile);

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.MODULE, null, "testmodule-01moduleheader");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-01moduleheader");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-01moduleheader");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.YANG_VERSION, "1");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.YANG_VERSION, "1");

      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.MODULE, null, "testmodule-01moduleheader");

    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

  @Test
  public void parseLinkage() {
    try (FileReader fromFile = Util.getFileReader("testmodule-01linkage.yang")) {
      YangPullParser ypp = new YangPullParserImpl(fromFile);

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.MODULE, null, "testmodule-01linkage");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-01linkage");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-01linkage");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.INCLUDE, null, "submodule-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.INCLUDE, null, "submodule-01");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.INCLUDE, null, "submodule-02");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REVISION_DATE, "2019-01-03");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REVISION_DATE, "2019-01-03");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.INCLUDE, null, "submodule-02");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.IMPORT, null, "testmodule-01revision");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.PREFIX, "em01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.PREFIX, "em01");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REVISION_DATE, "2008-02-15");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REVISION_DATE, "2008-02-15");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.IMPORT, null, "testmodule-01revision");

      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.MODULE, null, "testmodule-01linkage");
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

  @Test
  public void parseMeta() {
    try (FileReader fromFile = Util.getFileReader("testmodule-01meta.yang")) {
      YangPullParser ypp = new YangPullParserImpl(fromFile);

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.MODULE, null, "testmodule-01meta");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-01meta");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-01meta");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.ORGANIZATION, "org-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.ORGANIZATION, "org-01");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.CONTACT, "00-000-0000");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.CONTACT, "00-000-0000");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-01");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-01");

      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.MODULE, null, "testmodule-01meta");
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

  @Test
  public void parseRevision() {
    try (FileReader fromFile = Util.getFileReader("testmodule-01revision.yang")) {
      YangPullParser ypp = new YangPullParserImpl(fromFile);

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.MODULE, null, "testmodule-01revision");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-01revision");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-01revision");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REVISION, "2010-04-21");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REVISION, "2010-04-21");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REVISION, "2009-03-07");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REVISION, "2009-03-07");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REVISION, "2008-02-15");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REVISION, "2008-02-15");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REVISION, "2007-01-01");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-02");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-02");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-02");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-02");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REVISION, "2007-01-01");

      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.MODULE, null, "testmodule-01revision");
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

  @Test
  @Ignore
  public void parseYangVer10() {
    try (FileReader fromFile = Util.getFileReader("testmodule-01yangversion10.yang")) {
      YangPullParser ypp = new YangPullParserImpl(fromFile);

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.MODULE, null,
          "testmodule-01yangversion10");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.NAMESPACE,
          "urn:ns-01yangversion10");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-01yangversion10");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.YANG_VERSION, "1");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.YANG_VERSION, "1");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.CONTAINER, null, "c-01");
      ypp.next();
      fail();
    } catch (Exception e) {
      // here
    }
  }

  @Test
  @Ignore
  public void parseYangVer11() {
    try (FileReader fromFile = Util.getFileReader("testmodule-01yangversion11.yang")) {
      YangPullParser ypp = new YangPullParserImpl(fromFile);

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.MODULE, null,
          "testmodule-01yangversion11");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.NAMESPACE,
          "urn:ns-01yangversion11");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-01yangversion11");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.YANG_VERSION, "1.1");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.YANG_VERSION, "1.1");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.CONTAINER, null, "c-01");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.ACTION, null, "act-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.ACTION, null, "act-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.CONTAINER, null, "c-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.MODULE, null, "testmodule-01yangversion11");
    } catch (Exception e) {
      // here
    }
  }
}
