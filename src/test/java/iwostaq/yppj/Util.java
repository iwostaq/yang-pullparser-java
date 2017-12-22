package iwostaq.yppj;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import iwostaq.yppj.YangPullParser.EventType;
import iwostaq.yppj.YangPullParser.StatementType;

public class Util {

  static FileReader getFileReader(String filename) throws IOException {
    return new FileReader(new File("./test/testdata", filename));
  }

  static void assertStartStatementWithId(YangPullParser ypp, StatementType stmt, String ns,
      String id) {
    assertEquals(EventType.STATEMENT_START, ypp.getEventType());
    assertEquals(stmt, ypp.getStatementType());
    assertEquals(ns, ypp.getNamespace());
    assertEquals(id, ypp.getIdentifier());
    assertNull(ypp.getArgument());
  }

  static void assertEndStatementWithId(YangPullParser ypp, StatementType stmt, String ns,
      String id) {
    assertEquals(EventType.STATEMENT_END, ypp.getEventType());
    assertEquals(stmt, ypp.getStatementType());
    assertEquals(ns, ypp.getNamespace());
    assertEquals(id, ypp.getIdentifier());
    assertNull(ypp.getArgument());
  }

  static void assertStartStatementWithStringArg(YangPullParser ypp, StatementType stmt,
      String arg) {
    assertEquals(EventType.STATEMENT_START, ypp.getEventType());
    assertEquals(stmt, ypp.getStatementType());
    assertNull(ypp.getNamespace());
    assertNull(ypp.getIdentifier());
    assertEquals(arg, ypp.getArgument());
  }

  static void assertEndStatementWithStringArg(YangPullParser ypp, StatementType stmt, String arg) {
    assertEquals(EventType.STATEMENT_END, ypp.getEventType());
    assertEquals(stmt, ypp.getStatementType());
    assertNull(ypp.getNamespace());
    assertNull(ypp.getIdentifier());
    assertEquals(arg, ypp.getArgument());
  }
}
