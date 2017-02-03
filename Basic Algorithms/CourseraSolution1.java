
public class CourseraSolution1 {
	
       public  long CountX(String[] steps)
       {
    	   int n=steps.length;
    	   
    	   int rowmin=0;
    	   int columnMin=0;
    	   boolean flag=true;
    	   for(int i=0;i<n;i++)
    	   {
    		   String temp=steps[i];
    		   String[] splits=temp.split(" ");
    		   int row=Integer.parseInt(splits[0]);
    		   int column=Integer.parseInt(splits[1]);
    		   
    		  if(flag)
    		  {
    			  rowmin=row;
    			  columnMin=column;
    		  }
    		  else
    		  {
    			  if(row<rowmin)
    			  {
    				  rowmin=row;
    			  }
    			  if(column<columnMin)
    			  {
    				  columnMin=column;
    			  }
    		  }
    	   }
    	   return (long)rowmin*columnMin;
       }

}
