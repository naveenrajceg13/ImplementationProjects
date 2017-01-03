import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class Twitter3 {
	
	 class WordNode{
	        
	        String word;
	        int numSteps;
	        
	        public WordNode(String word,int numSteps)
	        {
	            this.word=word;
	            this.numSteps=numSteps;
	        }
	    }

	 static int findMutationDistance(String start, String end, String[] bank) {

	        Set<String> treeSet=new TreeSet<>();
	        Queue<Twitter3.WordNode> queue=new LinkedList<Twitter3.WordNode>();
	        
	        for(String s: bank)
	        {
	            treeSet.add(s);
	        }

	        
	        if(start.length()!=end.length())
	        return -1;
	        
	        char possible[] = {'A','C','T','G'};
	        
	        queue.add(new Twitter3().new WordNode(start,0));

	        while(!queue.isEmpty())
	        {
	            WordNode top=queue.remove();
	            String word=top.word;
	            
	            if(word.equals(end)){
	                return top.numSteps;
	            }
	            
	            char[] arr=word.toCharArray();
	            
	            for(int i=0;i<arr.length;i++)
	                {
	                  for(char c: possible)
	                  {
	                       char temp=arr[i];
	                      
	                       if(arr[i]!=c)
	                       {
	                           arr[i]=c;    
	                       }
	                      
	                      String newWord=new String(arr);
	                      if(treeSet.contains(newWord))
	                      {
	                             queue.add(new Twitter3().new WordNode(newWord,top.numSteps+1));
	                             treeSet.remove(newWord);
	                      }
	                      
	                      arr[i]=temp;
	                  }
	            }
	        }
	        
	        return -1;
	    }

}
/*
 * Complete the function below.
 */

   
    

