import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;

import javax.management.Query;

/**
 * Class to check for possibility of Eular Path in graph
 * @author Naveenraj
 *
 */
public class EularChecks {
	
	ArrayList<Vertex> oddEdgeVertexs=new ArrayList<>();
	
	/**
	 * Procedure to check validity of each vertex degree. 
	 * @param g
	 * @return
	 */
	public int checkGraphDegree(Graph g)
	{
		boolean haveZeroDegree=false;
		int number_of_odd=0;
		for(Vertex v: g.v)
		{
			if(v==null)
				continue;
			if(v.adj==null || v.adj.size()==0)
			{
				haveZeroDegree=true;                        // there is disconnected edge in graph 
			}
			else if(v.adj.size()%2!=0)
			{
				oddEdgeVertexs.add(v);                     // Eular can have maximum of 2 odd degree vertex 
				number_of_odd++;
			}
			
		}
		
		if(haveZeroDegree)
		{
			System.out.println("Graph is disconnected, No Eulerian");
			return -1;
		}
		else
		{
			if(number_of_odd==0 || number_of_odd==2)
			{
				return 1;                                 // there is possibility of 
			}
			else
			{
				System.out.println("Graph has irregular Odd edges, No Eulerian");
				return -1;
			}
			
		}
	
	}
	
	/**
	 * Procedure to reset the seen flag to the graph 
	 * @param g
	 */
	public void resetVertexSeen(Graph g)
	{
		for(Vertex v: g.v)
		{
			if(v==null)
				continue;                       
			v.seen=false;                           // for all vertex in graph seen is set to false 
			
		}
	}
	
	/**
	 * Use DFS to check connectedness of Graph
	 * @param g
	 * @return
	 */
	public int checkGraphConnected(Graph g)
	{
		ArrayList<Vertex> vertexList=(ArrayList<Vertex>)g.v;
		ArrayDeque<Vertex> dfsStack=new ArrayDeque<>();
		if(vertexList.size()==0)
			return -1;
	
		dfsStack.push(vertexList.get(1));
		Vertex temp;
		int count=0;
		while(!dfsStack.isEmpty())
		{
			temp=dfsStack.pop();
			
			if(!temp.seen)
			{
		    count++;
			temp.seen=true;
			for(Edge e: temp.adj)
			  {
				if(e.from!=temp)
					dfsStack.push(e.from);               // if all the vertex can be reached from a vertex, then the graph is connected. Else not 
				else
					dfsStack.push(e.to);
			  }
			}
		}
		
		if(count==(g.v.size()-1))
			return 1;
		else
		{
			 System.out.println("Graph is not connected, Cannot find Eular Path");
		     return -1;
		}
	}

}
