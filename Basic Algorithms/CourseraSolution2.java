
public class CourseraSolution2 {
	
	public static void main(String args[])
	{
		String s="hefg";
		CourseraSolution2 cs=new CourseraSolution2();
		System.out.println(cs.nextPerumutation(s));
	}
	
	public String nextPerumutation(String word)
	{
		char[] nums=word.toCharArray();
		
		int p=0;
		for(int i=nums.length-2;i>=0;i--)
		{
			if(nums[i]<nums[i+1])
			{
				p=i;
				break;
			}
		}
		int q=0;
		for(int i=nums.length-1;i>p;i--)
		{
			if(nums[i]>nums[p])
			{
				q=i;
				break;
			}
		}
	
		if(p==0 && q==0)
			return "no answer";
		char temp=nums[p];
		nums[p]= nums[q];
		nums[q]=temp;
		if(p<nums.length-1)
			reverse(nums,p+1,nums.length-1);
		
		return new String(nums);
	}

	public void reverse(char nums[],int left,int right)
	{
		while(left<right)
		{
			char temp=nums[left];
			nums[left]=nums[right];
			nums[right]=temp;
			left++;
			right--;
		}
	}
}
