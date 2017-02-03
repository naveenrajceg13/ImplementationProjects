import java.util.Scanner;

public class CatalanNumber {
	
	static long[] catalan;	
	static long[] sum;
	static final long modValue=((long)Math.pow(10, 9)+7);
	static int lastSet=1;
	public static void main(String args[])
	{
		
		Scanner s=new Scanner(System.in);
		int t=s.nextInt();
		int[] array=new int[t];
		boolean flag=true;
		int max=0;
		for(int i=0;i<t;i++)
		{
			array[i]=s.nextInt();
			if(flag)
			{
				max=array[i];
			}
			else
			{
			    if(max<array[i])
			    	max=array[i];
			}
		}
		
		int maxCount=(max/2)+1;
		//System.out.println(maxCount);
		catalan=new long[maxCount];
		sum=new long[maxCount];
		catalan[0] = catalan[1] = 1;
		sum[0]=sum[1]=1;
		
		for(int i=0;i<t;i++)
		{
			int currentValue=array[i]/2;
			//System.out.println(currentValue+" do ur work");
			if(currentValue!=0)
			  System.out.println(catalanDP(currentValue));
			else
			  System.out.println(0);
		}
			
	}
	
	
	public static long catalanDP(int n)
	{
	    if(catalan[n]!=0)
	    	return sum[n];
	    for (int i=lastSet+1; i<=n; i++)
	    {
	        catalan[i] = 0;
	        for (int j=0; j<i; j++)
	            catalan[i] += (((catalan[j]%modValue) * (catalan[i-j-1])%modValue)%modValue);    
	        sum[i]=(sum[i-1]+catalan[i])%modValue;
	    }
	    lastSet=n;
	    return sum[n];
	}
}
