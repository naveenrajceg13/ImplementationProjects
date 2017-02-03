
public class unionIntersectionList {

	public static void main(String args[])
	{
		unionIntersectionList unionIntersection=new unionIntersectionList();
		SinglyLinkedList<Integer> linkedList=new SinglyLinkedList<Integer>();
		SinglyLinkedList<Integer> linkedList1=new SinglyLinkedList<Integer>();
		for(int i=0;i<3;i++)
		{
			linkedList.add(i);
		}
		for(int i=0;i<3;i++)
		{
			linkedList1.add(i);
		}
		unionIntersection.union(linkedList, linkedList1).printList();
		
	}
	public SinglyLinkedList<Integer> union(SinglyLinkedList<Integer> first,SinglyLinkedList<Integer> second)
	{
		SinglyLinkedList<Integer>.Entry<Integer> first_node=first.header.next;
		SinglyLinkedList<Integer>.Entry<Integer> second_node=second.header.next;
		SinglyLinkedList<Integer>.Entry<Integer> head=first.header;
		SinglyLinkedList<Integer>.Entry<Integer> tail=head;
	
		while(first_node!=null || second_node!=null)
		{
			if(first_node!=null && second_node != null )
			{
				if(first_node.element <= second_node.element)
				{
					tail.next=first_node;
					first_node=first_node.next;
				}
				else
				{
					tail.next=second_node;
					second_node=second_node.next;
				}
				tail=tail.next;
			}
			else if(first_node==null)
			{
				tail.next=second_node;
				break;
			}
			else
			{
				tail.next=first_node;
				break;
			}
		}
		//printlist(head.next);
		first.header.next=head.next;
		return first;
	}
	public void printlist(SinglyLinkedList<Integer>.Entry<Integer> node)
	{
		System.out.println("List now");
		while(node!=null)
		{
			System.out.print(node.element+" ");
			node=node.next;
		}
		System.out.println("\n");
	}
}
