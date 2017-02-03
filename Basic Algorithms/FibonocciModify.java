import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.plaf.IconUIResource;
import javax.swing.plaf.synth.SynthSplitPaneUI;

public class FibonocciModify {

	public static long modValue1=(long) (Math.pow(10, 9)+7);
	public static int currentFiboEnd;
	public static int piscasoValue=2000000016;

	public static void main(String args[])
	{
		Scanner s=new Scanner(System.in);
		int t=s.nextInt();
		for(int i=0;i<t;i++)
		{
			int n=s.nextInt();
			long array[]=new long[n];
			for(int j=0;j<n;j++)
			{
				array[j]=s.nextInt();
			}

			long prefix[]=formPrefixArray(array);
			long maxNow=prefix[n-1];
			FiboItt(maxNow);
			System.out.println(calculateTotalFib(prefix));	
		}
	
	}
	
	public static void FiboItt(long max)
	{
		max=max%piscasoValue;
		for(;currentFiboEnd<=max;currentFiboEnd++)
		{
			
		}
		currentFiboEnd--;
	}
	
	public static BigInteger fibonacciMaths(long n)
	{
		BigInteger val1=new BigInteger(Double.toString(1.6180339));
		BigInteger val2=new BigInteger(Double.toString(-0.6180339));
		BigInteger n1=new BigInteger(Long.toString(n));
		BigInteger div=new BigInteger(Double.toString(2.236067977));
		val1=val1.pow((int)n);
		val2=val2.pow((int)n);
		BigInteger result=val1.subtract(val2);
		result=result.divide(div);
		return result;
	}
	
	
	
	public static long[] formPrefixArray(long[] list)
	{
		long prefix[]=new long[list.length];
		long sum=0;
		for(int i=0;i<list.length;i++)
		{
			sum=sum+list[i];
			prefix[i]=sum;
		}
		
		return prefix;
	}
	
	public static BigInteger calculateTotalFib(long[] prefix)
	{
		int numTerms=0;
		int index=0;
		BigInteger total=new BigInteger(Long.toString(0));
		BigInteger modValue=new BigInteger(Long.toString(modValue1));
		for(int i=0;i<prefix.length;i++)
		{
			while(index+numTerms<prefix.length)
			{
				long val=0;
				if(index>0)
				{
					val=prefix[(index+numTerms)]-prefix[index-1];		
				}
				else
				{
				    val=prefix[(index+numTerms)];
				}
				long modedValue=val%piscasoValue;
				BigInteger valTemp=fibonacciDAC(new BigInteger(Long.toString(modedValue)));
				total=total.add(valTemp);
				index=index+1;			
			}
			
			numTerms+=1;
			index=0;
		}
		return total.mod(modValue);
	}
	
	public static BigInteger fibonacciDAC(BigInteger n)
	{
		BigInteger[][] fib=new BigInteger[2][2];
		fib[0][0]=new BigInteger("1");
		fib[0][1]=new BigInteger("1");
		fib[1][0]=new BigInteger("1");
		fib[1][1]=new BigInteger("0");
		
		if(n.equals(new BigInteger("0")))
			return new BigInteger("0");
		
		power(fib,n.subtract(new BigInteger("1")));              // (fib)^(n-1) 
	    return fib[0][0];		                                 // (0,0) index of fib
	} 
	
	/**
	 * Procedure to calculate Matrix power n 
	 * @param fib
	 * @param n
	 */
	public static void power(BigInteger[][] fib,BigInteger n)
	{
		if(n.equals(new BigInteger("0"))||n.equals(new BigInteger("1")))
		{
			return ;
		}
		
		BigInteger[][] temp=new BigInteger[2][2];
		temp[0][0]=new BigInteger("1");
		temp[0][1]=new BigInteger("1");
		temp[1][0]=new BigInteger("1");
		temp[1][1]=new BigInteger("0");
		
		power(fib,n.divide(new BigInteger("2")));                   // compute (2*2) matrix and use it recursively
		multiply(fib, fib);
		
		if(!(n.mod(new BigInteger("2")).equals(new BigInteger("0"))))
		{
			multiply(fib,temp);                                     // if odd have to multiply with one extra matrix
		}
			
		
		
	}
	
	/**
	 * Procedure to multiply 2*2 matrix
	 * @param fib
	 * @param fib1
	 */
	public static void multiply(BigInteger[][] fib,BigInteger[][] fib1)
	{
	     BigInteger w = (fib[0][0].multiply(fib1[0][0])).add(fib[0][1].multiply(fib1[1][0]));
	     BigInteger x = (fib[0][0].multiply(fib1[0][1])).add(fib[0][1].multiply(fib1[1][1]));
	     BigInteger y = (fib[1][0].multiply(fib1[0][0])).add(fib[1][1].multiply(fib1[1][0]));
	     BigInteger z = (fib[1][0].multiply(fib1[0][1])).add(fib[1][1] .multiply(fib1[1][1]));
        
		fib[0][0]=w;                                 // simple 2*2 matrix calculations 
		fib[0][1]=x;
		fib[1][0]=y;
		fib[1][1]=z;

	}
	

}
