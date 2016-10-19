import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Naveenraj, Niveditha, Archana, Jegathis
 * Class to do addition and Subtract of numbers stored in List in reverse order. 
 * @param <T>
 */
public class AddtionSubractionLists<T> {

	/**
	 * Main Method creates list and call functions
	 * @param args
	 */
	public static void main(String args[])
	{
		// create object to call non static methods of this class. 
		AddtionSubractionLists<Integer> addSub=new AddtionSubractionLists<>();
		
		//create empty List
		List<Integer> list1=new LinkedList<>();
		List<Integer> list2=new LinkedList<>();
		List<Integer> result=new LinkedList<>();
		
		//populate arrays.
	    Integer[] temp_array_one={9};
  	    Integer[] temp_array_two={7};
  	    
  	    //populate List from Array. 
  	    addSub.addValuesToList(temp_array_one,list1);
  	    addSub.addValuesToList(temp_array_two,list2);
  	    
  	    //Call's Add method. 
  	    addSub.add(list1, list2,result,10);
  	    addSub.print(result);
  	    result.clear();
  	    //Call's Sub method.
  	    addSub.sub(list1, list2,result,10);
  	   // addSub.print(result);
	}
	
    /**
     * Print Elements in the list. 
     * @param list : List<T> : List to be printed.
     */
    public <T extends Comparable<? super T>> void  print(List<T> list)
	{
	    Iterator<T> listItt=list.iterator();
	    System.out.println();
	      while(listItt.hasNext())
	      {
	    	  System.out.print(listItt.next()+" "); // Iterate and Print Elements. 
	      }
	}
	      
	 /**
	 * @param array : Array to be copied 
	 * @param list : Target List 
	 */
	public <T extends Comparable<? super T>> void  addValuesToList(T array[],List<T> list)
     {
   	  for(int i=0;i<array.length;i++)
   	  {
   		  list.add(array[i]);
   	  }
     }
	 
	/**
	 * Procedure that adds values in the list 
	 * @param list1 : List<Integer> : List 1
	 * @param list2 : List<Integer> : List 2
	 * @param result : List<Integer> : Result List
	 * @param b : int : base
	 */
	
	public <T extends Comparable<? super T>> int size(List<Integer> list1)
	{
		return list1.size();
	}

	public <T extends Comparable<? super T>> void padWithZero(List<Integer> list1,int size)
	{
		for(int i=0;i<size;i++)
		{
			list1.add(0);
		}
	}
	
	public <T extends Comparable <? super T>> void add(List<Integer> list1,List<Integer> list2,List<Integer> result,int b)
	{
		//carry
		int carry=0;
		
		int list1_size=size(list1);
		int list2_size=size(list2);
		
		//Iterator for List
		Iterator<Integer> list1Itt=list1.iterator();
		Iterator<Integer> list2Itt=list2.iterator();
		
		//Loop till value is there in both the list
		while(list1Itt.hasNext() && list2Itt.hasNext())
		{
			int sum=list1Itt.next()+list2Itt.next()+carry;
			carry=0;
			if(sum>=b)
				carry=sum/b;
			result.add((sum)%b);
		}
		
		//If only one list is left, add values in that list with carry and add 
		while(list1Itt.hasNext())
		{
			int sum=list1Itt.next()+carry;
			carry=0;
			if(sum>=b)
				carry=sum/b;
			result.add((sum)%b);
		}
		
		//If only one list is left, add values in that list with carry and add 
		while(list2Itt.hasNext())
		{
			int sum=list2Itt.next()+carry;
			carry=0;
			if(sum>=b)
				carry=sum/b;
			result.add((sum)%b);
		}
		
		//If carry is only left, add carry
		if(carry>0)
		{
			result.add(carry);
		}
	}
	
	/**
	 * Procedure that adds values in the list 
	 * @param list1 : List<Integer> : List 1
	 * @param list2 : List<Integer> : List 2
	 * @param result : List<Integer> : Result List
	 * @param b : int : base
	 */
	public <T extends Comparable <? super T>> void sub(List<Integer> list1,List<Integer> list2,List<Integer> result,int b)
	{
		// intilize barrow
		int barrow=0;
		//create Iterator
		Iterator<Integer> list1Itt=list1.iterator();
		Iterator<Integer> list2Itt=list2.iterator();
		
		//loop till both the list has values 
	    while(list1Itt.hasNext() && list2Itt.hasNext())
	    {
	    	int first_num=list1Itt.next()-barrow;
	    	int second_num=list2Itt.next();
	    	barrow=0;
	    	if(second_num>first_num)
	    	{
	    		first_num=first_num+b;
	    		barrow=1;
	    	}
	    	result.add(first_num-second_num);  //subraction is performed with barrow and added to list
	    }
	    
	    // list one is only left, subtract and add values to list
	    while(list1Itt.hasNext())
	    {
	    	int first_num=list1Itt.next()-barrow;
	    	barrow=0;
	    	if(first_num<0)
	    	{
	    		first_num=first_num+b;
	    		barrow=1;
	    	}
	    	result.add(first_num);
	    }
		
	}
}