/*                                                                                    
 * Name: Qianwen (Tiffany) Zheng                                                      
 * Lab: CSCI 136 (Section 05) LAB 4                                                   
 *                                                                                    
 * This class is designed to be an extension of the Vector class that allows          
 * for sorting elements inside the vector                                             
 *                                                                                    
 */

import structure5.*;
import java.util.Comparator;

public class MyVector<E> extends Vector<E> {

    /*                                                                                
     * Constructs the MyVector by calling the constructor for Vector                  
     */
    public MyVector() {
	super();
    }

    /*                                                                                
     * pre: c is a valid comparator                                                   
     * post: sorts this vector in order determined by c                               
     */
    public void sort (Comparator<E> c) {
	for (int currC=size()-1;currC>0;currC--){
	    boolean swapped = false;
	    for (int i=1;i<=currC;i++){
		if (c.compare(get(i-1),get(i))> 0){
		    E temp = get(i-1);
		    set(i-1,get(i));
		    set(i,temp);
		    swapped = true;
		}
	    }
	    if (!swapped){
		break;
	    }
	}
    }

}

