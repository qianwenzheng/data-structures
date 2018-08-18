/*
 * Name: Qianwen (Tiffany) Zheng
 * Lab: CSCI 136 (Section 05) LAB 4
 *
 * This class is designed to compare two Students according to their SU Box
 * numbers 
 */

import java.util.Comparator;

public class SUBoxComparator implements Comparator<Student>{

    /*
     * pre: Student a and b both have valid SU Box numbers
     * post: Returns a negative number if a has a smaller SU Box than b
     */
    public int compare(Student a,Student b){
	int aSU = a.getSUBox();
	int bSU = b.getSUBox();
	return aSU - bSU;
    }
}
