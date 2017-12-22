package iwostaq.yppj;

import java.io.IOException;
import iwostaq.yppj.exception.YangPullParserException;

/**
 * An interface for Yang Pull Parser implementations.
 * 
 */
public interface YangPullParser {

  public enum EventType {
    /** At the end of the module. */
    END_MODULE,
    /** At the start of a statement. */
    STATEMENT_START, //
    /** At the end of a statement. */
    STATEMENT_END,
  }

  public enum StatementType {
    ACTION, //
    ANYDATA, // FORMATTER BLOCKER
    ANYXML, //
    ARGUMENT, //
    AUGMENT, //
    BASE, //
    BELONGS_TO, //
    BIT, //
    CASE, //
    CHOICE, //
    CONFIG, //
    CONTACT, //
    CONTAINER, //
    DEFAULT, //
    DESCRIPTION, //
    DEVIATION, //
    ENUM, //
    ERROR_APP_TAG, //
    ERROR_MESSAGE, //
    EXTENSION, //
    FEATURE, //
    FRACTION_DIGITS, //
    GROUPING, //
    IDENTITY, //
    IF_FEATURE, //
    IMPORT, //
    INCLUDE, //
    INPUT, //
    INVERT_MATCH, //
    KEY, //
    LEAF, //
    LEAF_LIST, //
    LENGTH, //
    LIST, //
    MANDATORY, //
    MAX_ELEMENTS, //
    MIN_ELEMENTS, //
    MODIFIER, //
    MODULE, //
    MUST, //
    NAMESPACE, //
    NOTIFICATION, //
    ORDERED_BY, //
    ORGANIZATION, //
    OUTPUT, //
    PATH, //
    PATTERN, //
    POSITION, //
    PREFIX, //
    PRESENCE, //
    RANGE, //
    REFERENCE, //
    REFINE, //
    REQUIRE_INSTANCE, //
    REVISION, //
    REVISION_DATE, //
    RPC, //
    STATUS, //
    SUBMODULE, //
    TYPE, //
    TYPEDEF, //
    UNIQUE, //
    UNITS, //
    UNKNOWN, //
    USES, //
    VALUE, //
    WHEN, //
    YANG_VERSION, //
    YIN_ELEMENT,
  }

  /**
   * Return next parser event.
   *
   * @return the next parsing event
   * @throws IOException
   * @throws YangPullParserException
   */
  public EventType next() throws IOException, YangPullParserException;

  /**
   * Return the depth of the current position in the module.
   * 
   * @return the depth of the current statement
   */
  public int getDepth();

  /**
   * Returns the type of the current event.
   * 
   * @return the type of the current event
   */
  public EventType getEventType();

  /**
   * Return the name of the current statement.
   * 
   * @return the name of the current statement
   */
  public StatementType getStatementType();

  /**
   * Returns the namespace of the current statement.
   * 
   * @return the namespace of the current statement
   */
  public String getNamespace();

  /**
   * Returns the identifier of the current statement.
   * 
   * @return the identifier of the current statement
   */
  public String getIdentifier();

  /**
   * Returns the argument of the current statement.
   * 
   * @return the argument of the current statement
   */
  public String getArgument();
}
