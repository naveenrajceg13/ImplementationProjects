import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Twitter5 {
	
	class Snap
	{
		String s;
		int index;
		
		Snap(String s,int index)
		{
			this.s=s;
			this.index=index;
		}
	}
	public static void main(String args[])
	{
		Twitter5 tw=new Twitter5();
		Scanner s=new Scanner(System.in);
		String input;
		
		while(s.hasNext())
		{
			input=s.nextLine();
			String[] parsed=tw.getParsedInput(input);
			if(parsed.length==1)
				System.out.println(input+" other");
			else
			{
			     String operations=parsed[1];
			     String value=parsed[0];
			     List list=tw.getOperationsInOrder(operations);
			     for(Object c: list)
			     {
			    	 if((char)c=='R')
			    	    value=tw.reverse(value);
			    	 else
			    		value=tw.simplify(value);
			     }
			     System.out.println(value);
			}
		}
	}
	public String simplify(String value)
	{
		String res="";
		for(int i=0;i<value.length();i++)
		{
		   if(i==0 && value.charAt(i)=='(')
		   {
			   Snap s=foundStartBrace(value, ++i);
			   res=s.s;
			   i=s.index;
		   }
		   else
		   {
			   if(value.charAt(i)=='(')
			   {
				   Snap s=foundStartBrace(value, ++i);
				   res=res+"("+s.s+")";
				   i=s.index;
			   }
			   else
			   {
				   res=res+value.charAt(i);
			   }
		   }
		}
		return res;
	}
	
	public Snap foundStartBrace(String value,int i)
	{
		   String res;
		   StringBuilder rest=new StringBuilder();
		   while(value.charAt(i)!=')')
		   {
			    if(value.charAt(i)=='(')
				{
			    	Snap s=foundStartBrace(value, ++i);
			    	i=s.index;
			    	rest.append("("+s.s+")");
				}
			    else
			    {
				rest.append(String.valueOf(value.charAt(i)));
			    }
				i++;
		   }
		   res=simplify(rest.toString());
		   return new Snap(res,i);
	}
	public String reverse(String value)
	{
		Stack<String> stack=new Stack<>();
		for(int i=0;i<value.length();i++)
		{
			String res="";
			StringBuilder rest=new StringBuilder();
			Stack<Character> symbolCheck=new Stack<>();
			if(value.charAt(i)=='(')
			{
				symbolCheck.add('('); i++;
				while(!symbolCheck.isEmpty())
				{
					if(value.charAt(i)=='(')
						symbolCheck.add('(');
					if(value.charAt(i)==')')
						 symbolCheck.pop();
					if(!symbolCheck.isEmpty())
					     rest.append(String.valueOf(value.charAt(i)));
					i++;
				}
				res="("+reverse(rest.toString())+")"; i--;
			}
			else
			{
				res=res+value.charAt(i);
			}
			stack.add(res);
		}
		String res="";
		while(!stack.isEmpty())
		{
			res=res+stack.pop();
		}
		return res;
	}
	public List getOperationsInOrder(String operations)
	{
		int index=0;
		List<Character> list=new ArrayList<>();
		while(true)
		{
		 char firstChar=operations.charAt(index);
		 index++;
	     int count=1;
	     while(index<operations.length())
	     {
	    	 if(firstChar==operations.charAt(index))
	             count++;	
	    	 else
	    	     break;
	    	 index++;
	     }
	     if(firstChar=='R' && count%2!=0)
	    	 list.add('R');
	     if(firstChar=='S')
	    	 list.add('S');
	     if(index>=operations.length())
	    	 break;
		}
		
		return list;
	}
	
	public String[] getParsedInput(String s)
	{
		String[] res=s.split("/");
		return res;
	}

}
