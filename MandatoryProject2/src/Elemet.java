
/**
 * @author Naveenraj
 *
 * Class Element to keep track of the Plus Infinity and Minus Infinity in the Skip List
 */
public class Elemet<T extends Comparable<? super T>> {

	boolean minusInfinity;
	boolean plusInfinity;       
	T value;
	
	public Elemet(boolean minusInfinity,boolean plusInfinity,T value) {
		this.minusInfinity=minusInfinity;          // minus infinity is set for the header of skip list
		this.plusInfinity=plusInfinity;            // plus infinity is set for the tail of skip list
		this.value=value;	
		}
}
