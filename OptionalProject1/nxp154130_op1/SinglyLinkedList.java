/** @author rbk
 *  Singly linked list: for instructional purposes only
 *  Ver 1.0: initial description.
 *  Ver 1.1: added iterator: 2016/8/30.
 *  Ver 1.2: simplified the code of add by starting with tail=header instead of null: 2016/9/2
 */

import java.util.*;

/**
 * @author Naveenraj, Niveditha, Jagathis
 *
 * @param <T>
 */
public class SinglyLinkedList<T> implements Iterable<T> {

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

    SinglyLinkedList() {
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
	size++;
    }

    void printList() {
	/* Code without using implicit iterator in for each loop:

        Entry<T> x = header.next;
        while(x != null) {
            System.out.print(x.element + " ");
            x = x.next;
        }
	*/
	
	for(T item: this) {
	    System.out.print(item + " ");
	}

	System.out.println();
    }

    // Rearrange the elements of the list by linking the elements at even index
    // followed by the elements at odd index. Implemented by rearranging pointers
    // of existing elements without allocating any new elements.
    void unzip() {
	if(size < 3) {  // Too few elements.  No change.
	    return;
	}

	Entry<T> tail0 = header.next;
	Entry<T> head1 = tail0.next;
	Entry<T> tail1 = head1;
	Entry<T> c = tail1.next;
	int state = 0;

	// Invariant: tail0 is the tail of the chain of elements with even index.
	// tail1 is the tail of odd index chain.
	// c is current element to be processed.
	// state indicates the state of the finite state machine
	// state = i indicates that the current element is added after taili (i=0,1).
	while(c != null) {
	    if(state == 0) {
		tail0.next = c;
		tail0 = c;
		c = c.next;
	    } else {
		tail1.next = c;
		tail1 = c;
		c = c.next;
	    }
	    state = 1 - state;
	}
	tail0.next = head1;
	tail1.next = null;
    }
    
    /**
     * Procedure to multiUnZip
     * @param unZipSize
     */
    void multiUnZip(int unZipSize)
    {
    	//if List size is less than unzip size just return
    	if(this.size<unZipSize+1)
    		return;
    	
    	//Create head for both for list for reference. 
    	Entry<T> heads[]=(Entry<T>[])new Entry[unZipSize];
    	Entry<T> tails[]=(Entry<T>[])new Entry[unZipSize];
    	tails[0]=header.next;
    	heads[0]=tails[0];
    	
    	//create numbers of tails, corresponding to unZipSize
    	for(int i=1;i<unZipSize;i++)
    	{
    		heads[i]=tails[i-1].next;
    		tails[i]=tails[i-1].next;
    	}
    	
    	//Current to next element of last tail
    	Entry<T> current=tails[unZipSize-1].next;
    	int current_zip=0;
    	while(current!=null)
    	{
    		tails[current_zip].next=current;   
    		tails[current_zip]=current;    
    		current=current.next;      //keep moving tail and current and linking it. 
    		current_zip=(current_zip+1)%unZipSize;
    	}
    	
    	for(int i=0;i<unZipSize-1;i++)
    	{
    		tails[i].next=heads[i+1]; //join all the unziped.
    	}
    	
    	tails[unZipSize-1].next=null; //last element of list to Null 
    	
    	header.next=heads[0]; // Assign head to first unzip head. 
    }

    public static void main(String[] args) {
        int n = 10;
        if(args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        SinglyLinkedList<Integer> lst = new SinglyLinkedList<>();
        for(int i=1; i<=n; i++) {
            lst.add(new Integer(i));
        }
        lst.printList();
	lst.multiUnZip(4);
        lst.printList();

	Iterator<Integer> it = lst.iterator();
	while(it.hasNext()) {
	    Integer x = it.next();
	    if(x.intValue() == 9) {
		it.remove();
	    }
	}
	//lst.printList();
    }
}

/* Sample output:
   1 2 3 4 5 6 7 8 9 10 
   1 3 5 7 9 2 4 6 8 10
   1 3 5 7 2 4 6 8 10 
*/