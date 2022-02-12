
public class Term implements Comparable<Term> {

	int coefficient;
	char variable; // char type limits terms to having only one variable letter
	int exponent;
	
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
	
	//Converts term to char form
	public String toString() {
		return "" + this.coefficient + this.variable + "^" + this.exponent;
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
		if(t.exponent == this.exponent) {
			return 0;//Terms are of same power
		}else if(t.exponent > this.exponent) {
			return -1;//passed term is of lower power
		}else if(t.exponent < this.exponent) {
			return 1;//passed term is of higher power
		}
		return -2;//Error
	}	
}
