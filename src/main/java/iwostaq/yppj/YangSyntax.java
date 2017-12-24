package iwostaq.yppj;

import java.util.Arrays;
import org.antlr.v4.runtime.Token;
import iwostaq.yppj.YangPullParser.StatementType;
import iwostaq.yppj.g.YangLexer;

/**
 * Yang syntax class.
 * 
 *
 */
public class YangSyntax {

  private static final String[] STATEMENTS_WITHOUT_ARG = new String[] {"input", "output"};

  private static final String[] STATEMENTS_WITH_ID = new String[] { //
      "anyxml", //
      "base", // FORMATTER BLOCKER
      "belongs-to", //
      "bit", //
      "case", //
      "choice", //
      "container", //
      "extension", //
      "feature", //
      "grouping", //
      "identity", //
      "if-feature", //
      "import", //
      "include", //
      "leaf", //
      "leaf-list", //
      "list", //
      "module", //
      "rpc", //
      "submodule", //
      "type", //
      "typedef", //
      "uses"};

  private static final String[] STATEMENTS_WITH_ARGS = new String[] { //
      "argument", //
      "augment", // FORMATTER BLOCKER
      "config", //
      "contact", //
      "default", //
      "description", //
      "enum", //
      "error-app-tag", //
      "error-message", //
      "fraction-digits", //
      "key", //
      "length", //
      "mandatory", //
      "max-elements", //
      "min-elements", //
      "must", //
      "namespace", //
      "ordered-by", //
      "organization", //
      "path", //
      "pattern", //
      "position", //
      "prefix", //
      "presence", //
      "range", //
      "reference", //
      "refine", //
      "require-instance", //
      "revision", //
      "revision-date", //
      "status", //
      "unique", //
      "units", //
      "value", //
      "when", //
      "yang-version", //
      "yin-element"};

  protected StatementType[] stTypeWithId;
  protected StatementType[] stTypeWithArgs;
  protected StatementType[] stTypeWithoutArg;

  /*
   * private class StatementToken implements Comparable<StatementToken> { String name; StatementType
   * stmtType;
   * 
   * StatementToken(String name) { assert (name != null); this.name = name.replaceAll("_", "-");
   * this.stmtType = StatementType.valueOf(name.toUpperCase()); }
   * 
   * @Override public int compareTo(StatementToken stmtToken) { return
   * this.name.compareTo(stmtToken.name); } }
   */

  /**
   * Constructor.
   * 
   */
  public YangSyntax() {
    this.stTypeWithId = Arrays.stream(YangSyntax.STATEMENTS_WITH_ID)
        .map(s -> StatementType.valueOf(s.toUpperCase().replaceAll("-", "_")))
        .toArray(StatementType[]::new);

    this.stTypeWithArgs = Arrays.stream(YangSyntax.STATEMENTS_WITH_ARGS)
        .map(s -> StatementType.valueOf(s.toUpperCase().replaceAll("-", "_")))
        .toArray(StatementType[]::new);

    this.stTypeWithoutArg = Arrays.stream(YangSyntax.STATEMENTS_WITHOUT_ARG)
        .map(s -> StatementType.valueOf(s.toUpperCase().replaceAll("-", "_")))
        .toArray(StatementType[]::new);
  }

  public StatementType searchStatementsExpectingIdFor(Token token) {
    return this.searchGivenStatementArrayFor(token, YangSyntax.STATEMENTS_WITH_ID,
        this.stTypeWithId);
  }

  public StatementType searchStatementsExpectingArgumentsFor(Token token) {
    return this.searchGivenStatementArrayFor(token, YangSyntax.STATEMENTS_WITH_ARGS,
        this.stTypeWithArgs);
  }

  public StatementType searchStatementsExpectingNoArgFor(Token token) {
    return this.searchGivenStatementArrayFor(token, YangSyntax.STATEMENTS_WITHOUT_ARG,
        this.stTypeWithoutArg);
  }

  public boolean isUnknownStatement(Token token) {
    return (token != null && token.getType() == YangLexer.UNQUOTED_STRING);
  }

  public boolean isEndStatement(Token token) {
    return (token.getType() == YangLexer.S_RBR || token.getType() == YangLexer.S_SEMICOLON);
  }

  protected StatementType searchGivenStatementArrayFor(Token token, String[] statementNames,
      StatementType[] statementTypes) {
    assert (statementNames != null);
    assert (statementTypes != null);

    if (token == null || (token.getType() != YangLexer.QUOTED_STRING
        && token.getType() != YangLexer.UNQUOTED_STRING)) {
      return null;
    }

    int index = -1;
    String tokenString = token.getText();
    if ((index = Arrays.binarySearch(statementNames, tokenString)) < 0) {
      return null;
    } else {
      return statementTypes[index];
    }
  }
}
