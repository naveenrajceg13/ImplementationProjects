
import java.lang.invoke.SwitchPoint;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * @author Naveenraj , Niveditha, Archana, Jegathis
 * Class to convert Infix expression to PostFix expression
 */
public class InfixToPostfix<T> {
	
	/**
	 * Procedure to call methods which perform infix to postfix
	 * @param args : String : Infix expression string 
	 */
	public static void main(String args[])
	{
		//intilize object to call non static methods
		InfixToPostfix<String> infixToPostFix=new InfixToPostfix<>();
		
		String infixExpression=null;
		
		if(args.length>0)
			infixExpression=args[0];
		else
			infixExpression="3+4*2/(1-5)^2^3";
		
		System.out.println(infixToPostFix.infixTOPostFixConversion(infixExpression));
		
	}

	public <T extends Comparable<? super T>> String infixTOPostFixConversion(String infixString)
	{
		StringBuffer postfixString=new StringBuffer();    // String buffer acts as output                                 
	    Deque<String> operatorStack=new ArrayDeque<>();   // to store operators 
	    
		for(char x: infixString.toCharArray())            // for each char in String 
		{
		   int variableType=checkVaraible((int)x);
		   if(variableType==0)
		   {
 			   postfixString.append(x);                    // if operand just add to output
		   }
		   if(variableType==1)
		   {
			   int variablePrecedence=checkOperendPrecedence((int)x);   //operator
			   while(operatorStack.size()>0 && (variablePrecedence>=(checkOperendPrecedence((int)operatorStack.element().charAt(0)))))
			   {
				 if((checkOperendPrecedence((int)operatorStack.element().charAt(0)))==1 && variablePrecedence==1)
					 break; 
				 postfixString.append(operatorStack.pop()); // pop out and add to output if current variable has highest precedence
			   }
			   
			   operatorStack.push(String.valueOf(x));          // push current operator to stack 
		   }
		   if(variableType==2)
		   {
			   int variablePrecedence=checkOperendPrecedence((int)x);    //pranthesis 
			   
			   if(variablePrecedence==10)
			        operatorStack.push(String.valueOf(x));               // (
			   
			   else if(variablePrecedence==20) 
			   { 		                                                 //  )
				   while(!(checkOperendPrecedence((int)operatorStack.element().charAt(0))==10))
				   {
					   postfixString.append(operatorStack.pop());       // pop out till find (
				   }
				   operatorStack.pop();                                 // pop out (
			   }
		   }
		}
		
		while(operatorStack.size()>0)
		{
			postfixString.append(operatorStack.pop());
		}
		
		return postfixString.toString();
	}
	
	/**
	 * Procedure to check variable type 
	 * @param x : int : ASCII value of char to check type
	 * @return : int : 0 if operand, 1 is operator & 2 if pranthesis 
	 */
	
	public int checkVaraible(int x)
	{
		if((x >= 65 && x <= 90)||(x >= 97 && x <= 122)||(x >= 48 && x <= 57))
		{
			   return 0;    //returns 0 if variables 
		}
		else if((x >= 42 && x <= 43)|| (x == 45) || (x == 47) || (x == 94) || (x == 37) || (x == 38) || (x==33))
		{
			   return 1;   //returns 1 if operator 
		}
		else if((x >= 40 && x <= 41))
		{
			   return 2;   //returns 2 if pranthesis 
		}
		
		return -1;        // means not a valid one
	}
	
	/**
	 * Procedure to check precedence
	 * @param x : int : variable to check Precedence 
	 * @return : int : Variable precedence
	 */
	public int checkOperendPrecedence(int x)
	{
		switch(x)
		{
			case 33: return 0;  // !
			case 94: return 1;  // ^
			case 47: return 2;  // /
			case 42: return 2;  // *
			case 43: return 3;  // +
			case 45: return 3;  // -
			case 40: return 10; // (
			case 41: return 20; // )
		}
		
		return -5;  //none
	}
}
