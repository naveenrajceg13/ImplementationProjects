/** Critical Path Class
 * author : Naveenraj  
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CriticalPaths {
	
	Graph g;
	TopologicalOrdering topOrdering;
	HelperForCriticalPath helper;
	PrintHelper printHelper;
	List topologicalOrder;
	List reverseTopologicalOrder;
	HashMap criticalMap;
	List<LinkedList> criticalPaths;
	
    /**
     * Intilize the graph and all helper Classes 
     */
    CriticalPaths(Graph g) {
    	this.g=g;
    	helper=new HelperForCriticalPath();
    	printHelper=new PrintHelper();
    	topOrdering=new TopologicalOrdering();
    	helper.createEdgeForDummyStartandEndVertex(g);
    	criticalMap=new HashMap<Vertex,Boolean>();
    	criticalPaths=new ArrayList<>();
    }
    
    /**
     *  Find Critical Path Method, Finds and Enumerate's all Critical Paths. 
     */
    void findCriticalPaths() {

    	int size=g.size;
    	Vertex start=g.getVertex(size-1);
    	Vertex end=g.getVertex(size);
    	
    	List list=new LinkedList<>();
    	list.add(start);
    	formTopologicalOrder(g);
    	
    	helper.createValuesOfECforVertexes(g,topologicalOrder);                         //find EC
    	helper.computeLCAndSlack(g, reverseTopologicalOrder,criticalMap);               //find LC

    	this.findCriticalPaths(criticalPaths,start,end,list,start);                     //Enumerate Path and store in List<List>-criticalPaths
    	printInOrder(start,end); 
    }
    
    /**
     * Print in the order as mentioned for MP4.
     * @param start : Start Vertex (S)
     * @param end : End Vertex (T)
     */
    public void printInOrder(Vertex start,Vertex end)
    {
    	printHelper.printLengethOfCriticalPath(end);
    	printHelper.printACriticalPath(start, end, criticalPaths);
    	printHelper.printTableOfECandLCandSlack(start, end,g);
    	System.out.println();                                                          //PrintHelper implements all the print functions required
    	printHelper.printNumberOfCriticalNodes(criticalMap);
    	printHelper.printNumberOfCriticalPath(criticalPaths);
    	printHelper.printCriticalPaths(start, end, criticalPaths); 
    }

	public void formTopologicalOrder(Graph g)
	{
		this.reverseTopologicalOrder=topOrdering.findReverseTopologicalOrder(g);
		this.topologicalOrder=new LinkedList<>(this.reverseTopologicalOrder);           //Get reverse topological order from the Helper
		Collections.reverse(this.topologicalOrder);                                     //cache it in class variable for feature reference
	}	 
	
	/**
	 * 
	 * Enuration of critical paths
	 * @param criticalPath : List of Critical paths
	 * @param nodeNow   : Current processing node
	 * @param end  : End node (T)
	 * @param listNow : Current path
	 * @param start  : Start node (S)
	 */
	public void findCriticalPaths(List criticalPath,Vertex nodeNow,Vertex end,List listNow,Vertex start)
	{
		if(nodeNow.name==end.name)
		{
			LinkedList<Vertex> list=new LinkedList<>(listNow);
			criticalPath.add(list);                                // End node is reached, Path is valid, Add to list
		}
		else
		{
				for(Edge e: nodeNow.adj)
				{
					if((nodeNow.name==start.name)||(helper.isEdgeCritical(e,criticalMap)) )
					{
						Vertex v=e.otherEnd(nodeNow);
						listNow.add(v);
						findCriticalPaths(criticalPaths,v,end,listNow,start);          //Edge is critical, then recurse to find its path
						listNow.remove(v);                                             // restore List for upcoming paths
					}
				}
		}
	}
	
	
}
