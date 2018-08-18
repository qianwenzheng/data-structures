/*
 * Name: Qianwen (Tiffany) Zheng & Steven Omondi
 * Lab: CSCI 136 (Thursday Afternoon - Jon) LAB 9
 *
 * This abstract class aids in the creation of the single line and multiple line simulations. It includes methods to create
 * a customer sequence, print out the components of the simulations, and step functions to control what happens in the 
 * simulations for every time step.  
 */

/*
 * THOUGHT QUESTIONS:
 * 
 * 1) In general, the multiple line mechanism seems to process all the customers fastest. There are instances, for example,
 * when there are 40 time steps, 30 customers and 10 tellers, and a seed of 5, both mechanisms took 33 time steps to complete
 * the simulation. There has not been an instance encountered in which the single line queue took a faster time than a
 * multiple line one. In the instance where there are 20 steps, 5 customers and 2 tellers, with a seed of 986410022, the 
 * single line took 12 time steps and the multiple line took 9 time steps. In the instance where there are 25 steps, 20 
 * customers, 5 tellers, with a seed of -1367962149, the single line took 24 time steps and the multiple line took 19 time
 * steps. For every instance, the minimum service time is 1 time step and the maximum service time is 10 time steps. It is
 * reasonable to conclude that the multiple line mechanism is faster than the single line. 
 *
 * 2) For 15 time steps, 5 customers, 2 tellers and a seed of -1208954502, the single line simulation had an average wait time of
 * 3.4 time steps while the multiple line simulation had an average wait time of 1. Therefore, the multiple line mechanism has a 
 * shorter average waiting time for customers than the single line. 
 * 
 * 3) If customers were able to move from one end of a line to another until the lines are fairly even, we would not use queues 
 * to store the customers because it is difficult to retrieve the last customer in a queue (who is at the end of the line). Since
 * queues only allow us to get the customer at the head of the line, they would be an unhelpful data structure for this purpuse. 
 * Rather, we would store the customers in a doubly linked list that would allow us to access the customers at the end of the 
 * lines. 
 * 
 * 4) For the single queue simulation, having different lines dedicated to serving customers of varying lengths of service times
 * would not improve the average wait time for a customer because in this mechanism, only one customer can be accessed from the
 * main customer queue at a time. Therefore, each teller line can only have one customer at a time, dependent on their arrival
 * time. Customers with short service times cannot be helped by tellers dedicated to longer service times, even if they are at
 * the head of the queue waiting to be served. This is inefficient. 
 *
 * In the multiple line simulation, it would be advantageous to have lines dedicated to varying length of service times only if
 * customers were able to join lines that do not correspond to their service time if those lines are empty and there are no more
 * corresponding customers to join that line. In this case, average wait time would be improved because no tellers are left
 * empty inefficiently. In the case that the lines are not able to serve customers that do not correspond to their dedicated 
 * service time, this would be inefficient because there could be empty tellers with no more corresponding customers yet there 
 * are other customers waiting to be served at corresponding tellers. Therefore, it would not improve average waiting time in 
 * this case.   
 */
import java.util.Vector;
import java.util.Random;
import structure5.*;

public abstract class BusinessSimulation {

    /* sequence of customers, prioritized by randomly-generated event time */
    PriorityVector<Customer> eventQueue; 

    /* series of service points where customers queue and are served */
    Vector<QueueVector<Customer>> servicePoints;

    /* current time step in the simulation */
    protected int time;

    /* seed for Random() so that simulation is repeatable */
    int seed;

    /* Used to bound the range of service times that Customers require */
    static final int MIN_SERVICE_TIME = 1;
    static final int MAX_SERVICE_TIME = 10;

    /**
     * Creates a BusinessSimulation.
     * @post Creates the vector of tellers and the queue of customers so that the step() function may be called.
     * @numCustomers number of random customers that appear in the simulation
     * @numSerivicePoints number of tellers in this simulation
     * @maxEventStart latest timeStep that a Customer may appear in the simulation
     * @seed used to seed a Random() so that simulation is repeatable.
     */
    public BusinessSimulation(int numCustomers, int numServicePoints,
			      int maxEventStart, int theSeed) {
	
	servicePoints = new Vector<QueueVector<Customer>>(numServicePoints);
	for (int i = 0; i < numServicePoints; i ++){
	    servicePoints.add(new QueueVector<Customer>());
	}
       
	eventQueue = new PriorityVector<Customer>();
    }

    /**
     * Generates a sequence of Customer objects, stored in a PriorityVector.
     * Customer priority is determined by arrival time
     * @arg numCustomers number of customers to simulate
     * @arg maxEventStart maximum timeStep that a customer arrives
     *      in @eventQueue
     * @arg seed to make customer sequence repeatable
     * @return A PriorityVector of Customers that represents Customer arrivals,
     *         ordered by Customer arrival time
     */
    public PriorityVector<Customer> generateCustomerSequence(int numCustomers,
							     int maxEventStart,
							     int seed) {
	Random rnd = new Random();
	rnd.setSeed(seed);

	//Generates random arrival and service times for each Customer object in the customer queue
	for (int i = 0; i < numCustomers; i++){
	    Customer c = new Customer(rnd.nextInt(maxEventStart), MIN_SERVICE_TIME + rnd.nextInt(MAX_SERVICE_TIME));
	    eventQueue.add(c);
	}
	return eventQueue;
    }

    /**
     * Step method to control what happens in one time step
     * @post returns true if the simulation is over, false otherwise
     */
    abstract public boolean step();

    /**
     * Step method to control what happens in timeSteps time steps
     * @post returns true if the simulation is over, false otherwise
     */
    abstract public boolean step(int timeSteps);

    /**
     * Helper method for step function with a certain number of time steps
     * @post the simulation has advanced @timeSteps time steps
     * @return true if the simulation is over, false otherwise
     */
     public boolean stepHelper(int timeSteps){
	 for (int i = 0; i < timeSteps; i++){
	     stepHelper();
	 }
	return stepHelper();
    }
    
    /**
     * Helper method for step function for one time step
     * @post the simulation has advanced 1 time step
     * @return true if the simulation is over, false otherwise
     */
    public boolean stepHelper(){

	// At every step, increments the time and removes any customer who is finished
	removeCust();
	time++;
	
	// Returns false if the simulation is not over
	if (!eventQueue.isEmpty()){
	    for (int i = 0; i < servicePoints.size(); i++){
		if (!servicePoints.get(i).isEmpty()){
		    return false;
		}
	    }
	}
	return true;
    }

    /**
     * Removes a customer from a teller line if he is finished
     */
     public void removeCust(){
	for (int i = 0; i < servicePoints.size(); i++){
	    if (!servicePoints.get(i).isEmpty()){
	    Customer cust = servicePoints.get(i).getFirst();
	    if (time == (cust.getEventTime() + cust.getServiceTime())){
		    servicePoints.get(i).remove();
	    }
	    } 
	}
     }
    
    /**
     * @return a string representation of the simulation
     */
    public String toString() {
	String str = "Time: " + time + "\n";
	str = str + "Event Queue: ";
	if (eventQueue != null) {
	    str = str + eventQueue.toString();
	}
	str = str + "\n";
	
	if (servicePoints != null)  {
	    for (Queue<Customer> sp : servicePoints) {
		str = str + "Service Point: " + sp.toString() + "\n";
	    }
	}
	
	return str;
    }
}
