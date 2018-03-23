public class ComplexFunctionRuleCheck {

	public String myComplexFunction() {
		String str = "SPSA writes a complex function";
		String line = " How many lines of code in that file";
		String prob = "Probably 30 to 50 statement lines";
		String author = "SpSenthil Arumugam";
		String company = "XYZ Incorporation";
		StringBuilder sBuf = new StringBuilder(); // 6

		if (str.length() > 10) {
			sBuf.append(str);
			sBuf.append("ssa");
			if (true) {// Noncompliant@+1 {{Avoid more than 10 statement lines in a function}}
				sBuf.append("ppp");// Noncompliant@+1 {{Avoid more than 10 statement lines in a function}}
				int a = 1;// Noncompliant@+1 {{Avoid more than 10 statement lines in a function}}
				int b = 2;// Noncompliant@+1 {{Avoid more than 10 statement lines in a function}}
				int c = a + b;// Noncompliant@+3 {{Avoid more than 10 statement lines in a function}}
			}
		}
		return sBuf.toString();
	}
}
