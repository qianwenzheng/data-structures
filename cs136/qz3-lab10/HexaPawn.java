/**
 * 2 points for design doc
 */

/*                                                                                                                     
 * Name: Qianwen (Tiffany) Zheng
 * Lab: CSCI 136 (Wednesday Afternoon - Jon) LAB 10                                                                          
 *   
 * This class runs the simulation of the Hex-a-Pawn game given a certain number of rows and columns and two player types. 
 *                                                                                                                      
 */

/*
 * THOUGHT QUESTIONS:
 *
 * 1) There are 6003 board positions in the 3x4 board. There are 215150 board positions in the 3x5 board. This is found by
 * printing the static variable "size" in the GameTree class. 
 * 
 * 2) If you pit the two machines against each other, the second player, HER, will win more frequently in a 3x3 board. For
 * larger boards: HER wins more frequently in the 3x4 board and 3x5 board. HIM wins more frequently in the 4x4 board, when the game is
 * played 3000 times. 
 *  
 * This is because after the two computers learn and prune the losing moves from the GameTree, they would both essentially prune moves that
 * lead to wins for the other player. At the end of the pruning, the first player will automatically lose because it is unable to
 * make any winning moves. There will only be the root node remaining and because the first player therefore has no moves, it loses the
 * game. All the other levels get removed essentially because the pruning removes the grandparent of the node that represents a loss, 
 * unless it is the root node. So, if both players prune out losing moves, the only grandparent that would be left is the root. This only
 * works if the initial tree has an even number of levels, so that a child of the root would eventually be the grandparent of a leaf
 * node, and thus able to be removed. If the tree has an odd number of levels, the end tree after all the pruning will not have only the
 * root node, but have two levels. In a tree with an odd number of levels, the children of the root will never be a grandparent of a leaf
 * node. Their children, however, will be and are removed. Thus, the children of the root will be left with no children after pruning. This
 * explains the case of larger boards which may not have an initial tree with even number of levels. Therefore, their root node will have
 * several children after pruning. Since the first player moves first, it will automatically choose a winning move, leaving the second with
 * no moves. Therefore, the first wins more frequently in cases like the 4x4 board. 
 *
 */

import structure5.*;
import java.util.Scanner;

public class HexaPawn {

    public static void main(String args[]){

	// Retrieves the number of rows and columns from user input
	Scanner in  = new Scanner(System.in);
	int rows = Integer.parseInt(args[0]);
	int cols = Integer.parseInt(args[1]);

	// Creates new GameTree object 
	HexBoard board = new HexBoard(rows,cols);
	GameTree t = new GameTree(null, null, board, HexBoard.WHITE);
	
	String first = args[2];
	String second = args[3];
	Player player1;
	Player player2;

	// Determines the types of players from user input
	
	switch(first) {
	case "human":
	    System.out.println("Enter player 1's name: ");
	    String name1 = in.nextLine();
	    player1 = new HumanPlayer(HexBoard.WHITE, name1);
	    break;
	case "comp":
	    player1 = new CompPlayer(HexBoard.WHITE, "Player 1");
	    break;
	case "random":
	    player1 = new RandPlayer(HexBoard.WHITE, "Player 1");
	    break;
	default:
	    player1 = null;
	    break;
	}

	switch(second) {
	case "human":
	    System.out.println("Enter player 2's name: ");
	    String name2 = in.nextLine();
	    player2 = new HumanPlayer(HexBoard.BLACK, name2);
	    break;
	case "comp":
	    player2 = new CompPlayer(HexBoard.BLACK, "Player 2");
	    break;
	case "random":
	    player2 = new RandPlayer(HexBoard.BLACK, "Player 2");
	    break;
	default:
	    player2 = null;
	    break;
	}

	System.out.println("Size of game tree is: " + t.size);
	
	// Executes the play methods of the two players
	player1.play(t,player2);
    }
}
