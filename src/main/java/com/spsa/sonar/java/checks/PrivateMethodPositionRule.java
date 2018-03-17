package com.spsa.sonar.java.checks;

import java.util.List;
import java.util.stream.Collectors;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.squidbridge.annotations.SqaleSubCharacteristic;

/**
 * It improves readability of a class, push all the private methods to the
 * bottom of the class and keep the public methods at the top. The @Rule
 * annotation allows to specify metadata like rule key, name, description or
 * default severity.
 */
@Rule(key = "PrivateMethodPositionRule",
        name = "It improves readability of a class, push all the private methods to the bottom of the class and keep the public methods at the top.",
        description = "It improves readability of a class, push all the private methods to the bottom of the class and keep the public methods at the top."
                + " Anyone who reads the class would be interested in public methods initially, because the tiny private methods can be mostly understandable by its name.",
        tags = { "class", "method" }, priority = Priority.INFO)
@SqaleSubCharacteristic(RulesDefinition.SubCharacteristics.READABILITY)
@SqaleConstantRemediation("10min")
public class PrivateMethodPositionRule extends BaseTreeVisitor implements JavaFileScanner {

    private JavaFileScannerContext context;

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
    }

    @Override
    public void visitClass(ClassTree tree) {
        boolean markPrivate = false;
        int position = 0;
        List<Tree> methods = tree.members().stream().filter( m -> m.is(Kind.METHOD)).collect(Collectors.toList());
        for (Tree methodTree : methods) {
            if (methodTree.is(Kind.METHOD)) {
                MethodTree method = (MethodTree) methodTree;
                if (method.symbol().isPrivate()) {
                    markPrivate = true;
                }else if(markPrivate && (method.symbol().isPublic() || method.symbol().isProtected() || method.symbol().isPackageVisibility()) && methods.size() != position){
                    context.reportIssue(this, tree,
                            "There is a private method above this public method. Push all the private methods to the bottom of the class and keep the public methods at the top, to improve readability");
                }
            }
            position++;
        }
    }
}
