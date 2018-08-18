import structure5.*;
import java.util.Random;
/*
 * Name: Qianwen (Tiffany) Zheng
 * Lab: CSCI 136 (Section 05) LAB 2
 *   
 * This class is designed to store various character sequences from within a 
 * given string and the frequencies in which different characters occur after
 * each sequence. This information is implemented as a vector of associations
 * such that for each association, the key is a specific character sequence
 * stored as a string and the value is the FrequencyList associated with that
 * sequence. 
 * 
 */

public class Table {

    // the table represented by a vector of characters and frequency lists
    protected Vector<Association<String,FrequencyList>> freqTable;


    /*
     * Creates a table represented by a vector of character sequences and 
     * their respective 
     */
    public Table() {
	freqTable = new Vector<Association<String,FrequencyList>>();
    }

    /*
     * Updates the table given a specific character sequence and the 
     * subsequent letter by updating the frequency list of that association
     * or adding a new association to the table
     */
    public void update(String charSequence, String nextLetter){
	Association<String,FrequencyList> selected =
	    getAssociation(charSequence);
	FrequencyList freqList = new FrequencyList();
	
	if (selected == null) {
	    freqTable.add(new Association<String,FrequencyList>
			  (charSequence,freqList));
    } else {
	freqList = (FrequencyList)selected.getValue();
    }
    freqList.updateFrequency(nextLetter);
}

    /*
     * Returns the total frequency within the frequency list of a specified
     * character sequence
     */
    public Integer getTotalFrequency(String charSequence) {
	Association<String,FrequencyList> selected =
	    getAssociation(charSequence);
	FrequencyList freqList = (FrequencyList)selected.getValue();
	Integer totalFrequency = 0;
	
	for(int index=0;index<freqList.size();index++){
	    Association<String,Integer> letterFreq =
		(Association<String,Integer>)freqList.get(index);
	    Integer frequency = (Integer)letterFreq.getValue();
	    totalFrequency = totalFrequency + frequency;
	}
	return totalFrequency;
    }

    /*
     * Returns the next letter as a string based on probabilities of the 
     * specified preceding character sequence
     */
    public String getNextLetter (String charSequence){
	Association<String,FrequencyList> selected =
	    getAssociation(charSequence);
	Random rnd = new Random();

	if(selected != null) {
	    FrequencyList freqList = (FrequencyList)selected.getValue();
	    int randomNum = rnd.nextInt(getTotalFrequency(charSequence));

	    //Subracts subsequent frequencies from the random number
	    for(int index=0; index<freqList.size(); index++) {
		Association<String,Integer> letterFreq =
		    (Association<String,Integer>)freqList.get(index);
		Integer frequency = (Integer)letterFreq.getValue();
		randomNum = randomNum - frequency;

		// Returns the letter with the last frequency subtracted
		if(randomNum<=0){
		    return (String)letterFreq.getKey();
		}
	    }
	} 

	// If the character sequence did not exist in reference string, it
	// returns a previously existing random character sequence 
	Association<String,FrequencyList> random =
	    freqTable.get(rnd.nextInt(freqTable.size()));
	String randomSequence = (String)random.getKey();
	return randomSequence;
    }

    /*
     * Returns an association in the table given a character sequence within
     * the table
     */
    public Association<String,FrequencyList> getAssociation(String
							    charSequence) {
	for (int index=0; index < freqTable.size(); index++) {
	    Association<String,FrequencyList> selected =
		(Association<String,FrequencyList>)freqTable.get(index);
	    String theSequence = (String)selected.getKey();
	    if(theSequence.equals(charSequence)){
		return selected;
	    }
	}
	return null;
    }
    }
	
