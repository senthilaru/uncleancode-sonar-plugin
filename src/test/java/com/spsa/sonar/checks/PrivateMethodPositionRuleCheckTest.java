package com.spsa.sonar.checks;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

import com.spsa.sonar.java.checks.PrivateMethodPositionRule;

public class PrivateMethodPositionRuleCheckTest {

    @Test
    public void detected() {
        // Verifies that the check will raise the adequate issues with the
        // expected message.
        // In the test file, lines which should raise an issue have been
        // commented out
        // by using the following syntax: "// Noncompliant {{Avoid null checking in the private method, this leads to more than one responsibility, If-else branch with null and non-null value}}"
        JavaCheckVerifier.verify("src/test/files/PrivateMethodPositionRuleCheck.java", new PrivateMethodPositionRule());
    }
}
