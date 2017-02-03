import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.List;
import java.util.Comparator;



interface Index {
	
	public void putIndex(int index);
	public int getIndex();

}

interface PQ<T> {
	
	public void insert(T x);
	public T deleteMin();
	public T min();
	public void add(T x);
	public T remove();
	public T peek();

}

class BinaryHeap<T> implements PQ<T> {
	
	T[] pq;
	Comparator<T> c;
	private int size;
	
	// Build a priority queue with a given array
	BinaryHeap(T[] q, Comparator<T> comp) {
		pq = q;
		size = q.length - 1;
		c = comp;
		buildHeap();
	}
	
	// create an empty priority queue of given maximum size
	public BinaryHeap(int n, Comparator<T> comp) {
		c = comp;
		pq = (T[]) new Object[n + 1];
		size = 0;
	}
	
	// Insert new element into the heap
	public void insert(T x) {
		add(x);
	}
	
	//delete the root element in the heap
	public T deleteMin() {
		if(size == 0) return null;
		
		return remove();
	}
	
	// Return the root element in the heap which would be the maximum or minimum 
	// element depending upon the comparator
	
	public T min() {
		return peek();
	}
	
	// add an element into the heap
	
	public void add(T x) {
		
		// resize the size of the heap if it has reached its limit
		if(size == pq.length - 1) {
			T[] temp = pq;
			pq = (T[]) new Object[temp.length * 2 + 1];
			for(int i = 0; i < temp.length; i++) {
				pq[i] = temp[i];
			}
		}
		
		pq[++size] = x;
		percolateUp(size);
	}
	
	void percolateUp(int idx) {
		int hole = idx;
		T elt = pq[idx];
		
		pq[0] = elt;
		while(c.compare(pq[0], pq[hole / 2]) < 0) {
			assignIndex(hole, hole / 2);
			hole /= 2;
		}
		
		assignIndex(hole, 0);
	}
	
	// Remove the minimum or maximum element based on the comparator type
	public T remove() {
		T val = peek();
		
		pq[1] = pq[size--];
		percolateDown(1);
		
		return val;
	}
	
	// Remove the minimum or maximum element based on the comparator type
	public T peek() {
		if(size == 0) {
			System.out.println("Heap has no elements");
			return null;
		}
		
		return pq[1];
	}
	
	void percolateDown(int idx) {
		pq[0] = pq[idx];
		
		while(2 * idx <= size) {
			int child = 2 * idx;
			
			// get the minimum of the left child and right child
			if(child < size && c.compare(pq[child], pq[child + 1]) > 0) child++;
			
			if(c.compare(pq[child], pq[0]) < 0) {
				assignIndex(idx, child);
			} else {
				break;
			}
			
			idx = child;
		}
		
		assignIndex(idx, 0);
	}
	
	// build a heap
	void buildHeap() {
		for(int i = size / 2; i > 0; i--) {
			percolateDown(i);
		}
	}
	
	//check if the heap is empty or not
	public boolean isEmpty() {
		return size == 0;
	}
	
	// assign the index
	public void assignIndex(int srcIdx, int destIdx) {
		pq[srcIdx] = pq[destIdx];
	}
	
	/* sort array A[1..n].  A[0] is not used. 
    Sorted order depends on comparator used to buid heap.
    min heap ==> descending order
    max heap ==> ascending order
	 */
	public static<T> void heapSort(T[] A, Comparator<T> comp) {
		BinaryHeap<T> heap = new BinaryHeap<>(A, comp);
		
		for(int i = heap.size; i >= 0; i--) {
			T temp = A[1];
			A[1] = A[i];
			A[i] = temp;
			
			heap.size--;
			heap.percolateDown(1);
		}
		
		for(T elt : heap.pq) {
			System.out.println();
		}
	}


}

class IndexedHeap<T extends Index> extends BinaryHeap<T> {
	
	//build a priority queue with a given array
	IndexedHeap(T[] q, Comparator<T> comp) {
		super(q, comp);
	}
	
	// restore heap order property after the property of x has been decreased
	void decreaseKey(T x) {
		percolateUp(x.getIndex());
	}
	
	//override
	public void assignIndex(int srcIdx, int destIdx) {
		super.assignIndex(srcIdx, destIdx);
		pq[srcIdx].putIndex(srcIdx);
	}

}

class Edge {
	public Vertex From; // head vertex
	public Vertex To; // tail vertex
	public int Weight;// weight of the arc
	public boolean isActive; // for shortest paths lvl2

	/**
	 * Constructor for Edge
	 * 
	 * @param u
	 *            : Vertex - The head of the arc
	 * @param v
	 *            : Vertex - The tail of the arc
	 * @param w
	 *            : int - The weight associated with the arc
	 */
	Edge(Vertex u, Vertex v, int w) {
		From = u;
		To = v;
		Weight = w;
		isActive = false;
	}

	/**
	 * Method to find the other end end of the arc given a vertex reference
	 * 
	 * @param u
	 *            : Vertex
	 * @return
	 */
	public Vertex otherEnd(Vertex u) {
		// if the vertex u is the head of the arc, then return the tail else
		// return the head
		if (From == u) {
			return To;
		} else {
			return From;
		}
	}

	/**
	 * Method to represent the edge in the form (x,y) where x is the head of the
	 * arc and y is the tail of the arc
	 */
	public String toString() {
		return  From + " " + To + " " + Weight;
	}
}

class Vertex implements Index, Comparator<Vertex> {
	
	public int name; // name of the vertex
	public boolean seen; //flag to check if the vertex has been visited or not
	public Vertex parent; //parent of the vertex
	public int distance; //distance to the vertex from source vertex
	public List<Edge> Adj, revAdj; // adjacency list; using linkedlist or arraylist
	public int indegree; //count of incoming edges
	public int index; // index information for indexed heap
	public int count; // count for bellman ford faster implementation
	public int top; // top for topological order
	public int numPaths; // count of shortest paths
	public boolean insideStack; // for cycle detection
	
	Vertex(int n) {
		name = n;
		seen = false;
		parent = null;
		Adj = new ArrayList<Edge>();
		revAdj = new ArrayList<Edge>(); // only for directed graphs
		numPaths = 0;
	}
	
	// set the index value
	public void putIndex(int idx) {
		index = idx;
	}
	
	//get the index value
	public int getIndex() {
		return index;
	}
	
	// compare function 
	public int compare(Vertex a, Vertex b) {
		return a.distance - b.distance;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + "";
	}
	

}

class Graph implements Iterable<Vertex> {
	public List<Vertex> verts; // array of vertices
	public int numNodes; // number of verices in the graph

	/**
	 * Constructor for Graph
	 * 
	 * @param size
	 *            : int - number of vertices
	 */
	Graph(int size) {
		numNodes = size;
		verts = new ArrayList<>(size + 1);
		verts.add(0, null);
		// create an array of Vertex objects
		for (int i = 1; i <= size; i++)
			verts.add(i, new Vertex(i));
	}

	/**
	 * Method to add an edge to the graph
	 * 
	 * @param a
	 *            : int - one end of edge
	 * @param b
	 *            : int - other end of edge
	 * @param weight
	 *            : int - the weight of the edge
	 */
	void addEdge(int a, int b, int weight) {
		Vertex u = verts.get(a);
		Vertex v = verts.get(b);
		Edge e = new Edge(u, v, weight);
		u.Adj.add(e);
		v.Adj.add(e);
	}

	/**
	 * Method to add an arc (directed edge) to the graph
	 * 
	 * @param a
	 *            : int - the head of the arc
	 * @param b
	 *            : int - the tail of the arc
	 * @param weight
	 *            : int - the weight of the arc
	 */
	void addDirectedEdge(int a, int b, int weight) {
		Vertex head = verts.get(a);
		Vertex tail = verts.get(b);
		Edge e = new Edge(head, tail, weight);
		head.Adj.add(e);
		tail.revAdj.add(e);
	}

	/**
	 * Method to create an instance of VertexIterator
	 */
	public Iterator<Vertex> iterator() {
		return new VertexIterator();
	}

	/**
	 * A Custom Iterator Class for iterating through the vertices in a graph
	 * 
	 *
	 * @param <Vertex>
	 */
	private class VertexIterator implements Iterator<Vertex> {
		private Iterator<Vertex> it;

		/**
		 * Constructor for VertexIterator
		 * 
		 * @param v
		 *            : Array of vertices
		 * @param n
		 *            : int - Size of the graph
		 */
		private VertexIterator() {
			it = verts.iterator();
			it.next(); // Index 0 is not used. Skip it.
		}

		/**
		 * Method to check if there is any vertex left in the iteration
		 * Overrides the default hasNext() method of Iterator Class
		 */
		public boolean hasNext() {
			return it.hasNext();
		}

		/**
		 * Method to return the next Vertex object in the iteration Overrides
		 * the default next() method of Iterator Class
		 */
		public Vertex next() {
			return it.next();
		}

		/**
		 * Throws an error if a vertex is attempted to be removed
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public static Graph readGraph(Scanner in, boolean directed) {
		// read the graph related parameters
		int n = in.nextInt(); // number of vertices in the graph
		int m = in.nextInt(); // number of edges in the graph

		// create a graph instance
		Graph g = new Graph(n);
		for (int i = 0; i < m; i++) {
			int u = in.nextInt();
			int v = in.nextInt();
			int w = in.nextInt();
			if (directed) {
				g.addDirectedEdge(u, v, w);
			} else {
				g.addEdge(u, v, w);
			}
		}
		in.close();
		return g;
	}
}

public class NearestDistance {
	
	public static void main(String args[])
	{
		Scanner s=new Scanner(System.in);
		int n=s.nextInt();
		int m=s.nextInt();
		int q=s.nextInt();
		HashMap<Integer, ArrayList<Integer>> hm=new HashMap<>();
		for(int i=1;i<=m;i++)
		{
			int numPlaces=s.nextInt();
			ArrayList<Integer> list=new ArrayList<>();
			for(int j=0;j<numPlaces;j++)
			{
				int place=s.nextInt();
				list.add(place);
			}
			hm.put(i, list);
		}
		LinkedList<ArrayList<Integer>> order=new LinkedList<>();
		int count=1;
		boolean flag=true;
		for(int i=0;i<q;i++)
		{
			int food=s.nextInt();
			int place=s.nextInt();
			count=count+hm.get(food).size();
			count++;
			ArrayList<Integer> tempList=new ArrayList<>();
			tempList.add(food);
			tempList.add(place);
			order.add(tempList);
		}
		Graph g=new Graph(count);
		int current=1;
		int vertexCount=1;
		for(ArrayList<Integer> lis: order)
		{
			int food=lis.get(0);
			int place=lis.get(1);
			ArrayList<Integer> listT=hm.get(food);
			int count1=1;
			int finalsize=listT.size();
			for(int node: listT)
			{
				g.addDirectedEdge(vertexCount, vertexCount+count1, distanceBetweenNodes(current, node));
				g.addDirectedEdge(vertexCount+count1,vertexCount+finalsize+1, distanceBetweenNodes(node, place));
				count1++;
			}
			current=place;
			vertexCount=vertexCount+finalsize+1;
		}
	   Vertex starting=g.verts.get(1);
	   boolean check=Dijkstra(g, starting);
	   Vertex ending=g.verts.get(vertexCount);
	   System.out.println(ending.distance);
		
	}
	
	
	public static boolean Dijkstra(Graph g, Vertex s) {

		final int INF = Integer.MAX_VALUE;
		// Initialize
		for (Vertex u : g) {
			u.distance = INF;
			u.parent = null;
			u.count = 0;
			u.seen = false;
		}

		s.distance = 0;

		Vertex[] vertices_arr = new Vertex[g.numNodes + 1];
		vertices_arr[0] = null; // to avoid edge case

		int idx = 1;
		for (Vertex v : g.verts) {
			if (v != null) {
				vertices_arr[idx++] = v;
			}
		}
       // System.out.println(g.numNodes);
		for (int j = 1; j < vertices_arr.length; j++) {
			vertices_arr[j].index = j;
		}

		// Build a priority queue of vertices using vertex.distance as priority
		IndexedHeap<Vertex> pq = new IndexedHeap<Vertex>(vertices_arr, new Vertex(0));

		while (!pq.isEmpty()) {
			Vertex u = pq.remove();
			//System.out.println("Vertex "+u.name);
			u.seen = true;

			// Relax edges out of u
			for (Edge e : u.Adj) {
				Vertex v = e.otherEnd(u);
               
				if (e.Weight < 0)
					return false; // Negative weight;

				if (u.distance!=INF && !v.seen && (v.distance > (u.distance + e.Weight)) ) {
					v.distance = u.distance + e.Weight;
					v.parent = u;
					pq.decreaseKey(v); 
				}
			}
		}
		return true;
	}
	
	public static int distanceBetweenNodes(int a1,int a2)
	{
		if(a1==a2)
			return 0;
		if(a2<a1)
		{
			int t=a2;
			a2=a1;
			a1=t;
		}
		int dist1=0;
		int dist2=0;
		HashMap<Integer, Integer> hm=new HashMap<>();
		hm.put(a1,0);
		int n1=a1;
		int n2=a2;
		while(a1>1)
		{
			a1=a1/2;
			dist1++;
			hm.put(a1, dist1);
		}
		while(a2>1)
		{
			a2=a2/2;
			dist2++;
			if(hm.containsKey(a2))
			{
				dist1=hm.get(a2);
				break;
			}
		}
		return dist1+dist2;
	}

}
