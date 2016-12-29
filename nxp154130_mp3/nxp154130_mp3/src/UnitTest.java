import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

public class UnitTest {
	
	
	public static void main(String args[]) throws FileNotFoundException
	{
		Scanner in;
        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(new File("mp2-ta\t1.txt"));
        } else {
            in = new Scanner(new File("mp3/mp3/g1.txt"));
        }
	    Graph g = Graph.readGraph(in);
        Vertex s = g.getVertex(1);
        Vertex[] vertices_arr = new Vertex[g.size + 1];
		vertices_arr[0] = null; // to avoid edge case
        System.out.println();
		int idx = 1;
		for (Vertex v : g.v) {
			if (v != null) {
				vertices_arr[idx++] = v;
			}
		}
		System.out.println();
        for (int j = 1; j < vertices_arr.length; j++) {
			vertices_arr[j].index = j;
		}
        
        IndexedHeap<Vertex> pq = new IndexedHeap<Vertex>(vertices_arr, new Vertex(0));
        //pq.heapSort(vertices_arr, new Vertex(0));
    }
	

	
}
