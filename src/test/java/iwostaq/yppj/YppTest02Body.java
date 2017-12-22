package iwostaq.yppj;

import static org.junit.Assert.*;
import java.io.FileReader;
import org.junit.Test;
import iwostaq.yppj.YangPullParser.StatementType;

public class YppTest02Body {

  @Test
  public void parseExtension() {
    try (FileReader fromFile = Util.getFileReader("testmodule-02extension.yang")) {
      YangPullParser ypp = new YangPullParserImpl(fromFile);

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.MODULE, null, "testmodule-02extension");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-02extension");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-02extension");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.EXTENSION, null, "ext-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.EXTENSION, null, "ext-01");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.EXTENSION, null, "ext-02");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.ARGUMENT, "arg-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.ARGUMENT, "arg-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.EXTENSION, null, "ext-02");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.EXTENSION, null, "ext-03");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.ARGUMENT, "arg-02");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.ARGUMENT, "arg-02");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.EXTENSION, null, "ext-03");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.EXTENSION, null, "ext-04");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.ARGUMENT, "arg-03");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.YIN_ELEMENT, "true");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.YIN_ELEMENT, "true");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.ARGUMENT, "arg-03");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.EXTENSION, null, "ext-04");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.EXTENSION, null, "ext-05");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.ARGUMENT, "arg-04");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.YIN_ELEMENT, "false");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.YIN_ELEMENT, "false");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.ARGUMENT, "arg-04");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.EXTENSION, null, "ext-05");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.EXTENSION, null, "ext-06");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.EXTENSION, null, "ext-06");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.EXTENSION, null, "ext-07");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.EXTENSION, null, "ext-07");

      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.MODULE, null, "testmodule-02extension");

    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

  @Test
  public void parseFeature() {
    try (FileReader fromFile = Util.getFileReader("testmodule-02feature.yang")) {
      YangPullParser ypp = new YangPullParserImpl(fromFile);

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.MODULE, null, "testmodule-02feature");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-02feature");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-02feature");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.FEATURE, null, "feat-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.FEATURE, null, "feat-01");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.FEATURE, null, "feat-02");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.IF_FEATURE, null, "feat-04");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.IF_FEATURE, null, "feat-04");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.FEATURE, null, "feat-02");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.FEATURE, null, "feat-03");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.IF_FEATURE, null, "feat-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.IF_FEATURE, null, "feat-01");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.IF_FEATURE, null, "feat-02");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.IF_FEATURE, null, "feat-02");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.FEATURE, null, "feat-03");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.FEATURE, null, "feat-04");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.STATUS, "obsolete");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.STATUS, "obsolete");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.FEATURE, null, "feat-04");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.FEATURE, null, "feat-05");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.FEATURE, null, "feat-05");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.FEATURE, null, "feat-06");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.FEATURE, null, "feat-06");

      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.MODULE, null, "testmodule-02feature");

    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

  @Test
  public void parseIdentity() {
    try (FileReader fromFile = Util.getFileReader("testmodule-02identity.yang")) {
      YangPullParser ypp = new YangPullParserImpl(fromFile);

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.MODULE, null, "testmodule-02identity");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-02identity");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-02identity");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.IDENTITY, null, "id-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.IDENTITY, null, "id-01");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.IDENTITY, null, "id-02");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.IDENTITY, null, "id-02");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.IDENTITY, null, "id-03");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.BASE, null, "id-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.BASE, null, "id-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.IDENTITY, null, "id-03");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.IDENTITY, null, "id-04");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.STATUS, "current");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.STATUS, "current");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.IDENTITY, null, "id-04");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.IDENTITY, null, "id-05");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.IDENTITY, null, "id-05");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.IDENTITY, null, "id-06");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.IDENTITY, null, "id-06");

      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.MODULE, null, "testmodule-02identity");
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

  @Test
  public void parseTypedef() {
    try (FileReader fromFile = Util.getFileReader("testmodule-02typedef.yang")) {
      YangPullParser ypp = new YangPullParserImpl(fromFile);
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.MODULE, null, "testmodule-02typedef");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-02typedef");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-02typedef");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.IMPORT, null, "testmodule-02type");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.PREFIX, "r");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.PREFIX, "r");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.IMPORT, null, "testmodule-02type");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPEDEF, null, "td-01");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPE, "r", "td-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPE, "r", "td-01");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.UNITS, "u-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.UNITS, "u-01");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.DEFAULT, "def-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.DEFAULT, "def-01");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.STATUS, "deprecated");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.STATUS, "deprecated");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPEDEF, null, "td-01");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPEDEF, null, "td-02");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-01");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-01");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPE, "r", "td-03");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPE, "r", "td-03");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPEDEF, null, "td-02");

      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.MODULE, null, "testmodule-02typedef");
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

  @Test
  public void parseType() {
    try (FileReader fromFile = Util.getFileReader("testmodule-02type.yang")) {
      YangPullParser ypp = new YangPullParserImpl(fromFile);
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.MODULE, null, "testmodule-02type");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-02type");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-02type");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.IMPORT, null, "testmodule-02identity");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.PREFIX, "tm-02");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.PREFIX, "tm-02");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.IMPORT, null, "testmodule-02identity");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPEDEF, null, "td-01");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPE, null, "string");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPE, null, "string");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPEDEF, null, "td-01");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPEDEF, null, "td-02");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPE, null, "int32");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.RANGE, "1..100");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.RANGE, "1..100");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPE, null, "int32");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPEDEF, null, "td-02");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPEDEF, null, "td-03");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPE, null, "int64");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.RANGE, "1..100");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.ERROR_MESSAGE, "erm-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.ERROR_MESSAGE, "erm-01");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.ERROR_APP_TAG, "eat-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.ERROR_APP_TAG, "eat-01");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-01");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.RANGE, "1..100");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPE, null, "int64");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPEDEF, null, "td-03");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPEDEF, null, "td-04");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPE, null, "decimal64");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.FRACTION_DIGITS, "12");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.FRACTION_DIGITS, "12");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPE, null, "decimal64");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPEDEF, null, "td-04");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPEDEF, null, "td-05");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPE, null, "string");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.LENGTH, "10");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.LENGTH, "10");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.PATTERN, "pat-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.PATTERN, "pat-01");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.PATTERN, "pat-02");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.ERROR_MESSAGE, "erm-02");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.ERROR_MESSAGE, "erm-02");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.ERROR_APP_TAG, "eat-02");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.ERROR_APP_TAG, "eat-02");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-02");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-02");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-02");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-02");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.PATTERN, "pat-02");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPE, null, "string");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPEDEF, null, "td-05");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPEDEF, null, "td-06");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPE, null, "string");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.LENGTH, "20");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.ERROR_MESSAGE, "erm-03");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.ERROR_MESSAGE, "erm-03");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.ERROR_APP_TAG, "eat-03");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.ERROR_APP_TAG, "eat-03");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-03");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-03");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-03");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-03");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.LENGTH, "20");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPE, null, "string");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPEDEF, null, "td-06");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPEDEF, null, "td-07");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPE, null, "enumeration");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.ENUM, "en-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.ENUM, "en-01");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.ENUM, "en-02");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.VALUE, "1");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.VALUE, "1");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-04");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-04");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-04");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-04");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.ENUM, "en-02");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPE, null, "enumeration");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPEDEF, null, "td-07");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPEDEF, null, "td-08");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPE, null, "leafref");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.PATH, "../l-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.PATH, "../l-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPE, null, "leafref");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPEDEF, null, "td-08");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.LEAF, null, "l-01");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPE, null, "empty");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPE, null, "empty");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.LEAF, null, "l-01");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPEDEF, null, "td-09");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPE, null, "identityref");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.BASE, "tm-02", "id-05");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.BASE, "tm-02", "id-05");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPE, null, "identityref");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPEDEF, null, "td-09");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPEDEF, null, "td-10");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPE, null, "instance-identifier");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REQUIRE_INSTANCE, "false");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REQUIRE_INSTANCE, "false");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPE, null, "instance-identifier");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPEDEF, null, "td-10");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPEDEF, null, "td-11");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPE, null, "bits");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.BIT, null, "bt-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.BIT, null, "bt-01");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.BIT, null, "bt-02");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.POSITION, "12");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.POSITION, "12");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.STATUS, "deprecated");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.STATUS, "deprecated");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-05");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-05");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-05");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-05");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.BIT, null, "bt-02");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPE, null, "bits");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPEDEF, null, "td-11");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPEDEF, null, "td-12");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPE, null, "union");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPE, null, "string");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPE, null, "string");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPE, null, "union");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPE, null, "string");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPE, null, "string");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPE, null, "int32");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPE, null, "int32");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPE, null, "union");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPE, null, "union");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPEDEF, null, "td-12");

      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.MODULE, null, "testmodule-02type");
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

  @Test
  public void parseGrouping() {
    try (FileReader fromFile = Util.getFileReader("testmodule-02grouping.yang")) {
      YangPullParser ypp = new YangPullParserImpl(fromFile);
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.MODULE, null, "testmodule-02grouping");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-02grouping");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-02grouping");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.GROUPING, null, "gr-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.GROUPING, null, "gr-01");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.GROUPING, null, "gr-02");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.GROUPING, null, "gr-02");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.GROUPING, null, "gr-03");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.STATUS, "current");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.STATUS, "current");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.DESCRIPTION, "desc-01");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REFERENCE, "ref-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.GROUPING, null, "gr-03");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.GROUPING, null, "gr-04");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPEDEF, null, "td-01");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPE, null, "string");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPE, null, "string");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPEDEF, null, "td-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.GROUPING, null, "gr-04");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.GROUPING, null, "gr-05");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.GROUPING, null, "sgr-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.GROUPING, null, "sgr-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.GROUPING, null, "gr-05");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.GROUPING, null, "gr-06");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.LEAF, null, "l-01");
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPE, null, "int32");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPE, null, "int32");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.LEAF, null, "l-01");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.GROUPING, null, "gr-06");
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }
}
