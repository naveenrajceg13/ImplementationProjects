import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Tweet {
 
	public static void main(String args[])
	{
		Tweet t=new Tweet();
		int[][] follow={{4,3},{1,2},{1,3},{1,4},{5,6}};
		int[][] tweets={{5,6},{2,10},{3,10},{4,10},{2,11},{3,12},{4,11}};
		int[] result=t.gettweet(follow, tweets, 1, 3);
		t.print(result);
		
	}
	
	int[] gettweet(int[][] followgraph,int[][] likegraph,int targetuser,int minLikeThrewshold)
	{
		
	    ArrayList<Integer> followlist=new ArrayList<>();
	    
	    for(int i=0;i<followgraph.length;i++)
	    {
	    	if(followgraph[i][0]==targetuser)
	    	   followlist.add(followgraph[i][1]);
	    }
	    HashMap<Integer,ArrayList<Integer>> likemap=new HashMap<>();
	    
	    for(int i=0;i<likegraph.length;i++)
	    {
	    	if(likemap.get(likegraph[i][0])!=null)
	    	{
	    		ArrayList<Integer> tempList=likemap.get(likegraph[i][0]);
	    		tempList.add(likegraph[i][1]);
	    		likemap.put(likegraph[i][0], tempList);
	    	}
	    	else
	    	{
	    		ArrayList<Integer> tempList=new ArrayList<>();
	    		tempList.add(likegraph[i][1]);
	    		likemap.put(likegraph[i][0], tempList);
	    	}
	    	
	    }
		HashMap<Integer, Integer> map=new HashMap<>();
		HashMap<Integer,Integer> hashMap=new HashMap<>();
		for(int following: followlist)
		{
			if(likemap.containsKey(following))
			{
			ArrayList<Integer> likes=likemap.get(following);
			
			for(int like: likes)
			{
				if(map.get(like)!=null)
				{
					map.put(like, map.get(like)+1);
				}
				else
				{
					map.put(like,1);
				}
			}
		}
	}
		for(Integer key: map.keySet())
		{
			int value = map.get(key);
			if(value>=minLikeThrewshold)
			{
				//System.out.println(key+" "+value);
				hashMap.put(key, value);
			}
		}
		HashMap<Integer, Integer> resultMap=sortByValues(hashMap);
		int result[]=new int[hashMap.size()];
		
		int index=0;
		for(Integer key: sortByValues(hashMap).keySet())
		{
			result[index]=key;
			index++;
		}
		
		return result;
	}
	
	private  HashMap<Integer,Integer> sortByValues(HashMap<Integer,Integer> map) { 
	       List list = new LinkedList(map.entrySet());
	       // Defined Custom Comparator here
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o2)).getValue())
	                  .compareTo(((Map.Entry) (o1)).getValue());
	            }
	       });

	       // Here I am copying the sorted list in HashMap
	       // using LinkedHashMap to preserve the insertion order
	       HashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	  }
	
	public void print(int[] result)
	{
		for(int i=0;i<result.length;i++)
		{
			//System.out.println(i);
			System.out.println(result[i]);
		}
	}
	
	public void printList(ArrayList<Integer> result)
	{
		System.out.println();
		for(int x:result)
		{
			System.out.println(x);
		}
	}
}
