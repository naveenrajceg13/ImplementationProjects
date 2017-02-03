import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FactorsOnlyThree {

	  public static void main(String args[])
	  {
		  List<Integer> list=new ArrayList<>();
		  FactorsOnlyThree fact=new FactorsOnlyThree();
		  System.out.println(fact.getSum3Fact());
	  }
	  
	  public int getSum3Fact()
	  {
		  Scanner s=new Scanner(System.in);
		  int total=s.nextInt();
		  int count=0;
		  int sum=0;
		  while(count<total)
		  {
			  int numNow=s.nextInt();
			  if(getNumberOfFact(numNow)==3)
				    sum+=numNow;
			  count++;
		  }
		  
		  return sum;
	  }
	  public int getNumberOfFact(int num)
	  {
		  int total=0;
		  
		  for(int i=1;i<num;i++)
		  {
			   if(num%i==0)
			   {
				   if(total>=3) return -1;
				   total++;
			   }
		  }
		  return total;
	  }
}
