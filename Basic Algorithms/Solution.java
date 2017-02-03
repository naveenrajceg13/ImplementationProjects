
public class Solution {
	
	
	public void commonSubString(String a[],String b[])
	{
		int n=a.length;
		
		for(int i=0;i<n;i++)
		{
			int aLength=a[i].length();
			int bLength=b[i].length();
			int min=0;
			
			if(aLength>bLength)
				min=aLength;
			else
				min=bLength;
			char[] first=a[i].toCharArray();
			char[] second=b[i].toCharArray();
			boolean flag=false;
			int[] count=new int[258];
			for(int j=0;j<aLength;j++)
			{
				count[first[j]]=count[first[j]]+1;
			}
			for(int j=0;j<bLength;j++)
			{
				if(count[second[j]]>=1)
				{
				count[second[j]]=count[second[j]]+1;
				flag=true;
				break;
				}
			}
			
			if(flag)
			System.out.println("YES");
			else
			System.out.println("NO");
		}
	}

	public static void main(String args[])
	{
		Solution s=new Solution();
		String[] a={"Hello","Hi"};
		String[] b={"world","it"};
		
		s.commonSubString(a, b);
	}
}
