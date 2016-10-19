
public class MergeSort {
	
	public static void main(String args[]){
		
		MergeSort mergeSort=new MergeSort();
		int array[]={4,3,5,2,6,8,11,45,8,1,34,12,14};
		mergeSort.mergesort(array, 0,array.length-1);
		mergeSort.print(array);
	}
	
	public void print(int[] arr)
	{
		for(int i=0;i<arr.length;i++)
		{
			System.out.print(arr[i]+" ");
		}
	}
	
	public void mergesort(int arr[],int start,int end)
	{
	    if(start< end)
	    {
	    	//find mid point
	    	int mid=(start+end)/2;
	    	
	    	//sort first half
	    	mergesort(arr,start,mid);
	    	
	    	//sort second half
	    	mergesort(arr,mid+1,end);
	    	
	        //merge sorted portions
	    	merge(arr,start,mid,end);
	    }
	}
	
	public void merge(int arr[],int start,int mid,int end)
	{
		//find the length of left and right sub array to be merged
		int left_array_size=mid-start+1;
		int right_array_size=end-mid;
		
		//copy elements into temp array
		int[] left_array=new int[left_array_size];
		int[] right_array=new int[right_array_size];
		
		for(int i=0;i<left_array_size;i++)
		{
			left_array[i]=arr[start+i];
		}
		
		for(int i=0;i<right_array_size;i++)
		{
			right_array[i]=arr[mid+1+i];
		}
		
		//index of left array and right array set to 0 to traverse 
		int left_array_index=0;
		int right_array_index=0;
		
		//index of the merge array is set to start
		int merge_array_index=start;
		
		//loop till either of array reaches its end
		while(left_array_index<left_array_size && right_array_index<right_array_size)
		{
			
			if(left_array[left_array_index]<right_array[right_array_index])
			{
				arr[merge_array_index]=left_array[left_array_index];
				left_array_index++;
			}
			else
			{
				arr[merge_array_index]=right_array[right_array_index];
				right_array_index++;
			}
			merge_array_index++;
			
		}
		
		// if right array reaches end, then copy all remaining left array elements alone 
		while(left_array_index<left_array_size)
		{
			arr[merge_array_index]=left_array[left_array_index];
			left_array_index++;
			merge_array_index++;
		}
		
		//if left array reaches end, then copy all remaining right array elements alone
		while(right_array_index<right_array_size)
		{
			arr[merge_array_index]=right_array[right_array_index];
			right_array_index++;
			merge_array_index++;
		}
		
	}

}
