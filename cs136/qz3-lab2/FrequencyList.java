import structure5.*;
import java.util.Random;
/*
 * Name: Qianwen (Tiffany) Zheng
 * Lab: CSCI 136 (Section 05) LAB 2
 * 
 * This class is designed to keep track of the characters that appear after a
 * certain character sequence as well as the respective frequencies that those
 * characters occur within a given string. This information is implemented as
 * a vector of associations in which a single letter stored as a string is the
 * key and the number of times that that letter occured after the given
 * sequence is the value in each association. 
 */

public class FrequencyList {
    
    
    protected Vector<Association<String,Integer>> freqList;  //the frequency list
    Random rnd = new Random();  //random number generator

    /*
     * Creates a frequency list of letters and their respective frequencies
     */
    public FrequencyList() {
	freqList = new Vector<Association<String,Integer>>();
    }
   
    /*
     * Updates the frequencies of a specific letter specified by the parameter
     * or adds a new component to the list if that letter does not yet exist  
     */
     public void updateFrequency(String letter) {
     
	Association<String,Integer> selected = getAssociation(letter);
	if (selected != null) {
	    Integer f = (Integer)selected.getValue();
	    selected.setValue(new Integer(f.intValue() + 1));
	} else {
	    freqList.add(new Association<String,Integer>(letter, 1));
	}
    }

    /*
     * Retrieves an association in the frequency list given a specific letter
     */
    public Association<String,Integer> getAssociation(String letter) {
	for (int index=0; index < freqList.size(); index++) {
	    Association<String,Integer> letterFreq =
		(Association<String,Integer>)freqList.get(index);
	    String theLetter = (String)letterFreq.getKey();
	    if (theLetter.equals(letter)) {
		return letterFreq;
	    }
	}
	return null;
	}
    
    /*
     * Returns the size of vector representing the frequency list as an integer
     */
    public int size(){
	return freqList.size();
    }

    /*
     * Returns a specific association in the frequency list given its location 
     * index represented by an integer in the vector
     */
    public Association<String,Integer> get(int index){
	return freqList.get(index);
    }
}


	  

		
		    
		     
	    
    
     
  

    
    
	
	
