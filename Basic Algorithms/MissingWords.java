import java.util.HashMap;

public class MissingWords {
	
	public static void main(String args[])
	{
		String s="I am using HackerRank to improve programing";
		String t="am HackerRank to ";
		
		MissingWords mis=new MissingWords();
		
		for(String value:mis.missingWords(s, t))
		{
			System.out.println(value);
		}
	}
	
	public String[] missingWords(String s,String t)
	{
		String[] ss=s.split(" ");
		String[] tt=t.split(" ");
		
		String[] result=new String[ss.length-tt.length];
		
		HashMap<String,Integer> map=new HashMap<>();
		
		for(String value: tt)
		{
			if(map.get(value)!=null)
			{
				map.put(value, map.get(value)+1);
			}
			else
			{
				map.put(value,1);
			}
		}
		int index=0;
		for(String value: ss)
		{
			if(map.get(value)==null)
			{
			   result[index]=value;
			   index++;
			}
			else
			{
				if(map.get(value)>0)
				{
					map.put(value,map.get(value)-1);
				}
				else
				{
					result[index]=value;
					index++;
				}
			}
		}
		return result;
	}

}
