
/* Ver 1.0: Starter code for Prim's MST algorithm */

import java.util.Scanner;
import java.lang.Comparable;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * @author Naveenraj
 *
 */
public class MST {
    static final int Infinity = Integer.MAX_VALUE;

    static int PrimMST(Graph g, Vertex s)
    {
        int wmst = 0;
        final int INF = Integer.MAX_VALUE;
    	int i = 1;
    	
    	Vertex[] vertices = new Vertex[g.size + 1];
		vertices[0] = null;
		
		for (Vertex u : g) {
			u.d = INF;
			u.infinityFlag=true;
			u.p = null;
			u.seen = false;
			if (u != null) {
				vertices[i] = u;
				vertices[i].index=i;
				i++;
			}
		}
		s.d = 0;
		
		IndexedHeap<Vertex> pq = new IndexedHeap<Vertex>(vertices, new Vertex(0));
		
			while (!pq.isEmpty()) {
			Vertex u = pq.remove();	
			u.seen = true;
			wmst+=u.d;
			for (Edge e : u.adj) {
				Vertex v = e.otherEnd(u);
				if (!v.seen && (v.d > e.weight) ) {
					v.d = e.weight;
					v.p = u;
					pq.decreaseKey(v);
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
        int src = in.nextInt();
        int target = in.nextInt();
        Vertex s = g.getVertex(src);
        Vertex t = g.getVertex(target);
        Timer timer = new Timer();
        System.out.println(PrimMST(g, s));
        System.out.println(timer.end());
    }
}
