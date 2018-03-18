package com.spsa.sonar;

import java.util.Arrays;

import org.sonar.plugins.java.api.CheckRegistrar;
import org.sonar.plugins.java.api.JavaCheck;

import com.spsa.sonar.java.checks.AvoidPublicBooleanParameterRule;
import com.spsa.sonar.java.checks.ComplexFunctionRule;
import com.spsa.sonar.java.checks.AvoidMultipleParameterRule;
import com.spsa.sonar.java.checks.AvoidNullCheckInPrivateMethodRule;
import com.spsa.sonar.java.checks.PrivateMethodPositionRule;

public class CleanCodeFileCheckRegistrar implements CheckRegistrar {

	@Override
	public void register(RegistrarContext registrarContext) {
		registrarContext.registerClassesForRepository(CleanCodeRulesDefinition.REPOSITORY_KEY,
				Arrays.asList(checkClasses()), Arrays.asList(testCheckClasses()));
	}

	/**
	 * Lists all the checks provided by the plugin
	 */
	@SuppressWarnings("unchecked")
	public static Class<? extends JavaCheck>[] checkClasses() {
		return new Class[] { AvoidPublicBooleanParameterRule.class, AvoidMultipleParameterRule.class,
				AvoidNullCheckInPrivateMethodRule.class, PrivateMethodPositionRule.class, ComplexFunctionRule.class };
	}

	/**
	 * Lists all the test checks provided by the plugin
	 */
	@SuppressWarnings("unchecked")
	public static Class<? extends JavaCheck>[] testCheckClasses() {
		return new Class[] {};
	}

}
