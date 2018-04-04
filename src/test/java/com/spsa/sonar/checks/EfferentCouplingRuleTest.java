package com.spsa.sonar.checks;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

import com.spsa.sonar.java.checks.EfferentCouplingRule;

public class EfferentCouplingRuleTest {
	@Test
    public void detected() {
        // by using the following syntax: "// Noncompliant {{Avoid null checking in the private method, this leads to more than one responsibility, If-else branch with null and non-null value}}"
        JavaCheckVerifier.verify("src/test/files/EfferentCouplingRuleCheck.java", new EfferentCouplingRule());
    }
}
