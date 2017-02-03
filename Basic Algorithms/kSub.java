import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class kSub {
	
	 static int isSubsetSum(int set[], int n, int sum,int k)
	    {
	       // Base Cases
	       if (sum%3==0)
	         return 1;
	       if (n == 0 && sum != 0)
	         return 0;
	      
	       // If last element is greater than sum, then ignore it
	       if (set[n-1] > sum)
	         return isSubsetSum(set, n-1, sum,k);
	      
	       /* else, check if sum can be obtained by any of the following
	          (a) including the last element
	          (b) excluding the last element   */
	       return isSubsetSum(set, n-1, sum,k) +
	                                   isSubsetSum(set, n-1, sum-set[n-1],k);
	    }
	    /* Driver program to test above function */
	    public static void main (String args[])
	    {
	          int set[] = {1,2,3,4,2,1};
	          int sum = 3;
	          int n = set.length;
	          System.out.println(isSubsetSum(set, n, sum,3));
	            
	    }
}
