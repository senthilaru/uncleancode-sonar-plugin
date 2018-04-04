import com.spsa.sonar.java.checks.ResponseForClassRule;

public class EfferentCouplingRuleCheck {

	private ComplexFunctionRuleCheck check;
	private PrivateMethodPositionRuleCheck positionCheck;
	private ResponseForClassRuleCheck rfcCheck;
	private Boolean enableFeature;
	
	public void checkForBoolean() {
		AvoidPublicBooleanParameterRuleCheck boolCheck = new AvoidPublicBooleanParameterRuleCheck();
	}
	
	public void checkForBoolean2(ResponseForClassRule rules) {
		if(enableFeature) {
			String boolCheck = new String();
			AvoidMultipleParametersRuleCheck multipleCheck = new AvoidMultipleParametersRuleCheck();
			
		}
		AvoidPublicBooleanParameterRuleCheck boolCheck = new AvoidPublicBooleanParameterRuleCheck();// Noncompliant@+1 {{Avoid more than 6 different class used in a class}}
		AvoidNullCheckInPrivateMethodRuleCheck cc = new AvoidNullCheckInPrivateMethodRuleCheck();// Noncompliant@+1 {{Avoid more than 6 different class used in a class}}
		ResponseForClassRule rule = new ResponseForClassRule();
	}
}
