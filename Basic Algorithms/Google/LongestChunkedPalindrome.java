package Google;

import java.util.HashMap;

public class LongestChunkedPalindrome {
	
	HashMap<String,Integer> hm;
	public static void main(String args[])
	{
		LongestChunkedPalindrome lonPal=new LongestChunkedPalindrome();
		String s="ghiabcdefhelloadamhelloabcdefghi";
		lonPal.hm=new HashMap<>();
		System.out.println(lonPal.calculateLongestChunkPalindrome(s));
	}

	public int calculateLongestChunkPalindrome(String s)
	{
		if(s.length()<=1)
		    return s.length();
		
		if(hm.containsKey(s))
			return hm.get(s);
		
		int max=1;
		int goTill=s.length()/2;
		
		for(int i=1;i<=goTill;i++)
		{
			String left=s.substring(0,i);
			String middle=s.substring(i,s.length()-i);
			String right=s.substring(s.length()-i,s.length());

			if(left.equals(right))
				max=Math.max(max, calculateLongestChunkPalindrome(middle)+2);
			
		}
		
		hm.put(s, max);
		return max;
	}
}
