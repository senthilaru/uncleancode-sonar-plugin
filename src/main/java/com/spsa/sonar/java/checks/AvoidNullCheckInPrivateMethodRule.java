package com.spsa.sonar.java.checks;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.BinaryExpressionTree;
import org.sonar.plugins.java.api.tree.ExpressionTree;
import org.sonar.plugins.java.api.tree.IfStatementTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.plugins.java.api.tree.VariableTree;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;

/**
 * It enforces SRP principle by identifying the null checking inside the private
 * method. This leads to more than one responsibility, If-else branch with null
 * and non-null value. The @Rule annotation allows to specify metadata like rule
 * key, name, description or default severity.
 */
@Rule(key = "AvoidNullCheckInPrivateMethodRule",
        name = "Avoid null checking in the private method, this leads to more than one responsibility, If-else branch with null and non-null value.",
        description = "Avoid null checking in the private method, this leads to more than one responsibility, If-else branch with null and non-null value. "
                + "Its a defensive way of programming and would lead to numerous null checks in private methods."
                + "Its a good practice to keep the null checks in the public method, make use of null check annotations.",
        tags = { "method" }, priority = Priority.MINOR)
@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.MODULARITY)
@SqaleConstantRemediation("10min")
public class AvoidNullCheckInPrivateMethodRule extends BaseTreeVisitor implements JavaFileScanner {
    /**
     * Private field to store the context: this is the object used to create
     * issues.
     */
    private JavaFileScannerContext context;

    /**
     * The class extends BaseTreeVisitor: the visitor for the Java AST. The
     * logic of the rule is implemented by overriding its methods. It also
     * implements the JavaFileScanner interface to be injected with the
     * JavaFileScannerContext to attach issues to this context.
     */
    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
    }

    /**
     * Overriding the visitor method to implement the logic of the rule.
     *
     * @param tree
     *            AST of the visited method.
     */
    @Override
    public void visitMethod(MethodTree tree) {
        if (tree.symbol().isPrivate()) {
            super.visitMethod(tree);
        }
    }

    @Override
    public void visitIfStatement(IfStatementTree tree) {
        if (tree.is(Kind.IF_STATEMENT)) {
            super.visitIfStatement(tree);
        }
    }

    @Override
    public void visitMethodInvocation(MethodInvocationTree tree) {
        if (!tree.parent().is(Kind.IF_STATEMENT)) {
            return;
        }
        MethodTree method = getMethodTree(tree);
        for (VariableTree variable : method.parameters()) {
            String variableName = variable.simpleName().name();
            if (isInputReferenceAndObjectsNullCheck(tree, variableName)) {
                context.reportIssue(this, tree,
                        "Avoid null checking in the private method, this leads to more than one responsibility, If-else branch with null and non-null value");
            }
        }
        super.visitMethodInvocation(tree);
    }

    private boolean isInputReferenceAndObjectsNullCheck(MethodInvocationTree tree, String variableName) {
        if (tree.symbol() == null || tree.symbol().name() == null) {
            return false;
        }
        String methodName = tree.symbol().name();
        if (tree.arguments().size() == 1 && tree.arguments().get(0).toString().equals(variableName)
                 && (methodName.equals("requireNonNull") || methodName.equals("nonNull") || methodName.equals("isNull") || methodName.equals("isNullOrEmpty"))) {
            return true;
        }
        return false;
    }

    @Override
    public void visitBinaryExpression(BinaryExpressionTree tree) {
        if (!tree.parent().is(Kind.IF_STATEMENT)) {
            return;
        }
        MethodTree method = getMethodTree(tree);
        for (VariableTree variable : method.parameters()) {
            String variableName = variable.simpleName().name();
            if (isLeftHasReferenceAndNullCheckCondition(tree, variableName)
                    || isRightHasReferenceAndNullCheckCondition(tree, variableName)) {
                context.reportIssue(this, tree,
                        "Avoid null checking in the private method, this leads to more than one responsibility, If-else branch with null and non-null value");
            }
        }
        super.visitBinaryExpression(tree);
    }

    private boolean isLeftHasReferenceAndNullCheckCondition(BinaryExpressionTree tree, String variableName) {
        if (variableName.equals(tree.leftOperand().toString()) && isNullCheckCondition(tree.rightOperand())) {
            return true;
        }
        return false;
    }

    private boolean isRightHasReferenceAndNullCheckCondition(BinaryExpressionTree tree, String variableName) {
        if (variableName.equals(tree.rightOperand().toString()) && isNullCheckCondition(tree.leftOperand())) {
            return true;
        }
        return false;
    }

    private boolean isNullCheckCondition(ExpressionTree expressionTree) {
        if (expressionTree.is(Kind.NULL_LITERAL)) {
            return true;
        }
        return false;
    }

    private MethodTree getMethodTree(Tree tree) {
        if (tree.kind() == Kind.METHOD) {
            return (MethodTree) tree;
        } else {
            return getMethodTree(tree.parent());
        }
    }

}
