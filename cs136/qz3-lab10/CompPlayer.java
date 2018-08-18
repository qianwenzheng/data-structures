/*                                                                                             
 * Name: Qianwen (Tiffany) Zheng
 * Lab: CSCI 136 (Wednesday Afternoon - Jon) LAB 10                                                                          
 * 
 * This class represents a Player that modifies the GameTree to remove losing moves. 
 */

import structure5.*;
import java.util.Random;

public class CompPlayer implements Player {

    char color;  // the color that the player represents in the game
    Random rnd;  // random number generator
    String name; // the name of the player
    GameTree currentNode;  // the node the player is currently at

    /**                                                                                                      
     * Constructs the player                                                                                 
     * @param the color and name of the player                                                               
     */
    public CompPlayer(char theColor, String theName){
	color = theColor;
	name = theName;
	rnd = new Random();
    }

    /**                                                                                                      
     * @param the current GameTree object, and the rival player                                              
     * @pre the node is a non-null game tree node, opponent is the next player                               
     * @post game is played from this node on, and winning player is returned                                
     */
    public Player play(GameTree node, Player opponent){

	// Checks for the player's loss
	if(node.getHexBoard().win(HexBoard.opponent(color))){
	    System.out.println(node.getHexBoard());
	    System.out.println(name + " loses!");
	    
	    if (currentNode.getParent() != null) {
		currentNode.removeSelf();
	    }
	    return opponent;
	}
	return opponent.play(advance(node),this);
    }

    /**                                                                                                      
     * @param the current GameTree object                                                                    
     * @pre the GameTree must be non-null                                                                    
     * @post advances the game by one move and returns the resulting GameTree after the move                 
     */
    
    public GameTree advance(GameTree t){
	
	HexBoard board = t.getHexBoard();
	System.out.println(board);
	System.out.println(name + " moved:");

	// Executes a random move from the current set of non-losing moves
	Vector<HexMove> winningMoves = t.getMoves();
	int index = rnd.nextInt(winningMoves.size());
	HexMove selected = winningMoves.get(index);
	GameTree child = t.getChild(selected);
	currentNode = child;
	return child;
    }
}
