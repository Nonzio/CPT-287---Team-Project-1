
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;


public class Polynomial<T> extends ArrayList<Term> {

	//Constructors
	public Polynomial() {}
	
	public Polynomial(Polynomial<T> other) {//copy constructor
		for (int w =0; w < other.size(); w++) {
			this.add(other.get(w));
		}
	}
	
	/**
	 * adds the first and second polynomials and returns a new polynomial with the added terms
	 * @param Second: the second polynomial ("this" is the first polynomial)
	 * @return: added: A polynomial made by adding the terms in second and "this"(first)
	 */
		public Polynomial<Term> combinePoly(Polynomial<Term> Second){
			Polynomial<Term> added = new Polynomial(this);
			int Coef = 0;
			boolean hasBeenAdded = false;
			for (int i = 0; i < Second.size(); i++) {//iterates through the second polys terms
				for (int a = 0; a < added.size(); a++) {//iterates through the first polys terms
					if ((added.get(a)).compareTo(Second.get(i))==0) {//compares each term in the second poly to each term in the first poly
						Coef = (added.get(a)).getCoefficient() + (Second.get(i)).getCoefficient();//adds the term that matches coefficient to the new terms coefficient
						added.dropTerm(added.get(a));//removes the matching term
						if (Coef != 0) {//If the Coef is not 0 add the new combined term
							Term newTerm = new Term(Second.get(i).getCoefficient(), Second.get(i).getVariable(),Second.get(i).getExponent());
							newTerm.setCoefficient(Coef);
							added.add(newTerm);
							hasBeenAdded = true;
						}
					}else {//if there are no matching coefficients then the second polys term is added
						added.add(Second.get(i));
						hasBeenAdded = true;
					}
					if (hasBeenAdded == true) {break;}
				}
			hasBeenAdded = false;
			}
			return added;
		}//O(n^2)
		
		public void sortPoly() {
			Collections.sort(this);
			Collections.reverse(this);
		}
	
	public void addTerm(Term t) {
		/*Term new_term = t;
		//super.add(new_term);
		if(this.isEmpty()) {
			super.add(new_term);
		}else {
			this.sort(new_term);
		}*/
	}
	/* Sort method - Sorts terms within a single polynomial expression
	 *  ArrayList shall be loaded via addTerm, sorting terms individually, as passed.
	 * */
	private void sort(Term t) {
		/*ListIterator<Term> it = this.listIterator();
		if(it.hasNext()) {
			//While there is another term to evaluate
			while(it.hasNext()) {
				Term next_term = it.next();
				//If-check for variable to avoid constants with exponents
				if(t.getVariable() != ' ') {
					//If next_term has a lower exponent
					if(t.compareTo(next_term) == -1) {
						it.add(t);
						break;
					}
				}
			}
		}*/
	}
	
	public void dropTerm(Term t) {
		this.remove(t);
	}
	
}
