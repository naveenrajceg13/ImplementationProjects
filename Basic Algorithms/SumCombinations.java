import java.util.Scanner;

public class SumCombinations {
	
    static int fact[];
	
	public static void main(String args[])
	{
		Scanner s=new Scanner(System.in);
		
		int n=s.nextInt();
		fact=new int[n+1];
		fact[1]=1;
		int x=s.nextInt();
		int total=0;
		int t=n;
		while(t>0)
		{
			int value=computerCombinations(n,t);
			System.out.println(value);
			total=value+total;
			t--;
		}
		int result=0;
		if(x>total)
		   result=x-total;
		else
		   result=total-x;
		System.out.println(result);
	}
	
	public static int computerCombinations(int n,int t)
	{
		
		int value=(fact(n)/(fact(t)*fact(n-t)));
		return value;
	}
	
	public static int fact(int n) {
		 
	    if (n == 0 || n == 1) {
	        return 1;
	    } else {
	        if (fact[n] != 0)
	            return fact[n];
	        else
	            return fact[n] = (n * fact(n - 1));
	    }
	 
	}

}
