package com.spsa.sonar.java.checks;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;

/**
 * This class suggested to keep the API definition simple and concise. 
 * The ideal case for a method is zero arguments but unavoidably we can have two parameters, but not the same data type.
 * Otherwise, this will cause confusion to the users. 
 * The @Rule annotation allows to specify metadata like rule key,
 * name, description or default severity.
 */
@Rule(key = "AvoidMultipleParameterRule", name = "Avoid more than two parameters in methods",
        description = "Avoid more than two parameters in methods", tags = { "complexity", "method" },
        priority = Priority.INFO)
@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.INSTRUCTION_RELIABILITY)
@SqaleConstantRemediation("15min")
public class AvoidMultipleParameterRule extends BaseTreeVisitor implements JavaFileScanner {

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
        // System.out.println(PrinterVisitor.print(context.getTree()));
    }

    /**
     * Overriding the visitor method to implement the logic of the rule.
     *
     * @param tree
     *            AST of the visited method.
     */
    @Override
    public void visitMethod(MethodTree tree) {

        if (tree.symbol().parameterTypes().size() > 2) {
            context.reportIssue(this, tree, "Passing more than two parameters in a method increases complexity");
        }
        super.visitMethod(tree);
    }

}
