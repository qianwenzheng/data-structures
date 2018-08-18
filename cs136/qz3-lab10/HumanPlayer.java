/*
 * Name: Qianwen (Tiffany) Zheng
 * Lab: CSCI 136 (Wednesday Afternoon - Jon) LAB 10
 *
 * This class represents a Player that allows the user to select moves through user input.
 */

import structure5.*;
import java.util.Random;
import java.util.Iterator;

public class HumanPlayer implements Player {

    char color; // the color that the player represents in the game
    String name; // the name of the player

    /**
     * Constructs the player
     * @param the color and name of the player
     */
    public HumanPlayer(char theColor, String theName){
	color = theColor;
	name = theName;
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
	System.out.println(name + "'s move:");

	// Provides the user with the available moves to make
	Vector<HexMove> moves = t.getHexBoard().moves(color);
	Iterator i = moves.iterator();
	int j = 0;
	while (i.hasNext())
	    {
		System.out.println(j+". "+i.next());
		j++;
	    }

	// Gets user input and makes chosen move
	System.out.println("Choose a move: ");
	ReadStream r = new ReadStream();
	int index = r.readInt();
	HexMove selected = moves.get(index);
	GameTree child = t.getChild(selected);
	HexBoard board = child.getHexBoard();
	return child;
    }
}
