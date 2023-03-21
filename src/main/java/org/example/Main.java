package org.example;


import com.jogamp.common.net.Uri;
import org.antlr.v4.codegen.target.Python3Target;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.example.antlr4.*;
import org.example.gen.ImprovedGrammarBaseListener;
import org.example.gen.ImprovedGrammarLexer;
import org.example.gen.ImprovedGrammarParser;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        try {
            String content = Files.readString(Paths.get("input.txt"), StandardCharsets.UTF_8);


            String line = content;
            System.out.println(line);

            // Process the string
            System.out.println("String read from file: " + line);

            CharStream input = CharStreams.fromString(line);

            // Create a lexer that reads from the CharStream
            ImprovedGrammarLexer lexer = new ImprovedGrammarLexer(input);

            // Create a buffer of tokens pulled from the lexer
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // Create a parser that reads from the token buffer
            ImprovedGrammarParser parser = new ImprovedGrammarParser(tokens);

            // Parse the program rule
            ParseTree tree = parser.program();

            // Walk the parse tree with a custom listener that generates Python code
            ParseTreeWalker walker = new ParseTreeWalker();
            ImprovedGrammarPythonListener listener = new ImprovedGrammarPythonListener();

            walker.walk(listener, tree);
            String code = listener.getPythonCode();
            System.out.println(code);

            FileWriter writer = new FileWriter("output.py");
            writer.write(code);
            writer.close();
            System.out.println("Successfully wrote Python code to output.py");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());

        }
    }}
class ImprovedGrammarPythonListener extends ImprovedGrammarBaseListener {
    private Map<String, Integer> variables = new HashMap<>();
    private int variableCounter = 0;
    private StringBuilder pythonCode = new StringBuilder();

    @Override
    public void exitAssignmentStatement(ImprovedGrammarParser.AssignmentStatementContext ctx) {
        String id = ctx.ID().getText();
        int value = evaluateExpression(ctx.expression());
        variables.put(id, value);
        pythonCode.append(id).append(" = ").append(value).append("\n");
    }

    @Override
    public void enterPrintStatement(ImprovedGrammarParser.PrintStatementContext ctx) {
        String expression = ctx.expression().getText();
        pythonCode.append("print(").append(expression).append(")\n");
    }

    private int evaluateExpression(ImprovedGrammarParser.ExpressionContext ctx) {
        int result = evaluateTerm(ctx.term(0));
        for (int i = 1; i < ctx.term().size(); i++) {
            ImprovedGrammarParser.TermContext term = ctx.term(i);
            int value = evaluateTerm(term);
            String operator = ctx.getChild((i * 2) - 1).getText();
            switch (operator) {
                case "+":
                    result += value;
                    break;
                case "-":
                    result -= value;
                    break;
            }
        }
        return result;
    }

    private int evaluateTerm(ImprovedGrammarParser.TermContext ctx) {
        int result = evaluateFactor(ctx.factor(0));
        for (int i = 1; i < ctx.factor().size(); i++) {
            ImprovedGrammarParser.FactorContext factor = ctx.factor(i);
            int value = evaluateFactor(factor);
            String operator = ctx.getChild((i * 2) - 1).getText();
            switch (operator) {
                case "*":
                    result *= value;
                    break;
                case "/":
                    result /= value;
                    break;
            }
        }
        return result;
    }

    private int evaluateFactor(ImprovedGrammarParser.FactorContext ctx) {
        if (ctx.ID() != null) {
            String id = ctx.ID().getText();
            if (variables.containsKey(id)) {
                return variables.get(id);
            } else {
                variableCounter++;
                variables.put(id, variableCounter);
                pythonCode.append(id).append(" = ").append(variableCounter).append("\n");
                return variableCounter;
            }
        } else if (ctx.INTEGER_LITERAL() != null) {
            return Integer.parseInt(ctx.INTEGER_LITERAL().getText());
        } else {
            int value = evaluateExpression(ctx.expression());
            variableCounter++;
            pythonCode.append("temp").append(variableCounter).append(" = ").append(value).append("\n");
            return variableCounter;
        }
    }

    public String getPythonCode() {
        return pythonCode.toString();
    }
}
class PythonCodeGenerator extends ExprBaseVisitor<String> {
    private StringBuilder pythonCode = new StringBuilder();
    private Map<String, Integer> variables = new HashMap<>();
    @Override
    public String visitAssignment(ExprParser.AssignmentContext ctx) {
        String varName = ctx.ID().getText();
        String exprCode = visit(ctx.expression());

       // System.out.println(varName+" "+exprCode);

        return varName + " = " + exprCode + "\n";
    }

    @Override
    public String visitExpression(ExprParser.ExpressionContext ctx) {
        String leftCode = visit(ctx.term(0));
        for (int i = 1; i < ctx.term().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();
            String rightCode = visit(ctx.term(i));

            leftCode = "print(" + leftCode + " " + op + " " + rightCode + ")";
        }
        return leftCode;
    }

    @Override
    public String visitTerm(ExprParser.TermContext ctx) {
        String leftCode = visit(ctx.factor(0));
        for (int i = 1; i < ctx.factor().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();
            String rightCode = visit(ctx.factor(i));
            leftCode = "(" + leftCode + " " + op + " " + rightCode + ")";
        }
        return leftCode;
    }

    @Override
    public String visitFactor(ExprParser.FactorContext ctx) {
        if(ctx.ID()!=null){
            String id = ctx.ID().getText();
            if(variables.containsKey(id)){

            }
        }
        if (ctx.ID() != null) {
            return ctx.ID().getText();
        } else if (ctx.INT() != null) {
            return ctx.INT().getText();
        } else {
            return visit(ctx.expression());
        }
    }
}