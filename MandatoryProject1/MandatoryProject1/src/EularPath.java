import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class to find and vertify Eular Path if there is one
 * @author Naveenraj
 *
 */
public class EularPath {
	
	/**
	 * Procedure will return Eular path, if it is present. 
	 * @param start
	 * @param g
	 * @return
	 */
	public CircularLinkedList<Tour> findEularPath(Vertex start,Graph g)
	{
		//System.out.println("Start vertex "+start.name);
		Queue<Vertex> discoveredVertex=new LinkedList<>();
		int hashArray[]=new int[g.v.size()];
		
		for(int i=0;i<hashArray.length;i++)
		{
			hashArray[i]=-1;                                 // sets to -1, so that it will be used to check vertex processed. 
		}
		
		Vertex temp=null;                                    // temp vertex 
		Edge edgeNow=null;                                   // temp edge
		CircularLinkedList<Tour> circularList=new CircularLinkedList<>();    //Circular List to store the eular path  
		CircularLinkedList<Tour>.Entry<Tour> stichPosition=null;             // pointer to keep track of node to stich now
		boolean firstRow=true;
		
		int count=0;
		Queue<CircularLinkedList<Tour>.Entry<Tour>> stichPlace= new LinkedList<>();    // Queue to maintain, what all node we need to stich 
		discoveredVertex.add(start);                                                   // Add start vertex to the 
		hashArray[start.name]=start.name;
		int countEdgeCurVertex=0;
		while(!discoveredVertex.isEmpty())            
		{                                                                              // loop till there is a vertex to be processed
			countEdgeCurVertex=0;
			temp=discoveredVertex.poll();
		    int startVertex=temp.name;                                                // making curent vertex to seen and start finding sub tour 
			//System.out.println("current processing vertex "+startVertex);
		    if(!temp.seen)
			{
				temp.seen=true;
				while(true)
				{
			          edgeNow=findUnvisitedEdge(temp);                         //get an unvisited edge for the vertex
			          if(edgeNow==null)
			          {
			        	  if(countEdgeCurVertex>=1)
			        	     return null;
			        	  else 
			        		  break;
			          }
			          countEdgeCurVertex++;
			          //System.out.println(temp.name+" "+edgeNow.from.name+" "+edgeNow.to.name);
			          edgeNow.visited=true;                                    //mark edge as visited
				      Tour t=new Tour();
				      if(edgeNow.from!=temp)
				     {
					    t.start=temp;
					    t.end=edgeNow.from;
				     }
				     else
				     {
					    t.start=temp;
					    t.end=edgeNow.to;
				     }
                     t.curEdge=edgeNow;                                         //store details in the class
				     if(!(hashArray[t.end.name]==t.end.name))
				     {
                         discoveredVertex.add(t.end);
                         hashArray[t.end.name]=t.end.name;                      // vertex is encountered first time 
                         count++;
                         CircularLinkedList<Tour>.Entry<Tour> temp1=circularList.addAt(t,stichPosition);     //add to the circular List current path
			    	     stichPlace.add(temp1);                                // if vertex is encountered first time, need to maintain stich position 
			    	     if(!firstRow)
			    	     stichPosition=temp1;
				     }
				     else
				     {
				    	  CircularLinkedList<Tour>.Entry<Tour> temp1=circularList.addAt(t,stichPosition);
				    	  if(!firstRow)                                       //vertex is already encounterd 
				    	  stichPosition=temp1;
				     }
				     if(t.end.name==startVertex)
						    break;                                            //if start vertex is encountered again break the sub tour 
				     temp=t.end;
				}
				//printList(circularList);
				stichPosition=stichPlace.poll();
				//if(count >=(g.v.size()-1))                                    // all the vertex has encountered means , there should be a eular tour 
					//break;
				firstRow=false;
			}
		}
		return circularList;
	}
	
	/**
	 * Procedure to print the tours
	 * @param tours
	 */
	public void printLists(ArrayList<ArrayList<Tour>> tours)
	{
		
		for(ArrayList<Tour> t: tours)
		{
			System.out.println();
			for(Tour list: t)
			{
				System.out.print("( "+list.start.name+","+list.end.name+" ),");
			}
		}
	}
	
	public <T extends Comparable <? super T>> void printList(CircularLinkedList<Tour> list1)
	{
		CircularLinkedList<Tour>.Entry<Tour> head=list1.header.next;
		CircularLinkedList<Tour>.Entry<Tour> start=head;
		while(head!=null)
		{
			System.out.print("( "+head.element.start+","+head.element.end+" ),");  //loop till last element in list and print
			head=head.next;
			if(head==start)
				break;
		}
		System.out.println();
	}
	
	/**
	 * 
	 * Procedure to find and return an unvisited edge. 
	 * @param v
	 * @return
	 */
	public Edge findUnvisitedEdge(Vertex v)
	{
		int size=v.adj.size();
		if(v.index<size)
		{
		Edge e=v.adj.get(v.index);
		v.index++;                                            // have pointer of last used edge of verted and get the next one. 
		while(e.visited && v.index<size)
		{
			e=v.adj.get(v.index);
			v.index++;                                        // from last seen edge loop to find unvisited edge. 
		}
		if(e.visited)
			return null;
		return e;                                            // if no unvisited edge is found, return null
		}
		return null;
	}
	
	/**
	 * Procedure to validate the Eular Path
	 * @param g
	 * @param list
	 * @return
	 */
	public boolean validatePath(Graph g,CircularLinkedList<Tour> list)
	{
		CircularLinkedList<Tour>.Entry<Tour> head=list.header.next;
		CircularLinkedList<Tour>.Entry<Tour> start=head;
		boolean checkFlag=true;
		int prev=0;
		int countEdge=0;
		while(head!=null)                                  
		{
			int cur=head.element.start.name;                           // if current node start and previous node end are not same then, it is not valid path
			
			if(head.element.curEdge!=null)
			{
			   if(head.element.curEdge.visited)
			   {
				   head.element.curEdge.visited=false;                 // if current edge if already used, we cant use it again. So it is not valid path 
			       countEdge++;
			   }
			   else
			   {
				   checkFlag=false;
				   break;
			   }
			}
			else
			{
				checkFlag=false;
				break;
			}
			
			if(prev!=0 && cur!=prev)
			{
				checkFlag=false;                                     // works by the basic of, finding falult. If the first incorrect path is found. Break and make invalid
				break;
			}
			
			prev=head.element.end.name;
			
			head=head.next;
		}
		
		if(g.numEdge==countEdge)
			checkFlag=true;
		else
			checkFlag=false;
		
		return checkFlag;
	}

}
