grammar ImprovedGrammar;

// Define a rule for a program
program: statement+ ;

// Define a rule for a statement
statement: printStatement | assignmentStatement ;

// Define a rule for a print statement
printStatement: 'printIn' expression ';' ;

// Define a rule for an assignment statement
assignmentStatement: ID '=' expression ';' ;

// Define a rule for an expression
expression: term ( ( '+' | '-' ) term )* ;

// Define a rule for a term
term: factor ( ( '*' | '/' ) factor )* ;

// Define a rule for a factor
factor: ID | INTEGER_LITERAL | '(' expression ')' ;

// Define tokens for identifiers and literals
ID: [a-zA-Z]+ ;
INTEGER_LITERAL: DIGIT+ ;
fragment DIGIT: [0-9] ;

// Ignore whitespace and newlines
WS: [ \t\r\n]+ -> skip ;
