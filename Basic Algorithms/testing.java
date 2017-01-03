import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class testing {
	
	
	public static void main(String args[])
	{
		HashMap<Integer,List> hm=new HashMap<>();
        String s="hello";
        int a[]={1,2,3,4};
        HashSet<Integer> set=new HashSet<>();

        String abbri=s.charAt(0)+String.valueOf(s.substring(1,s.length()-1).length())+s.charAt(s.length()-1);
        
        String str="input_in_snake_case";
        boolean start=true;
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<str.length();i++)
        {
            if(!(str.charAt(i)=='_') && start)
            {
            	int value=str.charAt(i)-97;
            	sb.append((char)(65+value));
            	start=false;
            }
            else if(str.charAt(i)=='_')
            {
            	start=true;
            }
            else
            {
            	sb.append(str.charAt(i));
            }
        }
        System.out.println(sb.toString());
        start=true;
        StringBuilder ssb=new StringBuilder();
        for(int i=0;i<sb.toString().length();i++)
        {
        	if(start && sb.toString().charAt(i)<'a')
        	{
        		ssb.append((char)((sb.toString().charAt(i)-'A')+97));
        		start=false;
        	}
        	else if(sb.toString().charAt(i)<'a')
        	{
        		ssb.append("_");
        		ssb.append((char)((sb.toString().charAt(i)-'A')+97));
        	}
        	else
        	{
        		ssb.append(sb.toString().charAt(i));
        	}
        }
        System.out.println(ssb.toString());
        
        System.out.println("word".substring(0,0));
	}

}
