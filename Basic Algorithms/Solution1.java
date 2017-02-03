import java.util.Collections;
import java.util.PriorityQueue;

public class Solution1 {
    class Pivot
    {
        int pivot1;
        int pivot2;
        
        Pivot(int pivot1,int pivot2)
        {
            this.pivot1=pivot1;
            this.pivot2=pivot2;
        }
    }
    public int findKthLargest(int[] nums, int k) {    // kth smallest = n-kthlargest+1. 6 nums, 2nd largest is 5th smallest
        
    	return getKthLargest(nums,nums.length-k,0,nums.length-1);
    }
    
    public void print(int[] nums)
    {
    	System.out.println();
    	for(int num: nums)
    		System.out.print(num+" ");
    }
    public int getKthLargest(int[] nums,int k,int start,int end)
    {
           Pivot pivot=doDuealPivot(nums,start,end);
           if(k>end) return -1;
           while(true)
           {
               if(pivot.pivot1==k) return nums[pivot.pivot1];
               if(pivot.pivot2==k) return nums[pivot.pivot2];
               
               if(k<pivot.pivot1) 
                   end=pivot.pivot1-1;
               else if(k>pivot.pivot2){
                   start=pivot.pivot2+1;}
               else{
                   start=pivot.pivot1+1;
                   end=pivot.pivot2-1;
               }
               
               pivot=doDuealPivot(nums,start,end);
           }
    }
    
    Pivot doDuealPivot(int[] nums,int start,int end)
    {
        
        if(nums[start]>nums[end])
            swap(nums,start,end);    //[3,2,1,5,6,4] //leftPivot- 3, rightPivot -4  -- 1,2,3,4,5,6 -- pivot1-2 , pivot2-3 
        
        int leftPivot=nums[start];
        int rightPivot=nums[end];
        
        int movingPrt=start+1;
        int larestLast=end-1;
        int smallestLast=movingPrt;
        
        while(movingPrt<=larestLast)
        {
            if(nums[movingPrt]<leftPivot){                    
                swap(nums,movingPrt,smallestLast);
                movingPrt++; 
                smallestLast++;
            }
            else if(nums[movingPrt]>rightPivot)
            {
                swap(nums,movingPrt,larestLast);
                larestLast--;
            }
            else
            {
                movingPrt++;
            }
        }
        swap(nums,start,--smallestLast);
        swap(nums,end,++larestLast);
        Pivot pivot=new Pivot(smallestLast,larestLast);
        return pivot;
    }
    
    public void swap(int[] nums,int loc1,int loc2)
    {
        int temp=nums[loc1];
        nums[loc1]=nums[loc2];
        nums[loc2]=temp;
    }
    
    public static void main(String args[])
    {
    	Solution1 s1=new Solution1();
    	int[] nums={3,2,1,5,6,4};
    	int k=2;
    	System.out.println(s1.findKthLargest(nums, k));
    }
}