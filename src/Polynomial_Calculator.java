
import java.util.Scanner;

public class Polynomial_Calculator {

	static Polynomial<Term> firstPoly = new Polynomial();//These polynomials should be in this order in listOfPoly
	static Polynomial<Term> secondPoly = new Polynomial();
	static Polynomial<Term> addedPoly = new Polynomial();
	
	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		int returnPack[] = new int[4];//stores extractTerm return data
		char variable = ' ';
	
		
		System.out.print("Chose a command:\n"
				   + "f: Enter first polynomial\n"
				   + "s: Enter second polynomial\n"
				   + "a: Add the polynomials\n"
				   + "p: Print the added polynomial\n"
				   + "q: Quit"
				   + "\n"
				   + "Please type \"f\" \"s\" \"a\" \"p\" or \"q\":");
		char userChoice = input.nextLine().charAt(0);
		System.out.println();

		while (userChoice != 'q'){
			switch(userChoice) {
				case 'f'://enters the first polynomial
					System.out.println("Enter polynomial with no spaces. Use '^' to represent powers.\n ( example: \"2x^3-3x^-2+3+x+3x\" ): ");//asks user for first polynomial to add
					String userInput = input.nextLine();
					if (firstPoly.size()!= 0) {//clears the first poly so you can fill it with new terms
						firstPoly.clear();
					}
					returnPack = extractTerm(userInput, 0);//Contents 0: coefficient, 1: variable (0 = ' ', 1 = 'x'), 2:exponent, 3: index To Start At next
					while (returnPack[3] <= userInput.length()) {//Iterates polynomial extracting Terms adding them to firstPoly. combines terms with same coefficient and wont add terms with 0 coefficient.
						variable = ' ';
						if (returnPack[1] == 1) {
							variable = 'x';
						}//converts variable number to letter for Term constructor
						Term newTerm = new Term(returnPack[0], variable, returnPack[2]);
						firstPoly.addTerm(newTerm);
						returnPack = extractTerm(userInput, returnPack[3]);// Updates returnPack with the data for the next Term
					}//end while
					firstPoly.sortPoly();
					break;//end 'f' case
			
					
				case 's':
					System.out.println("Enter polynomial with no spaces. Use '^' to represent powers.\n ( example: \"2x^3-3x^-2+3+x+3x\" ): ");//asks user for first polynomial to add
					userInput = input.nextLine();
					if (secondPoly.size()!= 0) {//clears the second poly so you can fill it with new terms
						secondPoly.clear();
					}
					returnPack = extractTerm(userInput, 0);//Contents 0: coefficient, 1: variable (0 = ' ', 1 = 'x'), 2:exponent, 3: index To Start At next
			
					while (returnPack[3] <= userInput.length()) {//Iterates polynomial extracting Terms adding them to firstPoly. combines terms with same coefficient and wont add terms with 0 coefficient.
						variable = ' ';
						if (returnPack[1] == 1) {
							variable = 'x';
						}//converts variable number to letter for Term constructor
						Term newTerm = new Term(returnPack[0], variable, returnPack[2]);
						secondPoly.addTerm(newTerm);
						returnPack = extractTerm(userInput, returnPack[3]);// Updates returnPack with the data for the next Term
					}
					secondPoly.sortPoly();
					break;//end 's' case
			
					
				case 'a'://add the polynomials together
					if (addedPoly.size() !=0) {//clears old addedPoly data 
						addedPoly.clear();
					}
					addedPoly.combinePoly(firstPoly, secondPoly);//addedPoly = firstPoly + secondPoly
					addedPoly.sortPoly();
					System.out.println("\nYour polynomials have been added. \n");
					break;//end 'a' case
					
	
				case 'p'://print the terms in all the polynomials
					System.out.println("results: " + firstPoly.toString()+ " + " + secondPoly.toString() + " = " + addedPoly.toString());
					System.out.println();
					break;//end 'p' case
					
					
				default:
					System.out.println("The character you entered is unsupported.");
					break;
			}//end switch
			System.out.print("f: Enter first polynomial\n"//prints the menu again after each command is run unless 'q' is entered
					+ "s: Enter second polynomial\n"
					+ "a: Add the polynomials\n"
					+ "p: Print the added polynomial\n"
					+ "q: Quit"
					+ "\n"
					+ "Please type \"f\" \"s\" \"a\" \"p\" or \"q\":");
			userChoice = input.nextLine().charAt(0);
			System.out.println();
		}//end 'q' while
		
	}//end main
	
	
	/**
	 * Extracts the variable, exponent, and coefficient from the users string and
	 * adds them to an array with the index to start extracting the next term at.
	 * @param poly: Original string that user entered
	 * @param start: the index to start parsing the string
	 * @return: pack[]: an array of ints containing elements in this order: coefficient, variable, exponent, index to start at next
	 */
	public static int[] extractTerm(String poly, int start) {
		int[] pack = new int[4];
		int variable = 0;//if it is 0 there is no x term. if it is 1 there is
		StringBuilder coef = new StringBuilder();
		StringBuilder expo = new StringBuilder();
		int i = start;
		
		if (start >= poly.length()) {
			return pack;
		}
		if (poly.charAt(i) == '−' || poly.charAt(i) == '-') {//first character is a - sign
			coef.append('-');
			i++;
	
		} else if (poly.charAt(i)== '+') {//first character is a + sign
			i++;
	
		}
 
		if (Character.isDigit(poly.charAt(i))) {//coefficient is greater than 1
			while (i < poly.length() && Character.isDigit(poly.charAt(i))) {
				coef.append(poly.charAt(i));
				i++;
			}
			if (i < poly.length() && poly.charAt(i) == 'x') {//an x follows the coefficient number
				variable = 1;
				i++;
			}
 
		} else if (i < poly.length() && poly.charAt(i) == 'x') {//first term after sign is x
			variable = 1;
			coef.append('1');
			i++;
		}
 
		if (i < poly.length() && poly.charAt(i)== '^'){//the exponent is greater than 1
			++i;
	
			if (poly.charAt(i) == '−' || poly.charAt(i) == '-') {//a - sign follows the ^ sign
				expo.append('-');
				i++;
			}
	
			while (i < poly.length() && Character.isDigit(poly.charAt(i))) {//records exponent number
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


