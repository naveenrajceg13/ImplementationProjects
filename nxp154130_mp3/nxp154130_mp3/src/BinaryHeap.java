/**
 * @author Naveenraj
 *
 * @param <T>
 */
import java.util.Comparator;
public class BinaryHeap<T> implements PQ<T> {
    T[] pq;
    Comparator<T> c;
    int size;
    
    /** Build a priority queue with a given array q */
    BinaryHeap(T[] q, Comparator<T> comp) {
	pq = q;
	c = comp;
	size = q.length -1 ; 
	buildHeap();
    }

    /** Create an empty priority queue of given maximum size */
    BinaryHeap(int n, Comparator<T> comp) { /* to be implemented */
    	
    	this.c=comp;
    	pq= (T[])new Object[n+1];
    	size=0;
    }

    public void insert(T x) {
	add(x);
    }

   public T deleteMin() {
    	
    	if(size == 0) return null;
	return remove();
    }

    
    public T min() { 
	return peek();
    }

    public void add(T x) { /*implemented */
    	
    	if(size == pq.length -1 )
    	{
    		T[] temp= pq;
    		pq = (T[]) new Object[temp.length * 2 + 1 ];
    		for(int i=0;i<temp.length;i++)
    		{
    			pq[i]=temp[i];
    		}
    	}
    	pq[++size] = x;
    	percolateUp(size);
    }


    public T remove() { /*implemented */
    
    	T top = peek();
    	pq[1]=pq[size--];
    	percolateDown(1);
    	
    	return top;
    }

   public T peek() { /* implemented */
    	
    	if(size==0)
    	{
    		System.out.println("Heap has no Element");
    		return null;
    	}
	return pq[1];
    }

    /** pq[i] may violate heap order with parent */
    void percolateUp(int i) { /* implemented */
    	
    	int hole = i;
		pq[0] = pq[i];
		while(c.compare(pq[0], pq[hole / 2]) < 0) 
		{
			changeIndexValue(hole,hole/2);
			hole = hole/2;
		}
		changeIndexValue(hole,0);
    }

    /** pq[i] may violate heap order with children */
    void percolateDown(int i) 
    { 
    	pq[0] = pq[i];
    	
    	while(2*i <= size)
    	{
    		if (2*i == size)
    		{
    			if (c.compare(pq[0], pq[size]) > 0)
    			{
    				changeIndexValue(i,size);
    				i = size;
    			}
    			else
    			{
    				break;
    			}
    		}
    		else
    		{
    			int child = 2*i;
        		if (c.compare(pq[child], pq[child+1]) > 0)
        		{
        			child++;
        		}
        		if (c.compare(pq[0], pq[child]) >0)
        		{
        			changeIndexValue(i,child);
        			i = child;
        		}
        		else
        		{
        			break;
        		}
    			
    		}
    		
    	}
    	changeIndexValue(i,0);
    	
    }
    
   
    /** Create a heap.  Precondition: none. */
    void buildHeap() {
    	
    	for(int i=size/2;i>0;i--)
    	{
    		percolateDown(i);
    	}
    }

    /* sort array A[1..n].  A[0] is not used. 
    Sorted order depends on comparator used to buid heap.
    min heap ==> descending order
    max heap ==> ascending order
  */
 public static<T> void heapSort(T[] A, Comparator<T> comp) { /* implemented */
 	BinaryHeap<T> heap = new BinaryHeap<>(A, comp);
 	for (int i = heap.size; i >= 0; i--) {
         T temp = A[1];
         A[1] = A[i];
         A[i] = temp;
         heap.size--;
         heap.percolateDown(1);
     }
 	
 	
 	for(Object ele: heap.pq)
 	{
 		System.out.println(ele);
 	}
 }

	public void changeIndexValue(int src, int dest) {
		pq[src] = pq[dest];
	}
 
	public boolean isEmpty() {
		return size == 0;
	}
}
