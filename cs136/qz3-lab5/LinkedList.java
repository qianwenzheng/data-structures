/*                                                                                 
 * Name: Qianwen (Tiffany) Zheng                                                      
 * Lab: CSCI 136 (Section 05) LAB 5                                                  
 *                                                                                    
 * This class is designed to be an extension of the class DoublyLinkedList 
 * that implements lists using doubly linked elements and dummy nodes. This 
 * class makes use of two dummy nodes: one at the head of the list and one
 * at the end.                                             
 *                                                                                    
 */

/*
 * Thought Questions:
 * 
 * 1)The if statements that are a part of the three-parameter constructor for the
 * DoublyLinkedNode are responsible for checking if the next and previous nodes are null.
 * Because the three parameter constructor would update the next and previous nodes to
 * point to the new node that is added, it is necessary to check if the next and previous
 * are null. If the next node is null, that means that the node is being constructed at 
 * the end of the list and so next node would have no references anyways. If the previous
 * node is null, then that means that the node is being constructed at the beginning of 
 * the list and therefore, the previous node's references would not have to be adjusted
 * because it has no references. Therefore, the if statements are necessary to only 
 * update the references of the previous or next nodes if they are not null. If you
 * replace the calls to the three parameter constructor with the one parameter one and
 * manually use setNext and setPrevious to set the appropriate references, the if 
 * statements would disappear because there is no longer any need for them. Because the 
 * if statements were only necessary if you were to update the references of the previous
 * and next nodes, they are not necessary for the one parameter constructor. The one 
 * parameter constructor will update the references for the node by using setNext and 
 * setPrevious and will update the references for next node and previous node 
 * respectively. If either the next node or the previous node was null, then one would
 * already know when setting them manually. When setting them manually, if the next
 * node or the previous node is null, then one would not bother to update its 
 * non-existent references. The if statements disappear because they are only needed if 
 * you do not know whether next node or previous node is null. Since you are setting
 * the nodes manually, you would already know if they are null and would not update their
 * references if so. 
 * 
 * 2)The contains method can be written by making use of the indexOf method but not the
 * other way around because the contains method will return true (a value is in the 
 * list) if the indexOf method, when called for that value, will not return -1. 
 * Because the indexOf method returns the index of the specified value and returns -1
 * if that value is not found in the list, it can easily be used in the contains method.
 * This is because if a value is contained in a list, then when indexOf is called on that
 * value, it would never return a -1. If that value is not contained in the list, 
 * however, the indexOf method, when called, will return a positive number. Therefore,
 * the indexOf method is an easy way to check if a list contains a value depending on
 * what indexOf returns.The indexOf method, on the other hand, cannot be written with
 * the contains method. If the contains method were to work without making use of the 
 * indexOf method, the indexOf method cannot be written using contains because indexOf
 * requires more checks than simply checking if a value is contained in a list. The 
 * indexOf method is supposed to return the index of a specific value in the list, and
 * simply using the contains method will only check if the value is a part of the list.
 * Therefore, the indexOf method is expected to do more than check if a value is 
 * contained in the list - it must return the actual index of that value if it is part
 * of the list. So, simply using the contains method alone will not work in getting
 * indexOf to work properly (contains returns a boolean value while indexOf returns an
 * int value). It is possible to employ the contains method in indexOf (for example, if 
 * the list does not contain the value, then return -1, else go through the list to find
 * the first index at which that value is found). This would allow indexOf to do less 
 * work by immediately returning -1 if the value is not contained in the list. However,
 * the contains method alone cannot be used to return an index. 
 * 
 * 3)The insertAfter method can be replaced with an insertBefore method and it would 
 * essentially accomplish the same goal of inserting an element at somewhere specific
 * in the list. The remove methods are different in that they do not remove an element
 * "after" or "before" another one. The three remove methods are able to remove an 
 * element at a certain index i, remove the first element in the list with a specific
 * value, and remove the first and last elements of the list. There cannot be two 
 * different versions of remove because we never essentially pass a dummy node to the
 * remove methods. The user has no access to the head and tail dummy nodes so he will 
 * never be able to remove them. The insertAfter/insertBefore methods, however, are 
 * different in that the user can possibly pass a dummy node to those methods (for 
 * example, if they insert a value after tail.previous(), then the value for 
 * tail.previous() would have to be updated and if they inserted a value before 
 * head.next(), then the value for head.next() would have to be updated as well, so the
 * references of the dummy nodes will possibly have to be adjusted with the insert 
 * methods). The remove methods, however, cannot have two versions of each that would
 * do the same thing because they are not dependent on the elements of the list that
 * come after or before the chosen element. There is not a choice between the versions
 * of remove because they are not concerned with the neighboring elements, so a
 * different version of the method that checks for the previous or next element of the
 * specific element you want to remove cannot exist. Because the remove methods are not
 * concerned with neighboring elements, there cannot be two similar versions.  
 *
 * 4)The special cases that could appear in the indexed version of add are the case 
 * when the index that the user wants to add the element to is the first index (0) or the
 * last index (size()-1) of the list. If the user wanted to add an element at the first
 * index of the list, then one could simply call the addFirst() method to add that 
 * element after the dummy head of the list. If the user wanted to add an element at the
 * last index of the list, then one could call the addLast() method to add that 
 * element before the dummy tail of the list. It is desirable to handle these two cases
 * because if the index was the first or the last, then the method would only call the 
 * addFirst() or addLast() methods without having to go through the while loop and look
 * for the index specified. Granted, this would perhaps be more helpful if the index
 * was the last rather than the first. This is because if the first index was the one
 * specified, even though the while loop is being executed, it is only going around the 
 * loop once and then the element would be added. Therefore, checking for this case, 
 * although useful, does not make such a big difference in the time taken to run the
 * code. Handling this special case is not very desirable because not handling it would
 * not cost much time anyways. However, the case where the last index is where the user
 * wants to add an element is very desirable to be handled. This is because simply 
 * calling the addLast() method is much faster than the alternative if the case was not
 * handled (the while loop would have to be executed muliple times until the end of the 
 * list is reached, which takes up much unnecessary time). Therefore, handling the case
 * where the last index is specified is desirable because it costs less in terms of time.
 *
 * 5) The original file (12KB) is bigger than the final result source file (11KB). In 
 * terms of the amount of code, the final result had much less code than the original
 * file due to making use of dummy nodes representing the head and tail. 
 *
 */

import structure5.Assert;
import structure5.DoublyLinkedList;
import structure5.DoublyLinkedNode;
import structure5.DoublyLinkedListIterator;
import java.util.Iterator;

public class LinkedList<E> extends DoublyLinkedList<E>
{
    /**
     * Number of elements within the list.
     */
    protected int count;
    /**
     * Reference to head of the list.
     */
    protected DoublyLinkedNode<E> head;
    /**
     * Reference to tail of the list.
     */
    protected DoublyLinkedNode<E> tail;

    /**
     * Constructs an empty list.
     *
     * @post constructs an empty list
     * 
     */
    public LinkedList()
    {
	head= new DoublyLinkedNode<E> (null);
	tail = new DoublyLinkedNode<E> (null);
	head.setNext(tail);
	tail.setPrevious(head);
	count = 0;
    }

    /**
     * Determine the number of elements in the list.
     *
     * @post returns the number of elements in list
     * 
     * @return The number of elements found in the list.
     */
    public int size()
    {
	return count; 
    }

    /**
     * Determine if the list is empty.
     *
     * @post returns true iff the list has no elements.
     * 
     * @return True iff list has no values.
     */
    public boolean isEmpty()
    {
	return size() == 0;
    }

    /**
     * Remove all values from list.
     *
     * @post removes all the elements from the list
     */
    public void clear()
    {
        head = new DoublyLinkedNode<E>(null);
	tail = new DoublyLinkedNode<E>(null);
	head.setNext(tail);
	tail.setPrevious(head);
	count = 0;
    }

    /**
     * A private routine to add an element after a node.
     * @param value the value to be added
     * @param previous the node the come before the one holding value
     * @pre previous is non null
     * @post list contains a node following previous that contains value
     */
    protected void insertAfter(E value, DoublyLinkedNode<E> previous)
    {
	DoublyLinkedNode<E> newNode = new DoublyLinkedNode<E> (value);

	// Updates the value of tail.previous() if necessary
	if (previous == tail.previous()) {
	    tail.setPrevious(newNode);
	} 
	newNode.setNext(previous.next());
	newNode.setPrevious(previous);
	previous.setNext(newNode);
	count++;
    }

    /**
     * A private routine to remove a node.
     * @param node the node to be removed
     * @pre node is non null
     * @post node node is removed from the list
     * @return the value of the node removed
     */
    protected E remove(DoublyLinkedNode<E> node)
    {
	node.previous().setNext(node.next());
	node.next().setPrevious(node.previous());
	count--; 
	return node.value();
    }

    
    /**
     * Add a value to the head of the list.
     *
     * @pre value is not null
     * @post adds element to head of list
     * 
     * @param value The value to be added.
     */
    public void addFirst(E value)
    {
	insertAfter(value,head);
    }

    /**
     * Add a value to the tail of the list.
     *
     * @pre value is not null
     * @post adds new value to tail of list
     * 
     * @param value The value to be added.
     */
    public void addLast(E value)
    {
	insertAfter(value,tail.previous());	
    }

    /**
     * Remove a value from the head of the list.
     * Value is returned.
     *
     * @pre list is not empty
     * @post removes first value from list
     * 
     * @return The value removed from the list.
     */
    public E removeFirst()
    {
	return remove(head.next());
    }

    /**
     * Remove a value from the tail of the list.
     *
     * @pre list is not empty
     * @post removes value from tail of list
     * 
     * @return The value removed from the list.
     */
    public E removeLast()
    {
	return remove(tail.previous());
    }

    /**
     * Get a copy of the first value found in the list.
     *
     * @pre list is not empty
     * @post returns first value in list.
     * 
     * @return A reference to first value in list.
     */
    public E getFirst()
    {
	return head.next().value();
    }

    /**
     * Get a copy of the last value found in the list.
     *
     * @pre list is not empty
     * @post returns last value in list.
     * 
     * @return A reference to the last value in the list.
     */
    public E getLast()
    {
	return tail.previous().value();
    }

    /**
     * Insert the value at location.
     *
     * @pre 0 <= i <= size()
     * @post adds the ith entry of the list to value o
     * @param i the index of this new value
     * @param o the the value to be stored
     */
    public void add(int i, E o)
    {
	DoublyLinkedNode<E> finger = head;
	while (i>0){
	    finger = finger.next();
	    i--;
	}
	insertAfter(o,finger);
    }

    /**
     * Remove and return the value at location i.
     *
     * @pre 0 <= i < size()
     * @post removes and returns the object found at that location.
     *
     * @param i the position of the value to be retrieved.
     * @return the value retrieved from location i (returns null if i invalid)
     */
    public E remove(int i)
    {
	DoublyLinkedNode<E> finger = head;

	// Removes the first element
	if (i==0){
	    return removeFirst();
	}

	// Removes the last element
	else if (i==size()-1){
	    return removeLast();
	}

	// Returns null if i is invalid
	else if (i<0 || i > size()-1){
	    return null;
	}

	// Removes the element at i and returns its value
	else {
	while (i>0){
	    finger = finger.next();
	    i--;
	}
	remove(finger.next());
	return finger.next().value();
	}
    }

    /**
     * Get the value at location i.
     *
     * @pre 0 <= i < size()
     * @post returns the object found at that location.
     *
     * @param i the position of the value to be retrieved.
     * @return the value retrieved from location i (returns null if i invalid)
     */
    public E get(int i)
    {
	DoublyLinkedNode<E> finger = head;

	// Gets the last element
	if(i==size()-1){
	    getLast();
	}
	
	// Gets the first element
	else if (i==0){
	    getFirst();
	} else {
	    
	// search for the ith element or end of list
	while (i > 0)
	{
	    finger = finger.next();
	    i--;
	}
	return finger.value();
	}
	return null;
    }

    /**
     * Set the value stored at location i to object o, returning the old value.
     *
     * @pre 0 <= i < size()
     * @post sets the ith entry of the list to value o, returns the old value.
     * @param i the location of the entry to be changed.
     * @param o the new value
     * @return the former value of the ith entry of the list.
     */
    public E set(int i, E o)
    {
	DoublyLinkedNode<E> finger = head;

	// search for the ith element or the end of the list
	while (i > 0)
	{
	    finger = finger.next();
	    i--;
	}
	// get old value, update new value
	E result = finger.next().value();
	finger.next().setValue(o);
	return result;
    }

    /**
     * Determine the first location of a value in the list.
     *
     * @pre value is not null
     * @post returns the (0-origin) index of the value,
     *   or -1 if the value is not found
     * 
     * @param value The value sought.
     * @return the index (0 is the first element) of the value, or -1
     */
    public int indexOf(E value)
    {
	int i = 0;
	DoublyLinkedNode<E> finger = head;
	
	// search for value or end of list, counting along the way
	while (!finger.next().value().equals(value))
	{
	    finger = finger.next();
	    i++;
	}
	// finger points to value, i is index
	if (finger.next() == null)
	{   // value not found, return indicator
	    return -1;
	} else {
	    // value found, return index
	    return i;
	}
    }

    /**
     * Determine the last location of a value in the list.
     *
     * @pre value is not null
     * @post returns the (0-origin) index of the value,
     *   or -1 if the value is not found
     * 
     * @param value the value sought.
     * @return the index (0 is the first element) of the value, or -1
     */
    public int lastIndexOf(E value)
    {
	int i = size()-1;
	DoublyLinkedNode<E> finger = tail.previous();
	
	// search for the last matching value, result is desired index
	while (!finger.value().equals(value))
	{
	    finger = finger.previous();
	    i--;
	}
	if (finger == null)
	{   // value not found, return indicator
	    return -1;
	} else {
	    // value found, return index
	    return i;
	}
    }

    /**
     * Check to see if a value is within the list.
     *
     * @pre value not null
     * @post returns true iff value is in the list
     * 
     * @param value A value to be found in the list.
     * @return True if value is in list.
     */
    public boolean contains(E value)
    {
	return indexOf(value) != -1;
    }

    /**
     * Remove a value from the list.  At most one value is removed.
     * Any duplicates remain.  Because comparison is done with "equals,"
     * the actual value removed is returned for inspection.
     *
     * @pre value is not null.  List can be empty.
     * @post first element matching value is removed from list
     * 
     * @param value The value to be removed.
     * @return The value actually removed.
     */
    public E remove(E value)
    {
	DoublyLinkedNode<E> finger = head;
	while (!finger.value().equals(value))
	{
	    finger = finger.next();
	}
	if (finger != null){
	    remove(finger);
	    return finger.value();
	}
	return null;
    }

    /**
     * Construct an iterator to traverse the list.
     *
     * @post returns iterator that allows the traversal of list.
     * 
     * @return An iterator that traverses the list from head to tail.
     */
    public Iterator<E> iterator()
    {
	return new DoublyLinkedListIterator<E>(head,tail);
    }

    /**
     * Construct a string representation of the list.
     *
     * @post returns a string representing list
     * 
     * @return A string representing the elements of the list.
     */
    public String toString()
    {
	StringBuffer s = new StringBuffer();
	s.append("<LinkedList:");
	Iterator<E> li = iterator();
	while (li.hasNext())
	{
	    s.append(" "+li.next());
	}
	s.append(">");
	return s.toString();
    }
}


