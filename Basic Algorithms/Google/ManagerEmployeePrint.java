package Google;

import java.util.HashMap;
import java.util.LinkedList;

public class ManagerEmployeePrint {
	
	public static void main(String args[])
	{
		ManagerEmployeePrint managerEmployeePrint =new ManagerEmployeePrint();
		HashMap<String,LinkedList> hm=new HashMap<>();
		managerEmployeePrint.getManualData(hm);
		managerEmployeePrint.companyPreetyPrint(hm);
	}

	public void companyPreetyPrint(HashMap<String,LinkedList> hm)
	{
		System.out.println("AAAA");
		companyPreetyPrint(hm.get("AAAA"),1,hm);
	}
	
	public void companyPreetyPrint(LinkedList<String> list,int space,HashMap<String, LinkedList> hm)
	{
		for(String s: list)
		{
			for(int i=0;i<space;i++)
				System.out.print("-");
			System.out.println(s);
			if(hm.containsKey(s))
				companyPreetyPrint(hm.get(s),space+1,hm);
		}
	}
	public void getManualData(HashMap<String,LinkedList> hm)
	{
		LinkedList<String> list=new LinkedList<>();
		list.add("BBBB");
		list.add("CCCC");
		list.add("DDDD");
		list.add("EEEE");
		list.add("FFFF");
		hm.put("AAAA", list);
		LinkedList<String> list1=new LinkedList<>();
		list1.add("GGGG");
		list1.add("HHHH");
		list1.add("IIII");
		list1.add("JJJJ");
		list1.add("KKKK");
		hm.put("CCCC", list1);
		LinkedList<String> list2=new LinkedList<>();
		list2.add("LLLL");
		list2.add("MMMM");
		list2.add("NNNN");
		list2.add("OOOO");
		list2.add("PPPP");
		hm.put("EEEE", list2);
		LinkedList<String> list3=new LinkedList<>();
		list3.add("1111");
		list3.add("2222");
		list3.add("3333");
		list3.add("4444");
		list3.add("5555");
		hm.put("HHHH", list3);
		LinkedList<String> list4=new LinkedList<>();
		list4.add("6666");
		list4.add("7777");
		list4.add("8888");
		list4.add("9999");
		list4.add("1234");
		hm.put("2222", list4);
	}
}
