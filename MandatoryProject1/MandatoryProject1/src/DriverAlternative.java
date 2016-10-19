import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

/**
 * Driver class to call required methods 
 * @author Naveenraj
 *
 */
public class DriverAlternative {
    /**
     * Main methos checks eular path and calls method to get and verify the Eular Path
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
	Scanner in;
	EularChecks e=new EularChecks();                //Instance to check the Eular Existance 
	EularPath ep=new EularPath();                   //Instance to find the Eular Path 
	
	File inputFile;
        if (args.length > 0) {
        	inputFile= new File(args[0]);             
         } else {                                    //get the input file to check for eular path
        	inputFile = new File("mp1-big.txt");
        } 
    in=new Scanner(inputFile);
	Graph g = Graph.readGraph(in,false);             // Reads the graph 
	if(e.checkGraphDegree(g)==1)
	{
		if(e.checkGraphConnected(g)==1)
		{                                            // This means there is possibility of Eular Path in Graph 
		   	e.resetVertexSeen(g);	
		    Vertex start=null;
			if(e.oddEdgeVertexs.size()==2)          // pick the vertex with max number ( can pick minimum also )
				start=(e.oddEdgeVertexs.get(0).name>e.oddEdgeVertexs.get(1).name)?e.oddEdgeVertexs.get(0):e.oddEdgeVertexs.get(1);
			else
			    start=g.getVertex(1);               // pick the start vertex 
			
			long time1 = System.nanoTime();
			System.out.println("finding Eular Path");
			CircularLinkedList<Tour> eularPath=ep.findEularPath(start,g);      // Find the return Eular path if there is one
			long time2 = System.nanoTime();
			System.out.println("Time(second) for Finding Eular Path and Stich : " + (time2-time1)/Math.pow(10, 9));
			
			if(eularPath!=null)
			{
				//ep.printList(eularPath);
			    time1 = System.nanoTime();
				boolean validatePath=ep.validatePath(g, eularPath);          // Validating Eular Path
				time2 = System.nanoTime();
				System.out.println("Time(second) for Validating Eular Path: " + (time2-time1)/Math.pow(10, 9));
				if(validatePath)
					System.out.println("Eular Path is valid");
				else
					System.out.println("Eulat Path is invalid");
			}
			else
			{
				System.out.println("No Eular Path for Input Graph");
			}
			
		}
		
	}
	
	
    }
}

