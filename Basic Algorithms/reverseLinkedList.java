
public class reverseLinkedList {

	public static void main(String args[])
	{
		reverseLinkedList reverseList=new reverseLinkedList();
		SinglyLinkedList<Integer> linkedList=new SinglyLinkedList<Integer>();
		for(int i=0;i<30;i++)
		{
			linkedList.add(i);
		}
		reverseList.reverseList(linkedList);
		linkedList.printList();
	}
	public SinglyLinkedList<Integer> reverseList(SinglyLinkedList<Integer> node)
	{
		SinglyLinkedList<Integer>.Entry<Integer> current=node.header.next;
		SinglyLinkedList<Integer>.Entry<Integer> next_node=null;
		SinglyLinkedList<Integer>.Entry<Integer> prev=null;
		while(current!=null)
		{
			next_node=current.next;
			current.next=prev;
			prev=current;
			current=next_node;
		}
		node.header.next=prev;
		return node;
	}
}
