/*
 * Name: Qianwen (Tiffany) Zheng & Steven Omondi
 * Lab: CSCI 136 (Thursday Afternoon - Jon) LAB 9
 *
 * This class represents a multiple line simulation implementation of the program. It contains a constructor that creates a 
 * representation of customers in a multiple line queue waiting to be served.
 */
import java.util.Random;
import structure5.*;
import java.util.Vector;
import java.util.Scanner;

public class MultipleLine extends BusinessSimulation {

    // Variables to be referenced in the class
    int numCust; 
    int maxEvStart;
    int seed;
    int numServPoints;
    Random rnd;

    /**
     * Constructs a queue of customers and vector of tellers
     */
    public MultipleLine (int numCustomers, int numServicePoints, int maxEventStart, int theSeed){
	super(numCustomers,numServicePoints,maxEventStart,theSeed);
	
	rnd = new Random();
	rnd.setSeed(theSeed);
	
	numCust = numCustomers;
	maxEvStart = maxEventStart;
	seed = theSeed;
	numServPoints = numServicePoints;

	eventQueue = generateCustomerSequence(numCust,maxEvStart,seed);
    }

    /**
     * @pre the parameter must an integer greater than zero
     * @param the number of time steps advanced in the simulation
     * @post the simulation is advanced by numSteps time steps and returns true if the simulation is over
     */
    public boolean step(int numSteps){
	for(int i = 0; i < numSteps-1; i++){
	    step();
	}
	return step();
    }

    /**
     * @post the simulation is advanced by one time step and returns true if the simulation is over
     */
    public boolean step(){
	
	// Places the customer in the shortest teller queue
	if (eventQueue.size() != 0 && time >= eventQueue.getFirst().getEventTime()){
	    QueueVector<Customer> shortLine = getShortest();
	    if (shortLine != null){
		Customer c = eventQueue.remove();
		System.out.println("The waiting time for this customer is: " + (time-c.getEventTime()));
		shortLine.add(c);
	    }

	    // Prints out the string representation of the simulation
	    System.out.println(toString());
	}
	return stepHelper();
    }
    
    /**
     * @pre the parameter must be an integer 
     * @param the size of the shortest teller queue
     * @post returns the number of shortest teller queues of the same size 
     */
    public int numShortest(int size){
        int count = 0;
	for (int i = 0; i < numServPoints; i++){
	    if (servicePoints.get(i).size() == size){
		count++;
	    }
	}
	return count;
    }
    
    /**
     * @post returns the shortest teller queue if there is one, and a randomly selected one if there are multiple 
     * shortest queues
     */
    public QueueVector<Customer> getShortest(){
	
	//Gets the shortest teller queue
	QueueVector<Customer> shortest = servicePoints.get(0);
	for (int i = 1; i < numServPoints; i++){
	    if (servicePoints.get(i).size() <= shortest.size()){
		shortest = servicePoints.get(i);
	    }
	}

	// If there are multiple shortest teller queues, randomly selects one to return
	int numShorts = numShortest(shortest.size());
	if(numShorts >1){
	    int count = rnd.nextInt(numShorts);
	    for (int i = 0; i < numServPoints; i++){
		if (servicePoints.get(i).size() == shortest.size()){
		    if (count == 0){
			shortest = servicePoints.get(i);
			break;
		    }
		    count--;

		}
	    }
	}
	return shortest;
    }
}



