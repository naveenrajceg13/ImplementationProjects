import java.util.Collections;
import java.util.PriorityQueue;

public class ZigZagArray {

	public static void main(String args[])
	{
		ZigZagArray zig=new ZigZagArray();
		
		int a[]={-27676,211621,904304,161270,-292822,732004,860511,-825806,-721722,536428,-927571,-28004};
		
		int result[]=zig.wiggleArrangeArray(a);
		
		for(int i=0;i<result.length;i++)
		{
			System.out.println(result[i]);
		}
	}
	static int[] wiggleArrangeArray(int[] intArr)
	{
		
		PriorityQueue<Integer> minPq=new PriorityQueue<>();
		PriorityQueue<Integer> maxPq=new PriorityQueue<>(intArr.length,Collections.reverseOrder());
		int[] result=new int[intArr.length];
		for(int i=0;i<intArr.length;i++)
		{
			minPq.add(intArr[i]);
			maxPq.add(intArr[i]);
		}
		boolean flag=true;
		for(int i=0;i<intArr.length;i++)
		{
			if(flag)
			{
				result[i]=maxPq.poll();
				flag=false;
			}
			else
			{
				result[i]=minPq.poll();
				flag=true;
			}
		}
		return result;
	}
}
