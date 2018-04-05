
public class AvoidMultipleParametersRuleCheck {
    int aField;

    public String aMethod(int isSP, float two, double thrid) { // Noncompliant {{Passing more than two parameters in a method increases complexity}}
        return "Yes:"+isSP;
    }
    
    private String bMethod(int isSP, float two, double thrid) {
        return "Yes:"+isSP;
    }
}
