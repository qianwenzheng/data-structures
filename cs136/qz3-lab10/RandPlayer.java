/*                                                                                                                             
 * Name: Qianwen (Tiffany) Zheng                                                                               
 * Lab: CSCI 136 (Wednesday Afternoon - Jon) LAB 10                                                                             
 *                          
 * This class represents a Player that randomly selects moves to make.
 */

import structure5.*;
import java.util.Random;

public class RandPlayer implements Player {

    char color;  // the color that the player represents in the game
    Random rnd;  // random number generator
    String name;  // the name of the player

    /**                                                                                                      
     * Constructs the player                                                                                 
     * @param the color and name of the player                                                               
     */
    public RandPlayer(char theColor, String theName){
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

	if(node.getHexBoard().win(HexBoard.opponent(color))){
	    System.out.println(node.getHexBoard());
	    System.out.println(name + " loses!");
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
	System.out.println(t.getHexBoard());
	System.out.println(name + " moved:");

	// Executes a random move 
	Vector<HexMove> moves = t.getHexBoard().moves(color);
	int index = rnd.nextInt(moves.size());
	HexMove selected = moves.get(index);
	GameTree child = t.getChild(selected);
	HexBoard board = child.getHexBoard();
	return child;
    }
}
