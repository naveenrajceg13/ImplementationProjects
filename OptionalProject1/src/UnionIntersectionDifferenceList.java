import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Naveenraj, Niveditha, Jagathis
 * Class to find Union Intersection and Difference of Lists
 * @param <T>
 */
public class UnionIntersectionDifferenceList<T> {
	
      /**
     * Main class to create list and call functions.  
     * @param args
     */
    public static void main(String args[])
      {
    	// create Objects to call non static methods 
    	  UnionIntersectionDifferenceList<Integer> unionIntersectionDifference = new UnionIntersectionDifferenceList<>();
    	  
    	  //Create Empty List's 
    	  List<Integer> listOne=new LinkedList<>();
    	  List<Integer> listTwo=new LinkedList<>();
    	  List<Integer> outList=new LinkedList<>();
    	  
    	  //Give Value to List 1 and 2 
    	  Integer[] temp_array_one={4,5,7,9,12,34,56,78,90};
    	  Integer[] temp_array_two={3,5,7,8,14,15,17,34,57,89};
    	  
    	  //Add values to the List
    	  unionIntersectionDifference.addValuesToList(temp_array_one,listOne);
    	  unionIntersectionDifference.addValuesToList(temp_array_two,listTwo);
    	  
    	  //Union
    	  unionIntersectionDifference.union(listOne, listTwo, outList);
    	  
    	  outList.clear(); //clear so that can be used for next function
    	  
    	  //Intersect
    	  unionIntersectionDifference.intersect(listOne, listTwo, outList);
    	  unionIntersectionDifference.print(outList);
    	  
    	  
    	  outList.clear(); //clear so that can be used for next function
    	  
    	  //difference 
    	  unionIntersectionDifference.difference(listOne, listTwo, outList);
 
     }
      /**
     * procedure to copy elements from array to list
     * @param array : T[] : contains value to copy to list
     * @param list : List<T> : Target List
     */
    public <T extends Comparable<? super T>> void  addValuesToList(T array[],List<T> list)
      {
    	  for(int i=0;i<array.length;i++)
    	  {
    		  list.add(array[i]); // takes value from array and add to list
    	  }
      }
    
     /**
     * Procedure to print elements in the List
     * @param list : List<T> : List to print 
     */
    public <T extends Comparable<? super T>> void  print(List<T> list)
    {
    	  
      Iterator<T> listItt=list.iterator();
      System.out.println();
      while(listItt.hasNext())
      {
    	  System.out.print(listItt.next()+" "); //Print elements in List
      }
    }
      
      
      /**
     * Procedure to get union of Lists 
     * @param l1 : List<T> : List 1
     * @param l2 : List<T> : List 2
     * @param result : List<T> : Output List
     */
    public <T extends Comparable <? super T>> void union(List<T> l1,List<T> l2,List<T> result)
      {
    	  //Create Iterator for both the List 
    	  Iterator<T> list1=l1.iterator();
    	  Iterator<T> list2=l2.iterator();
    	  
    	  //Set Flags to check which list is used last
    	  boolean listOneNotUsed=false;
    	  boolean listTwoNotUsed=false;
    	  
    	  //Make current values for both list to be empty 
    	  T list1_cur_value=null;
    	  T list2_cur_value=null;
    	  
    	  while((listOneNotUsed||list1.hasNext()) && (listTwoNotUsed||list2.hasNext()))
    	  {
    		  if(!listOneNotUsed)
    		        list1_cur_value=list1.next();  //Last value inserted is not from List1
    		  if(!listTwoNotUsed)
    		        list2_cur_value=list2.next();  //Last value inserted is not from List2
    		  
    		  if(list1_cur_value.compareTo(list2_cur_value)<0)
    	      {
    			  result.add(list1_cur_value);   //Got best value now from List1
    			  
    			  listOneNotUsed=false;
    			  listTwoNotUsed=true;
    		  }
    		  else if(list1_cur_value.compareTo(list2_cur_value)<0)
    		  {
    			  result.add(list2_cur_value);  //Got best value from List2
    			  
    			  listTwoNotUsed=false;
    			  listOneNotUsed=true;
    		  }
    		  
    	  }
    	  while(list1.hasNext())         // only List1 values are left
    	  {
    		    result.add(list1.next());     
    	  }
    	  while(list2.hasNext())       // only List2 values are left
    	  {
    		  result.add(list2.next());
    	  }
    	  
      }
    
    /**
     * Procedure to get Intersect of Lists 
     * @param l1 : List<T> : List 1
     * @param l2 : List<T> : List 2
     * @param result : List<T> : Output List
     */
    
    public <T extends Comparable <? super T>> void intersect(List<T> l1,List<T> l2,List<T> result)
      {
    	  // Create iterator for both the List
    	  Iterator<T> list1=l1.iterator();
    	  Iterator<T> list2=l2.iterator();
    	  
    	  // Create flag to check which list is currently used. 
    	  boolean listOneIsNotBehind=false;
    	  boolean listTwoIsNotBehind=false;
    	  
    	  // Current values of the list. 
    	  T list1_cur_value=null;
    	  T list2_cur_value=null;
    	  
    	  // loop till any one list became empty. 
    	  while((listOneIsNotBehind||list1.hasNext()) && (listTwoIsNotBehind||list2.hasNext()))
    	  {
    		  if(!listOneIsNotBehind)
    		        list1_cur_value=list1.next();  //Move List 1
    		  if(!listTwoIsNotBehind)
    		        list2_cur_value=list2.next();  //Move List 2
    		  
    		  if(list1_cur_value.compareTo(list2_cur_value)<0)
    	      {
    			  listOneIsNotBehind=false;         //Set Flags to Iterate List 1
    			  listTwoIsNotBehind=true;
    		  }
    		  else if(list2_cur_value.compareTo(list1_cur_value)<0)
    		  {
    			  listTwoIsNotBehind=false;       // Set Flags to Iterate List 2
    			  listOneIsNotBehind=true;
    		  }
    		  else if(list2_cur_value.compareTo(list1_cur_value)==0)
    		  {
    			  result.add(list2_cur_value);   // Intersect Found, add to List
    			  listTwoIsNotBehind=false;
    			  listOneIsNotBehind=false;
    		  }
    		  
    	  }

      }
    
    /**
     * Procedure to get difference of Lists 
     * @param l1 : List<T> : List 1
     * @param l2 : List<T> : List 2
     * @param result : List<T> : Output List
     */
    
    public <T extends Comparable <? super T>> void difference(List<T> l1,List<T> l2,List<T> result)
      {
    	// Create iterator for both the List
    	  Iterator<T> list1=l1.iterator();
    	  Iterator<T> list2=l2.iterator();
    	  
    	  // Create flag to check which list is currently used.
    	  boolean listOneNotUsed=false;
    	  boolean listTwoNotUsed=false;
    	  
    	// Current values of the list. 
    	  T list1_cur_value=null;
    	  T list2_cur_value=null;
    	  
    	// loop till any one list became empty. 
    	  while((listOneNotUsed||list1.hasNext()) && (listTwoNotUsed||list2.hasNext()))
    	  {
    		  if(!listOneNotUsed)
    		        list1_cur_value=list1.next();  //Iterate List 1
    		  if(!listTwoNotUsed)
    		        list2_cur_value=list2.next();  //Iterate List 2
    		  
    		  if(list1_cur_value.compareTo(list2_cur_value)<0)
    	      {
    			  result.add(list1_cur_value);     // List 1 Value is not in List 2
    			  listOneNotUsed=false;
    			  listTwoNotUsed=true;
    		  }
    		  else if(list1_cur_value.compareTo(list2_cur_value)>0)
    		  {
    			  result.add(list2_cur_value);    // List 2 value is not in list 1
    			  listTwoNotUsed=false;
    			  listOneNotUsed=true;
    		  }
    		  else if(list1_cur_value.compareTo(list2_cur_value)==0)
    		  {
    			  listTwoNotUsed=false;          // Same value Iterate Both List 
    			  listOneNotUsed=false;
    		  }
    		  
    	  }
    	  while(list1.hasNext())
    	  {
    		    result.add(list1.next());      // Only List 1 is left, add all values left
    	  }
    	  while(list2.hasNext())
    	  {
    		  result.add(list2.next());       // Only List 2 is left, add all values left
    	  }
    	  
      }

}
