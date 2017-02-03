import java.util.LinkedList;
import java.util.List;

public class Kth_element_from_end {
	
	public static void main(String args[])
	{
		Kth_element_from_end kth_element=new Kth_element_from_end();
		SinglyLinkedList<Integer> linkedList=new SinglyLinkedList<Integer>();
		for(int i=0;i<30;i++)
		{
			linkedList.add(i);
		}
		System.out.println(kth_element.returnKthElementFromEnd(linkedList, 5));
	}
	public int returnKthElementFromEnd(SinglyLinkedList list,int k)
	{
	    SinglyLinkedList<Integer>.Entry<Integer> head=list.header.next;
	    SinglyLinkedList<Integer>.Entry<Integer> head_slow=list.header.next;
	    for(int i=0;i<k && head.next!=null;i++)
	    {
	    	head=head.next;
	    }
	    while(head!=null)
	    {
	    	head_slow=head_slow.next;
	    	head=head.next;
	    }
		return head_slow.element;
	}

}
