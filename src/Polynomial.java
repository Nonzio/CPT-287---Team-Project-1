
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;





public class Polynomial extends ArrayList<Term> {

	//Constructors
	public Polynomial() {
		//default
	}
	
	public Polynomial(Polynomial other) {//copy constructor
		for (int w =0; w < other.size(); w++) {
			this.add(other.get(w));
		}
	}
	
	//Methods
	
	
		/**
		 * Adds a term to a polynomial.
		 * Wont add terms with 0 Coefficients. 
		 * Converts terms with 0 exponents to constants.
		 * Compares term to be added with terms already in polynomial to combine it with like terms.
		 * @param t: Term to be added
		 */
	public void addTerm(Term t) {
		if (t.getExponent() == 0) {//removes the variable if the exponent is 0
			t.setExponent(1);
			t.setVariable(' ');
		}
		
		if (t.getCoefficient() != 0) {//only add if coefficient is not 0
			if (this.size()==0) {//if there are no other terms add this one
				this.add(t);
			}else {//if there are other terms check to see if you can add with a term with the same exponent
				for (int d=0; d < this.size(); d++) {
					if ( this.get(d).compareTo(t) == 0 ) {//A Term matches the variable and exponent of the term to be added
						t.setCoefficient(t.getCoefficient() + this.get(d).getCoefficient());
						this.remove(this.get(d));//deletes the term that matches
						break;
					}
				}
					if (t.getCoefficient() != 0) {//Confirms that the new coefficient is still not 0 and adds the term
						this.add(t);
					}
			}
		}
	}//end addTerm
	
	/**
	 * Sorts Polynomials by decreasing exponent
	 */
	public void sortPoly() {
		Collections.sort(this);
		Collections.reverse(this);
	}
	
	/**
	 * adds the first and second polynomials and returns a new polynomial with the added terms
	 * @param: First: the first polynomial
	 * @param Second: the second polynomial 
	 * @return: added: A polynomial made by adding the terms in second and first polynomials
	 */
	public void combinePoly(Polynomial first, Polynomial second){
		Polynomial added = new Polynomial(first);
		int coef = 0;
		boolean hasBeenAdded = false;
		
		for (int i = 0; i < second.size(); i++) {//iterates through the second polys terms
			for (int a = 0; a < added.size(); a++) {//iterates through the first polys terms
				if ((added.get(a)).compareTo(second.get(i))==0) {//compares each term in the second poly to each term in the first poly
					coef = (added.get(a)).getCoefficient() + (second.get(i)).getCoefficient();//adds the term that matches coefficient to the new terms coefficient
					added.remove(added.get(a));//removes the matching term
					if (coef != 0) {//If the Coef is not 0 add the new combined term
						Term newTerm = new Term(second.get(i).getCoefficient(), second.get(i).getVariable(),second.get(i).getExponent());
						newTerm.setCoefficient(coef);
						added.add(newTerm);
						hasBeenAdded = true;
					}
				}
				if (hasBeenAdded == true) {
					break;
				}
			}//end added terms loop
			if (hasBeenAdded == false) {
				added.add(second.get(i));
			}
			hasBeenAdded = false;
		}//end second terms loop
		
		for (int n =0; n < added.size(); n++) {//adds the terms from added into addedPoly
			this.add(added.get(n));
		}
		
	}//end combinePoly
	
	public String toString() {
		ListIterator<Term> it = this.listIterator();
		StringBuilder sb = new StringBuilder();
		//For each term in polynomial
		while(it.hasNext()) {
			Term current_term = it.next();
			if(current_term.getCoefficient() > 0 && this.indexOf(current_term) != 0) {
				sb.append('+');
			}
			sb.append(current_term.toString());
		}
		
		return sb.toString();
	}

	
}
