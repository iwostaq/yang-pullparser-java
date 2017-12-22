lexer grammar YangLexer;

@header {
  package iwostaq.yppj.g;
}

tokens {
  QUOTED_STRING
}
// Comments

COMMENT_SINGLE
:
  '\n'*
  (
    '//' ~[\r\n]*
  ) '\r'? '\n' -> skip
;

COMMENT_BLOCK
:
  '/*'
  (
    .
  )*? '*/' -> skip
;

// Tokens with a single character

S_SEMICOLON
:
  ';'
;

S_LBR
:
  '{'
;

S_RBR
:
  '}'
;

S_PLUS
:
  '+'
;

S_DQUOT
:
  '"' -> skip , pushMode ( DQUOTED_STRING )
;

S_SQUOT
:
  '\'' -> skip , pushMode ( SQUOTED_STRING )
;

OPTSEP
:
  (
    ' '
    | '\t'
    | '\r'
    | '\n'
  )+ -> skip
;

UNQUOTED_STRING
:
  ~( ' ' | '\t' | ';' | '{' | '}' | '"' | '\'' | '\n' | '\r' )+
  // FIXME: RFC 7950 does not permit a '//' (comment header) sequence in a string.

;

mode SQUOTED_STRING;

SQSTR
:
  ~( '\'' )+ -> type (QUOTED_STRING)
;

SQUOT
:
  '\'' -> skip, popMode
;

mode DQUOTED_STRING;

DQSTR
:
  (
    YANG_CHAR
    | ESC_NEWLINE
    | ESC_TAB
    | ESC_DQUOT
  )+ -> type ( QUOTED_STRING )
;

YANG_CHAR
:
  '\u0009'
  | '\u000A'
  | '\u000D'
  | '\u0020' .. '\u0021' // excludes a double quotation (=0x22)

  | '\u0023' .. '\u005B' // excludes a back-slash (=0x5c)

  | '\u005D' .. '\uD7FF'
  | '\uE000' .. '\uFDCF'
  | '\uFDF0' .. '\uFFFD'
;

YANG_CHAR10
:
  '\uD800' .. '\uDFFF'
  | '\uFDD0' .. '\uFDEF'
  | '\uFFFE' .. '\uFFFF'
;

ESC_NEWLINE
:
  '\\n'
  {
    setText("\n");
  }

;

ESC_TAB
:
  '\\t'
  {
    setText("\t");
  }

;

ESC_DQUOT
:
  '\\"'
  {
    setText("\\\"");
  }

;

DQUOT
:
  '"' -> skip , popMode
;
