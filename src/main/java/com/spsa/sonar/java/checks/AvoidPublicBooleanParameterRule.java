package com.spsa.sonar.java.checks;

import java.util.List;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.semantic.Type;
import org.sonar.plugins.java.api.semantic.Type.Primitives;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;

/**
 * This class enforces SRP principle by identifying the method parameter type.
 * If a method has a boolean parameter, which means the method is performing
 * more than one responsibility. The @Rule annotation allows to specify metadata
 * like rule key, name, description or default severity.
 */
@Rule(key = "AvoidPublicBooleanParameterRule", name = "Avoid using boolean parameter in methods",
        description = "Avoid using boolean parameter in methods", tags = { "method" }, priority = Priority.MINOR)
@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.MODULARITY)
@SqaleConstantRemediation("10min")
public class AvoidPublicBooleanParameterRule extends BaseTreeVisitor implements JavaFileScanner {
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

        if (tree.symbol().parameterTypes().size() > 0) {
            List<Type> params = tree.symbol().parameterTypes();
            for (Type param : params) {
                if (param.isPrimitive(Primitives.BOOLEAN)) {
                    context.reportIssue(this, tree,
                            "Passing boolean as a method parameters violates SRP (Single Responsibility Principle)");
                }
            }
        }
        super.visitMethod(tree);
    }

}
