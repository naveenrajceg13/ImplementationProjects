package Google;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class DeleteInNArrayTree {
	
	private class TreeNode{
		int val;
		TreeNode[] child;
	}
	
	public void manuallyInsertValues(TreeNode t1)
	{
		TreeNode t10 = new TreeNode(); t10.val = 10;
        TreeNode t9 = new TreeNode(); t9.val = 9; t9.child = new TreeNode[] { t10 };
        TreeNode t8 = new TreeNode(); t8.val = 8;
        TreeNode t7 = new TreeNode(); t7.val = 7;
        TreeNode t6 = new TreeNode(); t6.val = 6; t6.child = new TreeNode[] { t9 };
        TreeNode t5 = new TreeNode(); t5.val = 5; t5.child = new TreeNode[] { t7, t8 };
        TreeNode t4 = new TreeNode(); t4.val = 4; t4.child = new TreeNode[] { t5, t6 };        
        TreeNode t3 = new TreeNode(); t3.val = 3;
        TreeNode t2 = new TreeNode(); t2.val = 2;
        t1.child = new TreeNode[] { t2, t3, t4 };
	}

	public List deleteNodes(TreeNode t,Set<Integer> set)
	{
		Queue<TreeNode> q=new LinkedList<>();
		List<TreeNode> deletes=new ArrayList<>();
		q.add(t);
		while(!q.isEmpty())
		{
			TreeNode now=q.poll();
			if(now.child==null)
				continue;
			for(int i=0;i<now.child.length;i++)
			{
				if(set.contains(now.child[i].val))
				{
					deletes.add(now.child[i]);
				    boolean bool=processDelete(now.child[i],now,i);
				    if(bool)
				    	    q.add(now.child[i]);
				}
				else
					q.add(now.child[i]);
			}
		}
		return deletes;
	}
	
	
	public boolean processDelete(TreeNode t,TreeNode parent,int i)
	{
		if(t.child==null)
		{
			parent.child[i]=null;
			return false;
		}
		else
		{
			parent.child[i]=t.child[0];
			append(t.child[0],t);
			t.child=null;
			return true;
		}
	}
	public void append(TreeNode t,TreeNode t1)
	{   
		TreeNode[] array;
		int index=0;
		if(t.child==null)
		{
			t.child=t1.child;
			array=new TreeNode[t1.child.length-1];
		}
		else
		{
		    array=new TreeNode[t.child.length+t1.child.length];
		    for(int i=0; t.child!=null && i<t.child.length;i++)
			{
				array[i]=t.child[i];
			}
		    index=t.child.length;
		}
		for(int i=1;t1.child!=null && i<t1.child.length;i++)
		{
			array[index]=t.child[i];
			index++;
		}
		t.child=array;
	}
	
	public void print(TreeNode t)
	{
		Queue<TreeNode> q=new LinkedList<TreeNode>();
		q.add(t);
		while(!q.isEmpty())
		{
			TreeNode now=q.poll();
			System.out.println("head "+now.val);
			if(now.child==null)
				continue;
			for(int i=0;i<now.child.length;i++)
			{
				if(now.child[i]!=null)
				{System.out.print(now.child[i].val+" ");
				q.add(now.child[i]);}
			}
			System.out.println();
		}
	}
	public static void main(String args[])
	{
		DeleteInNArrayTree delNArray=new DeleteInNArrayTree();
		HashSet<Integer> set = new HashSet<>();
        set.add(3); set.add(5); set.add(9);
        TreeNode t = delNArray.new TreeNode(); t.val = 1; 
        delNArray.manuallyInsertValues(t);
        List list=delNArray.deleteNodes(t, set);
        delNArray.print(t);
	}
}
