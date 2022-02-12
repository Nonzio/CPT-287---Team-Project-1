
public class Polynomial_Calculator {

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
		System.out.println(t.coefficient + t.variable + "^" +t.exponent);
		Term t2 = new Term(4,'x',4);
		System.out.println(t2.coefficient + t2.variable + "^" +t2.exponent);

		System.out.println(t.toString() + " > " + t2.toString() + " --> " + t.compareTo(t2));

		
		
	}
}