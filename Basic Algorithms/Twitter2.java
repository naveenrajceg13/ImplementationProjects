import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import javax.print.attribute.standard.DateTimeAtCompleted;

public class Twitter2 {
	
	
	public static void main(String args[]) throws FileNotFoundException
	{
		DateComparator dateCom=new Twitter2().new DateComparator();
		TreeMap<String, TreeMap<String, Integer>> hm=new TreeMap<>(new Twitter2().new MainTreeMap());
		Scanner s=new Scanner(new File("inputT1.txt"));
		String startRange=null;
		String endRange=null;
		if(s.hasNext())
			startRange=s.nextLine();
		if(startRange!=null)
		{
			String[] temp=startRange.split(",");
			startRange=temp[0].trim();
			endRange=temp[1].trim();
		}
		boolean flag=true;
		
		while(s.hasNext())
		{
			String temp;
			if(flag)
			{
				temp=s.nextLine();
				flag=false;
			}
			else
			{
				temp=s.nextLine();
                String[] collectTemp=temp.split(",");
                if(dateCom.compare(startRange, collectTemp[0])<=0 && dateCom.compare(collectTemp[0],endRange) <0)
                {
                	String mapKey = dateCom.getMonthYearOnly(collectTemp[0]);
                	if(collectTemp.length==3)
                	{
                		String map2Key=collectTemp[1].trim();
                		int value=Integer.parseInt(collectTemp[2].trim());
                		
                		if(hm.containsKey(mapKey))
                		{
                			TreeMap<String,Integer> subMap=hm.get(mapKey);
                			if(subMap.containsKey(map2Key))
                			{
                				subMap.put(map2Key,value+(subMap.get(map2Key)));
                			}
                			else
                			{
                				subMap.put(map2Key, value);
                			}
                			hm.put(mapKey, subMap);
                		}
                		else
                		{
                			TreeMap<String,Integer> subMap=new TreeMap<String,Integer>();
                			subMap.put(map2Key, value);
                			hm.put(mapKey, subMap);
                		}
                	}
                }
			}
		}
		
		for(String str: hm.keySet())
		{
			Map m= hm.get(str);
			System.out.print(str);
			for(Object str2: m.keySet())
			{
				System.out.print(", "+str2+", "+m.get(str2));
			}
			System.out.println();
		}
	}

	class DateComparator implements Comparator<String>
	{

		@Override
		public int compare(String o1, String o2) {
			
			String[] s1=o1.split("-");
			String[] s2=o2.split("-");
			
			if(s1[0].equals(s2[0]))
			{
				if(s1[1].equals(s2[1]))
				{
					return 0;
				}
				else
				{
					return (Integer.parseInt(s1[1])-Integer.parseInt(s2[1]));
				}
			}
			else
			{
				return (Integer.parseInt(s1[0])-Integer.parseInt(s2[0]));
			}
	
		}
		
		public String getMonthYearOnly(String o1)
		{
			String[] s1=o1.split("-");
			
			return s1[0]+"-"+s1[1];
		}
		
	}
	
	class MainTreeMap implements Comparator<String>
	{
		DateComparator dateCom=new Twitter2().new DateComparator();		
		@Override
		public int compare(String o1, String o2) {
			return dateCom.compare(o2,o1);
		}
	}
	
	class SubTreeMap implements Comparator<Map.Entry<String,Integer>>
	{
		@Override
		public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
			// TODO Auto-generated method stub
			return o1.getValue()-o2.getValue();
		}
		
	}
}
