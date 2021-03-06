/*
 * Recursion.java
 *
 * Name: Qianwen (Tiffany) Zheng
 * Lab: CSCI 136 (Section 05) LAB 3
 *
 * This class includes methods for various operations using recursion.
 *
 */
import structure5.*;

public class Recursion {
    
    /*****  1  ***************************************************/

    /*
     * Returns the number of cannoballs in pyramid with the given height.
     *
     * Running Time = O(n)
     */
    public static int countCannonballs(int height) {
	if (height == 0) {
	    return 0;
	} else {
	    return (height*height) + countCannonballs(height-1);
	}
    }


    /*****  2  ***************************************************/

    /*
     * pre: str contains no spaces
     * post: returns true if str is a palindrome
     *
     * Running Time = O(n)
     */
    public static boolean isPalindrome(String str) {
	if (str.length() < 2) {
	    return true;
	} else {
	    return (str.charAt(0)==str.charAt(str.length()-1)) &&
		isPalindrome(str.substring(1,str.length()-1));
	}
    }

    /*****  3  ***************************************************/

    /*
     * pre: str contains only bracketing operators
     * post: returns true if str is a string of matched parens,
     * brackets, and braces.
     *
     * Running Time = O(n)
     */
    public static boolean isBalanced(String str) {
	if (str.isEmpty()) {
	    return true;
	}

	// Replaces all bracketing operators with empty strings
	if(str.contains("()")){
	    str = str.replace("()","");
	}
	else if(str.contains("{}")){
	    str = str.replace("{}","");
	}
	else if(str.contains("[]")){
	    str = str.replace("[]","");
	}

	// Returns false if string contains only unmatched bracketing operators
	else {
	    return false;
	}
	return isBalanced(str);
    }
    /*****  4  ***************************************************/

    /*
     * Prints all substrings of str in no particular order
     *
     * Running Time = O(n^2)
     */
    public static void printSubstrings(String str) {
	substringHelper(str,"");
    }

    /*
     * Helper method for printing substrings of str
     *
     * Running Time = O(n^2)
     */
    public static void substringHelper(String str, String soFar){
	if(str.isEmpty()){
	    System.out.println(soFar);
	}
	else {
	    substringHelper(str.substring(1),soFar);
	    substringHelper(str.substring(1),soFar+str.substring(0,1));
	}
    }

    /*****  5  ***************************************************/

    /*
     * pre: number is non-negative
     * post: prints number in binary
     *
     * Running Time = O(log n)
     */
    public static void printInBinary(int number) {
	if(number==0){
	    System.out.print(0);
	} else if(number/2 == 0){
	    System.out.print(1);
	} else {
	    printInBinary(number/2);
	    System.out.print(number%2);
	}

    }


    /*****  6  ***************************************************/

    /*
     * Returns whether a subset of the numbers in nums add up to sum,
     * and prints them out.
     *
     * Running Time = O(2^n)
     */
    public static boolean printSubSetSum(int nums[], 
					 int targetSum) {
	return printSubSetSumHelper(nums,targetSum,0);
    }

    /*
     * Helper method for printSubSetSum
     *
     * Running Time = O(2^n)
     */
    public static boolean printSubSetSumHelper(int set[],
					       int target,
					       int index){
	if(set.length == index){
	    return false;
	} else if (target == 0){
	    return true;
	} else if (printSubSetSumHelper(set,target,index+1)){
	    return true;
	} else if (printSubSetSumHelper(set,target - set[index],index+1)) {
	    System.out.println(set[index]);
	    return true;
	}
	return false;
    }
    

    /*
     * Returns the number of different ways elements in nums can be
     * added together to equal sum.
     *
     * Running Time = O(2^n)
     */
    public static int countSubSetSumSolutions(int nums[], 
					      int targetSum) {
	return countSubSetSumSolnsHelper(nums,targetSum,0);
    }

    /*
     * Helper method for countSubSetSumSolutions
     * that counts the number of different ways elements in set can 
     * be added together to equal target
     *
     * Running Time = O(2^n)
     */
    public static int countSubSetSumSolnsHelper(int set[],
						int target,
						int index){
	if(set.length == index) {
	    if (target == 0) {
		return 1;
	    } else {
		return 0;
	    }
	} else {
	    return (countSubSetSumSolnsHelper(set,target,index+1))+ 
		(countSubSetSumSolnsHelper(set,target-set[index],index+1));	    
	}
    }
    
    /*
     * Testing code to demonstrate that each of the
     * recursive methods works properly.
     */
    public static void main(String args[]) {

	// test code for problem 1
	System.out.println("countCannonballs(3) = " + countCannonballs(3));
	System.out.println("countCannonballs(10) = " + countCannonballs(10));

	// test code for problem 2
	System.out.println("mom is a palindrome: " + isPalindrome("mom"));
	System.out.println("races is a palindrome: " + isPalindrome("races"));

	// test code for problem 3
	System.out.println("[]{(())} is balanced: " + isBalanced("[]{(())}"));
	System.out.println("[[} is balanced: " + isBalanced("[[}"));
	System.out.println("() is balanced: " + isBalanced("()"));

	// test code for problem 4
	System.out.println("subtrings of apple are: ");
	printSubstrings("apple");
	System.out.println("substrings of ABC are: ");
	printSubstrings("ABC");

	// test code for problem 5
	System.out.println("The binary representation of 4 is: ");
	printInBinary(4);
	System.out.println("");

	// test code for problem 6a
	System.out.println("A subset of {4,3,1,7,2} that gives a sum of 8 is: ");
	int[] set1 = new int[] {4,3,1,7,2};
	printSubSetSum(set1,8);

	// test code for problem 6b
	System.out.println("no. of subsets in {2,4,2,0} that gives a sum of 4 are: ");
	int[] set2 = new int[] {2,4,2,0};
	System.out.println(countSubSetSumSolutions(set2,4));
    }
}
