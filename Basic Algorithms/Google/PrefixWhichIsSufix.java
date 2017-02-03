package Google;

public class PrefixWhichIsSufix {
	
	public static void main(String args[])
	{
		PrefixWhichIsSufix prefixSufix=new PrefixWhichIsSufix();
		String s="antaprezatepzapreanta";
		int temp[]=prefixSufix.computePrefixSuficArray(s);
		for(int i=0;i<temp.length;i++)
		{
			System.out.print(temp[i]+" ");
		}
		System.out.println();
		for(int i=0;i<temp.length;i++)
		{
			System.out.print(s.charAt(i)+" ");
		}
		int total=prefixSufix.computeSplit(temp, s);
		System.out.println();
		System.out.println(total);
	}

	public int computeSplit(int temp[],String s)
	{
		if(s.length()<=1)
			return s.length();
		else if(s.length()==2)
		{
			if(s.charAt(1)==s.charAt(0))
				return 1;
			else
				return 2;
		}
		int total=0;
		boolean valid=false;
		int passedTill=0;
		char arr[]=s.toCharArray();
		for(int i=temp.length-1;i>=0;i--)
		{
		      	if(arr[temp[i]]!=-1 && arr[temp[i]]==arr[i])
		      		{ 
		      		  temp[i]=-1;
		      		  valid=true;continue;
		      		}
		      	else
		      	{
		      		passedTill=i;
		      		break;
		      	}
		}
		if(!valid)
			return 1;
		int prev=temp[temp.length-1];
		for(int i=temp.length-2;i>=passedTill-1;i--)
		{
			 if(prev>temp[i])
			 {
				 prev=temp[i];
			 }
			 else
			 {
				 total++;
				 prev=temp[i];
			 }
		}
		System.out.println(((temp.length-1)-passedTill)+" "+temp.length+" "+passedTill);
		if(!(((temp.length-1)-passedTill)==temp.length))
			total++;
			
		return total;
	}
	public int[] computePrefixSuficArray(String s)
	{
		int[] arr=new int[s.length()];
		char[] temp=s.toCharArray();
		arr[0]=0;
		int j=0;
		boolean firstFound=false;
		for(int i=1;i<temp.length;)
		{
			if(temp[i]==temp[j])
			{
				arr[i]=j;
				firstFound=true;
				i++;j++;
			}
			else
			{
				if(firstFound)
					j=0;
				else
					j++;
				
				firstFound=false;
			    if(j>=i)
			        {j=0;i++;}
			}
		}
		return arr;
	}
}
