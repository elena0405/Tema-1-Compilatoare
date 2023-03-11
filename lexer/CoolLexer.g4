lexer grammar CoolLexer;

tokens { ERROR } 

@header{
    package cool.lexer;	
}

@members{    
    private void raiseError(String msg) {
        setText(msg);
        setType(ERROR);
    }
}

// Definesc mai jos o serie de tokeni de tipul:
// <categorie_lexicala, lexem>.

// COMENTARIU PE MAI MULTE LINII
BLOCK_COMMENT: '(*' (BLOCK_COMMENT | .)*? ('*)' { skip(); } | EOF { raiseError("EOF in comment"); });
COMMENT_ERROR_UNMATCHED: '*)' {raiseError("Unmatched *)");};

// COMENTARIU PE O SINGURA LINIE
LINE_COMMENT : '--' .*? ('\n') -> skip;

// OPERATORI RELATIONALI
EQ : '=';
LT : '<';
LE : '<=';

// OPERATORI ARITMETICI
PLUS  : '+';
MINUS : '-';
MULT  : '*';
DIV   : '/';

// OPERATORI UNARI
NEG    : '~' ;
NOT    : 'not' ;

// CAST
AT     : '@';

// ALTE ELEMENTE DE PUNCTUATIE
SEMI   : ';';
COMMA  : ',';
COLON  : ':';
DOT    : '.';

// OPERATORI DE ATRIBUIRE
ASSIGN : '<-';
RESULT : '=>';

// OPERATORI CARE DESEMNEAZA:
// - UN BLOC
// - O FUNCTIE
// - PRIORITATEA OPERATIILOR
LPAREN : '(';
RPAREN : ')';
LBRACE : '{';
RBRACE : '}';

// CUVINTE CHEIE
CLASS    : 'class';
INHERITS : 'inherits';
WHILE    : 'while';
LOOP     : 'loop';
POOL     : 'pool';
CASE     : 'case';
ESAC     : 'esac';
IF       : 'if';
THEN     : 'then';
ELSE     : 'else';
FI       : 'fi';
LET      : 'let';
IN       : 'in';
NEW      : 'new';
OF       : 'of';
ISVOID   : 'isvoid';

//INVALID_CHAR: '#' {raiseError("Invalid character #");};

// ELEMENTE FOLOSITE IN CONSTRUCTIA
// ALTOR ELEMENTE
fragment DIGIT     : [0-9];
fragment UPPERCASE : [A-Z];
fragment LOWERCASE : [a-z];

// Am folosit combinatia '\r' '\n' de caractere, pentru a
// desemna trecerea pe un rand nou, in Windows.

// TUPURI
BOOL       : 'true' | 'false';
INT        : DIGIT+;
ID         : LOWERCASE (LOWERCASE | UPPERCASE | '_' | DIGIT)*;
STRING     :  '"' ('\\"' | '\\' '\r'? '\n' | .)*?
            (
            '"'
            {
                String str = getText();
                str = str.substring(1, str.length() - 1);

                if (str.contains("\\n")) {
                    str = str.replace("\\n", "\n");
                } else if (str.contains("\\t")) {
                    str = str.replace("\\t", "\t");
                } else if (str.contains("\\b")) {
                    str = str.replace("\\b", "\b");
                } else if (str.contains("\\f")) {
                    str = str.replace("\\f", "\f");
                } else if (str.contains("\\\\")) {
                    str = str.replace("\\\\", "\\");
                } else if (str.contains("\\") && !str.contains("\\0")) {
                    str = str.replace("\\", "");
                }

                if (str.length() > 1024) {
                    raiseError("String constant too long");
                } else if (str.contains("\0")) {
                    raiseError("String contains null character");
                } else {
                    setText(str);
                }
            }
            | '\r'? '\n' {raiseError("Unterminated string constant");}
            | EOF {raiseError("EOF in string constant");}
            )
            ;


TYPE : 'Int' | 'Bool' | 'SELF_TYPE' | 'String' | (UPPERCASE (UPPERCASE | LOWERCASE | DIGIT | '_')*);

// CARACTERE LA CARE SE DA SKIP (MAI NEIMPORANTE)
WS :   [ \n\f\r\t]+ -> skip;

// ALTE CARACTERE INVALIDE
INVALID_CHARACTER : .
                    {raiseError("Invalid character: " + getText());};

