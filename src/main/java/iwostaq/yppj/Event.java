package iwostaq.yppj;

import iwostaq.yppj.YangPullParser.EventType;
import iwostaq.yppj.YangPullParser.StatementType;

/**
 * A class representing a Yang Pull Parser Event.
 * 
 */
public final class Event {

  public static final Event END_DEFINITION = new Event(EventType.END_MODULE, null);

  private EventType eventType;

  private StatementType statementType;

  private String namespace;

  private String identifier;

  private String argument;

  /**
   * Constructor.
   * 
   * @param eventType the type of the event
   * @param statement the statement
   */
  public Event(EventType eventType, StatementType statementType) {
    if (eventType == null) {
      throw new IllegalArgumentException();
    }
    this.eventType = eventType;
    this.statementType = statementType;
    this.namespace = null;
    this.identifier = null;
  }

  /**
   * Returns the type of the event.
   * 
   * @return the type of the event
   */
  public EventType getEventType() {
    return this.eventType;
  }

  /**
   * Sets the type of the event to the object.
   * 
   * @param eventType the type of the event to be set
   */
  public void setEventType(EventType eventType) {
    this.eventType = eventType;
  }

  /**
   * Returns the statement.
   * 
   * @return the statement
   */
  public StatementType getStatementType() {
    return this.statementType;
  }

  /**
   * Sets the type of the statement to the object.
   * 
   * @param statementType the type of the statement
   */
  public void setStatementType(StatementType statementType) {
    this.statementType = statementType;
  }

  /**
   * Returns the namespace.
   * 
   * @return the namespace
   */
  public String getNamespace() {
    return this.namespace;
  }

  /**
   * Sets the namespace to the object.
   * 
   * @param namespace the namespace to be set.
   */
  public void setNamespace(String namespace) {
    this.namespace = namespace;
  }

  /**
   * Returns the identifier.
   * 
   * @return the identifier
   */
  public String getIdentifier() {
    return this.identifier;
  }

  /**
   * Sets the identifier to the object.
   * 
   * @param identifier the identifier to be set
   */
  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  /**
   * Returns the argument.
   * 
   * @return argument the argument
   */
  public String getArgument() {
    return this.argument;
  }

  /**
   * Sets the argument to the object.
   * 
   * @param argument the argument to be set
   */
  public void setArgument(String argument) {
    this.argument = argument;
  }

  /**
   * Returns a string representation of the object.
   * 
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    if (this.namespace == null) {
      return String.format("[%s:%s]%s", this.eventType.name(), this.statementType.name(),
          this.identifier);
    } else {
      return String.format("[%s:%s]%s:%s", this.eventType.name(), this.statementType.name(),
          this.namespace, identifier);
    }
  }
}
