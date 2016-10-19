/**
 * @author Naveenraj, Niveditha, Jegathis,Archana
 * program to find non repeating elements in a given array
 * 
 */




import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;



public class NonRepeatingElements {
	
	/**
	 * Method to find non repeating elements in the given array
	 * @param A[] 
	 * @param b
	 * @return b_array[]
	 */
	public<T extends Comparable<? super T>> Comparable[] exactlyOnce(T[] A){
	
		ArrayList<T> b=new ArrayList<T>();
		
		
		Map<T,Integer> treeMap=new TreeMap<T,Integer>();
		////Insert the elements of array with count specifying number of times it occurs into treemap
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
		//add the elements to aarraylist
		for(int i=0;i<A.length;i++){
			if(treeMap.get(A[i])==1){
				b.add(A[i]);
				
			}
		
		}
	
	//move the elements in aaray list to array in same order
	Comparable[] b_array= new Comparable[b.size()];
	int i=0;
	for(T item: b){
		b_array[i]=item;
		i++;
	}
	return b_array;
		
	}
	public static void main(String args[]){
		
		NonRepeatingElements ne=new NonRepeatingElements();
		Scanner in=new Scanner(System.in);
		System.out.println("Enter the size of array");
		int n=in.nextInt();
		Integer a[] = new Integer[n];
			
		System.out.println("Enter the values of array");
		for(int i=0;i<n;i++){
			a[i]=in.nextInt();
		}
		
		Comparable[] b=ne.exactlyOnce(a);
		System.out.println("Non Repeating Elements");
		for(Comparable x:b)
		{
			System.out.print(x+" ");
		}
		
}
}