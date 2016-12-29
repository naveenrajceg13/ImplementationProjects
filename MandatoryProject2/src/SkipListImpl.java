/**
 * @author Naveenraj 
 * 
 * SkipList Implemntaion class, contains Implementation of Skip List
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
 // Skeleton for skip list implementation. 

public class SkipListImpl<T extends Comparable<? super T>> implements
		SkipList<T> {
	
	/**
	 * Entry Class to maintain SkipList Entries
	 * element will contain Element Class instance
	 * next[] will contain all the next pointer to the current entry
	 * level , Level of current list entry
	 * width[] to maintain width of the each pointer( how many nodes in between the pointer )
	 */
	public class Entry <T>{

		Elemet element;   
		Entry<T> [] next;
		int width[];            
		int level;
		public Entry(Elemet element, int level) {
			this.element = element;
			this.level = level;
			next = new Entry[level];                      //intilize next pointer's and width's of each pointer    
			this.width=new int[level];
		}
	}
	
	/**
	 * PrevPointer Class- Use Full in case of Rebuild
	 * Rebuild - logic see rebuild function 
	 * element - stores the Entry instance 
	 * sum - it tells sum of nodes crossed before assigning pointer to its destination
	 */
	public class PrevPointer<T>
	{
		Entry<T> element;
		int sum;
		
		public PrevPointer(Entry<T> element) {
			this.element=element;
			sum=0;                    // sum will be zero, first time since pointer is just created
		}
	}
	
	/**
	 * maxLevel - Max Level of the Skip List
	 * size - Current Level of the Skip List
	 * header - Header of skip List
	 * tail - Tail of skip list
	 * maxSizecanGo - max Size Allowed before resize
	 * minSizecanGo - min Size Allowed before resize
	 * @param <T>
	 */
	
	int maxLevel;
	int size;
	Entry<T> header,tail;
	T last;
	private int prevSum[];
	int maxSizecanGo;
	int minSizecanGo;
	
	
 
	/**
	 * Set Max Level to 15
	 * size to 0
	 * Intilize Header and Tail 
	 * Connect Header and Tail
	 * Calculate min Size can go and max size can go
	 */
	public SkipListImpl() {
		
		maxLevel=6;
		size=0;
		header=new Entry<T>(new Elemet(true, false, null), maxLevel);
		tail=new Entry<T>(new Elemet(false, true, null),maxLevel);
		last=null;
		maxSizecanGo=(int)Math.pow((double)2, (double)maxLevel);
		minSizecanGo=maxSizecanGo-(int)(this.maxSizecanGo*(0.5));
		maxSizecanGo=maxSizecanGo+(int)(this.maxSizecanGo*(0.5));
		for(int i=0;i<maxLevel;i++)
		{
			header.next[i]=tail;
			header.width[i]=1;                         // next of tail to null     
			tail.next[i]=null;                        //next of header to tail 
			tail.width[i]=0;                          //tail pointer will always have width 0, since this pointers are pointing anywhere
		}
	}
	
	/**
	 * @param x : Element to be inserted 
	 * @return : Entry object ( Previous entry of value to be inserted )
	 */
	private Entry<T>[] find(T x)
	{
		Entry<T> pointer=header;
		Entry<T> prev[]=new Entry[maxLevel];
		prevSum=new int[maxLevel];                   // Maintains number of nodes crossed for this current level pointer
		for(int i=maxLevel-1;i>=0;i--)
		{
			int sum=0;
			while(!pointer.next[i].element.plusInfinity && pointer.next[i].element.value.compareTo(x)<0)
			{ 
				sum=sum+pointer.width[i];           // sums all the nodes width traveled for this current level
				pointer=pointer.next[i];
			}
			prevSum[i]=sum;                         
			prev[i]=pointer;                       // Maintains from where current level pointer is started
		}
		return prev;                               // returns previous entry of node to be inserted
	}
	
	/**
	 * @param prevArray
	 * @param x 
	 * @return : True if element already there, False if not there
	 */
	private boolean checkElement(Entry<T> prevArray,T x)
	{
		if(prevArray!=null)
		{
			if(prevArray.next[0]!=null)
			{
				if(prevArray.next[0].element!=null)     // compare each value seperately to avoid null pointer exception 
				{
					if(prevArray.next[0].element.value!=null)
					{
						return prevArray.next[0].element.value.compareTo(x)==0;   
					}
				}
			}
		}
		return false;
	}
	/**
	 * @param x : Value to be inserted
	 * @return : False if element already there, True if not there before
	 */
    @Override
    public boolean add(T x) {
    	
    Entry<T>[] prev=find(x);     //get the start pointer for all the levels 
    if(checkElement(prev[0],x))
    {
    	return false;            // if element is already there, return false 
    }
    else
    {
    	int level=choice();
    	Entry<T> newEntry=new Entry(new Elemet(false, false, x),level+1);     // create new entry 

    	for(int i=0;i<=level;i++)
    	{
    		newEntry.next[i]=prev[i].next[i];                                 // Till the level of the current node
    		prev[i].next[i]=newEntry;                                         // assign prev pointer to current
    	}                                                                     // current to the prev's next 
    	
    	if(newEntry.next[0]==tail)
    	{
    		last=(T) newEntry.element.value;                                 // caching last node everytime
    	}                                                                    // use this in last function
    	
    	size++;
    	
    	maintainWidthArrayAdd(prev, level);                                  // function to maintain Width of the pointers
    	if(size>maxSizecanGo)      
    	{ 
    		rebuild();                                                       // rebuild if the condition voliates                       
    	}
    	return true;
    }
    }
    
    /**
     * @param prev
     * @param level 
     * 
     * function to maintain width of the pointers affect due to the insertion of current node
     */
    private void maintainWidthArrayAdd(Entry<T>[] prev,int level)
    {
    	int sum=0;
    	for(int i=0;i<maxLevel;i++)
    	{
    		if(i==0)
    		{
    			prev[i].width[i]=1;
    			prev[i].next[i].width[i]=1;                           // all level 0 pointers will have pointer width 1
    		}
    		else
    		{
    			if(i>level)
    			{
    				prev[i].width[i]+=1;                             // if pointer is just crossing current node, increment it
    			}
    			else
    			{
    				int currentWidth=prev[i].width[i];
    				sum=sum+prevSum[i-1];                           // nodes before current nodes are assigned to the prev pointer
    				prev[i].width[i]=sum+1;                         // nodes after current nodes are assigned to the current node pointer
    				prev[i].next[i].width[i]=currentWidth-prev[i].width[i]+1;
    			}
    		}
    	}
    }
    
    @Override
    public T findIndex(int index)
	{
    	index=index+1;                                        // since index starts with 0, add with 1 to avoid using minus 1 everytime
    	if(size<index)
    		return null;
		Entry<T> pointer=header;
		int currentIndex=0;
		for(int i=maxLevel-1;i>=0;i--)
		{
			while((currentIndex+pointer.width[i]<=index))
			{
				 currentIndex+=pointer.width[i];                   //loop till index to be searched is reached
				 pointer=pointer.next[i];                          
			}
			
			if(currentIndex==index)
			{
				break;                                             //node is reached
			}
		}
		return (T) pointer.element.value;
	}
    
    /**
     * Function to choose the levels for the current node insertion
     */
    private int choice()
    {
    	int level = 0;
		while (level < maxLevel - 1){
			boolean flag = new Random().nextBoolean();
			if (flag){
				level++;                                 // Till finding first tail
			}
			else{
				break;
			}
		}
		return level;
    }

    @Override
    public T ceiling(T x) {
    
      Entry<T>[] prev=find(x);
        
      if(prev!=null && checkElement(prev[0], x))
      {
        	return x;
      }
      else                                               // Returns node if node is there 
      {
        	if(prev!=null && prev[0].next[0]!=null)
        	{
        		return (T) prev[0].next[0].element.value;          //else return next value
        	}  	
      }	
    
    return null;
    }

    @Override
    public boolean contains(T x) {
    
    Entry<T>[] prev=find(x);
	return (prev!=null) && checkElement(prev[0], x);     // check element present of not
    }

  
    @Override
    public T first() {
    if(header.next==null)
    	return null;
	return (T) header.next[0].element.value;                 // returns node at index 0
    }

    @Override
    public T floor(T x) {
    	
    Entry<T>[] prev=find(x);
    
    if(prev!=null && checkElement(prev[0], x))
    {
    	return x;
    }
    else                                               // returns value, if value is present 
    {
    	if(prev!=null)                                   
    	{
    		return (T) prev[0].element.value;           // return previous value if value is not present
    	}
    	
    }
    	
	return null;
    }

    @Override
    public boolean isEmpty() {
	return size==0;                                       //checks list is empty of not
    }

    @Override
    public Iterator<T> iterator() {
	return new SkipListIterator(this.header);             // create object to Iterator and return the instance
    }

   
    @Override
    public T last() {
    	
    	if(last==null)                                   // Returns last from the cached value
    	{
    	Entry<T> node=this.header;                       // In case last is not cached traverse and find
    	while(node.next[0]!=this.tail)
    	{
    		node=node.next[0];
    	}
    	last=(T) node.element.value;
    	}
    	return last;
    }

    /**
     * Function to Rebuild the list, Logic follows below
     * Pointers for each level which is not ended before are stored in PrevPointer array
     * For the current level of the current node, take the node from the PrevPointer of current level 
     */
    @Override
    public void rebuild() {

    	Entry<T>[] newList=new Entry[size];
        int maxLevel=(int) ((int) this.maxLevel+(this.maxLevel*(0.5)));
        rebuild(newList,0,size-1,maxLevel);                          // create new rebuilded array
        
        PrevPointer<T>[] trackList=new PrevPointer[maxLevel];           // tracklist if the list to maintain not ended pointers
        
        Entry<T> header=new Entry<>(null, maxLevel);
        intilizeTrackList(trackList, header);             //create new header and copy values from old header
        header.element=this.header.element;
        
        Entry<T> iterator=this.header.next[0];
        for(int i=0;i<newList.length;i++)
        {
        	newList[i].element=iterator.element;
        	populateValues(newList[i], trackList,maxLevel);       // for each entry in the new list populate
        	iterator=iterator.next[0];                            // populate value,pointer, pointer widths
        }
        
        Entry<T> tail=new Entry<>(null,maxLevel);
        tail.element=this.tail.element;                          // create tail, copy value from old tail
        populateValues(tail, trackList,maxLevel);                // assign all un assigned pointers to the tail
        
        for(int i=0;i<maxLevel;i++)
        {
        	tail.next[i]=null;                                  // next of tail should be null
        }
        
        this.header=header;
        this.tail=tail;
        this.maxLevel=maxLevel;                                    // reassign all the Basic list values 
        this.last=(T) newList[newList.length-1].element.value;
        this.maxSizecanGo=(int)Math.pow((double)2, (double)maxLevel);
        minSizecanGo=maxSizecanGo-(int)(this.maxSizecanGo*(0.5));
		maxSizecanGo=maxSizecanGo+(int)(this.maxSizecanGo*(0.5));
		
    }
    
    /**
     * @param newList
     * @param trackList
     * @param maxLevel
     * 
     * Function to populate new Entry after rebuild from the un assigned pointers 
     */
    private void populateValues(Entry<T> newList,PrevPointer<T>[] trackList,int maxLevel)
    {
    	for(int i=0;i<maxLevel;i++)
    	{
    		if(i<newList.level)
    		{
    		trackList[i].element.next[i]=newList;
    		trackList[i].element.width[i]=trackList[i].sum+1;   // pointer assigned get the value from the sum and populate
    		trackList[i].element=newList;                        
            trackList[i].sum=0;
    		}
    		else
    		{
    			trackList[i].sum+=1;                    // pointer is still un assigned so just passing one more node
    		}
    	}
    }
    
    private void intilizeTrackList(PrevPointer<T>[] trackList,Entry<T> header)
    {
    	for(int i=0;i<header.level;i++)
    	{
    		PrevPointer tempPointer=new PrevPointer<>(header);
    		trackList[i]=tempPointer;                                  // Intilize pointers of the rebuild header
    	}
    }
    
    private void rebuild(Entry[] entries,int p,int r,int k)
    {
    	if(p<=r)
    	{
    		if(k==0)
    		{
    			for(int i=p;i<=r;i++)
    			{
    				entries[i]=new Entry<>(null, 1);                    // recursive function to create structured list
    			}
    		}
    		else
    		{
    			int q=(p+r)/2;
    			entries[q]=new Entry<>(null, k);                         // make list in such a way, List is perfectly balanced with mid's
    			rebuild(entries,p,q-1,k-1);
    			rebuild(entries,q+1,r,k-1);
    		}
    	}
        
    }

    @Override
    public T remove(T x) {
    	
    Entry<T>[] prev=find(x);
    
    if(checkElement(prev[0], x))
    {
    	Entry<T> entry=prev[0].next[0];
    	
    	if(entry.next[0]==tail)
    	{
    		last=(T) prev[0].element.value;                  // Change current next value
    	}
    	
    	for(int i=0;i<maxLevel;i++)
    	{
    		if(i<entry.level)
    		   prev[i].width[i]=prev[i].width[i]+entry.width[i]-1;     // Change the pointer widths by removing the node
    		else
    		   prev[i].width[i]-=1;
    	}
    	
    	for(int i=0;i<maxLevel;i++)
    	{
    		if(!prev[i].next[i].element.plusInfinity && prev[i].next[i].element.value.compareTo(x)==0)
    		{
    			prev[i].next[i]=entry.next[i];                       // pass the pointer's so that none meeting the node to be removed
    		}
    		else
    		{
    			break;
    		}
    	}
    	size--;
    	if(size>= 100 && size>maxSizecanGo)      
    	{ 
    		rebuild();                                                       // rebuild if the condition voliates                       
    	}
    	return (T) entry.element.value;
    }
    else
    {
    	return null;                // node is not present return null
    }
    }

    @Override
    public int size() {
	return size;
    }
    
    class SkipListIterator<T> implements Iterator<T>{
		Entry<T> currEntry;
		public SkipListIterator(Entry<T> header) {
			currEntry=header;
		}
		@Override
		public boolean hasNext() {
			return currEntry.next[0].element.value != null;
		}

		@Override
		public T next() {
			currEntry=currEntry.next[0];
			return (T) currEntry.element.value;
		}

	}

}
