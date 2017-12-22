package iwostaq.yppj;

import java.util.Arrays;
import java.util.Optional;
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
      "belongs_to", //
      "bit", //
      "case", //
      "choice", //
      "container", //
      "extension", //
      "feature", //
      "grouping", //
      "identity", //
      "if_feature", //
      "import", //
      "include", //
      "leaf", //
      "leaf_list", //
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
      "error_app_tag", //
      "error_message", //
      "fraction_digits", //
      "key", //
      "length", //
      "mandatory", //
      "max_elements", //
      "min_elements", //
      "must", //
      "namespace", //
      "ordered_by", //
      "organization", //
      "path", //
      "pattern", //
      "position", //
      "prefix", //
      "presence", //
      "range", //
      "reference", //
      "refine", //
      "require_instance", //
      "revision", //
      "revision_date", //
      "status", //
      "unique", //
      "units", //
      "value", //
      "when", //
      "yang_version", //
      "yin_element"};

  protected StatementToken[] stmtsWithId;
  protected StatementToken[] stmtsWithArgs;
  protected StatementToken[] stmtsWithoutArg;

  private class StatementToken {
    String name;
    StatementType stmtType;

    StatementToken(String name) {
      assert (name != null);
      this.name = name.replaceAll("_", "-");
      this.stmtType = StatementType.valueOf(name.toUpperCase());
    }
  }

  /**
   * Constructor.
   * 
   */
  public YangSyntax() {
    this.stmtsWithId = Arrays.stream(YangSyntax.STATEMENTS_WITH_ID).map(s -> new StatementToken(s))
        .sorted((a, b) -> a.name.compareTo(b.name)).toArray(StatementToken[]::new);

    this.stmtsWithArgs =
        Arrays.stream(YangSyntax.STATEMENTS_WITH_ARGS).map(s -> new StatementToken(s))
            .sorted((a, b) -> a.name.compareTo(b.name)).toArray(StatementToken[]::new);

    this.stmtsWithoutArg =
        Arrays.stream(YangSyntax.STATEMENTS_WITHOUT_ARG).map(s -> new StatementToken(s))
            .sorted((a, b) -> a.name.compareTo(b.name)).toArray(StatementToken[]::new);
  }

  public Optional<StatementType> searchStatementsExpectingIdFor(Token token) {
    return this.searchGivenStatementArrayFor(token, this.stmtsWithId);
  }

  public Optional<StatementType> searchStatementsExpectingArgumentsFor(Token token) {
    return this.searchGivenStatementArrayFor(token, this.stmtsWithArgs);
  }

  public Optional<StatementType> searchStatementsExpectingNoArgFor(Token token) {
    return this.searchGivenStatementArrayFor(token, this.stmtsWithArgs);
  }

  public boolean isUnknownStatement(Token token) {
    return (token != null && token.getType() == YangLexer.UNQUOTED_STRING);
  }

  public boolean isEndStatement(Token token) {
    return (token.getType() == YangLexer.S_RBR || token.getType() == YangLexer.S_SEMICOLON);
  }

  protected Optional<StatementType> searchGivenStatementArrayFor(Token token,
      StatementToken[] statementTokens) {
    assert (statementTokens != null);

    if (token == null || (token.getType() != YangLexer.QUOTED_STRING
        && token.getType() != YangLexer.UNQUOTED_STRING)) {
      return Optional.empty();
    }
    return Arrays.stream(statementTokens).filter(s -> s.name.equals(token.getText()))
        .map(s -> s.stmtType).findFirst();
  }
}
