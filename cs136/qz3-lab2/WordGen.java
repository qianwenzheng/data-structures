import structure5.*;
import java.util.Random;
import java.util.Scanner;

/*
 * Name: Qianwen (Tiffany) Zheng
 * Lab: CSCI 136 (Section 05) LAB 2
 * 
 * This class is designed to take text input, build a table representing the
 * probabilities of character occurences in the text, and print out a randomly
 * generated string based on the character sequence probabilities from the 
 * input text. This class employs the FrequencyList and Table classes.
 */

/*
 * Self Check Problems:
 * 3.2) The difference between the add(v) method and the add(i,v) methods of 
 * Vector is that the add(v) method adds an element to the end of the vector
 * that could be possibly expanding, at whichever size it is at while the 
 * add(i,v) method adds an element at a particular location, denoted by the 
 * integer "i" which represents the index at which you want to insert the 
 * element "v".
 *
 * 3.3) The difference between the add(i,v) and the set(i,v) methods of Vector
 * is that the add(i,v) method acutally adds an certain element that did not 
 * previously exist in the vector, at a particular location index denoted by
 * the integer "i", while the set(i,v) method does not particularly add to the
 * vector in that it does not increase the vector's size, but it replaces the
 * previous element at location index "i" with a new element "v". Therefore,
 * the set method changes the value of an element at a particular location in a
 * vector but does not increase the size of the vector.
 *
 * 3.4) The difference between the remove(v) method and the remove(i) method is
 * that the remove(v) method finds the element in the vector according to its 
 * value, denoted by v and removes that element from the vector while the 
 * remove(i) method finds the element in the vector at a particular location, 
 * denoted by the location index, integer "i", and removes whatever element is 
 * at that location. Therefore, the remove(v) removes an element from a vector
 * according to its value, while the remove(i) method removes an element from a
 * vector according to its location.
 *
 * Problem 3.6:
 * With a class, BitVector, that stores values in boolean type, the primary 
 * advantage would be that this special purpose vector would allow for easier 
 * and more efficient storage and operations. For example, the BitVector would 
 * not take up much space in memory due to it being stored in boolean type, and
 * has the capacity to help perform complicated operations/calculations with
 * minimal time.
 */

public class WordGen {

    protected Table theTable;    // collection of character sequences and their frequency lists
    protected static final int STRING_LENGTH = 500;   // length of text to be generated

    /*
     * Word generator is not represented by a specific object to be created
     */
    public WordGen() {
    }

    /*
     * Uses text input to generate and print random text based on character probabilities
     */
    public static void main (String args[]) {
	
	WordGen generator = new WordGen();

	// Allows user to specify the level of character analysis
	int k =0;
	if (args.length == 0) {
	    System.out.println("Usage: java WordGen <level>");
	} else {
	    k = Integer.parseInt(args[0]);
	}

	Scanner in = new Scanner(System.in);
	StringBuffer textBuffer = new StringBuffer();

	while(in.hasNextLine()) {
	    String line = in.nextLine();
	    textBuffer.append(line);
	    textBuffer.append("\n");
	}
	String text = textBuffer.toString();
	generator.makeTable(k,text);
	generator.printNewText(STRING_LENGTH,k,text);
    }

    /*
     * Constructs a table of character sequences and their frequency lists based on level
     * specified by the user and the input text
     */
    public void makeTable(int level, String text) {
	theTable = new Table();
	int beginIndex = 0;
	while(((beginIndex) + level) < text.length()) {
	    String charSequence = text.substring(beginIndex,
						 (beginIndex + level));
	    String nextLetter = String.valueOf(text.charAt(beginIndex+level));;
	    theTable.update(charSequence,nextLetter);
	     beginIndex++;
	}
    }

    /*
     * Prints out randomly generated string from character probabilities in reference string
     */
    public void printNewText(int length, int level, String text) {
	Random rnd = new Random();
	int randomIndex = rnd.nextInt(text.length()-level-1);
	String sequence = text.substring(randomIndex,randomIndex+level);
	String newText = sequence;
	
	while (newText.length()<= length){
	String nextLetter = theTable.getNextLetter(sequence);
	newText = newText + nextLetter;
	sequence = text.substring(text.indexOf(sequence)+1,
				  text.indexOf(sequence)+1+level);
	}

	System.out.println(newText);
	}
}
	
