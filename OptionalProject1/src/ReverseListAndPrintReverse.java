
/**
 * @author Naveenraj, Niveditha, Jagathis
 * Class to reverse Linked list using O(1) space and O(n) space and |||rly for print.
 * @param <T>
 */
public class ReverseListAndPrintReverse<T> {
	
	/**
	 * Main function to create list and call functions 
	 * @param args
	 */
	public static void main(String args[])
	{
		ReverseListAndPrintReverse<Integer> reverseList=new ReverseListAndPrintReverse<>();
		
		//create Empty list
		SinglyLinkedList<Integer> list1=new SinglyLinkedList<>();
		SinglyLinkedList<Integer> list2=new SinglyLinkedList<>();
		SinglyLinkedList<Integer> list3=new SinglyLinkedList<>();
		
		//add values to the list
		for(int i=0;i<10;i++)
		{
			list1.add(i);
			list2.add(i);
			list3.add(i);
		}
		
		//reverse list using O(1) space
		reverseList.print(reverseList.IttReverse(list1));
		
		//reverse list using O(n) space
		list2.header.next=reverseList.recursiveReverse(list2.header.next);
		reverseList.print(list2);
		
		//print list using O(n) space
		reverseList.reversePrint(list3.header.next);
		
		//print list using O(1) space
		reverseList.reversePrintNoExtraSpace(list3);
	}
	
	/**
	 * procedure to print linked list in reverse using recursion
	 * @param head : Entry<T> : head of list to print reverse
	 */
	public <T extends Comparable<? super T>> void reversePrint(SinglyLinkedList<T>.Entry<T> head)
	{
		if(head==null)
			return;
		reversePrint(head.next);
		System.out.print(head.element+" ");	
	}
	
	/**
	 * Reverse Print and Reverse again 
	 * @param list : List<T> : List to print in reverse
	 */
	public <T extends Comparable<? super T>> void reversePrintNoExtraSpace(SinglyLinkedList<T> list)
	{
		IttReverse(list); //reverse
		print(list);      // print 
		IttReverse(list); // print 
	}
	
	/**
	 * @param list : List<T> : List to print linked list
	 */
	public <T extends Comparable <? super T>> void print(SinglyLinkedList<T> list)
	{
		SinglyLinkedList<T>.Entry<T> head=list.header.next;
		System.out.println();
		while(head!=null)
		{
			System.out.print(head.element+" ");  //loop till end of list and print
			head=head.next;
		}
	}
	/**
	 * Procedure to reverse a linked list
	 * @param list1 : List<T> : List to reserve 
	 * @return : List<T> : reversed list
	 */
	public <T extends Comparable <? super T>> SinglyLinkedList<T> IttReverse (SinglyLinkedList<T> list1)
	{
		SinglyLinkedList<T>.Entry<T> head=list1.header.next;
		SinglyLinkedList<T>.Entry<T> current=head;
		SinglyLinkedList<T>.Entry<T> prev=null;
		SinglyLinkedList<T>.Entry<T> next_node=null;
		
		while(current!=null)
		{
			next_node=current.next; // make the latest node as current 
			current.next=prev;      // add previous list formed as next of current. 
			prev=current;            
			current=next_node;      //update current
		}
		list1.header.next=prev;     //update head
		return list1;
	}
	
	/**
	 * Procedure to reverse a linked list
	 * @param list1 : List<T> : List to reserve 
	 * @return : List<T> : reversed list
	 */
	public <T extends Comparable <? super T>> SinglyLinkedList<T>.Entry<T> recursiveReverse (SinglyLinkedList<T>.Entry<T> head)
	{
		if(head==null || head.next ==null)
		{
		     return head;
		}
		
		SinglyLinkedList<T>.Entry<T> next_node=head.next; //next node is moved to next node 
		
		head.next=null;  //update next of current node to null
		
		SinglyLinkedList<T>.Entry<T> rest=recursiveReverse(next_node);  //recurse 
		
		next_node.next=head;  //keep forming list now 
		
		return rest;
	}

}
