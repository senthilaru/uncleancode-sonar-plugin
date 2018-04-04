package com.spsa.sonar.java.checks;

import java.util.HashMap;
import java.util.Map;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.ModifiersTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;

@Rule(key = "EfferentCouplingRule", name = "No. of instance variable used in a class, its a measure of complexity, should be less than 6", description = "Its a scale to refactor the class, if the count is high, the class is performing more jobs and becomes critical", tags = {
		"class", "method" }, priority = Priority.MINOR)
@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.MODULARITY)
@SqaleConstantRemediation("30min")

public class EfferentCouplingRule extends BaseTreeVisitor implements JavaFileScanner {

	private static final int MAX_COUNT = 6;
	private JavaFileScannerContext context;
	private Map<String,Integer> classNames;

	@Override
	public void scanFile(JavaFileScannerContext context) {
		this.context = context;
		classNames = new HashMap<>();
		scan(context.getTree());
	}

	@Override
	public void visitModifier(ModifiersTree tree) {
		super.visitModifier(tree);
	}

	@Override
	public void visitIdentifier(IdentifierTree tree) {
		if (tree.symbolType().isUnknown() && tree.parent().is(Kind.VARIABLE)  ) {
			classNames.compute(tree.name(), (k,v) -> v == null? 1: v + 1);
			incrementStatmentAndReport(tree);
		}
		super.visitIdentifier(tree);
	}

	private void incrementStatmentAndReport(Tree tree) {
		if (classNames.size() > MAX_COUNT) {
			context.reportIssue(this, tree, "Avoid more than 6 different class used in a class");
		}
	}

}
