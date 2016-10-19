
import java.util.*;

/**
 * @author Naveenraj, Niveditha, Jagathis
 *
 * @param <T>
 */
public class CircularLinkedList<T> implements Iterable<T> {

    /** Class Entry holds a single node of the list */
    public class Entry<T> {
        T element;
        Entry<T> next;

        Entry(T x, Entry<T> nxt) {
            element = x;
            next = nxt;
        }
    }

    // Dummy header is used.  tail stores reference of tail element of list
    Entry<T> header, tail;
    int size;

    CircularLinkedList() {
        header = new Entry<>(null, null);
        tail = header;
        size = 0;
    }

    public Iterator<T> iterator() { return new SLLIterator<>(header); }

    private class SLLIterator<E> implements Iterator<E> {
	Entry<E> cursor, prev;
	SLLIterator(Entry<E> head) {
	    cursor = head;
	    prev = null;
	}

	public boolean hasNext() {
	    return cursor.next != null;
	}
	
	public E next() {
	    prev = cursor;
	    cursor = cursor.next;
	    return cursor.element;
	}

	// To do: error checking
	// What should cursor be set to after a remove?
	public void remove() {
	    prev.next = cursor.next;
	    prev = null;
	}
    }

    // Add new elements to the end of the list
    void add(T x) {
	tail.next = new Entry<>(x, null);
	tail = tail.next;
	tail.next=header.next;   // make the list circular 
	size++;
    }

    Entry<T> addAt(T x,Entry<T> headnow)
    {
    	 if(headnow==null)
    	 {
    		 add(x);
    	 return tail;
    	 }
    	 else
    	 {
         //System.out.println(((Tour)headnow.element).start+" "+((Tour)headnow.element).end);
         //System.out.println(((Tour)x).start+" "+((Tour)x).end);
         Entry<T> headnowNext=headnow.next;
         headnow.next=new Entry<>(x,null);
       	 headnow.next.next=headnowNext;
       	 size++;
       	 //System.out.println(((Tour)headnow.element).end+" "+((Tour)headnow.next.element).end+" "+((Tour)headnow.next.next.element).end);
       	  //System.out.println(((Tour)headnow.next.element).start+" "+((Tour)headnow.next.element).end);
       	  return headnow.next;
    	 }
    	 
    }
    void printList() {

	for(T item: this) {
	    System.out.print(item + " ");
	}

	System.out.println();
    }

    // Rearrange the elements of the list by linking the elements at even index
    // followed by the elements at odd index. Implemented by rearranging pointers
    // of existing elements without allocating any new elements.
  
    public static void main(String[] args) {
        int n = 10;
        if(args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        CircularLinkedList<Integer> lst = new CircularLinkedList<>();
        for(int i=1; i<=n; i++) {
            lst.add(new Integer(i));
        }
        lst.printList();

    }
}
