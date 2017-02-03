
public class nodesBetweenAandBfront {
	
	public static void main(String args[])
	{
		nodesBetweenAandBfront nodesBetweenAandBfront=new nodesBetweenAandBfront();
		SinglyLinkedList<Integer> linkedList=new SinglyLinkedList<Integer>();
		for(int i=0;i<10;i++)
		{
			linkedList.add(i);
		}
		nodesBetweenAandBfront.nodesBetweenAandBfrontChange(linkedList, 1, 6).printList();
	}
	
	public SinglyLinkedList<Integer> nodesBetweenAandBfrontChange(SinglyLinkedList<Integer> linkedList,int a, int b)
	{
		SinglyLinkedList<Integer>.Entry<Integer> head=linkedList.header; 
		SinglyLinkedList<Integer>.Entry<Integer> tail=head;
		SinglyLinkedList<Integer>.Entry<Integer> front_tail=head;
		SinglyLinkedList<Integer>.Entry<Integer> prev=null;
		tail=head.next;
		front_tail=head;
		while(tail!=null)
		{
			if(tail.element>=a && tail.element<=b)
			{
				if(tail==front_tail)
					continue;
				else{
					 prev.next=tail.next;
					 tail.next=front_tail.next;
					 front_tail.next=tail;
					 tail=prev;
				 }
				front_tail=front_tail.next;
				
			}
			prev=tail;
			tail=tail.next;
		}
		linkedList.header=head;
		return linkedList;
	}

}
