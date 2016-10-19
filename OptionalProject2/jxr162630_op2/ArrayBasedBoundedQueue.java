
/**
 * @author Naveenraj, Niveditha, Archana, Jegathis
 * Class to implement bounded queue with arrays. 
 * @param <T>
 */
public class ArrayBasedBoundedQueue<E>  {

	E[] Queue;
 	int currentEnd;                              // to keep track of the next index to push elements 
	int currentStart;                            // variable to keep track of element to be pop 
	int currentSize;                             // tells the current size of Queue 
	int numberOfElements;                        // Total number of elements currently present in Queue 
	
	public ArrayBasedBoundedQueue() {
		Queue=null;
		currentEnd=0;
		currentStart=0;
		currentSize=16;                          // Queue default size if 16
		numberOfElements=0;
	}
	
	/**
	 * Procedure to add element to the queue
	 * @param value : int : value to be inserted into queue
	 */
	public <T extends Comparable<? super T>> void offer(E value)
	{
		if(this.Queue==null){
			//T[] Queue=(T[])new Comparable[currentSize];
			Queue=(E[])new Comparable[currentSize];
			Queue[currentStart]=value;        
			currentEnd++;                       // if Queue is not at all present create one 
			numberOfElements++;                 // insert element in 0th index and move the currentend to 1
		}
		else
		{
			 Queue[currentEnd]=value;           //Queue is already present. Insert element at current end 
			 currentEnd++;                      // move current end 
			 numberOfElements++;
			 if((double)numberOfElements/(double)currentSize>.9)
			 {                                    // of queue is mostly full 
				 currentSize=currentSize*2;       // double the size and copy elements to new Queue 
				 E[] temp=Queue;
				 Queue=(E[]) new Comparable[currentSize];     
				 for(int i=0;i<numberOfElements;i++)
				 {
					 Queue[i]=temp[currentStart%((currentSize/2))];
					 currentStart++;
				 }
				 currentStart=0;                 // reintilize currentstart and currentend
				 currentEnd=numberOfElements;
			 }
			 if(currentEnd>=currentSize)
			 {
				currentEnd=currentEnd%(currentSize);    //if current end reaches its end it can start inserting in the empty space at front  
			 }
		}
	}
	
	/**
	 * procedure to pop top of the stack 
	 * @return :int : top of the stack 
 	 */ 
	public <T extends Comparable<? super T>> E poll()
	{
		if(isEmpty())
		   return null;                            // return null if Queue is empty 
		else
		{
			E element=Queue[currentStart];
			Queue[currentStart]=null;            // pop out element from currentstart 
			currentStart++;                      // move currentstart 
			numberOfElements--;                  // decrement number of elements in queue 
			if((((double)numberOfElements/(double)currentSize)*100)<25)
			{                                      
				 if(currentSize>16)             // if Queue is mostly empty and currentsize if greater than 16
				 {
				     currentSize=currentSize/2;
				     E[] temp=Queue;
				     Queue=(E[])new Comparable[currentSize];   // reduce the size of queue and copy to new queue
				     for(int i=0;i<numberOfElements;i++)
				     {
					     Queue[i]=temp[currentStart%((currentSize*2))];
					     currentStart++;
				     }  
				     currentStart=0;             // re intilize currentstart and currentend
				     currentEnd=numberOfElements;
				 }
				 
			}
			 if(currentStart>=currentSize)
			 {
				 currentStart=currentStart%(currentSize);    //if currentstart reaches end, it can start poping from start
			 }
			return element;
		}
	}
	
	/**
	 * @return : int : top of stack 
	 */
	public <T extends Comparable<? super T>> E peek()
	{
		if(isEmpty())
		   return null;
		else
			return Queue[currentStart];             // return top of element if queue is not empty 
	}
	
	/**
	 * @return : boolean : check if Queue is empty
	 */
	public <T extends Comparable<? super T>> boolean isEmpty()
	{
		return (currentStart==currentEnd);   //if currentstart and currentend are equal then queue is empty 
	}
	
	public static void main(String args[])
	{
		ArrayBasedBoundedQueue<Integer> arrayQueue=new ArrayBasedBoundedQueue<>();
		for(int i=0;i<20;i++)
		{
			arrayQueue.offer(i);
		}
		for(int i=0;i<16;i++)
		{
			System.out.print(arrayQueue.poll()+"-");
		}
		System.out.println();
		System.out.println(arrayQueue.currentSize+" "+arrayQueue.numberOfElements+" "+arrayQueue.currentStart+" "+arrayQueue.currentEnd+" "+arrayQueue.peek());
		
	}
	
}
