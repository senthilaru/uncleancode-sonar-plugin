
public class AvoidMultipleParametersCheck {
    int aField;

    public String aMethod(int isSP, float two, double thrid) { // Noncompliant {{Passing more than two parameters in a method increases complexity}}
        return "Yes:"+isSP;
    }
}
