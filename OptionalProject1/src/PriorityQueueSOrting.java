import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author Naveenraj, Niveditha, Jagathis
 * Class to add elements in PriorityQueue 
 * @param <T> 
 */

public class PriorityQueueSOrting<T> {
	

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
	 * Add elements to Priority Queue, by default it will be sorted. 
	 * @param args : String [] : File name contains value to be sorted. 
	 */
	
	public static void main(String args[])
	{
		// Object to call non static methods. 
		PriorityQueueSOrting<Double> priorityQueueSOrting= new PriorityQueueSOrting<>();
		
		// Use Java Min Heap ( PriortyQ Queue )
		PriorityQueue<Double> priorityQueue=new PriorityQueue<>();
		File f;
		Scanner in;
		long time1=0;
		try{
			if(args.length>0)
			{
				f=new File(args[0]);  // read file name from command line
			}
			else
			{
				f=new File("inputFile.txt"); // use that is provided
			}
			
			in=new Scanner(f);
			
		    time1 = System.nanoTime();
		
			while(in.hasNextDouble())
			{
				priorityQueue.add(in.nextDouble()); //add elements in sorted order. Priority Queue default adds elements in sorted order. 	
			}
			
			long time2 = System.nanoTime();
			
			System.out.println("Time(second) for merge sort : " + (time2-time1)/Math.pow(10, 9));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
