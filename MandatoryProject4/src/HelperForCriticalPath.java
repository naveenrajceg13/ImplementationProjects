/** CriticalPath Helper Class
 * author : Naveenraj 
  
 */

import java.security.cert.Certificate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class HelperForCriticalPath {
	
    /**
     * For Graph G, Create an edge for start and end vertex
     * @param g
     */
    void createEdgeForDummyStartandEndVertex(Graph g)
    {
    	int size=g.size;
    	
    	Vertex s=g.getVertex(size-1);
    	Vertex t=g.getVertex(size);
    	
    	for(Vertex v : g)
    	{
    		if(v.revAdj.size()==0 && (v.name!=s.name) && (v.name!=t.name))                // all node has indegree 0, edge with start
    		    g.addEdge(s, v, 1);
    	}
    	
    	for(Vertex v : g)
    	{
    		if(v.adj.size()==0 && (v.name!=s.name) && (v.name!=t.name)){
    		    g.addEdge(v, t, 1);                                                       // all node has outDegree 0, edge with end
    		}
    	}
    }
    
    /**
     * Create EC
     * @param g
     * @param topologicalOrder
     */
    void createValuesOfECforVertexes(Graph g,List topologicalOrder)
    {
    	for(Vertex v: g)
    	{
    		v.EC=v.d;
    	}
    	for(Object o: topologicalOrder)
    	{
               for(Edge e: ((Vertex)o).adj)
               {
            	   Vertex v=e.otherEnd(((Vertex)o));
            	   
            	   if(v.EC < (((Vertex)o).EC+v.d))                       //Max time allowed for a job to complete. 
            	   {
            		   v.EC= ((Vertex)o).EC+v.d;
            	   }
               }
    	}
    }
    
    
    
    /**
     * Create LC
     * @param g
     * @param reverseTopologicalOrder
     * @param criticalMap
     */
    public void computeLCAndSlack(Graph g,List reverseTopologicalOrder,Map criticalMap)
    {
    	Vertex t=g.getVertex(g.size);
    	t.LC = t.EC;
    	
    	for(Vertex v: g)
    	{
    		v.LC = t.LC;
    	}
    	for(Object u: reverseTopologicalOrder)
    	{
    		Vertex uVertex=((Vertex)u);
    		uVertex.slack = uVertex.LC - uVertex.EC;
    		checkVertexCritical(uVertex,criticalMap);
    		for(Edge e: uVertex.revAdj)
    		{
    			Vertex v = e.otherEnd(uVertex);
    			if (v.LC > (uVertex.LC-uVertex.d))
    			{
    				v.LC = uVertex.LC - uVertex.d;                            //Latest Time job is completed. 
    			}
    		}
    	}
    	
    }
    
    public void checkVertexCritical(Vertex v,Map m)
    {
    	if(v.slack==0)
    	{
    		m.put(v, true);                 // If vertex is found critical, add to critical List
    	}
    }
    
    public boolean isEdgeCritical(Edge e,Map criticalMap)
    {
    	Vertex u=e.from;
		Vertex v=e.to;
		if(criticalMap.containsKey(v) && criticalMap.containsKey(u))
		{
			if((u.LC+v.d) == v.LC)
			{
				return true;                                              // Check for Edge to be critical
			}
		}
		
		return false;
    }
    
    public boolean checkCriticalNode(Vertex v)
    {
    	return v.slack==0;                         // Node with slack 0 is critical 
    }
}
