
public class LargestEqualArray {
	
	public class Count
	{
		int zero;
		int one;
		Count(int zero,int one)
		{
			this.one=one;
			this.zero=zero;
		}
	}
	public static void main(String args[])
	{
		int a[]={0, 0, 1, 1, 0};
		Count count[]=new Count[a.length];
		int zeroCount=0;
		int oneCount=0;
		for(int i=0;i<a.length;i++)
		{
			if(a[i]==0)
				zeroCount++;
			else
				oneCount++;
			
			Count c=new LargestEqualArray(). new Count(zeroCount,oneCount);
			count[i]=c;
		}
		
		int start=0;
		int end=a.length-1;
		int prevZero=0;
		int prevOne=0;
		while(start<end)
		{
			Count c=count[end];
			int lastZeroCount=c.zero-prevZero;
			int lastOneCount=c.one-prevOne;
			
			if(lastZeroCount==lastOneCount)
			{
				System.out.println(start+" "+end);
				break;
			}
			else if(lastZeroCount>lastOneCount)
			{
				if(a[start]==0)
					start++;
				else
					end--;
			}
			else
			{
				if(a[end]==0)
					start++;
				else
					end--;
			}
			
			if(start!=0)
			{
				prevZero=count[start-1].zero;
				prevOne=count[start-1].one;
			}
		}
		
		System.out.println("finish");
	}

}
