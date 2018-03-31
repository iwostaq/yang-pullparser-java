package iwostaq.yppj;

import static org.junit.Assert.*;
import java.io.FileReader;
import java.io.StringReader;
import org.junit.Test;
import iwostaq.yppj.YangPullParser.EventType;
import iwostaq.yppj.YangPullParser.StatementType;

public class YppTest00Statement {

  @Test
  public void ConstructorWithNullReader() {
    try {
      new YangPullParserImpl(null);
      fail();
    } catch (IllegalArgumentException e) {
      // success
    }
  }

  @Test
  public void PropertiesAreNullAtFirst() {
    try {
      YangPullParser ypp = new YangPullParserImpl(new StringReader(""));
      assertEquals(null, ypp.getEventType());
      assertEquals(null, ypp.getNamespace());
      assertEquals(null, ypp.getIdentifier());
      assertEquals(null, ypp.getArgument());
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

  @Test
  public void WhiteSpacesShouldBeIgnored() {
    YangPullParser ypp = new YangPullParserImpl(new StringReader("  "));

    try {
      ypp.next();
      assertEquals(EventType.END_MODULE, ypp.getEventType());
      assertEquals(null, ypp.getIdentifier());
      ypp.next();
      assertEquals(EventType.END_MODULE, ypp.getEventType());
      assertEquals(null, ypp.getIdentifier());
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

  @Test
  public void CommentsShouldBeIgnored() {
    try (FileReader fromReader = Util.getFileReader("testmodule-00comment.yang")) {
      YangPullParser ypp = new YangPullParserImpl(fromReader);

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.MODULE, null, "testmodule-00comment");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-00comment");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-00comment");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.ORGANIZATION,
          "This string contains /* and */.");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.ORGANIZATION,
          "This string contains /* and */.");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.DESCRIPTION,
          "// This string starts and ends with //.");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.DESCRIPTION,
          "// This string starts and ends with //.");

      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.MODULE, null, "testmodule-00comment");
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

  @Test
  public void CheckIfGetDepthWorks() {
    try (FileReader fromFile = Util.getFileReader("testmodule-00depth.yang")) {
      YangPullParser ypp = new YangPullParserImpl(fromFile);

      ypp.next(); // module starts
      assertEquals(1, ypp.getDepth());
      ypp.next(); // namespace starts
      assertEquals(2, ypp.getDepth());
      ypp.next(); // namespace ends
      assertEquals(1, ypp.getDepth());
      ypp.next(); // prefix starts
      assertEquals(2, ypp.getDepth());
      ypp.next(); // prefix ends
      assertEquals(1, ypp.getDepth());
      ypp.next(); // container c1 starts
      assertEquals(2, ypp.getDepth());
      ypp.next(); // container c2 starts
      assertEquals(3, ypp.getDepth());
      ypp.next(); // container c2 ends
      assertEquals(2, ypp.getDepth());
      ypp.next(); // container c1 ends
      assertEquals(1, ypp.getDepth());
      ypp.next(); // module ends
      assertEquals(0, ypp.getDepth());
      ypp.next();
      assertEquals(EventType.END_MODULE, ypp.getEventType());
      ypp.next();
      assertEquals(EventType.END_MODULE, ypp.getEventType());
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

  @Test
  public void ComplecatedSpecificationsForString() {
    try (FileReader fromFile = Util.getFileReader("testmodule-00string.yang")) {
      YangPullParser ypp = new YangPullParserImpl(fromFile);

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.MODULE, null, "testmodule-00string");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-00string");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-00string");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REVISION, "2017-01-01");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.DESCRIPTION,
          "a string seperated by a plus.");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.DESCRIPTION,
          "a string seperated by a plus.");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REVISION, "2017-01-01");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REVISION, "2017-01-02");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.DESCRIPTION,
          "unquoted-string-should-be-ok");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.DESCRIPTION,
          "unquoted-string-should-be-ok");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REVISION, "2017-01-02");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REVISION, "2017-01-03");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.DESCRIPTION,
          "a new line character can be represented with\r\n    a backslash (\\\\)  + 'n' (i.e. \\\\n).");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.DESCRIPTION,
          "a new line character can be represented with\r\n    a backslash (\\\\)  + 'n' (i.e. \\\\n).");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REVISION, "2017-01-03");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REVISION, "2017-01-04");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.DESCRIPTION,
          "[0-9]+(d|days|h|hours|hr|m|min|minutes|s|sec|seconds|w|weeks|wk|y|years|yr){0,1}");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.DESCRIPTION,
          "[0-9]+(d|days|h|hours|hr|m|min|minutes|s|sec|seconds|w|weeks|wk|y|years|yr){0,1}");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REVISION, "2017-01-04");

      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.REVISION, "2018-03-01");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.DESCRIPTION, "");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.DESCRIPTION, "");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.REVISION, "2018-03-01");

    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

  // Test cases under below are unfinished yet.

  @Test
  public void ParseUnknownStatements() {
    try (FileReader fromFile = Util.getFileReader("testmodule-00unknown.yang")) {
      YangPullParser ypp = new YangPullParserImpl(fromFile);

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.MODULE, null, "testmodule-00unknown");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-00unknown");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.NAMESPACE, "urn:ns-00unknown");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.PREFIX, "pre-01");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.IMPORT, null, "testmodule-02extension");
      ypp.next();
      Util.assertStartStatementWithStringArg(ypp, StatementType.PREFIX, "pre-02");
      ypp.next();
      Util.assertEndStatementWithStringArg(ypp, StatementType.PREFIX, "pre-02");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.IMPORT, null, "testmodule-02extension");

      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.LEAF, null, "l-01");
      ypp.next();
      assertEquals(EventType.STATEMENT_START, ypp.getEventType());
      assertEquals(StatementType.UNKNOWN, ypp.getStatementType());
      assertEquals("pre-02", ypp.getNamespace());
      assertEquals("ext-03", ypp.getIdentifier());
      assertEquals("info-01", ypp.getArgument());
      ypp.next();
      assertEquals(EventType.STATEMENT_END, ypp.getEventType());
      assertEquals(StatementType.UNKNOWN, ypp.getStatementType());
      assertEquals("pre-02", ypp.getNamespace());
      assertEquals("ext-03", ypp.getIdentifier());
      assertEquals("info-01", ypp.getArgument());
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.TYPE, null, "string");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.TYPE, null, "string");
      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.LEAF, null, "l-01");

      ypp.next();
      assertEquals(EventType.STATEMENT_START, ypp.getEventType());
      assertEquals(StatementType.UNKNOWN, ypp.getStatementType());
      assertEquals("pre-02", ypp.getNamespace());
      assertEquals("ext-04", ypp.getIdentifier());
      assertEquals("info-02", ypp.getArgument());
      ypp.next();
      assertEquals(EventType.STATEMENT_START, ypp.getEventType());
      assertEquals(StatementType.UNKNOWN, ypp.getStatementType());
      assertEquals("pre-02", ypp.getNamespace());
      assertEquals("ext-05", ypp.getIdentifier());
      assertNull(ypp.getArgument());
      ypp.next();
      assertEquals(EventType.STATEMENT_START, ypp.getEventType());
      assertEquals(StatementType.UNKNOWN, ypp.getStatementType());
      assertEquals("pre-02", ypp.getNamespace());
      assertEquals("ext-06", ypp.getIdentifier());
      assertEquals("args", ypp.getArgument());
      ypp.next();
      assertEquals(EventType.STATEMENT_END, ypp.getEventType());
      assertEquals(StatementType.UNKNOWN, ypp.getStatementType());
      assertEquals("pre-02", ypp.getNamespace());
      assertEquals("ext-06", ypp.getIdentifier());
      assertEquals("args", ypp.getArgument());
      ypp.next();
      assertEquals(EventType.STATEMENT_END, ypp.getEventType());
      assertEquals(StatementType.UNKNOWN, ypp.getStatementType());
      assertEquals("pre-02", ypp.getNamespace());
      assertEquals("ext-05", ypp.getIdentifier());
      assertNull(ypp.getArgument());

      ypp.next();
      assertEquals(EventType.STATEMENT_END, ypp.getEventType());
      assertEquals(StatementType.UNKNOWN, ypp.getStatementType());
      assertEquals("pre-02", ypp.getNamespace());
      assertEquals("ext-04", ypp.getIdentifier());
      assertEquals("info-02", ypp.getArgument());

      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.MODULE, null, "testmodule-00unknown");
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

  @Test
  public void testReadIdentifier() {
    YangPullParser ypp = new YangPullParserImpl(new StringReader(" module mymodule {" + "" + "}"));
    try {
      ypp.next();
      Util.assertStartStatementWithId(ypp, StatementType.MODULE, null, "mymodule");

      ypp.next();
      Util.assertEndStatementWithId(ypp, StatementType.MODULE, null, "mymodule");
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }
}
