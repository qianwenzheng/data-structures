/*
 * Name: Qianwen (Tiffany) Zheng & Steven Omondi
 * Lab: CSCI 136 (Thursday Afternoon - Jon) LAB 8
 * This class is responsible for providing a constructor (that creates nodes) and
 * stores them in an orderd vecotr. It also contains methods necessary for the 
 * manipilation of the data structure that stores the nodes.
 */
import structure5.*;
import java.util.Iterator;

class LexiconNode implements Comparable<LexiconNode> {
    char letter;
    boolean isWord;
    OrderedVector<LexiconNode> v;

    // Constructor
    LexiconNode(char theLetter, boolean isAWord) {
	v = new OrderedVector<LexiconNode>();
	letter = theLetter;
	isWord = isAWord;
    }

    /**
     * PRE:@param is of type LexiconNode
     * POST:characters stored at the Lexicon Nodes are compared.
     */
    public int compareTo(LexiconNode o) {
	return Character.valueOf(letter).compareTo(Character.valueOf(o.letter()));
    } 

    /**
    * PRE:@param is of type char
    * POST: compares chars
    */
    public boolean equals(char c){
	return letter == c;  
	
    }
    /* Return letter stored by this LexiconNode */
    public char letter() {
	return letter;
    }

    /**
     * PRE:@param is a valid LexiconNedo
     * POST:Add LexiconNode child to correct position in child data structure 
     */
    public void addChild(LexiconNode ln) {
	v.add(ln);
    }

    
    /**
     * PRE:@param is of type ch
     * POST: GetS LexiconNode child for 'ch' out of child data structure 
     */
    public LexiconNode getChild(char ch) {
	Iterator<LexiconNode> iter = iterator();
	while (iter.hasNext()){
	    LexiconNode n = iter.next();
	    if (n.equals(ch)){
		return n;
	    }
	}
	return null;
    }

    /* Remove LexiconNode child for 'ch' from child data structure */
    public void removeChild(char ch) {
	v.remove(getChild(ch));
    }

    /* Iterate over children */
    public Iterator<LexiconNode> iterator() {
	return v.iterator();
    }
    
}
