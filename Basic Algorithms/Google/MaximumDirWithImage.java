package Google;

import java.util.HashSet;

public class MaximumDirWithImage {

	class Snap
	{
		int value;
	}
	class Store
	{
		String s;
		int space;
		Store(String s,int space)
		{
			this.s=s;
			this.space=space;
		}
	}
	public static void main(String args[])
	{
		MaximumDirWithImage maxImage=new MaximumDirWithImage();
		String s="dir1\n dir11\n dir12\n  picture.jpeg\n  dir121\n  file1.txt\ndir2\n file2.gif";
		HashSet<String> format=new HashSet<>();
		format.add("jpeg");
		format.add("png");
		format.add("gif");
		Snap snap=maxImage.new Snap();
		System.out.println(maxImage.getMaximumLengthOfImage(s,format,snap));
	}
	
	public int getMaximumLengthOfImage(String s,HashSet<String> format,Snap snap)
	{
		String[] temp=s.split("\n");
		Store[] values=new Store[temp.length];
		int index=0;
		for(String str: temp)
		{
			int spaceCount=0;
		    while(str.charAt(spaceCount)==' ')
		          spaceCount++;
		    
		    Store store=new Store(str.trim(),spaceCount);
		    values[index]=store;
		    index++;
		}
		int value=getMaximumLengthOfImage(values,format,0);
		return value;
	}
	public int getMaximumLengthOfImage(Store[] values,HashSet<String> format,int index)
	{
		int max=0;
		for(int i=0;i<values.length;i++)
		{
			Store temp=values[i];
			if(checkIsImage(temp.s,format))
			{
				int count=getLastNSpaceChar(values,i-1,temp.space-1);
				max=Math.max(count, max);
			}
		}
		return max;
	}

	public boolean checkIsImage(String s,HashSet<String> format)
	{
		String[] ss=s.split("\\.");
		if(ss.length>1)
		{
			if(format.contains(ss[1]))
				return true;
		}
		return false;
	}
	
	public int getLastNSpaceChar(Store[] values,int max,int spaces)
	{
		int add=spaces;
		int total=0;
		for(int i=max;i>=0 && spaces>=0 ;i--)
		{
			Store temp=values[i];
			if(temp.space==spaces)
			{
				total=total+temp.s.length();
				spaces--;
			}
		}
		return total+add+1;
	}
}
