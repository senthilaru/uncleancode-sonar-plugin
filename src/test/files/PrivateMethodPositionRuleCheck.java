import java.util.Objects;

public class PrivateMethodPositionRuleCheck {// Noncompliant {{There is a private method above this public method. Push all the private methods to the bottom of the class and keep the public methods at the top, to improve readability}}

    private String resolveName(String name){
        return "Hi " + name;
    }
    
    public String greetName(String name){
        return resolveName(name);
    }
}
