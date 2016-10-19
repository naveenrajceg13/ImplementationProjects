import java.util.PriorityQueue;
import java.util.Random;


public class SelectionKthLargest<T> {
	
	
	public static void main(String args[])
	{
		Random rn=new Random();
		SelectionKthLargest<Double> kLargest=new SelectionKthLargest<>();
		int n = (int) Math.pow(2, 20);
		Double[] array=new Double[n];
		for(int i=0;i<n;i++)
		{
			array[i]=(double) (rn.nextInt((n-1)-0+1)+0);
		}
		int k = 100;
		int pivot=rn.nextInt((array.length-1)-0+1)+0;
		kLargest.swap(array,pivot,array.length-1);
		long time1 = System.nanoTime();
		int q=kLargest.select(array,0,array.length-1,k);
		long time2 = System.nanoTime();
		
		System.out.println("K largest elements using select algorithm:");
		for(int i=q;i<=array.length-1;i++)
		{
			System.out.println(array[i]);
		}
		long time3 = System.nanoTime();
		PriorityQueue<Double> queue=kLargest.findKLargestElememt(array,k);
		long time4 = System.nanoTime();
		
		System.out.println("K largest elements using priority-queue-based algorithm:");
		while(!queue.isEmpty())
		{
			System.out.println(queue.poll());
		}	
		System.out.println("Time(second) for select algorithm: " + (time2-time1)/Math.pow(10, 9));
		System.out.println("Time(second) for priority-queue-based algorithm: " + (time4-time3)/Math.pow(10, 9));
	}

	/**
	 * Procedure to select k largest element of an array.
	 * @param arr
	 * @param start
	 * @param end
	 * @param k
	 * @return k largest elements.
	 */
	public <T extends Comparable<? super T>> int select(T[] A,int p,int r,int k)
	{
		int q;
		q = partition(A,p,r);
		if((r-q)>=k)
		{
			return select(A,q+1,r,k);
		}
		else if((r-q+1)==k)
		{
			return q;
		}
		else 
		{
			return select(A, p,q-1,k-(r-q+1));
		}
	
	}
	
	/**
	 * Procedure which implements partition and returns the pivot
	 * @param arr
	 * @param start
	 * @param end
	 */	
	public <T extends Comparable<? super T>> int partition(T[] A, int p, int r)
	{
		T x=A[r];
		int i=p-1;
		T temp;
		for(int j=p;j<=r-1;j++)
		{
			if(A[j].compareTo(x)<=0)
			{
				i=i+1;
				temp=A[i];
				A[i]=A[j];
				A[j]=temp;		
			}
		}
		temp=A[i+1];
		A[i+1]=A[r];
		A[r]=temp;
		return i+1;
	}
	
	/**
	 * Procedure to select k largest element of an array using priority-queue-based algorithm
	 * @param arr
	 * @param k
	 */	
	public <T extends Comparable<? super T>> PriorityQueue<T> findKLargestElememt(T[] arr,int k)
	{
		PriorityQueue<T> q=new PriorityQueue<T>(k);
		for(int i=0;i<k;i++)
		{
			
			q.add(arr[i]);
			
		}
		for(int i=k;i<arr.length;i++)
		{
			if(arr[i].compareTo(q.peek())>0)
			{
				q.remove();
				q.add(arr[i]);
			}
		}
		

		return q;
	}
	
	
	/**
	 * Procedure which swaps two elements
	 * @param arr
	 * @param first	
	 * @param second
	 */
	public <T extends Comparable<? super T>> void swap(T[] arr,int first,int second)
	{
		T temp=arr[first];
        arr[first]=arr[second];
        arr[second]=temp; 
	}
	
	
	/**
	 * Procedure which prints the array elements
	 * @param arr
	 */
	public <T extends Comparable<? super T>> void print(T[] arr)
	{
		// Iterate and Print elements in array. 
		for(int i=0;i<arr.length;i++)
		{
			System.out.print(arr[i]+" ");
		}
	}
}
