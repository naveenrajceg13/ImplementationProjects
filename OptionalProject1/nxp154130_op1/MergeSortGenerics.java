import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Naveenraj, Niveditha, Jagathis
 * Generic Merge Sort
 * @param <T> 
 */

public class MergeSortGenerics<T>{
	
	/**
	 * Procedure to run Merge sort 
	 * Returns void
	 * @param arr : T[] array to be sorted
	 * @param start : int : start index of array 
	 * @param end : int : end index of array
	 */
	public <T extends Comparable<? super T>> void mergesort(T[] arr,int start,int end)
	{
	    if(start< end)
	    {
	    	//find mid point
	    	int mid=(start+end)/2;
	    	
	    	//sort first half
	    	mergesort(arr,start,mid);
	    	
	    	//sort second half
	    	mergesort(arr,mid+1,end);
	    	
	        //merge sorted portions
	    	merge(arr,start,mid,end);
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

	/**
	 * @param arr : T[] : array to be printed
	 */
	public <T extends Comparable<? super T>> void print(T[] arr)
	{
		// Iterate and Print elements in array. 
		for(int i=0;i<arr.length;i++)
		{
			System.out.print(arr[i]+" ");
		}
	}
	
	
	/**
	 * Main funtion 
	 * @param args : String [] : File name input
	 */
	public static void main(String args[]){
		
		// Declare variables. 
		File f;
		Scanner in;
		List<Double> ar=new ArrayList<>();
		// Object oriented approach to call non static methods of same class 
		MergeSortGenerics<Double> mergeSort=new MergeSortGenerics<>();
		try
		{  
			if(args.length>0)
			{
				f=new File(args[0]); //command line input for file name
			}
			else
			{
				f=new File("inputFile.txt"); //else pick provided file. 
			}
			
			in=new Scanner(f);
			while(in.hasNextDouble())
			{
				ar.add(in.nextDouble()); //read all double values in File. 
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		// Change arrayList to array for doing Merge Sort 
		Double[] array=(Double[]) ar.toArray(new Double[ar.size()]);
		
		long time1 = System.nanoTime();
		mergeSort.mergesort(array, 0,array.length-1);  //Calling merge sort. 
        long time2 = System.nanoTime();
        
		System.out.println("Time(second) for merge sort : " + (time2-time1)/Math.pow(10, 9));
		//mergeSort.print(array);
	}
	

}
