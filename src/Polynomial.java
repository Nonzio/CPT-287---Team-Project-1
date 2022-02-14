
import java.util.ArrayList;
import java.util.Collections;


public class Polynomial<T> extends ArrayList<Term> {

	//Constructors
	public Polynomial() {}
	
	//needs a sort method and an add method
	
	public void dropTerm(Term t) { 
		this.remove(t);
	}
	
	}
