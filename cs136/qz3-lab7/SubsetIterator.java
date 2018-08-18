import structure5.*;
import java.util.Iterator;

/*                                                                                 
 * Name: Qianwen (Tiffany) Zheng                                                      
 * Lab: CSCI 136 (Section 05) LAB 7                                                  
 *                                                                                    
 * This class is designed to iterate over the elements of a subset by iterating over the elements from an initial iterator that 
 * corresponds to 1's in a given bit mask. 
 */

public class SubsetIterator<E> implements Iterator<E> {

    protected Iterator<E> base; //slave iterator
    protected int mask;  // the bit mask to determine what elements to iterate over
    
    /**
     * Constructs the SubsetIterator that iterates over the elements of the source iterator, it, according to a bit mask
     * @param source iterator and bit mask represented by an integer value
     */
    public SubsetIterator(Iterator<E> it, int bitMask){
	base = it;
	mask = bitMask;
    }

    /**
     * Returns true if there are more elements in this subset and the mask is not zero
     */
    public boolean hasNext(){
	return base.hasNext()&&mask!=0;
    }

    /**
     * @pre there are more elements available and the mask is not zero
     * @post returns the next element and increments the iterator according to the bit mask
     */
    public E next(){
	E current = null;
	
	while (hasNext()){
	    int b = mask&1;
	    mask = mask>>1;
	    if(b==1){
		current = base.next();
		break;
	    } else {
		base.next();
	    }
	}
	return current;
    }
    
    /**
     * Does nothing
     */
    public void remove(){}
}

