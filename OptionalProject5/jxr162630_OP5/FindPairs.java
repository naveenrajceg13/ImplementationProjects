

/**
 * @author Naveenraj, Niveditha, Jegathis,Archana
 * program to find how many pairs of elements of A sum to X 
 * 
 */

import java.util.Scanner;
import java.util.TreeMap;

public class FindPairs {
	
	
	public static void main(String args[]){
		
		FindPairs findPairs=new FindPairs();
		Scanner in=new Scanner(System.in);
		System.out.println("Enter the size of array");
		int n=in.nextInt();
		int a[] = new int[n];
			
		System.out.println("Enter the values of array");
		for(int i=0;i<n;i++){
			a[i]=in.nextInt();
		}
		
		System.out.println("Enter the X value:");
		int X=in.nextInt();
		int result=findPairs.findPairsUsingTreeMap(a,X);
		
		System.out.println("Number of Pairs "+result);
		
		System.out.println(result);
		
	}
/**
 * Method to find the number of pairs that add to form the given number's sum
 * @param A[] 
 * @param x
 * @return count
 */
	public int findPairsUsingTreeMap(int A[],int x)
	{
		TreeMap<Integer,Integer> treeMap=new TreeMap<>();
		
		//Insert the elements of array with count specifying number of times it occurs into treemap
		for(int i=0;i<A.length;i++)
		{
		     if(treeMap.get(A[i])!=null)
		     {
		    	 treeMap.put(A[i],treeMap.get(A[i])+1);
		     }
		     else
		     {
		    	 treeMap.put(A[i],1);
		     }
		}
		int count=0;
		// For each key in treemap find a count of number of pairs that gives the sum 'x'
		for(int value1: treeMap.keySet())
		{
			int value2=x-value1;	
			int result=0;
			if(treeMap.get(value2)!=null || treeMap.get(value2)!= 0)
			{
			   int count2=treeMap.get(value2);
			   int count1=treeMap.get(value1);
			   
			   
			   if(value2==value1)
			   {
				   result=(count2*(count2-1))/2;
			   }
			   else
			   {
				   result=count2*count1;
				   treeMap.put(value1,0);
				   treeMap.put(value2,0);
			   }
			}
			count+=result;
		}
		return count;
	}	

}
