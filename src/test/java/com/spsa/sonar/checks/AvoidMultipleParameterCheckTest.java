package com.spsa.sonar.checks;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

import com.spsa.sonar.java.checks.AvoidMultipleParameterRule;

public class AvoidMultipleParameterCheckTest {

    @Test
    public void detected() {
        // Verifies that the check will raise the adequate issues with the
        // expected message.
        // In the test file, lines which should raise an issue have been
        // commented out
        // by using the following syntax: "// Noncompliant {{EXPECTED_MESSAGE}}"
        JavaCheckVerifier.verify("src/test/files/AvoidMultipleParametersCheck.java", new AvoidMultipleParameterRule());
    }
}
