
public class KMPPatternMatching {

	  public static void main(String args[])
	  {
		         KMPPatternMatching kmp=new KMPPatternMatching();
                 String s="aaacecaaa";
                 String patt=s+"$"+new StringBuilder(s).reverse().toString();
                 int[] parrtenArr=kmp.getPatternArray(patt);
                 for(char c: patt.toCharArray())
                	 System.out.print(c+" ");
                 System.out.println();
                 for(int i: parrtenArr)
                	 System.out.print(i+" ");
                 
                 System.out.println(new StringBuilder(s).reverse().toString().substring(0,s.length()-parrtenArr[patt.length()-1])+" "+s);
	  }
	  
	  public int[] getPatternArray(String s)
	  {
		  int dp[]=new int[s.length()];
		  for(int i=1;i<s.length();i++)
		  {
			    int j=dp[i-1];
			    
			    while(j>0 && s.charAt(i)!=s.charAt(j))
			    	j=dp[j-1];
			    
			    if(s.charAt(i)==s.charAt(j))
			          dp[i]=j+1;
		  }
		  return dp;
	  }
}
