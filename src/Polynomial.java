
import java.util.ArrayList;
import java.util.ListIterator;


public class Polynomial<T> extends ArrayList<Term> {

	//Constructors
	public Polynomial() {}
	
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
