/* Name: Aaron Leung
 * Date: January 27, 2016
 * Subject: QUEUE ADT
 */

public interface QueueADT<T>
{
    public void enqueue (T element);
    
    public T dequeue();
    
    public T peek();
    
    public boolean isEmpty();
    
    public int size();
    
    public String toString();
}
        