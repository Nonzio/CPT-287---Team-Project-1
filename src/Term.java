
public class Term implements Comparable<Term> {

	private int coefficient;
	private char variable = ' '; // char type limits terms to having only one variable letter
	private int exponent = 1;

	
	//Constructor
	public Term(int coef, char vari, int expo) {
		this.coefficient = coef;
		this.variable = vari;
		this.exponent = expo;
	}
	
	//Setter functions
	public void setCoefficient(int coef) {
		this.coefficient = coef;
	}
	
	public void setVariable(char vari) {
		this.variable = vari;
	}
	
	public void setExponent(int expo) {
		this.exponent = expo;
	}
	
	//Getter functions
	public int getCoefficient() {
		return this.coefficient;
	}
	
	public char getVariable() {
		return this.variable;
	}
	
	public int getExponent() {
			return this.exponent;
	}
	
	//Converts term to String form
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(!(this.getVariable()=='x'&&this.getCoefficient()==1)) {//wont print a 1 coefficient for terms with x variables
			if(this.getCoefficient()==-1 && this.getVariable()=='x') {//wont print -1x. instead -x
				sb.append('-');
			}else {
			sb.append(this.coefficient);
			}
		}
		
		if(this.variable != ' ') {
			sb.append(this.variable);
		}
		
		//Exponent superscript mapping - May require UTF-8 Workspace settings
		int expo = this.exponent;
		
		boolean negExpo=false;
		if(expo < 0) {
			negExpo=true;
			expo *= -1;
			sb.append("\u207B");
		}
		String expo_stringform = Integer.toString(expo);

		//For every digit in exponent, append proper superscript
		for(int i = 0; i<expo_stringform.length();i++) {
			switch(expo_stringform.charAt(i)) {
				case '0':
					sb.append("\u2070");
					break;
				case '1':
					if( negExpo == true || expo_stringform.length() > 1) {//Only prints 1 in exponent if the exponent is negative or multiple digits
						sb.append("\u00B9");
					}
					break;
				case '2':
					sb.append("\u00B2");
					break;
				case '3':
					sb.append("\u00B3");
					break;
				case '4':
					sb.append("\u2074");
					break;
				case '5':
					sb.append("\u2075");
					break;
				case '6':
					sb.append("\u2076");
					break;
				case '7':
					sb.append("\u2077");
					break;
				case '8':
					sb.append("\u2078");
					break;
				case '9':
					sb.append("\u2079");
					break;
			}
		}
		
		return sb.toString();
	}

	//compareTo - compares one term to another on the basis of their exponents
	@Override
	public int compareTo(Term t) {
		
		//Directs negative exponents to end of polynomial
		if(t.exponent < 0) {
			if(t.variable == 'x' && this.variable == ' '){
				return 1;
			}else if(t.variable == ' ' && this.variable == 'x') {
				return -1;
			}
		}
		
		if(t.exponent == this.exponent && t.variable == this.variable) {
			return 0;//Terms are of same power
		}else if(t.exponent > this.exponent || (t.variable == 'x' && this.variable == ' ')) {
			return -1;//passed term is of lower power
		}else if(t.exponent < this.exponent || (t.variable == ' ' && this.variable == 'x')) {
			return 1;//passed term is of higher power
		}
		return -2;//Error
	}
}
