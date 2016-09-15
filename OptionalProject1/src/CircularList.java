import java.util.List;

/**
 * @author Naveenraj, Niveditha, Jagathis
 * class to merge two circular list
 * @param <T>
 */
public class CircularList<T> extends SinglyLinkedList<T>{
	
	/**
	 * main method, intilize values and call methods 
	 * @param args
	 */
	public static void main(String args[])
	{ 
		//create object to call non static methods 
		CircularList circularList=new CircularList();
		
		//create lists
		CircularLinkedList<Integer> list1=new CircularLinkedList<>();
		CircularLinkedList<Integer> list2=new CircularLinkedList<>();
		
		//add values to array
		Integer[] temp_array_one={3,1,2,3,4,1,3,5,7,3,8};
   	    Integer[] temp_array_two={3,9,8,10};
   	    
   	    //copy value from array to list
   	    circularList.addValuesToList(temp_array_one, list1);
   	    circularList.addValuesToList(temp_array_two, list2);
   	    
   	    //call merge functions
		circularList.printList(circularList.merge(list1, list2));
		
	}

	/**
	 * print list
	 * @param list1
	 */
	public <T extends Comparable <? super T>> void printList(CircularLinkedList<T> list1)
	{
		CircularLinkedList<T>.Entry<T> head=list1.header.next;
		while(head!=null)
		{
			System.out.print(head.element+" ");  //loop till last element in list and print
			head=head.next;
		}
	}
	
	 /**
	 * @param array : T[] : source array
	 * @param list : List<T> : Target list
	 */
	public <T extends Comparable<? super T>> void  addValuesToList(T array[],CircularLinkedList<T> list)
    {
   	  for(int i=0;i<array.length;i++)
   	  {
   		  list.add(array[i]);
   	  }
     }
	 
	 /**
	 * procedure to merge list  
	 * @param list1 : List<T> : bigger list
	 * @param list2 : List<T> : smaller list
	 * @return : list<T> : merged list
	 */
	public <T extends Comparable <? super T>> CircularLinkedList<T> merge(CircularLinkedList<T> list1,CircularLinkedList<T> list2)
	 {
		  //get header for two list
		 CircularLinkedList<T>.Entry<T> big_list=list1.header.next;
		 CircularLinkedList<T>.Entry<T> small_list=list2.header.next;
		 
		 //head of result is set to null
		 CircularLinkedList<T>.Entry<T> temp=null;
		 
		 //flag to check if the element is first element
		 boolean flag_first=false;
		 
		 //check if first element
		 if(big_list.element==small_list.element)
			 flag_first=true;
		 
		 //loop till you find the matched element 
		 while(big_list.element!=small_list.element && !flag_first)
		 {
			 temp=big_list;
			 big_list=big_list.next;
		 }
		 
		 if(!flag_first)   //element is first element 
		 {
			 temp.next=small_list;
			 list2.tail.next=big_list;
		 } 
		 else   //merge using the matched element
		 {
			 list2.tail.next=big_list;
			 list1.tail.next=small_list;
			 list1.header.next=small_list;
		 }
		 
		 return list1;
		 
	 }

}
