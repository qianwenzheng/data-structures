/*
 * Name: Qianwen (Tiffany) Zheng & Steven Omondi
 * Lab: CSCI 136 (Thursday Afternoon - Jon) LAB 9
 *
 * This class runs the simulation of the single line and multiple line mechanisms. For both of the
 * mechanisms, a sequence of customers is generated, they are put through the simulation and the
 * time is printed. 
 */

import java.util.Scanner;
import java.util.Random;
import structure5.*;

public class Main {
    
    public static void main(String args[]){
	Scanner in = new Scanner(System.in);
	System.out.println("Enter number of steps: ");
	int numSteps = in.nextInt();
	Assert.condition(numSteps > 10, "The number of steps must be greater than ten!");
       
	System.out.println("Enter number of customers: ");
	int customers = in.nextInt();
	System.out.println("Enter number of tellers: ");
	int servPoints = in.nextInt();
	int maxEvtStart = numSteps - BusinessSimulation.MAX_SERVICE_TIME;
	Random rnd = new Random();
	int seed = rnd.nextInt();

	//Generates the two mechanisms and calls their step functions 
	SingleLine single = new SingleLine(customers, servPoints, maxEvtStart, seed);
	MultipleLine multiple = new MultipleLine(customers, servPoints, maxEvtStart, seed);

	System.out.println("The seed is: " + seed);
	System.out.println("SINGLE LINE SIMULATION: \n");
	single.step(numSteps);
	System.out.println("------------------------------------------------------");
	
	System.out.println("MULTIPLE LINE SIMULATION: \n");
	multiple.step(numSteps);
	System.out.println("------------------------------------------------------");
    }
}
