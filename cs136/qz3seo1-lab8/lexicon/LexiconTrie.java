/*
 * Name: Qianwen (Tiffany) Zheng & Steven Omondi
 * Lab: CSCI 136 (Thursday Afternoon - Jon) LAB 8
 *
 * This class represents a data structure to store LexiconNodes that allows us to keep track of and manipulate strings.
 */

/*
 * Thought Questions: If we use an OrderedVector instead of a trie for storing our Lexicon, the process of searching for suggested
 * spelling corrections would differ from our implementation. When we use an OrderedVector, the nodes are stored in order. So, this 
 * is an advantage when searching for suggested spelling corrections since all the neighbours within a given distance can easily be
 * found. With an OrderedVector, instead of going through all the children of the root node to find one that matches the character,
 * you just simply call the "contains" method in the OrderedVector. When implementing the trie, you would have to go through all 
 * the children of the root to look for the one with the matching character. Therefore, the OrderedVector will be more efficient in 
 * searching for suggested corrections because of a faster run time. 
 */
import structure5.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Collections;

public class LexiconTrie implements Lexicon {
    
    LexiconNode node; // the root node
    int size;  // the number of words in the trie

    /** 
     * Contructs a LexiconNode object holding an empty string
     */
    public LexiconTrie() {
        node = new LexiconNode(' ',false);
    }

    /**
     * Helper method to add words
     * PRE:The node in parameters must be a valid node
     * @param the word to be added and the parent LexiconNode
     * POST: adds a word to the trie and returns true if added
     */
    public boolean addWord(String word, LexiconNode n) {
	LexiconNode pointer = n;

	// If the word does not already exist on a path
	if(pointer.getChild(word.charAt(0)) == null){
	    
	    //Create a new node for each letter
	for(int i = 0; i < word.length(); i++){
	    LexiconNode theNode = new LexiconNode(word.charAt(i), false);
	    if(theNode == null){ System.out.println("node" + node.letter());
	    }
	    pointer.addChild(theNode);
	    pointer = pointer.getChild(word.charAt(i));
	}

	// If there are nodes with corresponding letters to the word to be added
	}else{
	    while(word.length() > 0){
		if (pointer.getChild(word.charAt(0)) == null){
		    return addWord(word, pointer);
		}
		pointer = pointer.getChild(word.charAt(0));
		if(word.length() > 1){
		    word = word.substring(1);
		}else{
		    word = "";
		}
	    }
	}
	pointer.isWord = true;
	size++;
	return pointer.isWord;
    }

    /**
     * PRE: The parameter passed in must be of type string
     * @param the word to be added
     * POST: adds a word to the trie and returns true if added
     */
    public boolean addWord(String word){
	return addWord(word, node);
    }
	
    /**
     * PRE: the file in parameters must be a valid file of strings
     * @param a text file to be read
     * POST: adds the words read from file into the trie and returns the number of words in the trie
     */
    public int addWordsFromFile(String filename) {
	Scanner in = new Scanner(new FileStream(filename));	
	while (in.hasNextLine()){
	    String word = in.nextLine().toLowerCase();
	    addWord(word);
	    size++;
	}
	return size;
    }

    /**
     * PRE: The string in parameters must be a valid word in the trie
     * @param the word to be removed from the trie
     * POST: removes a word from the trie, returns true if removed and false if not
     */
    public boolean removeWord(String word) {
	return removeWord(word, node);
    }

    /**
     * Helper method for removeWord
     * PRE: must be a valid word and node in parameters
     * @param word to be removed and parent node
     * POST: returns true if word is removed
     */
    public boolean removeWord(String word, LexiconNode n){
	LexiconNode pointer = n;
	if (word.length()==0){
	    size--;
	    n.isWord = false;
	} else {
	    if (word.length() > 0){
		pointer = pointer.getChild(word.charAt(0));
		word = word.substring(1);
		removeWord(word,pointer);
	    }
	    return true;
	} 
	return false;
    }
    
    /**
     * Returns the number of words in the trie
     *
     */
    public int numWords() {
	    return size;
	}

    /**
     * PRE: The word in parameters must be of type string
     * @param the word to be checked
     * POST: Returns true if the trie contains the word
     */
    public boolean containsWord(String word){
	return containsWordHelper(word, node);
    }

    /**
     * PRE: The prefix in parameters must be of type string
     * @param the prefix to be checked
     * POST: Returns true if the trie contains the prefix
     */
    public boolean containsPrefix(String prefix){
	return  containsPrefixHelper(prefix, node);
    }

    /**
     * Helper method to check if trie contains prefix
     * PRE: Must be a valid string and node in parameters
     * @param the prefix to be checked and the parent node 
     * POST: returns true if the trie contains the prefix
     */
    public boolean containsPrefixHelper(String prefix, LexiconNode node){
	if( prefix.isEmpty()|| node.getChild(prefix.charAt(0)) == null) {
	    return false;
	} else {
	    if(prefix.length()>1){
	    containsPrefixHelper(prefix.substring(1), node.getChild(prefix.charAt(0)));
	    }else{
		containsPrefixHelper("", node.getChild(prefix.charAt(0)));
	    }
	}
	return true;
    }

    /**
     * Helper method to check if trie contains a word
     * PRE: Must be a valid string and node in parameters
     * @param the word to be checked and the parent node
     * POST: returns true if the trie contains the word
     */
    public boolean containsWordHelper(String word, LexiconNode node){
	if(word.isEmpty() || node.getChild(word.charAt(0)) == null) {
		return node.isWord;
	    } else {
		if(word.length() > 1){
		return containsWordHelper(word.substring(1), node.getChild(word.charAt(0)));
		}else{
		    return containsWordHelper("", node.getChild(word.charAt(0)));
		}
	    }
    }

    /**
     * Returns an iterator that iterates over the vector of words in alphabetical order
     */
    public Iterator<String> iterator() {
	Vector<String> v = new Vector<String>();
	getVector(v,"",node);
	return v.iterator();
    }
    
    /**
     * Helper method to iterate over words
     * PRE: Must be a valid vector, node and string in parameters
     * @param the vector of words, the word to be added, and the parent node
     * POST: Gets words from the trie and stores them in a vector
     */
    public void getVector(Vector<String> v, String s, LexiconNode n){
	s = s + n.letter();
	if(n.isWord){
	    v.add(s);
	}
	
	Iterator<LexiconNode> iter = n.iterator();
	while (iter.hasNext()){
	    getVector(v, s, iter.next());
	}	
    }

    /**
     * PRE: Must be a valid string and integer in parameters
     * @param the potentially misspelled word and the maximum distance by which char positions can differ
     * POST: Returns a set of words from the trie that differ from the target word by a certain number of char positions
     */
    public Set<String> suggestCorrections(String target, int maxDistance) {
	Set<String> set = new SetVector<String>();
	Iterator<LexiconNode> iter = node.iterator();
	while(iter.hasNext()){
	    suggestCorrectionsHelper(set, target,"",maxDistance,iter.next(),0);
	}
	    return set;
    }

    /**
     * Helper method for suggestCorrections
     * PRE: Must be valid sets, strings, integers, and node in parameters
     * @param the set to add words in, the target word and the matching word in the trie, the maximum number of character
     * positions they can differ by, the parent node, and the maximum number of errors
     * POST: Fills a set of words from trie that are suggested corrections
     */
    public void suggestCorrectionsHelper(Set s, String target, String word, int maxDistance, LexiconNode n, int errors){

	// Increments the number of errors when a mismatched character is found
	word = word + n.letter();
	if(n.letter()!=(target.charAt(0))){
	    errors++;
	}
	
	// Base case
	if (target.length()==1){
	    if(errors<=maxDistance){
		s.add(word);
	    }
	}else{
	    Iterator<LexiconNode> iter = n.iterator();   
	    while (iter.hasNext()){
		suggestCorrectionsHelper(s, target.substring(1), word, maxDistance, iter.next(), errors);
	    }
	}
    }
    
    /**
     * PRE: Must be a valid string in parameters
     * @param the regular expression
     * POST: Returns a set of words that match the regular expression
     *
     */
    public Set<String> matchRegex(String pattern){
	Set<String> set = new SetVector<String>();
	matchRegexHelper(set, pattern, "", node);
	return set;
    }

    /**
     * Helper method for matchRegex
     * PRE: Must be valid set, strings, and node in parameters
     * @param the set to add words to, the regular expression, the word from the trie to be added, and the parent node
     * POST: Fills a set of words from the trie that match the regular expression
     */
    public void  matchRegexHelper(Set s, String pattern, String word, LexiconNode n){
	Iterator<LexiconNode> iter = n.iterator();

	// Base case when the pattern is empty
	if(pattern.isEmpty()){
	    if(n.isWord == true){
		s.add(word);
	    }

	    // Determines what words to add to the set according to the specific character in the regular expression
	}else{
	    char firstChar = pattern.charAt(0);
	    
	    if (n.getChild(firstChar) != null && firstChar != '?' && firstChar != '*'){
		word = word + firstChar;
		matchRegexHelper(s, pattern.substring(1), word, n.getChild(firstChar));
	    }
	    else if (firstChar == '?'){
		while (iter.hasNext()){
		    LexiconNode theNode = iter.next();
		    matchRegexHelper(s,pattern.substring(1), word + theNode.letter(), theNode);
		}
		matchRegexHelper(s,pattern.substring(1),word, n);
	    }
	    else if(firstChar == '*'){
		while(iter.hasNext()){
		    LexiconNode theNode = iter.next();
		    matchRegexHelper(s,pattern.substring(1), word + theNode.letter(), theNode);
		    matchRegexHelper(s,pattern, word + theNode.letter(), theNode);
		}
		matchRegexHelper(s, pattern.substring(1), word, n);
	    }
	    
	}	
    }
}
