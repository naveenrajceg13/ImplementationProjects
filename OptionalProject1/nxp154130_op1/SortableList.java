import java.util.Scanner;

/**
 * @author Naveenraj, Niveditha, Jagathis
 *
 * @param <T>
 */
public class SortableList<T extends Comparable<? super T>> extends SinglyLinkedList<T> 
{
	
    public SortableList<T> merge(SortableList<T> l2) {
    	// get the first node of the two lists to be merged and set it as current node
    	SortableList<T>.Entry<T> currentNode_l1 = this.header.next;
    	SortableList<T>.Entry<T> currentNode_l2 = l2.header.next;
    	SortableList<T> mergedList=new SortableList<T>();
    	
    	// set the header of the merged list as the current node of the merged list
    	SortableList<T>.Entry<T> currentNode_mergedList = mergedList.header;
    	
    	// Apply the traditional merge algorithm to get the merged list ans return the merged list
    	while(currentNode_l1 != null && currentNode_l2 != null)
    	{
    		if(currentNode_l1.element.compareTo(currentNode_l2.element)<=0)
    		{
    			currentNode_mergedList.next =  currentNode_l1;
    			currentNode_l1 = currentNode_l1.next;
    		}
    		else
    		{
    			currentNode_mergedList.next =  currentNode_l2;
    			currentNode_l2 = currentNode_l2.next;
    		}
    		currentNode_mergedList = currentNode_mergedList.next;    		
    	}
    	if(currentNode_l1!=null)
		{
    		currentNode_mergedList.next=currentNode_l1;
		}
		if(currentNode_l2 !=null)
		{
			currentNode_mergedList.next= currentNode_l2;
		}
		return mergedList;
    }

    
    /**
     * Function: size
     * Description: calculates the size of the list
     */
    public int size()
    {
    	int size = 0;
    	SortableList<T>.Entry<T> currentNode = this.header.next;
    	while(currentNode != null)
    	{
    		size++;
    		currentNode = currentNode.next;
    	}
    	return size;
    }

    public static<T extends Comparable<? super T>> SortableList<T> mergeSort(SortableList<T> lst) {
    	
    	// get the size of the list
    	int size = lst.size();
    	
    	// if the list has more than one element, find the mid of the list and split the list into two
    	// make recursive calls to mergeSort each of the two lists
    	// finally call merge function to merge the sorted lists.
    	// The first list is cut in the middle and a new header is made to point the other half of the list.
    	// This ensures that only O(log n) extra space is consumed.
    	if(size>1)
    	{
    		int mid = size/2;
    		SortableList<T> list2 = new SortableList<T>();
    		SortableList<T>.Entry<T> currentNode = lst.header;
    		for (int i=0; i<mid;i++)
    		{
    			currentNode = currentNode.next;
    		}
    		lst.tail = currentNode;
    		list2.header.next = currentNode.next;
    		lst.tail.next = null;
    		lst = mergeSort(lst);
    		list2 = mergeSort(list2);
    		return lst.merge(list2);
    		
    	}
    	else
    	{
    		return  lst;
    	}
    	
    }

    public static void main(String[] args) {
    	SortableList<Integer> list = new SortableList<>();
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Enter the number of elements in the list: ");
		int list_size = scanner.nextInt();
		System.out.println("Enter all the " + list_size + " of the list: ");
		for(int i = 0; i < list_size; i++) {
			int value = scanner.nextInt();
			list.add(value);
		}
		scanner.close();
		System.out.println("List before merge sort: ");
		list.printList();
		list = mergeSort(list);
		System.out.println("List after merge sort: ");
		list.printList();

    }
}