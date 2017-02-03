package Google;

import java.util.Arrays;

class Tree{
	Entry root;
	class State
	{
		int longestIncreasing;
		int longestDecressing;
		State()
		{
			this.longestDecressing=1;
			this.longestIncreasing=1;
		}
	}
	class Entry{
		Entry left;
		Entry right;
		int data;
		Entry(int data)
		{
			this.data=data;
			this.left=null;
			this.right=null;
		}
	}
	Tree()
	{
		root=null;
	}
	
	void insert(int x)
	{
		 //System.out.println(x);
	     this.root=insert(x,this.root);
	     //this.inorder();
	}
	
	Entry insert(int x,Entry e)
	{
		if(e==null)
			return new Entry(x);
		
		if(x<=e.data)
			e.left=insert(x,e.left);
		else
			e.right=insert(x,e.right);
		
		return e;
	}
	
	void inorder()
	{
	    inorder(this.root);
	    System.out.println();
	}
	
	void inorder(Entry e)
	{
		if(e!=null)
		{
			inorder(e.left);
			System.out.print(e.data+" ");
			inorder(e.right);
		}
	}
	
	public void getLongestIncreasingOrDecresing()
	{
		State s=new State();
		getLongestIncreasingOrDecresing(this.root.left, s, 1, 1, this.root);
		getLongestIncreasingOrDecresing(this.root.right, s, 1, 1, this.root);
		
		System.out.println(s.longestDecressing+" "+s.longestIncreasing);
	}
	
	public void getLongestIncreasingOrDecresing(Entry e,State s,int minCount,int maxCount,Entry parent)
	{
	   if(e!=null)
	   {
		if(e.data>parent.data)
		{
			maxCount++;
			minCount=1;
		}
		else if(e.data<parent.data)
		{
			minCount++;
			maxCount=1;
		}
		else
		{
			minCount++;
			maxCount++;
		}
		
		if(minCount>s.longestDecressing)
			s.longestDecressing=minCount;
		if(maxCount>s.longestIncreasing)
			s.longestIncreasing=maxCount;
		
		getLongestIncreasingOrDecresing(e.left, s, minCount, maxCount, e);
		getLongestIncreasingOrDecresing(e.right, s, minCount, maxCount, e);
	   }
	}
}
public class LongestIncreasingOrDecresingSequence {

	public static void main(String args[]){
	LongestIncreasingOrDecresingSequence lcs=new LongestIncreasingOrDecresingSequence();
	Tree t=new Tree();
	int a[]={4,2,5,7,3,4,3,2,5,23,53,1,58,60,3,2};
	Arrays.sort(a);
	lcs.sortInsert(a, 0, a.length-1,t);
	t.getLongestIncreasingOrDecresing();
  }
	
	public void sortInsert(int[] a,int start,int end,Tree t)
	{
		if(start<=end)
		{
				int mid=(start+end)/2;
			    t.insert(a[mid]);
			    System.out.println(a[mid]);
				sortInsert(a, start, mid-1, t);
				sortInsert(a, mid+1, end, t);
		}
	}
}
