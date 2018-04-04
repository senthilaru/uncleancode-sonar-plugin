package com.spsa.sonar.java.checks;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;

@Rule(key = "ResponseForClassRule",
name = "No. of methods in a class, its an important metrics to understand the coupling. If the count is high, the class needs to be split up.",
description = "Its a scale to refactor the class, if the count is high, the class is performing more jobs",
tags = { "class", "method" }, priority = Priority.MINOR)
@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.MODULARITY)
@SqaleConstantRemediation("20min")
public class ResponseForClassRule  extends BaseTreeVisitor implements JavaFileScanner {

	private static final int RFC_MAX_COUNT = 20;
	private JavaFileScannerContext context;
	private int noOfMethods = 0;
	
	@Override
	public void scanFile(JavaFileScannerContext context) {
		this.context = context;
        scan(context.getTree());
	}
	
	@Override
    public void visitClass(ClassTree tree) {
		noOfMethods = 0;		
		super.visitClass(tree);
	}
	
	@Override
	public void visitMethod(MethodTree tree) {
		incrementStatmentAndReport(tree);
		super.visitMethod(tree);
	}
	
	private void incrementStatmentAndReport(Tree tree) {
		if (++noOfMethods > RFC_MAX_COUNT) {
			context.reportIssue(this, tree, "Avoid more than 20 functions/methods in a class");
		}
	}
	
}
