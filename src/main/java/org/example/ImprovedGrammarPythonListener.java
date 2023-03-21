package org.example;

import org.example.gen.ImprovedGrammarBaseListener;
import org.example.gen.ImprovedGrammarParser;

import java.util.HashMap;
import java.util.Map;

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