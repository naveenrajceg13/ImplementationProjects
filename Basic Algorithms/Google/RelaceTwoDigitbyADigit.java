package Google;

import java.util.ArrayList;

public class RelaceTwoDigitbyADigit {
	
	public static void main(String args[])
	{
		RelaceTwoDigitbyADigit replace=new RelaceTwoDigitbyADigit();
		int num=623315;
		System.out.println(replace.maximumNumber(num));
	}

	public int maximumNumber(int num)
	{
		ArrayList<Integer> arList=new ArrayList<>();
		while(num>0)
		{
			arList.add(num%10);
			num/=10;
		}
		int res=getMaximumNumberIndex(arList,arList.size(), 0);
		if(res!=-1)
		{
			if(res==-2)
				return num;
			int value=(arList.get(res)+arList.get(res-1)+1)/2;
			int total=0;
			for(int i=arList.size()-1;i>=0;i--)
			{
				if(i==(res))
				{
					continue;
				}
				else if(i==(res-1))
					total+=value;
				else
				{
					total+=arList.get(i);
				}
				if(i!=0)
				   total=total*10;
			}
	        return total;
		}
		else
		     return 0;
	}

	public int getMaximumNumberIndex(ArrayList<Integer> list,int max,int min)
	{
		if(list.size()<1)
			return -1;
		if(list.size()==1)
			return -2;
		int num1=list.get(list.size()-1);
		int num2;
		for(int i=list.size()-2;i>=0;i--)
		{
			num2=list.get(i);
			int value=(num1+num2+1)/2;
			if(value>num1)
				return i+1;
			num1=num2;
		}
		
		return 1;
	}
}
