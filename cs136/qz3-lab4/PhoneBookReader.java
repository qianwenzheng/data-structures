/*
 * Name: Qianwen (Tiffany) Zheng
 * Lab: CSCI 136 (Section 05) LAB 4
 *
 * This class is designed to read information from a phonebook
 * text file and perform operations to compare the data 
 * inside it. This class operates through the use of a 
 * MyVector of Student objects to represent entries in the 
 * phonebook file. Comparators are used to compare data from
 * the text input.  
 *
 */

import structure5.*;
import java.util.Comparator;
import java.util.Scanner;

public class PhoneBookReader {
    
    /*
     * Collects text input and performs comparison operations
     */
    public static void main (String args[]){
       
	//Constructs a MyVector of Students
	MyVector<Student> phoneBook = new MyVector<Student>();

	Scanner in = new Scanner (System.in);

	// Reads each entry, line by line, to create new
	// Students to be added to phoneBook
	while(in.hasNextLine()) {
	    String name = in.nextLine();
	    String addr = in.nextLine();

	    String nums = in.nextLine();
	    
	    String campNums = nums.substring
		(0,nums.indexOf(" "));
	    Long campPhone = Long.parseLong(campNums);
	    
	    String sUNums = nums.substring
		(nums.indexOf(" ")+1,
		 nums.indexOf(" ",nums.indexOf(" ")+1));
	    int sUBox = Integer.parseInt(sUNums);
	    
	    String celNums = nums.substring
		(nums.indexOf(" ", nums.indexOf(" ")+1)+1);
	    Long celPhone = Long.parseLong(celNums);
 
	    Student theStudent = new Student(name,addr,
					     campPhone,sUBox,
					     celPhone);
	    phoneBook.add(theStudent);

	    String separation = in.nextLine();
	}

	// Sorts students by name in alphabetical order
	NameComparator nameCompare = new NameComparator();
	phoneBook.sort(nameCompare);
	System.out.println("The first student in the alphabetical list is:");
	System.out.println(phoneBook.get(0));
	
	// Sorts students from smallest to biggest SU Box
	SUBoxComparator sUCompare = new SUBoxComparator();
	phoneBook.sort(sUCompare);
	System.out.println("The student with the smallest SU Box is:");
	System.out.println(phoneBook.get(0));
	System.out.println("The student with the largest SU Box is:");
	System.out.println(phoneBook.get(phoneBook.size()-1));

	// Sorts students according to the number of vowels in their name
	VowelComparator vowelCompare = new VowelComparator();
	phoneBook.sort(vowelCompare);
	System.out.println("The student with the most vowels in his name is:");
	System.out.println(phoneBook.get(0));

	// Constructs a MyVector of Associations of addresses and the number of students
	// who live at each address
        AddressAssociations studAddresses = new AddressAssociations();

	for (int i=0;i<phoneBook.size();i++){
	    Student s = phoneBook.get(i);
	    String addr = s.getAddress();

	    studAddresses.updateAddrList(addr);
	}

	// Sorts the MyVector of Associations by the most common addresses to the least
	AddressComparator addrCompare = new AddressComparator();
	studAddresses.sort(addrCompare);
	String commonAddress = studAddresses.get(0).getKey();
	System.out.println("The most common address is: " + commonAddress);
	
	// Prints out the names of students with the most common address
	System.out.println("The students with that address are:");
	for (int i=0;i<phoneBook.size();i++){
	    if (phoneBook.get(i).getAddress().equals(commonAddress)){
		System.out.println(phoneBook.get(i).getName());
	    }
	}
    }

}
