/*
 * Name: Qianwen (Tiffany) Zheng
 * Lab: CSCI 136 (Section 05) LAB 1
 * This program prints the first ten odd numbers.
 */

public class Odd {
    public static void main (String args[]) {
	for(int i = 0; i<20; i++) {
	    if(i%2 != 0) {
		System.out.println(i);
	    }
	}
    }
}
