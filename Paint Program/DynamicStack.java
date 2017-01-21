/* Name: Aaron Leung
 * Date: May 24, 2016
 * Subject: Dynamic Stack
 */


public class DynamicStack<T> implements StackADT<T>
{
    
    private LinkedList<T> stack = new LinkedList<T>();
    
    public DynamicStack(){
    }
    
    // Adds one element to the top of the stack.
    public void push(T element){
        
        stack.addFront(element);
    }
    
    
    // Removes and returns the top element from this stack.
    public T pop(){
        return stack.removeFront();
    }
    
    // Returns the top element, without removing it from this stack.
    public T peek(){
        return stack.first();
    }
    
    // Returns true if this stack contains no elements, false otherwise.
    public boolean isEmpty(){
        return stack.isEmpty();
    }
    
    // Returns the number of elements in this stack.
    public int size(){
        return stack.size();
    }
    
    //Makes the stack empty
    public void makeEmpty(){
        stack.makeEmpty();
    }

    
    // Returns a String representation of this stack.
    public String toString(){
        return stack.toString();
    }
    
    
    
} // End of StackADT Interface class