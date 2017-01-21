/* Name: Aaron Leung
 * Subject: LinkedList
 * Date: Feb 3 2016
 */

public class LinkedList<T> {
    private int numberOfNodes = 0; 
    private ListNode<T> front = null;
    private ListNode<T> end = null;
    private ListNode<T> secondLast = null;
    
    // Returns true if the linked list has no nodes, or false otherwise.
    public boolean isEmpty() {
        return (front == null);
    }
    
    // Deletes all of the nodes in the linked list.
    // Note: ListNode objects will be automatically garbage collected by JVM.
    public void makeEmpty() {
        front = null;
        end = null;
        numberOfNodes = 0;
    }
    
    // Returns the number of nodes in the linked list
    public int size() {
        return numberOfNodes;
    }
    
    //Adds an element to the front of the linked list
    public void addFront( T element ) {
        

        front = new ListNode<T>( element, front );
        numberOfNodes++;
        if (size() == 1)
        {
            end = front;

        }
    }
    
    //Shows the element at the front of the Linked List
    public T first() {
        if (isEmpty()) 
            return null;
        
        return front.getData();
    }
    
    //Shows the element at the back of the linked list
    public T last() {
        if (isEmpty()) 
            return null;
        
        return end.getData();
    }
    
    //Removes the element at the front
    @SuppressWarnings("unchecked")        
    public T removeFront() {
        T tempData;
        
        if (isEmpty()) 
            return null;
        
        tempData = front.getData();
        front = front.getNext();
        numberOfNodes--;
        return tempData;
    }
    
    //Checks if the element is in the linked list
    @SuppressWarnings("unchecked")
    public boolean contains( T key ) {
        ListNode<T> searchNode;
        searchNode = front;
        while ( (searchNode != null) && (!key.equals(searchNode.getData())) ) {
            searchNode = searchNode.getNext();
        }
        return (searchNode != null);
    }
    
    //Prints a representation of the linked list in a string form
    @SuppressWarnings("unchecked")
    public String toString() {
        ListNode<T> node;
        String linkedList = "FRONT ==> ";
        
        node = front;
        while (node != null) {
            linkedList += "[ " + node.getData() + " ] ==> ";
            node = node.getNext();
        }
        
        return linkedList + "NULL";
    }
    
    //Inserts a list node into the linked list
    @SuppressWarnings("unchecked")
    public void insert( Comparable key ) {
        ListNode<T> before = null;
        ListNode<T> after = front;
        ListNode<T> newNode;        
        
        // Traverse the list to find the ListNode before and after our insertion point.
        while ((after != null) && (key.compareTo(after.getData()) > 0)) {
            before = after;
            after = after.getNext();
        }
        
        // Create the new node with link pointing to after
        newNode = new ListNode<T>( (T)key, after);
        
        // Adjust front of the list or set before's link to point to new node, as appropriate
        if (before == null) {
            front = newNode;
        }
        else {
            before.setNext(newNode);
        }
        numberOfNodes++;
    }
    
    //Adds an element to the end of hte linked list
    @SuppressWarnings("unchecked")
    public void addEnd(T element){
        if (isEmpty()){
            addFront(element);
        }
        else{
            ListNode<T> temp = new ListNode<T>( element, null );
            end.setNext(temp);
            end = temp;
            numberOfNodes++;
        }
    }    
}