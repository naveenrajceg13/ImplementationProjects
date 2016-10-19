
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

/**
 * Driver class to call required methods 
 * @author Naveenraj
 *
 */
public class Driver {
    /**
     * Main methos checks eular path and calls method to get and verify the Eular Path
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
    Timer timer=new Timer();
	Scanner in;
	EularChecks e=new EularChecks();                //Instance to check the Eular Existance 
	EularPathWithSubTours ep=new EularPathWithSubTours();                   //Instance to find the Eular Path 
	boolean toPrint=false;
	File inputFile;
        if (args.length > 0) {
        	inputFile= new File(args[0]);             
         } else {                                    //get the input file to check for eular path
        	inputFile = new File("mp1-big.txt");
        } 
    if(args.length>1)
    {
    	if(args[1].equals("YES"))
    		toPrint=true;
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
			timer.start();
			System.out.println("finding Eular Path");
			//CircularLinkedList<Tour> eularPath=ep.findEularPath(start,g);      // Find the return Eular path if there is one
			List<CircularLinkedList<Tour>> eularSubTours=ep.findEularPath(start,g);
			long time2 = System.nanoTime();
			timer.end();
			System.out.println("for Finding Sub Tours: "+timer.toString());
			//System.out.println("Time(second) for Finding Sub Tours: " + (time2-time1)/Math.pow(10, 9));
			
			if(eularSubTours!=null)
			{
				
				time1 = System.nanoTime();
				timer.start();
				CircularLinkedList<Tour> eularPath=ep.switchTours((ArrayList<CircularLinkedList<Tour>>)eularSubTours);          // Validating Eular Path
				time2 = System.nanoTime();
				timer.end();
				System.out.println("for Stiching Sub Tours: "+timer.toString());
				//System.out.println("Time(second) for Stiching Sub Tours: " + (time2-time1)/Math.pow(10, 9));
				
			    //time1 = System.nanoTime();
			    timer.start();
				boolean validatePath=ep.validatePath(g, eularPath);          // Validating Eular Path
				//time2 = System.nanoTime();
				timer.end();
				System.out.println("for Validating Eular Path: " +timer.toString());
				//System.out.println("Time(second) for Validating Eular Path: " + (time2-time1)/Math.pow(10, 9));
				
				if(validatePath)
					System.out.println("Eular Path is valid");
				else
					System.out.println("Eulat Path is invalid");
				 
				 if(toPrint)
				 {
				 timer.start();
				 ep.printList(eularPath);
				 timer.end();
				 System.out.println("for Printing the List: " +timer.toString());
				 }
			}
			else
			{
				System.out.println("No Eular Path for Input Graph");
			}
			
		}
		
	}
	
	
    }
}


