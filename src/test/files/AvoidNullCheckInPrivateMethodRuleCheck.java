import java.util.Objects;

public class AvoidNullCheckInPrivateMethodRuleCheck {

    public String aMethod(String name) { 
        greetName(name); 
        greetName1(name); 
//        greetName2(name);
//        greetName21(name); 
//        greetName22(name); 
//        greetName3(name); 
        return null;
    }
    
    private String greetName(String name){
        if(name != null ){ // Noncompliant {{Avoid null checking in the private method, this leads to more than one responsibility, If-else branch with null and non-null value}}
            return "Hi " + name;
        }
        return null;
    }
    
    private String greetName1(String name){
        if(name == null){ // Noncompliant {{Avoid null checking in the private method, this leads to more than one responsibility, If-else branch with null and non-null value}}
            return "Hi " + name;
        }
        return null;
    }
    
//    private String greetName2(String name){
//        if(Objects.requireNonNull(name)){ // Noncompliant {{Avoid null checking in the private method, this leads to more than one responsibility, If-else branch with null and non-null value}}
//            return "Hi " + name;
//        }
//        return null;
//    }
    
//    private String greetName21(String niceName){
//        String niceName1 = niceName + "Modified";
//        if(Objects.nonNull(niceName)){ // Noncompliant {{Avoid null checking in the private method, this leads to more than one responsibility, If-else branch with null and non-null value}}
//            return "Hi " + niceName;
//        }
//        return null;
//    }
//    
//    private String greetName22(String name){
//        if(Objects.isNull(name)){ // Noncompliant {{Avoid null checking in the private method, this leads to more than one responsibility, If-else branch with null and non-null value}}
//            return "Hi " + name;
//        }
//        return null;
//    }
    
    // Didnt handle this scenario
//    private String greetName3(String name){
//        String msg = name;
//        if(msg == null){ 
//            return "Hi " + name;
//        }
//        return null;
//    }
}
