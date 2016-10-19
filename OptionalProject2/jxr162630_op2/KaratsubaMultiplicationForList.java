import java.util.ArrayList;
import java.util.List;

/**
 * @author Naveenraj , Niveditha , Archana , Jegathis
 * Class to multiply two list using karatsubaMultiplication
 */

public class KaratsubaMultiplicationForList {
	
	public AddtionSubractionLists<Integer> addSub=new AddtionSubractionLists<>();
	
	/**
	 * @param list1 : List<Integer> : list to be padded with zero
	 * @param size : int : number of zero to the added to the list
	 */
	public <T extends Comparable<? super T>> void padWithZero(List<Integer> list1,int size)
	{
		for(int i=0;i<size;i++)
		{
			list1.add(0);            // we just need to add trailing zero to make list in same size
		}
	}
	/**
	 * Procedure to add zero ( like multiplying with 10^size) 
	 * @param list1 : list to be added multiplied with 10^size
	 * @param size : int : power to 10 to be multiplied with 
	 * @return : List<Integer> : new multiplied list
	 */
	public <T extends Comparable<? super T>> List<Integer> padWithZeroAfter(List<Integer> list1,int size)
	{
		List<Integer> result=new ArrayList<>();
		for(int i=0;i<size;i++)
		{
			result.add(0);    // add number of zero and then add number so it will became like multiplying
		}
		for(int x: list1)
		{
			result.add(x);
		}
		return result;
	}
	
	/**
	 * Procedure to multiply 1*1 lists 
	 * @param list1 : List<Integer> : first list
	 * @param list2 : List<Integer> : second list
	 * @param base : int : base of numbers in list
	 * @return : List<Integer> : new multiplied list 
	 */
	public <T extends Comparable<? super T>> List<Integer> multiplyOneDigit(List<Integer> list1,List<Integer> list2,int base)
	{
		List<Integer> result=new ArrayList<>();
		int result1=(int)list1.get(0)*(int)list2.get(0);    //since both list has only one digit multiply both 0th index
		while(result1>=base)
		{
			result.add((int)result1%base);       // if multiplied value if bigger than base, split and put accordingly 
			result1=result1/base;
		}
		result.add(result1);
		return result;
	}

	/**
	 * Driver function to call add method of AdditionSubractionList class
	 * @param list1 : List<Integer> : List 1
	 * @param list2 : List<Integer> : List 2
	 * @param base  : int : Base 
	 * @return : List<Integer> : Added list
	 */
	public <T extends Comparable<? super T>> List<Integer> addTwoList(List<Integer> list1,List<Integer> list2,int base)
	{
		List<Integer> result=new ArrayList<>();
		addSub.add(list1, list2, result, base);  //calls method add of AdditionSubractionList      
		return result;
	}

	/**
	 * Driver function to call sub method of AdditionSubractionList class
	 * @param list1 : List<Integer> : List 1
	 * @param list2 : List<Integer> : List 2
	 * @param base  : int : Base 
	 * @return : List<Integer> : Subracted list
	 */
	
	public <T extends Comparable<? super T>> List<Integer> subTwoList(List<Integer> list1,List<Integer> list2,int base)
	{
		List<Integer> result=new ArrayList<>();
		addSub.sub(list1, list2, result, base);   // calls sub method of AdditionSubractionList
		return result;
	}
	
	/**
	 * @param list1 : List<Integer>
	 * @return : int :  size of the list
	 */
	public <T extends Comparable<? super T>> int size(List<Integer> list1)
	{
		return list1.size();
	}
	
	public <T extends Comparable<? super T>> int max(int value1,int value2)
	{
		return value1>value2?value1:value2;   //returns max of two values 
 	}
	
	/**
	 * Procedure to do KaraSubaMultiplication 
	 * @param list1 : List<Integer> : List 1 to multiply 
	 * @param list2 : List<Integer> : List 2 to multiply 
	 * @param base  : int : base of the values 
	 * @return  : List<Integer> : Multiplied List
	 */
	public <T extends Comparable<? super T>> List<Integer> karaSubaMultiply(List<Integer> list1,List<Integer> list2,int base)
	{
		
		/**
		 *   works based on the following algorithm 
		 *   
		 *   example : 
		 *   
		 *   num 1 = 7898
		 *   num 2 = 8792
		 *   
		 *   aPart=78
		 *   bPart=98
		 *   cPart=87
		 *   dPart=92
		 *   
		 *   EquationOne = aPart*cPart - ( 78 * 87 )
		 *   EquationTwo = bPart*dPart - ( 98 * 92 )
		 *   EquationThree = (aPart+bPart) * ( cPart+dPart) - (78+98) * (87*92) 
		 *   EquationFour= EquationThree- EquationOne - EquationTwo;  
		 * 
		 *   Result = EquationOne * 10^4(max size of two list) + EquationTwo + EquationFour * 10 ^ 2 ( half the max size of two list ) 
		 */
		
		int list1_size=size(list1);
		int list2_size=size(list2);
		
		if(list1_size<list2_size)
			padWithZero(list1,list2_size-list1_size);          // two list has to be in equal size
		if(list2_size<list1_size)
			padWithZero(list2,list1_size-list2_size);          // two list has to be in equal size
		
		int max_value=max(list1_size,list2_size);              //max size of two list
		
		int split_value=max_value/2;
		
		if(max_value==0)
		{
			List<Integer> result=new ArrayList<>();
			result.add(0);                                     //base case 
			return result;
		}
		if(max_value==1)
		{
			return multiplyOneDigit(list1, list2, base);      //base case
		}
		List<Integer> bPart;
		List<Integer> aPart;
		List<Integer> cPart;
		List<Integer> dPart;
		if(max_value%2==0)
		{
		   bPart=split(list1,0,split_value);
		   aPart=split(list1,split_value,max_value);
		   dPart=split(list2,0,split_value);
		   cPart=split(list2,split_value,max_value);
		}
		else                                                  // for odd numbers consisder the extra number
		{
			bPart=split(list1,0,split_value+1);
			aPart=split(list1,split_value+1,list1_size);
			dPart=split(list2,0,split_value+1);
			cPart=split(list2,split_value+1,list1_size);	
		}
		List<Integer> equationOne=trim(karaSubaMultiply(aPart, cPart, base));
		List<Integer> equationTwo=trim(karaSubaMultiply(bPart, dPart, base));
		List<Integer> equationThree=trim(karaSubaMultiply(addTwoList(aPart, bPart, base),addTwoList(cPart, dPart, base),base));
		List<Integer> equationFour=trim(subTwoList(equationThree, equationOne, base));
				      equationFour=trim(subTwoList(equationFour, equationTwo, base));
				      
		if(max_value%2!=0){
			max_value=max_value+1;                          // for odd numbers consisder the extra number 
			split_value+=1;
		}
		equationOne=trim(padWithZeroAfter(equationOne,max_value));
		equationFour=trim(padWithZeroAfter(equationFour, split_value));
		return trim(addTwoList(equationFour,addTwoList(equationOne,equationTwo,base),base));
	}
	
	/**
	 * Procedure to spilt the list 
	 * @param list : List<Integer> 
	 * @param start : int : Start of the list 
	 * @param end : int : End of the list
	 * @return : Splilted list
	 */
	public <T extends Comparable<?super T>> List<Integer> split(List<Integer> list,int start,int end)
	{
		List<Integer> result=new ArrayList<>();
		for(int i=start;i<end;i++)
		{
			result.add(list.get(i));
		}
		
		return result;
	}
	
	public <T extends Comparable<? super T>> void print(List<Integer> list)
	{
		 for(int x: list)
		   {
			   System.out.print(x+" ");  //just print
		   }
	}
	
	/**
	 * Procedure to remove the unwated zero at the end
	 * @param l1
	 * @return
	 */
	public <T extends Comparable<? super T>> List<Integer> trim(List<Integer> l1)
	{
		for(int i=(l1.size()-1);i>0;i--)
		{
			if(l1.get(i)!=0)
				break;                           //remove element till you find one zero first element 
			l1.remove(i);
		}
		return l1;
	}
	
	public static void main(String args[])
	{ 
	   KaratsubaMultiplicationForList multiplyNumber=new KaratsubaMultiplicationForList();
	   List<Integer> list1=new ArrayList<>();
	   List<Integer> list2=new ArrayList<>();
	   //345 
	   list1.add(6);list1.add(1);//list1.add(1);
	   //789
	   list2.add(2);//list2.add(9);list2.add(1);
	   
	   List<Integer> result=multiplyNumber.karaSubaMultiply(list1, list2, 2);
	   multiplyNumber.print(result);
	}
	
}