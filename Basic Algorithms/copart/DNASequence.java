/*
 * Naveenraj Palanisamy
 * Chandni Shankar
 * Apurva Alekar
 */

package copart;

import java.util.Scanner;

public class DNASequence {

	
	public static void main(String args[])
	{
		DNASequence dna=new DNASequence();
		String input;
		Scanner s=new Scanner(System.in);
		input=s.next();
		System.out.println(dna.getMaxSequenceSubArray(input.toCharArray()));   //helper function to call the required function
	}
	
	public String getMaxSequenceSubArray(char[] s)
	{
		if(s.length<1)
			return null;
		int max=1;
		char maxChar=s[0];
		int countNow=1;
		char charNow=s[0];
		for(int i=1;i<s.length;i++)
		{
			if(charNow==s[i])
			{
				countNow++;
				
				if(countNow>max)
				{
					max=countNow;
					maxChar=s[i];                    //linearly iterate and get the max Value of current sub array
				}
			}
			else
			{
				countNow=1;
				charNow=s[i];
			}
		}
		
		return maxChar+" "+max;
	}
}
