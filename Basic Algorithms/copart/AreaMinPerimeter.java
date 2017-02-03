/*
 * Naveenraj Palanisamy
 * Chandni Shankar
 * Apurva Alekar
 */

package copart;

import java.util.Scanner;

public class AreaMinPerimeter {
	
	public static void main(String args[])
	{
		AreaMinPerimeter areaMin=new AreaMinPerimeter();
		int value;
		int total;
		Scanner s=new Scanner(System.in);		
		total=s.nextInt();
		for(int i=0;i<total;i++)
		{
		value=s.nextInt();
		System.out.println((int)Math.ceil(areaMin.minimumPerimeter(value)));  // call to helper function 
		}
	}

	/*
	 * xy=area
	 * x=area/y
	 * 2x+2y=P
	 * X^2=area
	 * so.. 2*sqrt(Area)+2*area/sqrt(area)
	 */
	public double minimumPerimeter(int value)
	{
		double part1=Math.sqrt(value);   //x
		double part2=((2*value)/part1);  //2y
		
		return (2*part1+part2);   //2x+2y
	}
}
