import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Twitter1 {
    public static void main(String args[] ) throws Exception {
        
    	Twitter1 sol=new Twitter1();
        Scanner s=new Scanner(System.in);
        
        while(s.hasNext())
        {
            String str=s.nextLine();
            if(str.charAt(0)=='E')
            {
                sol.maskEmail(str);
            }
            else
            {
                sol.maskPhone(str);
            }
        }
    }
    
    
    public void maskEmail(String s)
    {
        String[] strArray=s.split(":");
        s=strArray[1];
        String[] parse=s.split("@");
        String secondPart=parse[1];
        char[] arr={parse[0].charAt(0),'*','*','*','*','*',parse[0].charAt(parse[0].length()-1)};
        String firstPart=new String(arr);
        
        String result="E:"+firstPart+"@"+secondPart;
        
        System.out.println(result);
    }
    
     public void maskPhone(String s)
    {
        HashMap<Character,Boolean> hm=new HashMap<>();
        
        String[] strArray=s.split(":");
        ArrayList<Integer> list=new ArrayList<>();
        boolean hasCountryCode=false;
        char[] toMask=strArray[1].toCharArray();
        
        for(int i=0;i<10;i++)
        	hm.put((char)(i+48), true);
        
        
        for(int i=toMask.length-1;i>=0;i--)
        {
        	if(hm.get(toMask[i]) != null )
        	{
        		list.add((int)(toMask[i]));
        	}
        }
        
        if(list.size()>10)
        	hasCountryCode=true;
        
        StringBuilder sbBase=new StringBuilder();
        
        sbBase.append("P:");
        
        if(hasCountryCode)
        {
        	sbBase.append("+");
        }
        
        String last="";
        String mid="";
        String first="";
        String country="";
        
        for(int i=0;i<list.size();i++)
        {
        	if(last.length()<4)
        	{
        		last=(char)(list.get(i).intValue())+last;
        	}
        	else if(mid.length()<3)
        	{
        		mid='*'+mid;
        	}
        	else if(first.length()<3)
        	{
        		first='*'+first;
        	}
        	else
        	{
        		country='*'+country;
        	}
        }
        
        if(hasCountryCode)
           System.out.println(sbBase.toString()+country+"-"+first+"-"+mid+"-"+last);
        else
        	System.out.println(sbBase.toString()+first+"-"+mid+"-"+last);
    }
}