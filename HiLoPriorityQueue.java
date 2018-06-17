
import java.util.AbstractCollection;
import java.util.Iterator;


public class HiLoPriorityQueue<E extends BinaryPrioritizable> extends AbstractCollection{
    private Object[] items;
    private int highHead;
    private int highTail;
    private int highCount;
    private int highCapacity;
    private int lowHead;
    private int lowTail;
    private int lowCount;
    private int lowCapacity;
    private int count;
    
    public HiLoPriorityQueue(int high_capacity, int low_capacity){
        /**
Create a PriorityQueue that supports two priorities (high and low) and satisfies the following properties:

a. The queue should consist of a high priority bounded sub-queue with initial capacity high_capacity and a low priority bounded
 sub-queue with capacity low_capacity.

b. An item of high priority (i.e. item.getPriority() == 1) should be assigned to the tail of the high priority sub-queue as long
 as there is room. If there is no room, then the high priority item should join the low priority queue in front of all low priority items 
 hind any previously existing high priority items. If the low priority queue is also full, then the item at the tail should be removed 
 to accommodate the high priority item.

c. An item of low priority should be assigned to the tail of the low priority queue as long as there is room; if not, then the item 
should not be added to the queue.

d. When removing an item (e.g., selecting a customer to serve), first check whether the high priority sub-queue is empty. 
If not, then remove an item from the head of this sub-queue. In the case that the high priority sub-queue was completely full prior 
to this removal and there is a high priority item at the head of the low priority sub-queue, then move this high priority item to the 
tail of the high priority sub-queue.In the case that there high priority sub-queue is empty, then remove an item from the head of the 
low priority sub-queue.

e. Once you add a bunch of items to this priority queue, you should have an appropriate Iterator object that cycles through all items 
in the queue with high priority items being reached before low priority items.
         */
        items = new Object[high_capacity + low_capacity];
        highHead = highTail = highCount = 0; 
        lowHead = lowTail = high_capacity;// initial capacity
        lowCount = 0;
        highCapacity = high_capacity;
        lowCapacity = low_capacity;
        count = 0;
        for(int i = 0; i < items.length; i++){
            items[i] = new Object(); // populating the queue
        }
    }

    public void print(){
        /**
        Traverse and print the contents of the queue
         */
        for(int i = 0; i < items.length; i++){
            System.out.println(items[i].toString());
        }
    }
   
    public E highPeek()
    {
        /**
        return the first item on the high priority end
         */
        return (E) items[highHead];
    }
    public E lowPeek()
    {
         /**
        return the first item on the low priority end
         */
        return (E) items[lowHead];
    }
    /**
    methods to check if the low priority queue or high priority queue is full or empty
     */
    public boolean highIsFull()
    {
        return highCount >= highCapacity;
    }
    public boolean lowIsFull(){
        return lowCount == lowCapacity;
    }
    public boolean highIsEmpty()
    {
        return highCount == 0;
    }
    public boolean lowIsEmpty(){
        return lowCount == 0;
    }
  
    public boolean isFull(){
        return highCount + lowCount == highCapacity + lowCapacity;
    }
    public int highSize()
    {
        /**
        return the number of items with high priority
         */
        return highCount;
    }
    public int lowSize()
    {
        /**
        return the number of items with low priority
         */
        return lowCount;
    }
  
    public boolean add(E obj)
    {
        /**
        add an item object into the queue based on the priority
         */
        if(obj.getPriority() == 1)
	{
            if(!highIsFull())
	    {
                items[highTail] = obj;
                highTail = (highTail + 1) % highCapacity;
                highCount++;
		        count++;
                return true;
         }
            else if(!lowIsFull())
	   {
                if(lowCount == 0)
                {
                    highCapacity++;
                    lowCapacity--;
                    lowTail = lowHead = highCapacity ;
                    items[highTail] = obj;
                    if(highTail == 0)
                        highTail = highCapacity - 1;
                    highTail = (highTail + 1) % highCapacity;
                    highCount++;
		             count++;
                    return true;
                }
                else
		        {
                    int i;
                    for(i = lowCapacity + highCapacity - 2 ; i >= lowHead ; i--)
                        items[i+1] = items[i];
                    highCapacity++;
                    lowCapacity--;
                    lowHead = highCapacity ;
                    if(highTail == 0)
                        highTail = highCapacity - 1;
                    items[highTail] = obj;
                    highTail = (highTail + 1) % highCapacity;
                    highCount++;
		             count++;
                    return true;
                }
            }
            else
                return false;
        }
        else{
            if(!lowIsFull()){
                items[lowTail] = obj;
                lowTail = (lowTail + 1) % (lowCapacity + highCapacity);
                if(lowTail == 0)
                    lowTail = lowCapacity + highCapacity;
                lowCount++;
	        	count++;
                return true;
            }
            else
                return false;
        }
    }
  
    public E remove()
    {
        if(highCount != 0)
	{
            E r = (E) items[highHead];
             highHead = (highHead + 1) % highCapacity;
            highCount--;
	          count--;
            if(highCount >= highCapacity){
                items[highTail] = items[lowHead];
                lowHead = (lowHead + 1) % (highCapacity + lowCapacity);
                highTail = (highTail + 1) % highCapacity;
               
            }
        return r;
        }
        else if(lowCount != 0){
            E r = (E) items[lowHead];
            lowHead = (lowHead + 1) % (highCapacity + lowCapacity);
            lowCount--;
	      count--;
            return r;
        }
        else
            return null;
    }
   
    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //throw exception that is handled by JVM
    }

    @Override
    public Iterator iterator() {
        /**
        define an iterator instance for the priority queue
         */
        return new Iterator<E>(){
        public boolean hasNext()
        {
            return visited < highCount + lowCount;
        }

        public E next()
        {
            E r = null;
            if(highCount != 0){
                int index = ( highHead + visited) % highCapacity;
                r = (E) items[index];
                visited++;
            }
            else if(lowCount != 0){
                int index = highCapacity - 1 + ((lowHead + visited) % lowCapacity);
                r = (E) items[index];
                visited++;
            }
            return r;
        }   

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        private int visited = 0;
        };
      
    }
}