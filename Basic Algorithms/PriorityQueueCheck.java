import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueCheck {
	
	
	public static void main(String args[])
	{
		PriorityQueue<Integer> pq=new PriorityQueue<>(Comparator.reverseOrder());
		
		pq.add(1);
		pq.add(4);
		pq.add(5);
		pq.add(7);
		pq.add(2);
		pq.add(3);
		
		
		for(int i=0;i<32;i++)
		{
			System.out.println(7>>1);
		}
		//System.out.println(pq.peek());
	}

}
