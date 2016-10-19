
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class to find and vertify Eular Path if there is one
 * @author Naveenraj
 *
 */
public class EularPathWithSubTours {
	
	/**
	 * Procedure will return Eular path, if it is present. 
	 * @param start
	 * @param g
	 * @return
	 */
	
	public Queue<CircularLinkedList<Tour>.Entry<Tour>> stichPlace;
	public ArrayList<CircularLinkedList<Tour>> findEularPath(Vertex start,Graph g)
	{
		
		Queue<Vertex> discoveredVertex=new LinkedList<>();
		ArrayList<CircularLinkedList<Tour>> circularLists;
		int hashArray[]=new int[g.v.size()];
		
		for(int i=0;i<hashArray.length;i++)
		{
			hashArray[i]=-1;                                 // sets to -1, so that it will be used to check vertex processed. 
		}
		
		Vertex temp=null;                                    // temp vertex 
		Edge edgeNow=null;                                   // temp edge 
		boolean firstRow=true;
		
		int count=0;
		stichPlace= new LinkedList<>();                                                // Queue to maintain, what all node we need to stich 
		circularLists=new ArrayList<>(); 
		discoveredVertex.add(start);                                                   // Add start vertex to the 
		hashArray[start.name]=start.name;
		int countEdgeCurVertex=0;
		while(!discoveredVertex.isEmpty())            
		{                                                                              // loop till there is a vertex to be processed
			CircularLinkedList<Tour> subTour=new CircularLinkedList<>();
			countEdgeCurVertex=0;
			temp=discoveredVertex.poll();
		    int startVertex=temp.name;                                                       // making curent vertex to seen and start finding sub tour 
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
			        	  {
			        		  break;
			        	  }
			          }
			          countEdgeCurVertex++;
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
                         subTour.add(t);
                         stichPlace.add(subTour.tail);
				     }
				     else
				     {
				    	 subTour.add(t);
				     }
				     if(t.end.name==startVertex)
						    break;                                            //if start vertex is encountered again break the sub tour 
				     temp=t.end;
				}
				circularLists.add(subTour);
			}
		}
		return circularLists;
	}
	
	public CircularLinkedList<Tour> switchTours(ArrayList<CircularLinkedList<Tour>> circularLists)
	{
		CircularLinkedList<Tour>.Entry<Tour> temp;
		CircularLinkedList<Tour>.Entry<Tour> tempNext;
		boolean firstTime=true;
		for(CircularLinkedList<Tour> list:circularLists)
		{
			if(!firstTime)
			{
			temp=stichPlace.poll();
			if(list.size==0)
				   continue;
			tempNext=temp.next;
			temp.next=list.header.next;
			list.tail.next=tempNext;
			}
			firstTime=false;
		}
		firstTime=true;
       
	   return circularLists.get(0);
	}
	
	/**
	 * Procedure to print the tours
	 * @param tours
	 */
	public void printLists(ArrayList<ArrayList<Tour>> tours)
	{
		int count=0;
		for(ArrayList<Tour> t: tours)
		{
			//System.out.println();
			for(Tour list: t)
			{
				//System.out.print("( "+list.start.name+","+list.end.name+" ),");
				count++;
			}
		}
		System.out.println(count);
	}
	
	public <T extends Comparable <? super T>> void printList(CircularLinkedList<Tour> list1)
	{
		CircularLinkedList<Tour>.Entry<Tour> head=list1.header.next;
		CircularLinkedList<Tour>.Entry<Tour> start=head;
		int count=0;
		while(head!=null)
		{
			//System.out.print("( "+head.element.start+","+head.element.end+" ),");  //loop till last element in list and print
			System.out.println(head.element.start);
			head=head.next;
			count++;
			if(head==start)
				break;
		}
		//System.out.println();
		//System.out.println(count);
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
