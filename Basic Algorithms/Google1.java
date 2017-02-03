
public class Google1 {
	
	
	public static void main(String args[])
	{
		Google1 g=new Google1();
		
		System.out.println(g.replaceMax(623315));
	}

	public int replaceMax(int num)
	{
		int toArrayNum[]=toIntArray(num);
		
		if(toArrayNum.length==2)
		{
			int res = (toArrayNum[0]+toArrayNum[1]);
			if(res%2!=0)
				res+=1;
			return res/2;
		}
		
		int max=0;
		boolean flag=true;
		for(int i=0;i<toArrayNum.length-1;i++)
		{
		        int num1=toArrayNum[i];
		        int num2=toArrayNum[i+1];
		        
		        int count=0;
		        int mul=1;
		        for(int j=0;j<=toArrayNum.length-1;j++)
		        {
		        	if(j==i+1)
		        		continue;
		        	if(j!=i)
		        	{
		        	   count+=toArrayNum[j];
		        	}
		        	else
		        	{
		        	   int res=num1+num2;
		        	   if(res%2!=0)
		        	   {
		        		   res=res+1;
		        	   }
		        	   count+= res/2;
		        	}
		        	count=count*10;
		        }
		        
		        count=count/10;
		       // System.out.println(count);
		        if(flag)
		        {
		        	max=count;
		        	flag=false;
		        }
		        else
		        {
		        	if(max<count)
		        	{
		        		max=count;
		        	}
		        }
		}
		return max;
	}
	
	public int[] toIntArray(int num)
	{
		int count=0;
		int temp=num;
		
		while(temp>0)
		{
			count++;
			temp/=10;
		}
		
		int result[] = new int[count];
		int index=count-1;
		temp=num;
		while(temp>0)
		{
			result[index]=temp%10;
			temp/=10;
			index--;
			
		}

		return result;
	}
}
