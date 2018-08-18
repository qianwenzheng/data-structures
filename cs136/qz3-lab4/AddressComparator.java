/*
 * Name: Qianwen (Tiffany) Zheng
 * Lab: CSCI 136 (Section 05) LAB 4
 *
 * This class is designed to compare two Associations of Addresses and the number of students
 * living there. 
 */

import java.util.Comparator;
import structure5.*;

public class AddressComparator implements Comparator<Association<String,Integer>> {
    /*
     * pre: both associations consist of valid addresses and number of students 
     * post: returns a negative number if Association a has a higher number of students than
     * Association b
     */
    public int compare(Association<String,Integer> a, Association<String,Integer> b){
	int aStudents = a.getValue();
	int bStudents = b.getValue();

	return bStudents - aStudents;
    }
}
