package Google;
import java.util.Arrays;

public class ThreeSum {

	public static void main(String args[])
	{
		int a[]={12, 3, 4, 1, 6, 9};	
		int k=24;
		Arrays.sort(a);
		boolean flag=false;
		for(int i=0;i<a.length;i++)
		{
			if(flag)
				break;
			int difference=k-a[i];
			int start=i+1;
			int end=a.length-1;
			while(start<end)
			{
				if(a[start]+a[end]==difference)
				{
					System.out.println(a[i]+" "+a[start]+" "+a[end]);
					flag=true;
					break;
				}
				else if(a[start]+a[end]<difference)
				{
					start++;
				}
				else
				{
					end--;
				}
			}
		}
	}
}
