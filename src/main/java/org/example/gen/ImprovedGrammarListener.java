package org.example.gen;// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ImprovedGrammarParser}.
 */
public interface ImprovedGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ImprovedGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(ImprovedGrammarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link ImprovedGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(ImprovedGrammarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link ImprovedGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(ImprovedGrammarParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ImprovedGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(ImprovedGrammarParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ImprovedGrammarParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void enterPrintStatement(ImprovedGrammarParser.PrintStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ImprovedGrammarParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void exitPrintStatement(ImprovedGrammarParser.PrintStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ImprovedGrammarParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentStatement(ImprovedGrammarParser.AssignmentStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ImprovedGrammarParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentStatement(ImprovedGrammarParser.AssignmentStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ImprovedGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(ImprovedGrammarParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ImprovedGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(ImprovedGrammarParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ImprovedGrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(ImprovedGrammarParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link ImprovedGrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(ImprovedGrammarParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link ImprovedGrammarParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(ImprovedGrammarParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ImprovedGrammarParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(ImprovedGrammarParser.FactorContext ctx);
}