import structure5.*;

/*                                                                                 
 * Name: Qianwen (Tiffany) Zheng                                                      
 * Lab: CSCI 136 (Section 05) LAB 7                                                  
 *                                                                                    
 * This class is designed to solve a puzzle in which n unique cubic blocks whose areas are 1, 2, ... n centimeters must be stacked in 
 * two towers such that the height of the towers are as close to each other as possible. This program prints the best solution to this
 * puzzle, displaying the areas of the faces of the blocks in each tower, the height of one of the towers and the difference in height
 * between the two towers. 
 */

public class TwoTowers {
    public static void main(String args[]){

	// Reads from standard input to get the number of blocks specified
	int numBlocks = Integer.parseInt(args[0]);  // the number of blocks to be used
	Assert.condition(numBlocks < 33, "The number of blocks cannot be greater than 32!");
	
	double targetHeight = totalHeight(numBlocks)/2;   // the target height of one tower (half of the total height)
	double currentMax = 0;  // the current maximum height closest but not greater than the target height of all the subsets computed so far
	int tallestSubset = 0;  // the subset with the height closest but not greater than the target height

	// Finds all possible subsets of the number of blocks and computes their total heights
	for (int num=1; num < (1 << numBlocks); num++){
	    int m = num;
	    double height = 0;
	    int i = 0;
	    while (m!=0){
		int b = m&1;
		if (b==1){
		    height = height + Math.sqrt(i);
		}
		i = i+1;
		m = m>>1;
	    }

	    // Finds the tallest subset which has the  height closest but not greater than the target height
	    if ((height > currentMax) && (height <= targetHeight)){
		currentMax = height;
		tallestSubset = num;
	    }
	}

	// Prints the areas of the faces of the blocks in the two towers in the best solution
	int i = 1;
	String tower1 = new String("Areas of the faces of the blocks in Tower 1 are:");
	String tower2 = new String("Areas of the faces of the blocks in Tower 2 are:");
	while (tallestSubset!=0){
	    int b = tallestSubset&1;
	    if (b==1){
		tower1 = tower1 + "  " + i;
	    } else {
		tower2 = tower2 + "  " + i;
	    }
	    i = i+1;
	    tallestSubset = tallestSubset >> 1;
	}
	System.out.println(tower1);
	System.out.println(tower2);

	// Prints the height of the first tower, and the difference in height between the two towers
	double diffHeight = (totalHeight(numBlocks) - currentMax) - currentMax;
	System.out.println("Height of Tower 1 is " + currentMax);
	System.out.println("The difference in the height between the two towers is " + diffHeight);
    }

    /**
     * @param the integer value specifying the total number of blocks
     * @pre the total number of blocks should be less than or equal to 32
     * @post sums the heights of each block and returns the total height 
     */
    public static double totalHeight(int numBlocks){
	if (numBlocks == 0) {
	    return 0;
	} else {
	    return Math.sqrt(numBlocks) + totalHeight(numBlocks-1);
	}
    }
    
}
