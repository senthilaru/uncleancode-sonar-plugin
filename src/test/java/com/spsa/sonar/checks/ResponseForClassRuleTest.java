package com.spsa.sonar.checks;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

import com.spsa.sonar.java.checks.ResponseForClassRule;

public class ResponseForClassRuleTest {
	@Test
    public void detected() {
        // by using the following syntax: "// Noncompliant {{Avoid more than 20 functions/methods in a class}}"
        JavaCheckVerifier.verify("src/test/files/ResponseForClassRuleCheck.java", new ResponseForClassRule());
    }
}
