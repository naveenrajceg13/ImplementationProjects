import java.util.Scanner;

public class BinarySearchTree<T extends Comparable<? super T>> {

	class Entry<T>
	{
		T element;
		Entry<T> left;
		Entry<T> right;
		
		Entry(T element, Entry<T> left,Entry<T> right)
		{
			this.element=element;
			this.left=left;
			this.right=right;
		}
	}
	
	Entry<T> root;
	int size;
	boolean flag=true;
	
	public BinarySearchTree() {
		root=null;
		size=0;
	}
	
	
	/**
	 * Procedure for finding an element in the BST
	 */	
	Entry<T> find(Entry<T> t,T x)
	{
		Entry<T> prev=t;
		while(t!=null)
		{
			int compare=x.compareTo(t.element);
			prev=t;
			if(compare==0)
				return t;
			if(compare<0)
				t=t.left;
			if(compare>0)
				t=t.right;
		}
		return prev;
	}
	
	public boolean contains(T x)
	{
		Entry<T> node=find(root,x);
		return (node!=null && node.element==x);
	}
	
	/**
	 * Procedure for adding an element in the BST
	 */	
	public boolean add(T x)
	{
		Entry<T> node=find(root,x);
		if(node==null)
		{
			root=new Entry<>(x,null,null);
		}
		else if(node.element==x)
			return false;
		else
		{
			Entry<T> newNode=new Entry<>(x,null,null);
			if(x.compareTo(node.element)<0)
			{
				node.left=newNode;
			}
			else
			{
			   node.right=newNode;	
			}
		}
		size++;
		return true;
	}
	
	/**
	 * Procedure for printing elements in the BST inorder
	 */	
	public void print()
	{
		inorder(root);
	}
	
	/**
	 * Procedure for inorder traversal of the BST
	 */	
	public void inorder(Entry<T> t)
	{
		if(t!=null)
		{
		inorder(t.left);
		System.out.print(t.element+" ");
		inorder(t.right);
		}
	}
	/**
	 * Procedure for removing an element from the BST
	 */
	public T remove(T x)
	{
		Entry<T> node=find(root,x);
		
		if(node!=null && node.element != x)
			return null;
		else
		{
		    if(node.element==x)
		    {
		    	T t=node.element;
		    	remove(node);
		    	size--;
		    	return t;
		    }
		}
		return null;
	}
	
	/**
	 * Procedure(helper function) for removing an element from the BST
	 */
	public void remove(Entry<T> node)
	{
		if(node.left!=null && node.right!=null)
		{
		    removeTwo(node);	
		}
		else
		{
			removeOne(node);
		}
	}
	
	/**
	 * Procedure for removing an element from the BST that has two child
	 */
	
	public void removeTwo(Entry<T> node)
	{
		if(flag)
		{
		   Entry<T> minRight=node.right;
		   while(minRight.left!=null)
		   {
			  minRight=minRight.left;
		   }
		   node.element=minRight.element;
		   removeOne(minRight);
		}
		else
		{
			Entry<T> maxLeft=node.left;
			while(maxLeft.right!=null)
			{
				maxLeft=maxLeft.right;
			}
			removeOne(maxLeft);
			node.element=maxLeft.element;
		}
		flag=!flag;
	}
	
	
	/**
	 * Procedure for removing an element from the BST that has one child
	 */
	public void removeOne(Entry<T> node)
	{
	     if(node==root)
	     {
	    	  root=oneChildNode(node);
	     }
	     else
	     {
	    	 Entry<T> parent=getParent(node);
	    	 Entry<T> childToReplace=oneChildNode(node);
	    	 if(parent.left==node)
	    		 parent.left=childToReplace;
	    	 else
	    		 parent.right=childToReplace; 
	    	
	     }
	}
	
	public Entry<T> getParent(Entry<T> node)
	{
	     Entry<T> parent=null;
	     Entry<T> temp=root;
	     while(temp!=null)
	     {
	    	 if(temp==node)
	    		 return parent;
	    	 else
	    	 {
	    		 parent=temp;
	    		 if((node.element.compareTo(temp.element)<0))
	    		 {
	    			 temp=temp.left;
	    		 }
	    		 else
	    		 {
	    			 temp=temp.right;
	    		 }
	    	 }	 
	     }
	     
	     return parent;
	}
	
	public Entry<T> oneChildNode(Entry<T> node)
	{
		return node.left!=null?node.left:node.right;
	}
	
	public static void main(String args[])
	{
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		System.out.println("Enter the elements to be added or removed the BST tree(separated by spaces and press enter):");
		Scanner s=new Scanner(System.in);
		
		while(s.hasNext())
		{
			int x=s.nextInt();
			 if(x > 0) 
			 {
				 	System.out.println();
					tree.add(x);
					System.out.println("Tree now");
					tree.print();
					System.out.println();
		     } 
			 else if(x < 0) 
			 {
				 	System.out.println();
                    System.out.println("Removed node " +tree.remove(Math.abs(x)));
					System.out.println("Tree now");
					tree.print();
					System.out.println();
		     }
		}
	}
}
