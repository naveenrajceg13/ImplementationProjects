import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopologicalOrdering {
	
	public List findReverseTopologicalOrder(Graph g)
    {
       List<Vertex> list=new ArrayList<>();
    	
    	for(Vertex v: g)
    	{
    		v.seen=false;
    	}
    	
    	for(Vertex v: g)
    	{
    		if(!v.seen)
    		{
    			DFSVisit(v,list);
    		}
    	}
    	
    	return list;
    }
    
    
    public void DFSVisit(Vertex v,List list)
    {
    	v.seen=true;
    	for(Edge e: v.adj)
    	{
    		Vertex u = e.otherEnd(v);
    		if(!u.seen)
    		{
    			DFSVisit(u,list);
    		}
    	}
    	list.add(v);
    }
}
