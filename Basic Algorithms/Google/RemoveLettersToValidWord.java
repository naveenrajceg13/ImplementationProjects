package Google;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class RemoveLettersToValidWord {
	
	
	public static void main(String args[])
	{
		RemoveLettersToValidWord rLetter=new RemoveLettersToValidWord();
		String[] dict={"rated","create","ate","ted","crea","hello","llow"};
		String word="created";
		Set<String> hs=new HashSet<String>();
		rLetter.formDictonarySet(dict, hs);
		System.out.println(rLetter.getLongestWord(word, hs));
    }

	public void formDictonarySet(String[] dict,Set hs)
	{
		for(String s: dict)
		{
			hs.add(s);
		}
	}
	
	public String getLongestWord(String word,Set hs)
	{
		HashSet<String> cache=new HashSet<>();
		Queue<String> q=new LinkedList<>();
		q.add(word);
		while(!q.isEmpty())
		{
		   String now=q.poll();
		   if(cache.contains(now))
			   continue;
		   cache.add(now);
		   boolean start=true;
		   for(int i=1;i<now.length();i++)
		   {
			   String then=null;
			   if(start){
				    then=now.substring(1,now.length()); start=false;}
			   else{
			        then=now.substring(0,i)+now.substring(i+1,now.length());}
			   
			   if(hs.contains(then))
			   {
				   return then;
			   }
			   q.add(then);
		   }
		}
		
		return null;
	}

}


