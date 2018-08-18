/*
 * Name: Qianwen (Tiffany) Zheng
 * Lab: CSCI 136 (Section 05) LAB 4
 *
 * This class is designed to store a MyVector of Associations of student addresses and the number 
 * of students living at each address
 */

import structure5.*;

public class AddressAssociations extends MyVector<Association<String,Integer>> {

    /*
     * Constructs a MyVector of Associations
     */
    public AddressAssociations() {
	super();
    }

    /*
     * Updates addrList by updating the number of students living at the specific address specified
     * by the parameter or adds a new Association to the MyVector if that address does not yet exist
     */
    public void updateAddrList(String addr) {
	if (!addr.equals("UNKNOWN")){
	    Association<String,Integer> selected = getAssociation(addr);
	    if (selected != null) {
		Integer f = (Integer)selected.getValue();
		selected.setValue(new Integer(f.intValue() + 1));
	    } else {
		add(new Association<String,Integer>(addr, 1));
	    }
    }
    }

    /*
     * Retrieves an association in the MyVector addrList given an address
     */
    public Association<String,Integer> getAssociation(String addr) {
	for (int index=0; index < size(); index++) {
	    Association<String,Integer> a  = (Association<String,Integer>)get(index);
	    String address = (String)a.getKey();
	    if (address.equals(addr)) {
		return a;
	    }
	}
	return null;
    }
    
}
