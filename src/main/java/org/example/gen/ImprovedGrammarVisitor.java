package org.example.gen;// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ImprovedGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ImprovedGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ImprovedGrammarParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(ImprovedGrammarParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link ImprovedGrammarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(ImprovedGrammarParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ImprovedGrammarParser#printStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStatement(ImprovedGrammarParser.PrintStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ImprovedGrammarParser#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStatement(ImprovedGrammarParser.AssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ImprovedGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(ImprovedGrammarParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ImprovedGrammarParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(ImprovedGrammarParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link ImprovedGrammarParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(ImprovedGrammarParser.FactorContext ctx);
}