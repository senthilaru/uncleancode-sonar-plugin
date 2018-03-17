
public class AvoidPublicBooleanParameterRuleCheck {
    int aField;

    public String aMethod(boolean isSP) { // Noncompliant {{Passing boolean as a method parameters violates SRP (Single Responsibility Principle)}}
        return "Yes:"+isSP;
    }
    
}
