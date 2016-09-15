import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * @author Naveenraj
 * Class to sort using Stack Merge sort
 * @param <T>
 */
public class MergeSortUsingStack<T> {

	/**
	 * Procedure to call merge sort
	 * @param args
	 */
	public static void main(String args[])
	{
	      MergeSortUsingStack<String> mergeSort=new MergeSortUsingStack<>();
	      Integer[] array={5,8,7,4,10,3,6,2,9};
	      mergeSort.mergeSort(array);
	      mergeSort.print(array);
	}

	/**
	 * @param arr : T[] : array to be printed
	 */
	public <T extends Comparable<? super T>> void print(T[] arr)
	{
		// Iterate and Print elements in array. 
		System.out.println();
		for(int i=0;i<arr.length;i++)
		{
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	
	/**
	 * @param arr
	 */
	public <T extends Comparable<? super T>>void mergeSort(T[] arr)
	{
		ArrayDeque<SnapShot<T>> stack=new ArrayDeque<>();   //stack which acts as recursion
		
	    SnapShot<T> snapShot=new SnapShot<>(0,arr.length-1,false);  //snapshot object which stores snapshot of last recursion call
	    stack.push(snapShot);                                             // push first function call to stack
		while(!stack.isEmpty())
		{
			snapShot=stack.element();
			int start=snapShot.start;
			int end=snapShot.end;                         // every time start and end is got from snapshot 
			if(start<end)
			{
			int mid=(start+end)/2;
			
			if(!snapShot.oneSideEnd)
			    snapShot=new SnapShot<>(start, mid,false);     //if left sub tree is traversed fully for current snapshot
			else
				snapShot=new SnapShot<>(mid+1,end,false);	   // traverse right sub tree
			
			stack.push(snapShot);                              // push next object to be processed to stack 
			}
			else
			{
				stack.pop();                                    // one full sub tree is traversed then pop out that tree
				if(!stack.element().oneSideEnd) 
				{
				   snapShot=stack.pop();
				   snapShot.oneSideEnd=true;                    // if other part is not traversed yet
				   stack.push(snapShot);
				}
				else
				{                                               // both sub tree traversed for current snapshot
					   
			     	   while(!stack.isEmpty() && stack.element().oneSideEnd)
			     	   {
			                       snapShot=stack.pop();
			                       int mid=(snapShot.start+snapShot.end)/2;          //go till the top tree, for which we didnt process right sub tree  
			                       merge(arr, snapShot.start, mid, snapShot.end);    // merge current snapshot if both sub tree is processed 
			     	   }
			     	   if(!stack.isEmpty())
			     	   {
			     	   snapShot=stack.pop();
					   snapShot.oneSideEnd=true;                                     // setting top tree from which only right sub tree is not processed
					   stack.push(snapShot);
			     	   }
			     	   else
			     	   {
			     		   break;                                                    // if stack is empty it means everything is processed
			     	   }
				}
			}
		}
	}
	/**
	 * Procedure to merge sorted sub arrays
	 * returns Void
	 * @param arr : T[] : array with sub array to be merged
	 * @param start : int : start index of Left Array 
	 * @param mid : int : end index of Left Array
	 * @param end : int : end index of Right Array 
	 */
	
	public <T extends Comparable<? super T>> void merge(T[] arr,int start,int mid,int end)
	{
		//find the length of left and right sub array to be merged
		int left_array_size=mid-start+1;
		int right_array_size=end-mid;
		
		//copy elements into temp array
		T[] left_array=(T[])new Comparable[left_array_size];
		T[] right_array=(T[])new Comparable[right_array_size];
		
		//copy left sub array to left_array
		for(int i=0;i<left_array_size;i++)
		{
			left_array[i]=arr[start+i];
		}
		
		//copy right sub array into right_array
		for(int i=0;i<right_array_size;i++)
		{
			right_array[i]=arr[mid+1+i];
		}
		
		//index of left array and right array set to 0 to traverse 
		int left_array_index=0;
		int right_array_index=0;
		
		//index of the merge array is set to start
		int merge_array_index=start;
		
		//loop till either of array reaches its end
		while(left_array_index<left_array_size && right_array_index<right_array_size)
		{
			//if left array has less values, put the value into original array and iterate Left array
			if(left_array[left_array_index].compareTo(right_array[right_array_index])<0)
			{
				arr[merge_array_index]=left_array[left_array_index];
				left_array_index++;
			}
			else //else copy put value of right array into original array and iterate Right array
			{
				arr[merge_array_index]=right_array[right_array_index];
				right_array_index++;
			}
			merge_array_index++;
			
		}
		
		// if right array reaches end, then copy all remaining left array elements alone 
		while(left_array_index<left_array_size)
		{
			arr[merge_array_index]=left_array[left_array_index];
			left_array_index++;
			merge_array_index++;
		}
		
		//if left array reaches end, then copy all remaining right array elements alone
		while(right_array_index<right_array_size)
		{
			arr[merge_array_index]=right_array[right_array_index];
			right_array_index++;
			merge_array_index++;
		}
		
	}
}

