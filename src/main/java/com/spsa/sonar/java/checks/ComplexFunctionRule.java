package com.spsa.sonar.java.checks;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.BreakStatementTree;
import org.sonar.plugins.java.api.tree.CaseLabelTree;
import org.sonar.plugins.java.api.tree.CatchTree;
import org.sonar.plugins.java.api.tree.ContinueStatementTree;
import org.sonar.plugins.java.api.tree.DoWhileStatementTree;
import org.sonar.plugins.java.api.tree.ForEachStatement;
import org.sonar.plugins.java.api.tree.ForStatementTree;
import org.sonar.plugins.java.api.tree.IfStatementTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.ReturnStatementTree;
import org.sonar.plugins.java.api.tree.ThrowStatementTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.plugins.java.api.tree.TryStatementTree;
import org.sonar.plugins.java.api.tree.VariableTree;
import org.sonar.plugins.java.api.tree.WhileStatementTree;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;

@Rule(key = "ComplexFunctionRule", name = "Its a blind check rule, to avoid complex function by limiting the statement lines in a function", description = "Avoid more than 10 statement lines in a function", tags = {
		"complexity", "method" }, priority = Priority.INFO)
@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.READABILITY)
public class ComplexFunctionRule extends BaseTreeVisitor implements JavaFileScanner {

	private JavaFileScannerContext context;
	private int statements = 0;

	@Override
	public void scanFile(JavaFileScannerContext context) {
		this.context = context;
		scan(context.getTree());
	}

	@Override
	public void visitMethod(MethodTree tree) {
		statements = 0;
		super.visitMethod(tree);
	}

	@Override
	public void visitVariable(VariableTree tree) {
		incrementStatmentAndReport(tree);
		super.visitVariable(tree);
	}

	@Override
	public void visitIfStatement(IfStatementTree tree) {
		incrementStatmentAndReport(tree.thenStatement());
		super.visitIfStatement(tree);
	}

	@Override
	public void visitMethodInvocation(MethodInvocationTree tree) {
		if (tree.parent().is(Kind.EXPRESSION_STATEMENT)) {
			incrementStatmentAndReport(tree);
		}
		super.visitMethodInvocation(tree);
	}

	@Override
	public void visitCaseLabel(CaseLabelTree tree) {
		incrementStatmentAndReport(tree);
		super.visitCaseLabel(tree);
	}

	@Override
	public void visitWhileStatement(WhileStatementTree tree) {
		incrementStatmentAndReport(tree);
		super.visitWhileStatement(tree);
	}

	@Override
	public void visitDoWhileStatement(DoWhileStatementTree tree) {
		incrementStatmentAndReport(tree);
		super.visitDoWhileStatement(tree);
	}

	@Override
	public void visitForStatement(ForStatementTree tree) {
		incrementStatmentAndReport(tree);
		super.visitForStatement(tree);
	}

	@Override
	public void visitForEachStatement(ForEachStatement tree) {
		incrementStatmentAndReport(tree);
		super.visitForEachStatement(tree);
	}

	@Override
	public void visitBreakStatement(BreakStatementTree tree) {
		incrementStatmentAndReport(tree);
		super.visitBreakStatement(tree);
	}

	@Override
	public void visitContinueStatement(ContinueStatementTree tree) {
		incrementStatmentAndReport(tree);
		super.visitContinueStatement(tree);
	}

	@Override
	public void visitReturnStatement(ReturnStatementTree tree) {
		incrementStatmentAndReport(tree);
		super.visitReturnStatement(tree);
	}

	@Override
	public void visitThrowStatement(ThrowStatementTree tree) {
		incrementStatmentAndReport(tree);
		super.visitThrowStatement(tree);
	}

	@Override
	public void visitTryStatement(TryStatementTree tree) {
		incrementStatmentAndReport(tree);
		super.visitTryStatement(tree);
	}

	@Override
	public void visitCatch(CatchTree tree) {
		incrementStatmentAndReport(tree);
		super.visitCatch(tree);
	}

	private void incrementStatmentAndReport(Tree tree) {
		if (++statements > 10) {
			context.reportIssue(this, tree, "Avoid more than 10 statement lines in a function");
		}
	}

}
