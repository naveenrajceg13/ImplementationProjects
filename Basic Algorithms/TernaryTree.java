import java.util.LinkedList;
import java.util.Queue;

public class TernaryTree {
	
	  class Snap
	  {
		  Node n;
		  int level;
		  Snap(Node n,int level)
		  {
			  this.n=n;
			  this.level=level;
		  }
	  }
	  class Node
	  {
		  Node left;
		  Node right;
		  Node middle;
		  int data;
		  Node(int data)
		  {
			  this.data=data;
			  this.left=null;
			  this.middle=null;
			  this.right=null;
		  }
	  }
	  
	  class Tree
	  {
		  Node head;
		  Tree()
		  {
			  head=null;
		  }
		  public void insert(int data)
		  {
			  head=insert(data,head);
		  }
		  public Node insert(int data,Node node)
		  {
			  if(node==null)
				  return new Node(data);
			  if(data<node.data){ 
				  node.left=insert(data,node.left);}
			  else if(data>node.data){ 
				  node.right=insert(data,node.right);}
			  else{ 
				  node.middle=insert(data,node.middle);}
			  
			  return node;
		  }
		  public void preetyPrint()
		  {
			  System.out.println();
			  preetyPrint(head,0);
		  }
		  public void preetyPrint(Node node,int count)
		  {
			  if(node!=null)
			  {
				  System.out.println();
				  for(int i=0;i<count;i++)
				      System.out.print(" ");
				  System.out.print(node.data);
				  preetyPrint(node.left,count+1);
				  preetyPrint(node.middle,count+1);
				  preetyPrint(node.right,count+1);
			  }
		  }
		  public void printLevel()
		  {
			  printLevel(head);
		  }
		  
		  private void printLevel(Node node)
		  {
			  Queue<Snap> q=new LinkedList<>();
			  q.add(new Snap(node,0));
			  int currLevelPrinting=0;
			  while(!q.isEmpty())
			  {
				  if(q.peek().level!=currLevelPrinting)
				  {
					  System.out.println();
					  currLevelPrinting=q.peek().level;
				  }
				  else
				  {
					  Snap now=q.poll();
					  System.out.print(now.n.data+" ");
					  if(now.n.left!=null)
						  q.add(new Snap(now.n.left,now.level+1));
					  if(now.n.right!=null)
						  q.add(new Snap(now.n.right,now.level+1));
					  if(now.n.middle!=null)
						  q.add(new Snap(now.n.middle,now.level+1));
				  }
			  }
		  }
	  }
	  
	  public static void main(String args[])
	  {
		  TernaryTree t=new TernaryTree();
		  int arr[]={10,8,10,15,6,8,9,10,12,15,18};
		  Tree tr=t.new Tree();
		  for(int i: arr)
		  {
			  tr.insert(i);
		  }
		  tr.printLevel();
		  tr.preetyPrint();
	  }
}
