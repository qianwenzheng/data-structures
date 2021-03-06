/*
 * Name: Qianwen (Tiffany) Zheng & Steven Omondi
 * Lab: CSCI 136 (Thursday Afternoon - Jon) LAB 9
 *
 * This class represents a singleLine simulation implementation of the program. It contains a constructor
 * that creates a representation of customers in a single queue waiting to be served. 
 */
import java.util.Random;
import structure5.*;
import java.util.Vector;

public class SingleLine extends BusinessSimulation{
    
    //Variables to be referenced in the class
    int numCust;
    int maxEvStart;
    int seed;
    int numServPoints;
    Random rnd;

    /**
     * Construct a queue of customers and vector of tellers
     */
    public SingleLine(int numCustomers, int numServicePoints, int maxEventStart, int theSeed){

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
     * @pre the parameter must be an integer greater than 0 
     * @param the number of time steps advanced in the simulation
     * @post: The simulation is advanced by numStep time steps
     */ 
    public boolean step (int numSteps){
	for (int i = 0; i < numSteps-1; i++){
	    step();
	}
	return step();
    }
    
    /**
     * @post advance the simulation one timeStep
     */
    public boolean step(){
	//confirm that there are customers waiting and get the appropriate one based on their event time
	if (eventQueue.size() != 0 &&  time >= eventQueue.getFirst().getEventTime()){
	    //store a teller that is free
	    QueueVector<Customer> freeTeller = getTeller();
	    //put customer into teller queue
	    if(freeTeller != null){
		Customer c = eventQueue.remove();
		freeTeller.add(c);
		System.out.println("The waiting time for this customer is: " + (time-c.getEventTime()));
	    }
	    System.out.println(toString());
	}
	return stepHelper();
    }

    /**
     * @return: returns a teller "t" that is free
     */
    public QueueVector<Customer>getTeller(){
	//count number of free tellers
	int free = freeTellers();
	QueueVector<Customer> t = null;
	//if free tellers are found
	 if(free > 0){
	     int count = rnd.nextInt(free);
	     for(int i = 0; i < numServPoints; i++){
		 if (servicePoints.get(i).isEmpty()){
		     if(count == 0){
			 t = servicePoints.get(i);
		     }
		     count--;
		 }
	     }
	 }
	 return t;
    }
    /**
     * @return returns the  number of tellers that are free and return them
     */
    public int freeTellers(){
	int count = 0;
	QueueVector<Customer> teller;
	//increase count each time free teller is found
	for (int i = 0; i < numServPoints; i++){
	    if (servicePoints.get(i).isEmpty()){
		count++;
	    }
	}
	return count;
    }
}

