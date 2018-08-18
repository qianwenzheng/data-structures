/*                                                                                                                          
 * Name: Qianwen (Tiffany) Zheng                                                                            
 * Lab: CSCI 136 (Wednesday Afternoon - Jon) LAB 10                                                                          
 *   
 * This class represents a GameTree object that generates a tree of all the boards reachable from the specified board position
 * during normal game play. Alternate levels of the tree represent boards that are considered by alternate players.   
 */

import structure5.*;
import java.util.Iterator;

class GameTree {

    HexBoard board;  // the board representing the state of the game
    Vector<GameTree> children;  // vector storing children of the GameTree node
    char color;  // the color that the player represents in the game
    Vector<HexMove> moves;  // vector of available moves from a given HexBoard
    GameTree parent;  // the parent node
    HexMove move;  // the move that brought about the GameTree node
    static int size = 1;  // the number of board positions in the tree, starting from the root
    
    /**
     * Constructs a GameTree by calling the generateTree method
     * @param the parent GameTree, the move that led to the current GameTree, the associated board, the associated color
     */ 
    public GameTree(GameTree theParent, HexMove theMove, HexBoard theBoard, char theColor){

	children = new Vector<GameTree>();
	board = theBoard;
	color = theColor;
	parent = theParent;
	move = theMove;
	moves = board.moves(color);

	generateTree();
    }

    /**
     * @pre the parameterized node must be valid
     * @param the root node of the tree
     * @post fills up the tree with children GameTree objects starting with the root
     */
    public void generateTree(){
	HexBoard parentBoard = this.getHexBoard();
	char parentColor = this.getColor();

	// Checks if it is a leaf node
	if (parentBoard.moves(parentColor).size() == 0  || parentBoard.win(HexBoard.opponent(parentColor))) {
	    return;
	} else {

	    // Creates children for each move at a particular level of the tree
	    Vector<HexMove> theMoves = parentBoard.moves(parentColor);
	    for (int i = 0; i < theMoves.size(); i++){
		HexBoard b = new HexBoard(parentBoard, theMoves.get(i));
		size++;
		GameTree child = new GameTree(this,theMoves.get(i),b,HexBoard.opponent(parentColor));
		this.addChild(child);
	    }
	}
    }

    /**
     * @post returns the parent node of the GameTree
     */
    public GameTree getParent(){
	return parent;
    }

    /**
     * @param the child node to be added to the parent GameTree
     * @pre the child must be a valid GameTree
     * @post adds a child node to the children vector associated with the  GameTree
     */
    public void addChild(GameTree child){
	children.add(child);
    }

    /**
     * @param the HexMove associated with the child
     * @pre m must be a valid HexMove
     * @post returns the child which is as a result of move m
     */
    public GameTree getChild(HexMove m){
	int index = moves.indexOf(m);
	return children.get(index);
    }

    /**
     * @post returns the HexBoard object associated with the GameTree
     */
    public HexBoard getHexBoard(){
	return board;
    }

    /**
     * @post returns the color associated with the GameTree
     */
    public char getColor(){
	return color;
    }

    /**
     * @post removes the node from the tree
     */
    public void removeSelf(){
	GameTree parent = getParent();
	parent.removeChild(this);
	
    }

    /**
     * @param the child node to be removed
     * @pre the child must be a valid node
     * @post removes the child from the child vector
     */
    public void removeChild(GameTree child){
	if (children.size() > 0){
	    int index = children.indexOf(child);
	    children.remove(child);
	    moves.remove(moves.get(index));
	}
    }
    
    /**
     * @post returns the vector of valid moves associated with the GameTree
     */
    public Vector<HexMove> getMoves(){
	return moves;
    }

    /**
     * @post returns the vector of children GameTree nodes
     */
    public Vector<GameTree> getChildren(){
	return children;
    }
    
    /**
     * @param the current GameTree object
     * @pre the node must be non-null
     * @post returns the number of board positions
     */
    
    public int numPositions(GameTree node){
	int size = 0;
	Vector<GameTree> theChildren = node.getChildren();
	size = size + theChildren.size();
	for (int i = 0; i < theChildren.size(); i++){
	    size = size + numPositions(theChildren.get(i));
	}
	return size;
    }
    
}
