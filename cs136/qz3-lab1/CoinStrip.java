import java.util.Scanner;
import java.util.Random;

/*
 * Name: Qianwen (Tiffany) Zheng
 * Lab: CSCI 136 (Section 05) LAB 1
 * Feb. 12, 2017
 */

/*
 * THOUGHT QUESTIONS:
 * 
 * 1) To pick game sizes in which the user has a lower probability of getting
 * a higher number of coins above three, then I would use a random number
 * generator that has a very high upper limit, for example 999, such that
 * there is a 50% chance of numbers being generated in the upper half (from 
 * 500 to 999) and if any of those numbers are generated then there would be
 *  three coins, there is a 25% chance of numbers being generated in between
 * the first quartile and the second quartile and if any number is generated 
 * from this range, then the number of coins would increase by one, there is a
 *  12.5% chance of numbers being generated in the upper half of the first
 *  quartile, and if any numbers are generated in that range, then the number of
 * coins would increase by two, and so on. The higher the upper limit, the more
 * exact probabilities could be achieved.This technique would not change my 
 * choice of using arrays, because the size of the array would remain fixed for
 * a single game (even though the number of coins depend on the randomly 
 * generated number.
 *
 * 2) To generate games that are not immediate wins, with a possibility of
 * n moves, then a two dimensional array could be used to represent the various 
 * number of ways in which users could move. The 2D array would use both the
 * different available (valid) coins that the user could pick, and the number of 
 * spaces to move that coin (out of what is valid). The program would have to 
 * calculate the number of valid moves (valid coins and spaces) with the current
 * layout of the board and then determine how the strip would be adjusted for each
 * of those valid moves. If any of those valid moves resulted in a win, then that 
 * would be a possible number of moves needed for a win. If not, then the program
 * would have to calculate the valid moves for the new layout of the board made 
 * with each previous move, and repeat until the game is won. The number of moves
 * would then be found. Thus, in order for one to generate a game with a 
 * possibility of n moves, then the program may have to check various randomly 
 * generated layouts until one with a possibility of n valid moves is found.
 * Perhaps a less complicated alternative is instead of working backwards, the 
 * program would take n possible moves, and generate possible layouts of the game
 * with a 2D array that would satisfy a win with n moves. 
 *
 * 3) If the computer wanted to provide good hints, there are multiple opportunities
 * that are easy to recognize. The most obvious one would be when the user only 
 * needs to move one coin once to make the winning move: an instance where this
 * could occur would be when all the coins are grouped next to each other on the
 * leftmost side of the strip, and there is one outlying coin at the far right
 * that needs to be moved. The computer can identify this instance in the same 
 * way that it checks if the game is over, only except for checking the first 
 * (numCoins) indices of the array, it would check if the first (numCoins-1) indices
 * of the array were not equal to zero (have coins inside) AND if the element of the
 * array at the numCoins(th) index was equal to zero, then there would be a gap 
 * between the rightwards coin and the rest. Therefore, the computer would hint
 * to move that coin the number of spaces needed to join with the others. Other
 * opportunities that are easy to recognize are if there are two outlying right
 * coins and all the rest are grouped together to the left, then a good hint to win
 * would be to move the one furthest to the right so that it is directly
 * right of the other outlying coin (assuming there is a space between them). This
 * way, the other player would have no other alternative but to move the other 
 * outlying coin closer to the left group. The hint would be to keep following every
 * move of the coin to the left such that they are always beside each other and the
 * other player cannot move it. Thus, it would guarantee a victory for the other 
 * since only he is able to make the last move.
 *
 * 4) For a method in which the computer plays to win, then the moves that the
 * computer makes will be based on those hints that it gave to the user (see
 * above). The integer values of the specific coin and the number of spaces to
 * move will be generated in the same way that the computer found opportune moves
 * and the move method would be called within the computerPlay method with the 
 * parameters being the values that the computer generates. The method would also
 * need to have a means to read the updated array after the user makes a move in
 * order to identify which coins and spaces are available. It would be ideal if 
 * the method could identify from the current layout of the board, the possible
 * moves that would result in a win, and adjust these possible moves according to
 * the subsequent moves made by the user. If the method cannot calculate a move 
 * that would result in its victory (eg. if there are none), then it would randomly
 * pick a valid coin and space from those available and make a move. 
 *
 * 5) If the coins were allowed to pass each other, I would have to change my
 * method of checking valid moves and coins since they were dependent on the 
 * coins being placed in order throughout the game. I set up my boolean for 
 * valid moves to return false if a coin attempted to move to a spot that had a 
 * lower index than the preceding coin (in terms of labelled number coin). Since 
 * the coins are able to pass each other, then that condition would not be used 
 * as a primary reference, rather the space that the user wants to move into will
 * be more significant (checking if it holds another coin). Since the coins are 
 * able to pass each other, putting the coins in order at the beginning of the 
 * game might also be unecessary if they are going to be shifted anyways (and it
 * would probably not be ideal to have coins change their labeled numbers since it
 * may confuse the user). Because of this, it would be perhaps be less confusing 
 * to label the coins in terms of the alphabet instead of numbers, because persons
 * reading the code may get confused with non-ordered numerical labels and indices
 * of the coin; therefore a character array may be an option.
 *
 */

/*
 * This class is designed to represent a strip of spaces in which coins 
 * are randomly placed. The strip will be represented as an integer array
 * of a random size. The program simulates a Silver Dollar Game in which
 * two users compete to move a certain number of coins to the leftmost
 * spaces on the display by moving one coin leftwards at a time. The 
 * player who makes the last move wins the game.  
 */

public class CoinStrip {

    private int[] strip;  //an array to store the integers in each space
    private static final int MIN_SPACES = 8;  //the minimum num. of spaces
    private static final int ADD_SPACES = 15; //the number of additional spaces
    private int numCoins;  //the number of coins generated in the array displayed
    
    /*
     * Generates a random board of coins and spaces
     */
    public CoinStrip() {

	Random rng = new Random();
	int numSpaces = rng.nextInt(ADD_SPACES) + MIN_SPACES;
	strip = new int[numSpaces];

	int coinNumLabel = 1; 
	int currentCoins = rng.nextInt(numSpaces/2) + 2;
	numCoins = currentCoins;

	//Randomly inserts coins into available positions in the strip
	while (currentCoins > 0) {
	    int coinPos = rng.nextInt(numSpaces-1);
	    if (strip[coinPos] == 0) {
		strip[coinPos] = 1;
		currentCoins--;
	    }
	}

	//Assigns an ordered number label to each coin
	for (int i = 0; i<strip.length; i++) {
	    if (strip[i] == 1) {
		strip[i] = coinNumLabel;
		coinNumLabel++;
	    }
	}
    }

    /*
     * Creates a CoinStrip object and controls the play of a single game
     */
    public static void main (String args[]) {

	CoinStrip theStrip = new CoinStrip();
	System.out.println(theStrip.toString());
	
	Scanner in = new Scanner(System.in);
	int playerNum = 1;
	
	while(!theStrip.win()) {

	System.out.println("Player " + playerNum + "'s " + "turn");

	System.out.print("Enter the number of the coin you want to move: ");
	int selectedCoin = in.nextInt();
	
	while (!theStrip.validCoin(selectedCoin)){
	    System.out.print("Not a valid coin! Enter the number of another coin:");
	    selectedCoin = in.nextInt();
	}
	
	System.out.print("Enter the number of spaces you want to move: ");
	int numMoves =in.nextInt();

	while (!theStrip.validMove(selectedCoin, numMoves)) {
		System.out.print("Not a valid move! Enter another number of spaces: ");
		numMoves = in.nextInt();
	    }

	// Lets the user move a valid coin a permitted number of spaces 
	theStrip.move(selectedCoin, numMoves);

	// Alternates players after moves if game is not over
	if (!theStrip.win()) {
	if (playerNum == 1) {
	    playerNum++;
	} else {
	    playerNum--;
	}
	    }
	}

	System.out.println("CONGRATS PLAYER " + playerNum + "! YOU WON!"); 
    }

   /*
    * Returns the coin strip array represented as a readable string
    */
   public String toString() {

      String output = "";

        for (int i=0; i<strip.length; i++){
         	if (strip[i] == 0) {
	            output = output + "[ ]";
                } else {
	            output = output + "[" + Integer.toString(strip[i]) + "]";
                       }
             }
       return output;
  }

  /*
   * Moves a specific coin a certain number of spaces specified by user
   * and prints the adjusted array as a string
   */
   public void move(int coin, int spaces) {
    
        int prevCoinPos = indexOf(coin);
        int newCoinPos = indexOf(coin) - spaces;

        int temp = strip[newCoinPos];
        strip[newCoinPos] = strip[prevCoinPos];
        strip[prevCoinPos] = temp;
    
        System.out.println(toString());
           }

    /*
     * Returns true if the user picks a legitimate coin that can be moved.
     * The coin parameter is the labeled number of the chosen coin.
     */
    public boolean validCoin(int coin) {
	return indexOf(coin) > 0 &&
	    ((indexOf(coin-1) < indexOf(coin) - 1) || coin==1);
	    }

    /*
     * Returns true if the user picks a permittable number of spaces to move. 
     * The parameters refer to the labelled number of the chosen coin and the
     *  number of spaces to move.
     */
    public boolean validMove (int coinLabel, int spaces) {
	return (spaces>0) && (indexOf(coinLabel)-spaces > -1) &&
	    (coinLabel == 1 || ((indexOf(coinLabel) - spaces) > indexOf(coinLabel-1)));
	    }

    /*
     * Returns true if the winning move is made
     */
    public boolean win(){
	for (int i = 0; i <numCoins; i++) {
	    if (strip[i] == 0) {
		return false;
	    }
	}
	return true;
    }

    /*
     * Takes in an integer representing the chosen coin and returns the index
     * of that coin within the array.
     */
    public int indexOf(int selected) {
	for (int i=0; i<strip.length; i++){
	    if(strip[i] == selected) {
		return i;
	    }
	}
	return -1;
    }
}
