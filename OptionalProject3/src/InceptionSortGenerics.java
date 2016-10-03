import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InceptionSortGenerics<T> {

	public <T extends Comparable<? super T>> void inceptionSort(T[] arr)
	{
	    for(int i=0;i<arr.length;i++)
	    {
	    	for(int j=i;j>0;j--)
	    	{
	    		if(arr[j-1].compareTo(arr[j])>0)
	    		{
	    			T temp=arr[j-1];
	    			arr[j-1]=arr[j];
	    			arr[j]=temp;
	    		}
	    	}
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
	
	public static void main(String args[])
	{
		// Declare variables. 
				File f;
				Scanner in;
				Shuffle suffle=new Shuffle();
				List<Double> ar=new ArrayList<>();
				// Object oriented approach to call non static methods of same class 
				InceptionSortGenerics<Double> inceptionSort=new InceptionSortGenerics<>();
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
				// Change arrayList to array for doing Inception Sort 
				Double[] array=(Double[]) ar.toArray(new Double[ar.size()]);
				Shuffle.shuffle(array);
				System.out.println("Inception Sort");
				long time1 = System.nanoTime();
				inceptionSort.inceptionSort(array);                       //Calling Inception sort. 
		        long time2 = System.nanoTime();
		        
		        //inceptionSort.print(array);
				System.out.println("\n Time(second) for merge sort : " + (time2-time1)/Math.pow(10, 9));
	}
	
	
	
}
