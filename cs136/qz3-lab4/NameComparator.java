/*
 * Name: Qianwen (Tiffany) Zheng
 * Lab: CSCI 136 (Section 05) LAB 4
 *
 * This class is designed to compare two student objects by their first names according to 
 * alphabetical order
 */

import java.util.Comparator;

public class NameComparator implements Comparator<Student> {

    /*
     * pre: Student b and Student a both have valid first names
     * post: should return a negative number if Student a has a first
     * name that should appear before b's if sorted alphabetically
     */
    public int compare(Student a,Student b) {

	// Gets first names of the students being compared
	String aName = a.getName();
	String aFirst = aName.substring(0,aName.indexOf(" "));
	String aLast = aName.substring(aName.lastIndexOf(" ")+1);
					 
	String bName = b.getName();
	String bFirst = bName.substring(0,bName.indexOf(" "));
	String bLast = bName.substring(bName.lastIndexOf(" ")+1);

	int compareFirst =  aFirst.compareTo(bFirst);
	int compareLast = aLast.compareTo(bLast);

	if (compareFirst != 0) {
	    return compareFirst;
	} else {
	    return compareLast;
    
    }
}
}
