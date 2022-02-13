
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
		System.out.println("Rebekah");
		System.out.println("Nunzio");
		
		
		/* Example of Term class.
		 * .compareTo() - Compares terms by their exponent.
		 * 		Returns 1 when the argument term has bigger exponent
		 * 			   -1 when the argument term has lower exponent
		 * 				0 when terms are of equal power
		 *      Use this when ordering / calculating polynomials.
		 *  */
		Term t = new Term(5,'x',6);
		System.out.println("" + t.coefficient + t.variable + "^" +t.exponent);
		Term t2 = new Term(4,'x',4);
		System.out.println("" + t2.coefficient + t2.variable + "^" +t2.exponent);

		System.out.println(t.toString() + " > " + t2.toString() + " --> " + t.compareTo(t2));
		
		listOfPoly.add(firstPoly);//adds polynomials in correct order
		listOfPoly.add(secondPoly);
		listOfPoly.add(addedPoly);
		
		
		//enters the first polynomial
		System.out.println("Enter first polynomial with no spaces. Use '^' to represent powers. (example: 3x^3-x^2+1): ");//asks user for first polynomial to add
		String userInput = input.nextLine();
		
		int userInputIndex = 0;
		while (userInputIndex < userInput.length()) {//Iterates through the users polynomial extracting the Terms
			userInputIndex = extractTerm(1, userInput, userInputIndex);// change first param to 2 when asking for second poly
		}
				
		
	//prints the terms in all the polynomials
		Iterator<Polynomial> listIterator = listOfPoly.listIterator();//creates an iterator for listOfPoly
		while (listIterator.hasNext()) {
			Polynomial nextPoly = listIterator.next();
			Iterator<Term> termIt = nextPoly.iterator();//creates an iterator for each polynomial
			
			while (termIt.hasNext()) {//prints each term
				System.out.println(termIt.next().toString());
			}
		}
		
	}//end main
	
		
/**
 * Extracts the variable, exponent, and coefficient from the users string and adds them to a new term.
 * That term is added to a polynomial based on the polyIDNumber. 
 * returns the index to start extracting the next term at.
 * @param polyIDNumber: Identifies witch polynomial to add term to
 * @param poly: Original string that user entered
 * @param start: the index to start parsing the string
 * @return: the index to start parsing the string next time
 */
public static int extractTerm(int polyIDNumber, String poly, int start) {
	int polyID = polyIDNumber;
	char variable = ' ';
	StringBuilder coef = new StringBuilder();
	StringBuilder expo = new StringBuilder();
	int i = start;
	poly = poly + " ";//the extra space is added to avoid out of bounds index error.
	

	
	if (poly.charAt(i) == '-') {//first character is a - sign
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
			variable = 'x';
			i++;
		}
 
	} else if (poly.charAt(i) == 'x') {//first term after sign is x
	 		variable = 'x';
	 		coef.append('1');
	 		i++;
		}
 
	if (poly.charAt(i)== '^'){//the exponent is greater than 1
		++i;
		if (poly.charAt(i) == '-') {//a - sign follows the ^ sign
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

	int FinalCoef = Integer.parseInt(coef.toString());//converts the string builders into integers
	int FinalExpo =Integer.parseInt(expo.toString());
 
	Term unit = new Term(FinalCoef, variable ,FinalExpo);//creates a term with the coefficient, variable and exponent
 
	if (polyID == 1) {firstPoly.add(unit);}//selects what polynomial to add the term based on polyID
	else if (polyID == 2) {secondPoly.add(unit);}

	return i;
	}//end extractTerm

}//end class


