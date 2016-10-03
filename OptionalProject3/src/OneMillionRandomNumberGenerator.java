import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author Naveenraj, Niveditha, Jagathis
 * Class to Generate random numbers. 
 */

public class OneMillionRandomNumberGenerator {
	
	/**
	 * @param args : String : File name to store random numbers. 
	 */
	public static void main(String args[])
	{
		File f;
		FileWriter in;
		PrintWriter pw;
		if(args.length>0)
	    f=new File(args[0]); //file name provided in console 
		else
		f=new File("inputFile.txt"); // use own file name 
		
		try{
			in=new FileWriter(f);
			pw=new PrintWriter(in);
			
			for(int i=0;i<Math.pow(2, 10);i++)
			{
				in.write((new Double(Math.random())).toString()); // get an random double and store in file as string. 
				in.write("\t");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
