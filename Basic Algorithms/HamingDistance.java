import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class HamingDistance {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s=new Scanner(System.in);
        int numItems=s.nextInt();
        for(int i=0;i<numItems;i++)
        {
               char[] temp=s.next().toCharArray();
               int k=s.nextInt();
               int count=0;
               for(int j=0;j<temp.length;j++)
               {
                   if(temp[j]!='a')
                   {
                       temp[j]='a';
                       count++;
                   }
                   else
                   {
                	   if(count+(temp.length-j)<=k)
                	   {
                		   temp[j]='b';
                	   }
                   }
                   if(count>=k)
                	   break;
               }
               Arrays.sort(temp);
               String result=new String(temp);
               System.out.println(result);
        }
    }
}