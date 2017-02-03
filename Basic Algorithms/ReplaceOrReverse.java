
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ReplaceOrReverse {

    public static void main(String[] args) {
        
    	Scanner s=new Scanner(System.in);
    	int n;
    	n=s.nextInt();
    	int[] array=new int[n];
    	
    	for(int i=0;i<n;i++)
    	{
    		array[i]=s.nextInt();
    	}
    	
    	boolean reverse=false;
    	boolean swap=false;
    	boolean breakpoint=false;
    	boolean endpoint=false;
    	boolean sorted=true;
    	boolean just_find=false;
    	int breakPoint=array[0];
    	int endPoint=array[0];
    	int count=0;
    	int prev=array[0];
    	int prev_index=0;
    	
    	for(int i=1;i<n;i++)
    	{
    		System.out.println(array[i]+" "+breakPoint+" "+endpoint);
    		if(!breakpoint)
    		{ 
    			if(array[i]<breakPoint)
    			    breakpoint=true;
                if(breakpoint)
                {
    			breakPoint=prev_index;
    			endPoint=i;
                }
                else
                breakPoint=array[i];	
    			
    		}
    		else if(!endpoint)
    		{
    			if(array[i]<prev)
    			{
    				endPoint=array[i];
    				count++;
    			}
    			else
    			{
    				endPoint=prev_index;
    				endpoint=true;
    				if(array[i]<array[breakPoint])
    				{
    					sorted=false;
    					break;
    				}
    			}
    		}
    		else
    		{
    			if(array[i]>prev)
    				continue;
    			else
    			{
    				sorted=false;
    				break;
    			}
    		}
    		prev=array[i];
    		prev_index=i;
    	}
    	
    	if(sorted)
    	{
    		if(count>1)
    		{
    			System.out.println("yes");
    			System.out.println("reverse "+(breakPoint+1)+" "+(endPoint+1));
    		}
    		else
    		{
    			if(breakpoint)
    			{
    			   if(endpoint)
    			   {
    			   System.out.println("yes");
    			   System.out.println("swap "+(breakPoint+1)+" "+(endPoint+1));
    			   }
    			   else
    			   {
    				System.out.println("yes");
        		    System.out.println("swap "+(breakPoint+1)+" "+(array.length));      
    			   }
    			}
    			else
    			{
    			   System.out.println("yes");
    			}
    		}
    	}
    	else
    	{
    		System.out.println("no");
    	}
    	
    }
}