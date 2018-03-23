package com.spsa.sonar;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.plugins.java.Java;
import org.sonar.squidbridge.annotations.AnnotationBasedRulesDefinition;

public class CleanCodeRulesDefinition implements RulesDefinition {

    public static final String REPOSITORY_KEY = "uncleancode";

    @Override
    public void define(Context context) {
        final NewRepository repository = context.createRepository(REPOSITORY_KEY, Java.KEY);
        repository.setName("UnCleanCodeRepo");

        AnnotationBasedRulesDefinition.load(repository, "java", RulesList.getChecks());
        AnnotationBasedRulesDefinition.load(repository, "javascript", RulesList.getJSChecks());
        repository.done();
    }

}
