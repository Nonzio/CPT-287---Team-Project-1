
import java.util.Scanner;


import java.util.ArrayList;
import java.util.Iterator;

public class Polynomial_Calculator {

	static ArrayList<Polynomial> listOfPoly = new ArrayList();
	static Polynomial<Term> firstPoly = new Polynomial();//These polynomials should be in this order in listOfPoly
	static Polynomial<Term> secondPoly = new Polynomial();
	static Polynomial<Term> addedPoly = new Polynomial();
	
	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		listOfPoly.add(firstPoly);//adds polynomials in correct order
		listOfPoly.add(secondPoly);
		listOfPoly.add(addedPoly);
		
		//enters the first polynomial
		System.out.println("Enter first polynomial with no spaces. Use '^' to represent powers. (example: 3x^3-x^2+1): ");//asks user for first polynomial to add
		String userInput = input.nextLine();
		
		int returnPack[] = new int[4];
		returnPack = extractTerm(userInput, 0);//Contents 0: coefficient, 1: variable (0 = ' ', 1 = 'x'), 2:exponent, 3: index To Start At next
		
		while (returnPack[3] <= userInput.length()) {//Iterates through the users polynomial extracting the Terms and adding them to firstPoly. 
			                                         //also combines terms with the same coefficient and wont add terms with 0 a coefficient.
			int Coef = returnPack[0];
			int varNum = returnPack[1];
			int Expo = returnPack[2];//Makes things more readable
			char var = ' ';
			if (varNum == 1) {var = 'x';}//converts var number to letter for Term constructor
			
			if (Coef != 0) {//only add if coefficient is not 0
				if (firstPoly.size()==0) {//if there are no other terms add this one
					Term newTerm = new Term(Coef, var, Expo);
					firstPoly.add(newTerm);
				}else {//if there are other terms check to see if you can add with a term with the same exponent
					for (int d=0; d < firstPoly.size(); d++) {
						if (firstPoly.get(d).getExponent() == Expo && firstPoly.get(d).getVariable()== var) {//A Term matches the variable and exponent of the term to be added
								Coef = Coef + firstPoly.get(d).getCoefficient();//adds the term that matches coefficient to the new terms coefficient
								firstPoly.dropTerm(firstPoly.get(d));//deletes the term that matches
							}
						}
						if (Coef != 0) {//Confirms that the new Coef is still not 0 and adds the term
							Term newTerm = new Term(Coef, var, Expo);
							firstPoly.add(newTerm);
						}
				}
			}
			returnPack = extractTerm(userInput, returnPack[3]);// Updates returnPack with the data for the next Term
		}
	
	//prints the terms in all the polynomials
		Iterator<Polynomial> listIterator = listOfPoly.listIterator();//creates an iterator for listOfPoly
		while (listIterator.hasNext()) {
			Polynomial nextPoly = listIterator.next();
			Iterator<Term> termIt = nextPoly.iterator();//creates an iterator for each polynomial
			
			while (termIt.hasNext()) {//prints each term
				System.out.println(termIt.next().toString());
				//nextPoly.addTerm(termIt.next());
			}
		}
		
		//System.out.println(listOfPoly.listIterator().next());
		
	}//end main
	
	/**
	 * Extracts the variable, exponent, and coefficient from the users string and
	 * adds them to an array with the index to start extracting the next term at.
	 * @param poly: Original string that user entered
	 * @param start: the index to start parsing the string
	 * @return: pack[]: an array of ints containing elements in this order: coefficient, variable, exponent, index to start at next
	 */
	public static int[] extractTerm(String polLen, int start) {

		
		int[] pack = new int[4];
		int variable = 0;//if it is 0 there is no x term. if it is 1 there is
		StringBuilder coef = new StringBuilder();
		StringBuilder expo = new StringBuilder();
		int i = start;
		String poly = polLen;
		poly = poly + " ";//the extra space is added to avoid out of bounds index error.
	
		if (start >= polLen.length()) {
			pack[3]=polLen.length()+1;
			return pack;
		}
	
		if (poly.charAt(i) == '−' || poly.charAt(i) == '-') {//first character is a - sign
			coef.append('-');
			i++;
	
		} else if (poly.charAt(i)== '+') {//first character is a + sign
			i++;
	
		}
 
		if (Character.isDigit(poly.charAt(i))) {//coefficient is greater than 1
			while (Character.isDigit(poly.charAt(i))) {
				coef.append(poly.charAt(i));
				i++;
			}
			if (poly.charAt(i) == 'x') {//an x follows the coefficient number
				variable = 1;
				i++;
			}
 
		} else if (poly.charAt(i) == 'x') {//first term after sign is x
			variable = 1;
			coef.append('1');
			i++;
		}
 
		if (poly.charAt(i)== '^'){//the exponent is greater than 1
			++i;
	
			if (poly.charAt(i) == '−' || poly.charAt(i) == '-') {//a - sign follows the ^ sign
				expo.append('-');
				i++;
			}
	
			while (Character.isDigit(poly.charAt(i))) {//records exponent number
				expo.append(poly.charAt(i));
				i++; 
			} 	 
		} else {//there is no ^ sign
			expo.append('1');
		}

		pack[0] = Integer.parseInt(coef.toString());//converts the coef from a StringBuilder to an int
		pack[1] = variable;
		pack[2] = Integer.parseInt(expo.toString());
		pack[3] = i;
 
		return pack;
	}//end extractTerm

}//end class


