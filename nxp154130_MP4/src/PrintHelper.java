/** Print Helper Class
 * author : Naveenraj  
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PrintHelper {
	
	/**
	 * Print all critical paths
	 * @param start
	 * @param end
	 * @param criticalPaths
	 */
	public void printCriticalPaths(Vertex start,Vertex end,List<LinkedList> criticalPaths)
	{
		for(List list: criticalPaths)
		{
			for(Object v: list)
			{
				if(((Vertex)v).name != start.name && ((Vertex)v).name !=end.name)
				    System.out.print(((Vertex)v).name+" ");
			}
			System.out.println();
		}
		
	}
	
	/**
	 * Length of Critical path is LC of T ( End Vertex )
	 * @param end
	 */
	public void printLengethOfCriticalPath(Vertex end)
	{
		System.out.println(end.LC);
	}
	
	/**
	 * Print Table as Asked
	 * @param start
	 * @param end
	 * @param g
	 */
	public void printTableOfECandLCandSlack(Vertex start,Vertex end,Graph g)
	{
		System.out.println("Task\tEC\tLC\tSlack");
		for(Vertex v: g)
		{
			if(((Vertex)v).name != start.name && ((Vertex)v).name !=end.name)
			     System.out.println(v.name+"\t"+v.EC+"\t"+v.LC+"\t"+v.slack);
		}
	}
	
	/**
	 * Print the first Critical Path
	 * @param start
	 * @param end
	 * @param criticalPaths
	 */
	public void printACriticalPath(Vertex start,Vertex end,List<LinkedList> criticalPaths)
	{
		if(criticalPaths.size()>0)
		{
			List list=criticalPaths.get(0);
			for(Object v: list)
			{
				if(((Vertex)v).name != start.name && ((Vertex)v).name !=end.name)
				    System.out.print(((Vertex)v).name+" ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Print Number of Critical Nodes, Excluding start and end
	 * @param criticalNodes
	 */
	public void printNumberOfCriticalNodes(Map criticalNodes)
	{
		System.out.println((criticalNodes.keySet().size()-2));
	}
	
	/**
	 * Print Number of Critical Path, Which is size of List<CriticalPath>
	 * @param criticalPaths
	 */
	public void printNumberOfCriticalPath(List<LinkedList> criticalPaths)
	{
		System.out.println(criticalPaths.size());
	}

}
