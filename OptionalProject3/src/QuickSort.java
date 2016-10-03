import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class QuickSort<T> {

	/**
	 * Procedure to sort arrys using dual quick sort
	 * @param arr
	 * @param start
	 * @param end
	 */
	public <T extends Comparable<? super T>> void quicksortDualPivot(T[] arr,int start,int end)
	{
	         if(end<=start)
	        	 return ;

	         T pivot1=arr[start];
	         T pivot2=arr[end];
	         
	         if(pivot1.compareTo(pivot2) > 0)
	         {
	        	 swap(arr,start,end);                        // check the pivots and exchange position
	         }
	         
	         pivot1=arr[start];
	         pivot2=arr[end];
	         
	         int i=start+1; int j=end-1; int l=start+1;
	         
	         while(i<=j)
	         {
	        	 if(arr[i].compareTo(pivot1)<0)
	        	 {
	        		 swap(arr,i,l);                         
	        		 i++; l++;
	        	 }
	        	 else if(arr[i].compareTo(pivot2)>0)        // divide into 3 partitons 
	        	 {
	        		   swap(arr,i,j);                       // left middle right 
	        		   j--;                                 
	        	 }
	        	 else                                        
	        	 {
	        		 i++;                                 
	        	 }
	         }
	         swap(arr,start,--l);                            
	         swap(arr,end,++j);
	         
	         quicksortDualPivot(arr,start,l-1);
	         quicksortDualPivot(arr, l+1, j-1);
	         quicksortDualPivot(arr, j+1, end);
	         
	}
	
	/**
	 * Procedure to swap values
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
	 * Procedure to do normal Quick sort 
	 * @param arr
	 * @param start
	 * @param end
	 */
	public <T extends Comparable<? super T>> void quicksort(T[] arr,int start,int end)
	{
		     Random rn=new Random();
	         if(arr==null || arr.length==0)
	        	 return ;
	         if(start>=end)
	        	 return ;
	         
	         int index=rn.nextInt(end-start+1)+start;            //get pivot randomly
	         T pivot=arr[index];
	         
	         int i=start; int j=end;
	         
	         while(i <= j)
	         {
	        	 while(arr[i].compareTo(pivot)<0)
	        		 i++;                                        // get low index which is greater than pivot 
	        	 
	        	 while(arr[j].compareTo(pivot)>0)
	        		 j--;                                        // get high index which is less than pivot 
	        	 
	        	 if(i <= j)
	        	 {
	        		 T temp = arr[i];
	        		 arr[i]=arr[j];                              // exchange them
	        		 arr[j]=temp;
	        		 i++;
	        		 j--;
	        	 }
	         }
	         
	         if(start < j)
	        	 quicksort(arr, start, j);                       // do quick sort recursively
	         if(end > i)
	        	 quicksort(arr, i, end);
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
	
public static void main(String args[]){
		
		// Declare variables. 
		File f;
		Scanner in;
		List<Double> ar=new ArrayList<>();
		// Object oriented approach to call non static methods of same class 
		QuickSort<Double> quickSort=new QuickSort<>();
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
		//Double[] array={6.0,7.0,2.0,4.0,1.0,9.0,8.0};
		Shuffle.shuffle(array);
		
		long time1 = System.nanoTime();
		quickSort.quicksort(array, 0,array.length-1);  //Calling merge sort. 
        long time2 = System.nanoTime();
        
		System.out.println("Time(second) for normal Quick sort : " + (time2-time1)/Math.pow(10, 9));
        
        Shuffle.shuffle(array);
        time1 = System.nanoTime();
		quickSort.quicksortDualPivot(array, 0,array.length-1);  //Calling merge sort. 
        time2 = System.nanoTime();
        System.out.println("Time(second) for dual pivot Quick sort : " + (time2-time1)/Math.pow(10, 9));
        
	}
	
}
