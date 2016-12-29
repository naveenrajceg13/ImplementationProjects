
/* Ver 1.0: Starter code for Prim's MST algorithm */

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.lang.Comparable;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * @author Naveenraj
 *
 */
public class MST1 {
    static final int Infinity = Integer.MAX_VALUE;

    static int PrimMST(Graph g, Vertex s)
    {
    	int wmst = 0;
        s.seen = true;
        s.d = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>(g.size * g.size, new Edge()); //PQ of Edges
        for(Edge e: s.adj)
        {
        	pq.add(e);
        }
        
        while(!pq.isEmpty())
        {
        	Edge e = pq.remove();
        	if (e.from.seen && e.to.seen)
        	{
        		continue;
        	}
        	Vertex v;
        	if (!e.from.seen)
        	    v=e.from;
        	else
        		v=e.to;
        	
        	v.seen = true;
        	wmst =  wmst + e.weight;
            for(Edge edg: v.adj)
            {
            	Vertex u = edg.otherEnd(v);
            	if(!u.seen)
            	{
            			pq.add(edg);
            	}
            }      	
        }
        
        return wmst;
    }

    public static void main(String[] args) throws FileNotFoundException {
	
    	Scanner in;

        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }
        
       
	    Graph g = Graph.readGraph(in);
        Vertex s = g.getVertex(1);
        Timer timer = new Timer();
        System.out.println(PrimMST(g, s));
        System.out.println(timer.end());
    }
}
