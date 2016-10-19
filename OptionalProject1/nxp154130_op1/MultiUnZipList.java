
/**
 * @author Naveenraj, Niveditha, Jagathis
 *
 * @param <T>
 */
public class MultiUnZipList<T> {
	
	public static void main(String args[])
	{
		SinglyLinkedList<Integer> list1=new SinglyLinkedList<>();
		for(int i=1;i<21;i++){
			list1.add(i);
		}
		list1.multiUnZip(4);
		list1.printList();

	}


}
