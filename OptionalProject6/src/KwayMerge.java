
public class KwayMerge<T> {
	
	
	public <T extends Comparable<? super T>> void mergesort(T arr[],int start,int end,int k)
	{
		
		if(start<end)
	    {
	    	int mid=(start+end)/k;
	    	
	    	int st=start;
	    	int en=mid;
	    	
	    	for(int i=0;i<k;i++)
	    	{
	    	   System.out.println(st+" "+en+" "+mid);
	    	   mergesort(arr,st,en,k);
	    	   st=mid+1;
	    	   en=st+mid;
	    	}
	    }
	}
	
	public static void main(String args[])
	{
		KwayMerge<Double> kMerge=new KwayMerge<>();
		Double[] array={4.0,4.5,3.2,5.6,7.8,1.2,3.4,5.6,7.9,8.0,11.0,12.0};
		kMerge.mergesort(array, 0, array.length-1,2);
	}

}
