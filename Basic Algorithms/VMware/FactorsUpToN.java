package VMware;

public class FactorsUpToN {
	
	public static void main(String args[])
	{
		FactorsUpToN fact=new FactorsUpToN();
		System.out.println(fact.getSumofFactUpToN(1));
	}
	
	public int getSumofFactUpToN(int num)
	{
		int prevSum=0;
		for(int i=1;i<=num;i++)
		{
			prevSum=getSumFactors(100)+prevSum;
		}
		return prevSum;
	}

	public int getSumFactors(int num)
	{
		int count=0;
		for(int i=1;i<=(int)(Math.sqrt(num));i++)
		{
			if(i*i==num && i%2!=0)
				count+=i;
			else if(num%i==0)
			{
				if((num/i)%2!=0)
				    count+=(num/i);
				if(i%2!=0)
				    count+=i;
			}
		}
		
		return count;
	}
}
