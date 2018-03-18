# Unclean Code Sonar Plugin #
The unclean code sonar plugin aims to capture complex code, metrics of design and architectural level. 
Provides a guided way for the developers to improve code quality.
Inspired by the book clean code by Robert Martin (Uncle Bob).

### Seven Axes of Quality ###
It aims to cover the last two axes.

  ~~1. Potential bugs 
 2. Coding rules 
 3. Tests
 4. Duplications 
 5. Comments~~ 
 6. **Architecture and design**
 7. **Complexity**
 

### Build Steps ###

`mvn clean install` 


### How to use this in local sonar server ###

Copy the generated `sonar-cleancode-plugin-0.0.1-SNAPSHOT.jar` from `/target` folder into the `SONAR_INSTALLED_PATH\extensions\plugins`
and restart the sonar server.


### How to use this in sonar quality profile ###

Login to your sonar server: http://localhost:9000. 
Search for rule repository with the `uncleancode`, it will list down all the rules bundled in this plugin.
Then, you can add rules under the appropriate quality profile.


### Few code smell and rules ###

> **AvoidPublicBooleanParameterRule** - It enforces SRP. If a public method has a boolean parameter, which means the method is performing more than one responsibility.

>**AvoidMultipleParameterRule** - To keep the API definition simple and concise, avoid using more than two parameters.

>**AvoidNullCheckInPrivateMethodRule** - It enforces SRP by identifying the null checking inside the private method. This leads to more than one responsibility, similar to boolean input parameter, if-else branch with null and non-null value. It avoids defensive way of programming with lot of null-checks inside any private methods. The validity of the input parameters should have been done in the public methods. 

> **PrivateMethodPositionRule** - It improves readability of a class, push all the private methods to the bottom of the class and keep the public methods at the top.
Anyone who reads the class would be interested in public methods initially, also the tiny private methods can be mostly understandable by its name.


there are more to add..,
