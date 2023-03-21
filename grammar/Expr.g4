grammar Expr;

program: statement+ ;

statement: assignment | expression ;

assignment:  ID '=' expression ;

expression : term ( ( '+' | '-' ) term )* ;

term: factor ( ( '*' | '/' ) factor )* ;

factor: ID | INT | '(' expression ')' ;

ID : [a-zA-Z]+ ;
INT : [0-9]+ ;
WS : [ \t\r\n]+ -> skip ;

