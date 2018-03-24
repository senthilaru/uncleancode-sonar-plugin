package com.spsa.sonar;

import java.util.List;

import org.sonar.plugins.java.api.JavaCheck;

import com.google.common.collect.ImmutableList;
import com.spsa.sonar.java.checks.AvoidPublicBooleanParameterRule;
import com.spsa.sonar.java.checks.AvoidMultipleParameterRule;
import com.spsa.sonar.java.checks.AvoidNullCheckInPrivateMethodRule;
import com.spsa.sonar.java.checks.PrivateMethodPositionRule;

public class RulesList {
    private RulesList() {
    }

    @SuppressWarnings("rawtypes")
    public static List<Class> getChecks() {
        return ImmutableList.<Class> builder().addAll(getJavaChecks()).build();
    }

    public static List<Class<? extends JavaCheck>> getJavaChecks() {
        return ImmutableList.<Class<? extends JavaCheck>> builder().add(AvoidPublicBooleanParameterRule.class)
                .add(AvoidMultipleParameterRule.class).add(AvoidNullCheckInPrivateMethodRule.class).add(PrivateMethodPositionRule.class)
                .build();
    }

    @SuppressWarnings("rawtypes")
    public static List<Class> getJSChecks() {
        return ImmutableList.<Class> builder().addAll(getJavaChecks()).build();
    }

}
