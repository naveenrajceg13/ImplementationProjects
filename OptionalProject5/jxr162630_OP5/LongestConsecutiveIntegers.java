/**
 * @author Naveenraj, Niveditha, Jegathis,Archana
 * program to find longest consecutive integers in array
 * 
 */




import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;



public class LongestConsecutiveIntegers {
	
	static int currentSequenceLength=1;
	static int fullSequenceLength=1;

	
	
public static int longestStreak(int[] A){
	
	/**
	 * Method to find longest streak in the given array
	 * @param A[] 
	 * @param currentSequenceLength
	 * @return fullSequenceLength
	 */
	
	Set<Integer> tree=new TreeSet<>();
		
		for(int x:A){
			tree.add(x);
		}
		
		int[] A_1=new int[A.length];
		
				
		Iterator<Integer> it=tree.iterator();
		int prev=it.next();
		int current;
		while(it.hasNext()){
			current=(int) it.next();
			if(prev==current-1){
				currentSequenceLength++;
			}
			else{
				currentSequenceLength=1;
			}
			prev=current;
			if(currentSequenceLength>fullSequenceLength){
				fullSequenceLength=currentSequenceLength;
			}
		}
		return fullSequenceLength;
}
	
public static void main(String args[]){
	
	
	Scanner in=new Scanner(System.in);
	System.out.println("Enter the size of array");
	int n=in.nextInt();
	int a[] = new int[n];
		
	System.out.println("Enter the values of array");
	for(int i=0;i<n;i++){
		a[i]=in.nextInt();
	}
	
	
	
	int consecutivelength=longestStreak(a);
	System.out.println("Consecutive Sequence Length " +consecutivelength );

	
}
}
