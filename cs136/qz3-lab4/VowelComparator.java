/*
 * Name: Qianwen (Tiffany) Zheng
 * Lab: CSCI 136 (Section 05) LAB 4
 *
 * This class is designed to compare two Students according to the number
 * of vowels that the students have in their names
 */

import java.util.Comparator;

public class VowelComparator implements Comparator<Student> {
    /*
     * pre: both Student a and b have valid names
     * post: returns a negative number if Student a has more vowels in his
     * name than Student b
     */
    public int compare(Student a,Student b) {
	int aVowels = 0;
	int bVowels = 0;

        String aName = a.getName().toLowerCase();
	String bName = b.getName().toLowerCase();

	for (int i=0;i<aName.length();i++){
	    char c = aName.charAt(i);
	    if (c=='a'||c=='e'||c=='i'||c=='o'||c=='u'){
		aVowels++;
	}
    }
	for (int i=0;i<bName.length();i++){
	    char c = bName.charAt(i);
	    if (c=='a'||c=='e'||c=='i'||c=='o'||c=='u'){
		bVowels++;
	    }
	    
	}

	return bVowels - aVowels;
}
}
