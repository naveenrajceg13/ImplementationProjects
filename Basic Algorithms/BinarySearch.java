
public class BinarySearch {
	
	public int binarySearch(int a[],int start,int end)
	{
		if(start<end)
		{
			int mid=(start+end)/2;
			if(a[mid]==mid)
				return mid;
			if(mid<a[mid])
				return binarySearch(a,start,mid-1);
			else
				return binarySearch(a,mid+1,end);
		}
		return -1;
	}
	public static void main(String args[])
	{
		int a[]={-10, -5, 0, 3, 7};
		System.out.println(new BinarySearch().binarySearch(a, 0, a.length-1));
	}

}
