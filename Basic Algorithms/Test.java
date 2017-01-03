
public class Test {
	
	public static void main(String args[])
	{
		int nums[]={1,2,2};
	
		for(int i=0;i<(int)Math.pow(2, nums.length);i++)
		{
			for(int k=0;k<nums.length;k++)
			{
			     if(k>0)
			    	 System.out.println(i+" "+k+" "+(i>>(k-1)&1));
			}
		}
	}

}
