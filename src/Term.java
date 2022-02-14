
public class Term implements Comparable<Term> {

	private int coefficient;
	private char variable = ' '; // char type limits terms to having only one variable letter
	private int exponent = 1;
	private boolean negexpo_flag = false;//Tracks whether exponent is negative - For ease of use in unicode lookup
	
	//Create a constant number term.
	public Term(int coef) {
		this.coefficient = coef;
		this.variable = ' ';
		this.exponent = 1;
	}
	
	//Create a variable with coefficient only. ex. 5x, 2a, 3b
	public Term(int coef, char vari) {
		this.coefficient = coef;
		this.variable = vari;
		this.exponent = 1;
	}
	
	//Create a complete term
	public Term(int coef, char vari, int expo) {
		this.coefficient = coef;
		this.variable = vari;
		this.setExponent(expo);
	}
	
	//Setter functions
	public void setCoefficient(int coef) {
		this.coefficient = coef;
	}
	
	public void setVariable(char vari) {
		this.variable = vari;
	}
	
	public void setExponent(int expo) {
		if(expo < 0) {
			this.exponent = -1 * expo;
			negexpo_flag = true;
		}else {
			this.exponent = expo;
			negexpo_flag = false;
		}
	}
	
	//Getter functions
	public int getCoefficient() {
		return this.coefficient;
	}
	
	public char getVariable() {
		return this.variable;
	}
	
	public int getExponent() {
		//Turn negative if appropriate
		if(negexpo_flag == true) {
			return this.exponent * -1;
		}else {
			return this.exponent;
		}
	}
	
	//Converts term to String form
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(this.coefficient != 0) {
			sb.append(this.coefficient);
		}
		
		if(this.variable != ' ') {
			sb.append(this.variable);
		}
		
		//Exponent superscript mapping - May require UTF-8 Workspace settings
		if(this.negexpo_flag == true) {
			sb.append("\u207B");
		}
		
		switch(this.exponent) {
		case 2:
			sb.append("\u00B2");
			break;
		case 3:
			sb.append("\u00B3");
			break;
		case 4:
			sb.append("\u2074");
			break;
		case 5:
			sb.append("\u2075");
			break;
		case 6:
			sb.append("\u2076");
			break;
		case 7:
			sb.append("\u2077");
			break;
		case 8:
			sb.append("\u2078");
			break;
		case 9:
			sb.append("\u2079");
			break;
		}
		
		return sb.toString();
	}
	
	//Returns true when term is only a constant number
	public boolean isConstant() {
		if(this.variable == ' ' && this.exponent == 1) {
			return true;
		}else {
			return false;
		}
	}

	//compareTo - compares one term to another on the basis of their exponents
	@Override
	public int compareTo(Term t) {
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
