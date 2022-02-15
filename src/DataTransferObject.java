
public class DataTransferObject {
	private int coefficient;
	private char variable;
	private int exponent;
	private int endIndex;
	
	public DataTransferObject() {
		//default constructor
	}
	
	//Setter Methods
	public void setCoefficient(int coef) {
		coefficient = coef;
	}
	
	public void setVariable(char vari) {
		variable = vari;		
	}
	
	public void  setExponent(int expo) {
		exponent = expo;
	}
	
	public void setNextIndex(int index) {//the next term will begin parsing at the end of the last term
		endIndex = index;
	}
	
	//Getter Methods
	
	public int getCoefficient() {
		return coefficient;
	}
	public char getVariable() {
		return variable;
	}
	
	public int getExponent() {
		return exponent;
	}
	
	public int getNextIndex() {//the next term will begin parsing at the end of the last term
		return endIndex;
	}
}

