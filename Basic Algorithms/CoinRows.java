import java.util.Scanner;

public class CoinRows {
	
	public static void main(String args[])
	{
		int num=4;
		int value=3;
		long[] values= {(long)Math.pow(10, 10),5,8,3};
		CoinRows cr=new CoinRows();
		cr.maxFullstairs(values);
	}
	
	public void maxFullstairs(long[] values)
	{
		for(int i=0;i<values.length;i++)
		{
            long currentNum=values[i];
            long number=(long)Math.ceil( Math.sqrt(2*currentNum));
            while(true)
            {
            	long totalSum=(long)(number*(number+1))/2;
            	if(totalSum<=currentNum)
            	{
            		System.out.println(number);
            		break;
            	}
            	number--;

            }
		}
	    
	}

}
	
