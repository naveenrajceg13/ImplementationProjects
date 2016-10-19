import java.io.BufferedReader;
import java.util.Scanner;


public class AVL<T extends Comparable<? super T>> {
	
	class Entry<T>
	{
		T element;
		Entry<T> left;
		Entry<T> right;
		int height;
		
		Entry(T element, Entry<T> left,Entry<T> right)
		{
			this.element=element;
			this.left=left;
			this.right=right;
			this.height=0;
		}
	}
	
	Entry<T> root;
	int size;
	boolean flag=true;
	
	public AVL() {
		root=null;
		size=0;
	}
	
	/**
	 * Procedure which returns true if element is added
	 */	
	public boolean add(T x)
	{
	     root=add(root,x);
	     return true;
	}
	
	/**
	 * Procedure which adds an element to the AVL tree
	 */	
	public Entry<T> add(Entry<T> node,T x)
	{
		if(node==null)
		{
			return new Entry<>(x,null,null);
		}
		else
		{
			if(x.compareTo(node.element)<0)
			{
				node.left=add(node.left,x);
			}
			 else
			{
				node.right=add(node.right,x);
			}
			
			node.height=Math.max(getHeight(node.left), getHeight(node.right))+1;
			
			int balance=getDifference(node);
			
			
			if(balance > 1 && x.compareTo(node.left.element)<0)
			{
				return rightRotation(node);
			}
			else if(balance < -1 && x.compareTo(node.right.element)>0)
			{
				return leftRotation(node);
			}
			else if(balance < -1 && x.compareTo(node.right.element)<0)
			{
				node.right=rightRotation(node.right);
				return leftRotation(node);
			}
			else if(balance > 1 && x.compareTo(node.left.element)>0)
			{
				node.left=leftRotation(node.left);
				return rightRotation(node);
			}
			return node;
		}
	}
	/**
	 * Procedure to perform left rotation
	 */	
	public Entry<T> leftRotation(Entry<T> node)
	{
		Entry<T> rootNow=node.right;
		Entry<T> nodeRight=rootNow.left;
		rootNow.left=node;
		node.right=nodeRight;
		
		rootNow.height=Math.max(getHeight(rootNow.left), getHeight(rootNow.right))+1;
		if(nodeRight!=null)
		nodeRight.height=Math.max(getHeight(nodeRight.left), getHeight(nodeRight.right))+1;
		return rootNow;
	}
	
	
	/**
	 * Procedure to perform right rotation
	 */	
	public Entry<T> rightRotation(Entry<T> node)
	{
		Entry<T> rootNow=node.left;
		Entry<T> nodeLeft=rootNow.right;
		
		rootNow.right=node;
		node.left=nodeLeft;
		
		rootNow.height=Math.max(getHeight(rootNow.left), getHeight(rootNow.right))+1;
		if(nodeLeft!=null)
		nodeLeft.height=Math.max(getHeight(nodeLeft.left), getHeight(nodeLeft.right))+1;
		
		return rootNow;
	}
	
	
	/**
	 * Procedure to check for height difference
	 */	
	public int getDifference(Entry<T> node)
	{
		if(node==null)
			return 0;
		return getHeight(node.left) - getHeight(node.right);
	}
	
	/**
	 * Procedure to return height of the node
	 */	
	public int getHeight(Entry<T> node)
	{
		if(node==null)
			return -1;
		
		return node.height;
	}
	
	/**
	 * Procedure to print the AVL tree inorder
	 */	
	public void print()
	{
		inorder(root);
	}
	
	/**
	 * Procedure for in order traversal of the tree
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
	 * Procedure to check if the given tree is BST
	 */	
	public boolean isBST()
	{
		BalancedCheckMaxMin<T> minValue=new BalancedCheckMaxMin<>(false, true, null);
		BalancedCheckMaxMin<T> maxValue=new BalancedCheckMaxMin<>(true, false, null);
		return isBST(root, minValue, maxValue);
	}
	
	public boolean isBST(Entry<T> node,BalancedCheckMaxMin<T> minValue,BalancedCheckMaxMin<T> maxValue)
	{
		BalancedCheckMaxMin<T> newMinValue=new BalancedCheckMaxMin<>(minValue.plusInfinityFlag, minValue.minusInfinityFlag, minValue.value);
		BalancedCheckMaxMin<T> newMaxValue=new BalancedCheckMaxMin<>(maxValue.plusInfinityFlag, maxValue.minusInfinityFlag, maxValue.value);
		if(node!=null)
		{
			newMaxValue.plusInfinityFlag=false;
			newMaxValue.value=node.element;
			newMinValue.minusInfinityFlag=false;
			newMinValue.value=node.element;
		}
		 if(node==null)
			return true;
		 return  (minValue.minusInfinityFlag || minValue.value.compareTo(node.element)<0)
				             && (maxValue.plusInfinityFlag || maxValue.value.compareTo(node.element)>0)
				             && (isBST(node.left,minValue,newMaxValue))
				             && (isBST(node.right, newMinValue, maxValue));
		
	}
	
	public boolean isBalanced()
	{
		return isBalanced(root);
	}
	
	public boolean isBalanced(Entry<T> node)
	{
		if(node==null)
			return true;
		
		if(Math.abs(getHeight(node.left)-getHeight(node.right))>1)
			return false;
		else
			return true;
	}
	
	/**
	 * Procedure to check if the given tree is balanced
	 */	
	public boolean isAVL()
	{
		return isBalanced() && isBST();
	}
	public static void main(String args[])
	{
		AVL<Integer> tree = new AVL<>();
		System.out.println("Enter the elements to be added to the AVL tree(separated by spaces and press enter):");
		Scanner s=new Scanner(System.in);
		
		while(s.hasNext())
		{
			int x=s.nextInt();
			 if(x > 0) 
			 {
					tree.add(x);
					System.out.println();
					System.out.println("Tree now(inorder)");
					tree.print();
					System.out.println();
					System.out.println("Is AVL?: "+tree.isAVL());
		     } 
		}
		s.close();
		
	}

}
