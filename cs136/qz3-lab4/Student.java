/*
 * Name: Qianwen (Tiffany) Zheng
 *Lab: CSCI 136 (Section 05) LAB 4
 *
 * This class represents a single student and stores the relevant information for each student 
 */

public class Student {

    // Information regarding the student
    String studName;
    String address;
    Long campPhone;
    int sUBox;
    Long celPhone;
    
    /*
     * Constructs a student object with the relevant information as parameters
     */
    public Student(String name,String addr,Long campNum,int sUNum,Long celNum) {
	studName = name;
	address = addr;
	campPhone = campNum;
	sUBox = sUNum;
	celPhone = celNum;
    }

    /*
     * Returns the name of the student
     */
    public String getName() {
	return studName;
    }

    /*
     * Returns the address of the student
     */
    public String getAddress() {
	return address;
    }

    /*
     * Returns the campus phone number of the student
     */
    public Long getCampPhone() {
	return campPhone;
    }

    /*
     * Returns the SU Box number of the student
     */
    public int getSUBox() {
	return sUBox;
    }

    /*
     * Returns the cell phone number of the student
     */
    public Long getCelPhone() {
	return celPhone;
    }
    
    /*
     * Prints out all the relevant information for a certain student
     */
    public String toString() {
	return (studName + "\n" + address + "\n" + Long.toString(campPhone)
		+ "\n" + Integer.toString(sUBox) + "\n" + Long.toString(celPhone));
    }

}
