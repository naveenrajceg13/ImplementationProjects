
public class Twitter4 {
	
	static int maxLength(int[] a, int k) {
        int curMax=0;
        int curMaxTot=0;
        int dp[]=new int[a.length];
        boolean flag=true;
        for(int i=0;i<a.length;i++)
        {
             if(flag)
             {
                 dp[i]=a[i];
                 flag=false;
             }
             else
             {
                 dp[i]=dp[i-1]+a[i];
             }
        }
        for(int i=a.length-1;i>=0;i--)
        {
            for(int j=0;(j+i)<a.length;j++)
            {
                int subSub=0;
                if(j!=0)
                {
                    subSub=dp[j-1];
                }
                if((dp[(j+i)]-subSub)<=k)
                    {
                	       curMaxTot=(i+1);
                           return curMaxTot;
                        
                    } 
            }
        }
        
        return curMaxTot;
    }



}

/*
 * Complete the function below.
 */

    