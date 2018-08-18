import structure5.*;
import java.util.Iterator;
/*                                                                                 
 * Name: Qianwen (Tiffany) Zheng                                                      
 * Lab: CSCI 136 (Section 05) LAB 5                                                  
 *                                                                                    
 * This class is designed to read standard input and perform math operators in PostScript. This is achieved by reading input as Token 
 * objects which are then analysed and can be placed on a stack, which helps in execution of operations. This program simulates the 
 * behavior of a small subset of PostScript. 
 *
 * Problems:
 * 10.3: In order to fill a stack with a copy of another while maintaining the order of the elements, you would do this by creating 
 * another stack in addition to the stack you wish to fill. You would pop each element from the original stack, and then push it into 
 * the additional stack you created. When you are finished popping all the elements from the original stack until it is empty and pushing
 * them into the additional stack, you will them begin to fill the stack you wanted to fill. You will do this by popping the elements 
 * from the additional stack you created. Each element popped from the additional stack will be pushed into the stack you wish to fill
 * until the additional stack is empty. This will maintain the order of the elements in the original stack. You need an additional stack
 * because stacks follow the LIFO (Last in, first out) principle, so the last element entered in the original stack would be the first
 * entered in the additional stack, reversing the order. In order to maintain the order, putting the elements from the additional stack
 * into the one you want to fill restores the original order. This would leave the original copy empty, but if you wish to preserve the
 * elements inside it then you could first make a temporary copy from the original stack, thus manipulating the temporary copy instead
 * of the original stack.  
 * 
 * 10.4: If you wish to reverse the order of elements of a stack, you would want to create two additional stacks. You would pop the 
 * elements from the original stack and push them into the first additional stack one at a time. When that is done and the original
 * stack is empty, the first additional stack would have the elements in reverse order. You would create another additional stack and
 * pop the elements from the first additional stack and push them into the second additional stack. When this is done and the first 
 * additional stack is empty, the elements in the second stack would be in the original order. You would then pop the elements from this
 * second additional stack and push them into the empty original stack. When this is done, the elements in the original stack would be 
 * in reverse order. 
 * 
 * 10.5: If you wish to copy a queue into another, assuming that you can only use queue operations and no iterators, you would need to
 * first make a temporary queue that is equal to the original queue so that you do not have to modify the original. Since queues follow
 * the FIFO (first in first out) principle, you would use dequeue to remove the element at the head of the temp. queue, and use enqueue
 * to add it to the queue you wish to fill. You would do this for every element until the temp. queue is empty. The queue you filled 
 * would have the elements in the original order.  
 */

public class Interpreter {

    private StackList<Token> stack = new StackList<Token>(); // the stack of tokens to be manipulated and printed to the console
    private SymbolTable table = new SymbolTable();  // object that keeps track of String-Token associations for defining symbols

    /**
     * Constructs an Interpreter object and parses the postscript program in the command line
     */
    public static void main (String args[]) {
	Interpreter inter = new Interpreter();
	Reader r = new Reader();
	inter.interpret(r);
    }

    /**
     * @param Reader object that allows you to read Tokens from an input stream
     * Takes the PostScript Tokens returned by the reader and processes them
     */
    public void interpret(Reader r) {

	// Processes tokens depending on their type as long as the reader keeps returning them
	while (r.hasNext()){
	    Token t = (Token)r.next();
	    if (t.isNumber()){
		stack.push(t);
	    }else if (t.isBoolean()){
		stack.push(t);
	    }else if (t.isSymbol()){
		String tString = t.toString();
		if(tString.equals("quit")){
		    break;
		}

		// Executes different operations depending on the specific symbol of the token
		switch(tString) {
		case "add":
		    add();
		    break;
		case "sub":
		    sub();
		    break;
		case "mul":
		    mul();
		    break;
		case "div":
		    div();
		    break;
		case "dup":
		    dup();
		    break;
		case "exch":
		    exch();
		    break;
		case "eq":
		    eq();
		    break;
		case "ne":
		    ne();
		    break;
		case "def":
		    def();
		    break;
		case "ptable":
		    pTable();
		    break;
		case "pop":
		    stack.pop();
		    break;
		case "pstack":
		    pstack();
		    break;
		case "lt":
		    lt();
		    break;
		case "if":
		    theIf();
		    break;
		default:

		    // Pushes symbol token into the stack if it is to be defined
		    if (tString.startsWith("/")){
			Token token = new Token(tString.substring(1));
			stack.push(token);
		    }

		    // If the symbol of the token is already defined in the symbol table, execute the procedure if its associated token is
		    // a procedure and push its associated token into the stack if otherwise
		    else if(table.contains(tString)){
			Token token = table.get(tString);
			if (token.isProcedure()){
			    List<Token> list = token.getProcedure();
			    Reader listR = new Reader(list);
			    interpret(listR);
			} else {
			    stack.push(token);
			}
		    } else {
			Assert.fail("Error: Symbol " + tString + " is not defined.");
		    }
		}
	    } else if (t.isProcedure()){
		stack.push(t);
	    }
	}
    }


    /**
     * @pre at least two tokens must be in the stack and the top two must be numbers
     * @post pops off the top two tokens in the stack, adds their numbers, and pushes a token representing their sum into the stack
     */
    public void add() {
	Assert.condition(stack.size()>1, "Error: Must have at least two elements in stack before adding them");
	Token first = stack.pop();
	Token second = stack.pop();
    	
	Assert.condition((first.isNumber()&&second.isNumber()), "Error: The two elements must be numbers to do the add operation");
	double num1 = first.getNumber();
	double num2 = second.getNumber();
	Token result = new Token(num1+num2);
	stack.push(result);
    }

    /**                                                                                                                             
     * @pre at least two tokens must be in the stack and the top two must be numbers                                                
     * @post pops off the top two tokens in the stack, subtracts the first number from the second number, and pushes a token representing their
     * difference into the stack
     */
    public void sub(){
	Assert.condition(stack.size()>1, "Error: Must have at least two elements in stack before subtracting them");
	Token first = stack.pop();
	Token second = stack.pop();
	
	Assert.condition((first.isNumber()&&second.isNumber()), "Error: The two elements must be numbers to do the subtract operation");
	double num1 = first.getNumber();
	double num2 = second.getNumber();
	Token result = new Token(num2-num1);
	stack.push(result);
    }

    /**                                                                                                                             
     * @pre at least two tokens must be in the stack and the top two must be numbers                                                
     * @post pops off the top two tokens in the stack, multiplies their numbers, and pushes a token representing their product into the stack
     */
    public void mul(){
	Assert.condition(stack.size()>1, "Error: Must have at least two elements in stack before multiplying them");
	Token first = stack.pop();
	Token second = stack.pop();

	Assert.condition((first.isNumber()&&second.isNumber()), "Error: The two elements must be numbers to do the multiply operation");
	double num1 = first.getNumber();
	double num2 = second.getNumber();
	Token result = new Token(num2*num1);
	stack.push(result);
    }

    /**                                                                                                                             
     * @pre at least two tokens must be in the stack and the top two must be numbers                                                
     * @post pops off the top two tokens in the stack, divides the second number by the first number, and pushes a token representing their
     * quotient into the stack
     */
    public void div(){
	Assert.condition(stack.size()>1, "Error: Must have at least two elements in stack before dividing them");
	Token first = stack.pop();
	Token second = stack.pop();

	Assert.condition((first.isNumber()&&second.isNumber()), "Error: The two elements must be numbers to do the divide operation");
	double num1 = first.getNumber();
	double num2 = second.getNumber();
	Token result = new Token(num2/num1);
	stack.push(result);
    }

    /**                                                                                                                             
     * @pre at least one token must be in the stack                                                
     * @post gets the top token in the stack, creates an identical token and pushes it into the stack
     */
    public void dup(){
	Assert.condition(stack.size()>0, "Error: Must have at least one element in stack before duplicating its value"); 
	Token first = stack.get();
	stack.push(first);
    }

    /**                                                                                                                             
     * @pre at least two tokens must be in the stack                                                
     * @post pops off the top two tokens in the stack, and pushes them back as to reverse their order and exchange their values
     */
    public void exch(){
	Assert.condition(stack.size()>1, "Error: Must have at least two elements in stack before exchanging their values");
	Token first = stack.pop();
	Token second = stack.pop();
	stack.push(first);
	stack.push(second);
    }

    /**                                                                                                                             
     * @pre at least two tokens must be in the stack and the top two must be numbers                                                
     * @post pops off the top two tokens in the stack, compares their values, and pushes a boolean token, representing whether their values are
     * equal, into the stack
     */
    public void eq(){
	Assert.condition(stack.size()>1, "Error: Must have at least two elements in stack before testing for equality");
	Token first = stack.pop();
	Token second = stack.pop();

	Assert.condition((first.isNumber()&&second.isNumber()), "Error: The two elements must be numbers to test for equality");
	double num1 = first.getNumber();
	double num2 = second.getNumber();
	Token result = new Token(num1==num2);
	stack.push(result);
    }

    /**                                                                                                                             
     * @pre at least two tokens must be in the stack and the top two must be numbers                                                
     * @post pops off the top two tokens in the stack, compares their values, and pushes a boolean token, representing whether their values are
     * unequal, into the stack
     */
    public void ne(){
	Assert.condition(stack.size()>1, "Error: Must have at least two elements in stack before testing for equality");
	Token first = stack.pop();
	Token second = stack.pop();

	Assert.condition((first.isNumber()&&second.isNumber()), "Error: The two elements must be numbers to test for equality");
	double num1 = first.getNumber();
	double num2 = second.getNumber();
	Token result = new Token(num1!=num2);
	stack.push(result);
    }

    /**                                                                                                                             
     * @pre at least two tokens must be in the stack, the second must be a symbol token, and the first either a number or procedure token
     * @post pops off the top two tokens in the stack, associates the second token's symbol with the first token, and adds that association to
     * the symbol table
     */
    public void def(){
	Assert.condition(stack.size()>1, "Error: Must have at least two elements in stack before defining a symbol");
	Token first = stack.pop();
	Token second = stack.pop();
	Assert.condition((first.isNumber()|| first.isProcedure()) && second.isSymbol(),"Error: a symbol followed by a value must be present for definition to be made");
	table.add(second.toString(), first);
    }

    /**                                                                                                                             
     * @post prints the string representation of the stack of tokens or prints an empty string if the stack is empty
     */
    public void pstack(){
	if (!stack.isEmpty()){
	    for (Iterator<Token> i = stack.iterator(); i.hasNext();) {
		Token t = i.next();
	    System.out.println(t.toString());
	}
	} else {
	    System.out.println("");
	}
    }

    /**                                                                                                                             
     * @post prints the string representation of the symbol table to the console
     */
    public void pTable(){
	System.out.println(table.toString());
    }

    /**                                                                                                                             
     * @pre at least two tokens must be in the stack, the first should be a procedure token and the second should be a boolean token             
     * @post pops off the top two tokens in the stack, and executes the procedure of the procedure token if the boolean token is true
     */
    public void theIf(){
	Assert.condition(stack.size()>1, "Error: Must have at least two elements in the stack before executing the if operator");
	Token first = stack.pop();
	Token second = stack.pop();

	Assert.condition(first.isProcedure() && second.isBoolean(), "Error: The two elements must be a boolean and a procedure to do if operator");
	if (second.getBoolean()){
	    List<Token> list = first.getProcedure();
	    Reader read = new Reader(list);
	    this.interpret(read);
	}
    }

    /**                                                                                                                             
     * @pre at least two tokens must be in the stack and the top two must be numbers                                                
     * @post pops off the top two tokens in the stack, compares their values, and pushes a boolean token, representing whether the second number
     * is less than the first one, into the stack
     */
    public void lt(){
	Assert.condition(stack.size()>1, "Error: Must have at least two elements in the stack before executing the lt operator");
	Token first = stack.pop();
	Token second = stack.pop();

	Assert.condition (first.isNumber() && second.isNumber(), "Error: The two elements must be numbers to check for less than");
	double num1 = first.getNumber();
	double num2 = second.getNumber();
	Token result = new Token(num2<num1);
	stack.push(result);
    }
}



    
