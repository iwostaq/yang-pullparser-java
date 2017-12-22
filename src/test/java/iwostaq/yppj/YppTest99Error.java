package iwostaq.yppj;

import static org.junit.Assert.*;
import java.io.StringReader;
import org.junit.Test;
import iwostaq.yppj.exception.YangPullParserException;

public class YppTest99Error {

  @Test
  public void stringsAreConcatenatedWithAPlus() {
    try {
      YangPullParser ypp = new YangPullParserImpl(new StringReader("organization \"abc\" + {"));

      ypp.next();
      fail();
    } catch (Exception e) {
      assertEquals(YangPullParserException.class, e.getClass());
      assertEquals("unexpected token: {", e.getMessage());
    }
  }

  @Test
  public void missingIdentifier() {
    try {
      YangPullParser ypp = new YangPullParserImpl(new StringReader("module { }"));

      ypp.next();
      fail();
    } catch (Exception e) {
      assertEquals(YangPullParserException.class, e.getClass());
      assertEquals("unexpected token: {", e.getMessage());
    }
  }
}
