parser grammar CoolParser;

options {
    tokenVocab = CoolLexer;
}

@header{
    package cool.parser;
}

// Pentru generarea gramaticii, m-am inspirat din pagina 16 din manualul limbajului COOL.
// Am luat de acolo toate regulile necesare.


program : (classes+=class_definition)* EOF
     ;

class_definition : class=CLASS name=TYPE (inherits=INHERITS parent_class_name=TYPE)? LBRACE (features+=feature SEMI)* RBRACE SEMI;

feature : func_name=ID LPAREN (declarations+=declaration (COMMA declarations+=declaration)*)? RPAREN COLON type=TYPE LBRACE (lines+=expr)* RBRACE # funcDecl
        | var_name=ID COLON type=TYPE (ASSIGN value=expr)? # varDecl
        ;

declaration : id=ID COLON type=TYPE;
varDeclAndInit : var_name=ID COLON type=TYPE (ASSIGN value=expr)?;

expr :
      func_name=expr (AT type=TYPE)? DOT met_name=ID LPAREN (args+=expr (COMMA args+=expr)*)? RPAREN    # method
     | func_name=ID LPAREN (args+=expr (COMMA args+=expr)*)? RPAREN                                     # funcCall
     | IF cond=expr THEN thenBranch=expr ELSE elseBranch=expr FI                                        # if
     | WHILE cond=expr LOOP line=expr POOL                                                              # while
     | LBRACE (line+=expr SEMI)* RBRACE                                                                 # blockSection
     | LET declarations+=varDeclAndInit (COMMA declarations+=varDeclAndInit)* IN line=expr              # let
     | CASE var_name=expr OF (vars+=ID COLON types+=TYPE RESULT branches+=expr SEMI)+ ESAC              # case
     | NEW type=TYPE                                                                                    # instantiation
     | ISVOID op=expr                                                                                   # isvoid
     | LPAREN value=expr RPAREN                                                                         # paren
     | NEG operand=expr                                                                                 # negExpr
     | INT                                                                                              # int
     | BOOL                                                                                             # bool
     | STRING                                                                                           # string
     | ID                                                                                               # id
     | left_op=expr MULT right_op=expr                                                                  # mult
     | left_op=expr DIV right_op=expr                                                                   # div
     | left_op=expr PLUS right_op=expr                                                                  # plus
     | left_op=expr MINUS right_op=expr                                                                 # minus
     | left_op=expr op=(LE | LT | EQ) right_op=expr                                                     # relOp
     | var_name=ID ASSIGN value=expr                                                                    # varAssign
     | NOT op=expr                                                                                      # notExpr
     ;
