/*
 * Name: Qianwen (Tiffany) Zheng & Steven Omondi
 * Lab: CSCI 136 (Thursday Afternoon - Jon) LAB 9
 *
 * This class represents a data structure to store LexiconNodes that allows us to keep track of and manipulate strings.
 */

import structure5.*;

public class Customer implements Comparable<Customer> {

    int evTime;
    int servTime;

    // TODO: fill this class in, adding member variables and
    // methods as needed

    /**
     * Creates a Customer that arrives at time step @eventTime and
     * requires @serviceTime time steps to be satisfied after
     * arriving at a service point.
     */
    public Customer(int eventTime, int serviceTime) {
	evTime = eventTime;
	servTime = serviceTime;
    }
    /**
     * Compares Customers by arrival time
     */
    public int compareTo(Customer other) {
	//possible source of error
	return evTime - other.getEventTime();
    }

    public String toString() {
	return "Event time: " + evTime + "Service time: " + servTime;
    }

    public int getEventTime(){
	return evTime;
    }

    public int getServiceTime(){
	return servTime;
    }
}
