/* Name: Aaron Leung
 * Date: January 27, 2016
 * Subject: Dynamic Queue
 */

public class DynamicQueue<T> implements QueueADT<T>
{
    private int tail;
    private LinkedList<T> queue = new LinkedList<T>();
    
    public DynamicQueue(){
    }
    
    //Adds an element to the back of the queue
    public void enqueue(T element){
        queue.addEnd(element);
    }
    
    //Removes the element at the front of the list
    public T dequeue(){
        return queue.removeFront();
    }
    
    //Checks the element at the front of the queue
    public T peek(){
        return queue.first();
    }
    
    //Checks if the queue is empty
    public boolean isEmpty(){
        return queue.isEmpty();
    }
    
    //Returns the size of the queue
    public int size(){
        return queue.size();
    }
    
    //Makes queue empty
    public void makeEmpty(){
        queue.makeEmpty();
    }
    
    
    //Returns the queue in string format
    public String toString(){
        return queue.toString();
    }
            
}
            