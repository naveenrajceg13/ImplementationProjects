import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class HyeStackAnagram {

	
	public static void main(String args[])
	{
		String s1="abdcghbaabcdij";
		String s2="bcda";
		if(s2.length()==0 || s1.length()==0 || s2.length()>s1.length())
			return ;
		HashMap<Integer, String> hm=new HashMap<>();
		ArrayList<Integer> list=new ArrayList<>();
		for(int i=0;i<s1.length()-s2.length();i++)
		{
			String temp=s1.substring(i, s2.length()+i);
			char[] arr=temp.toCharArray();
			Arrays.sort(arr);
			hm.put(i, new String(arr));
		}
		char[] s22=s2.toCharArray();
		Arrays.sort(s22);
		String sf2=new String(s22);
		for(Integer i: hm.keySet())
		{
			String temp=hm.get(i);
			if(temp.equals(sf2))
			{
				System.out.println(i);
				list.add(i);
			}
		}
		Collections.sort(list);
	}
}
