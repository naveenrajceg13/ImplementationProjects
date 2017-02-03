import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AmazonStronglyAndWeeklyConnected {

	
	public static void main(String args[])
	{
		AmazonStronglyAndWeeklyConnected ama=new AmazonStronglyAndWeeklyConnected();
		String item="ABC";
		List<String> list=new ArrayList<>();
		list.add("first:ABC");
		list.add("first:HIJ");
		list.add("sec:HIJ");
		list.add("sec:KLM");
		list.add("third:NOP");
		list.add("fourth:QRS");
		list.add("first:DEF");
		list.add("fifth:KLM");
		list.add("fifth:TUV");
		Map<String,Set<String>> userMap=new HashMap<>();
		Map<String,Set<String>> productMap=new HashMap<>();
		ama.UserMapAndProductMapPopulate(list, userMap, productMap);
		Set<String> mutalBuyers=new HashSet<>();
		int strongReco=ama.getStronglyConnected(item, userMap, productMap,mutalBuyers);
		int weeklyReco=ama.getWeeklyConnected(userMap, productMap,mutalBuyers);
		System.out.println("Strongly recommended"+strongReco);
		System.out.println("Weekly recommended"+weeklyReco);
	}
	
	public int getWeeklyConnected(Map<String,Set<String>> userMap,Map<String,Set<String>> productMap,Set<String> mutalBuyer)
	{
		 Set<String> res=new HashSet<>();
	     for(String s: mutalBuyer)
	     {
	    	 for(String it: userMap.get(s))
	    	 {
	    		 res.add(it);
	    	 }
	     }
	     return res.size();
	}
	public int getStronglyConnected(String item,Map<String,Set<String>> userMap,Map<String,Set<String>> productMap,Set<String> mutalBuyer)
	{
	       int total=0;
	       if(!productMap.containsKey(item)) return total;
		   for(String user: productMap.get(item))
		   {
			   total+=(userMap.get(user).size()-1);
			   for(String it: userMap.get(user))
			   {
				   if(it.equals(item)) continue;
				   for(String us: productMap.get(it))
				   {
					   mutalBuyer.add(us);
				   }
			   }
		   }
		   for(String user: productMap.get(item))
		   {
			   mutalBuyer.remove(user);
		   }
		   return (total+1);
	}
	public void UserMapAndProductMapPopulate(List<String> list,Map<String,Set<String>> userMap,Map<String,Set<String>> productMap)
	{
		  for(String s: list)
		  {
			 String[] temp=s.split(":");
			 Set<String> tempS;
			 if(userMap.containsKey(temp[0]))
				 tempS=userMap.get(temp[0]);
			 else
				 tempS=new HashSet<>();
			 tempS.add(temp[1]);
			 Set<String> tempP;
			 if(userMap.containsKey(temp[1]))
				 tempP=userMap.get(temp[1]);
			 else
				 tempP=new HashSet<>();
			 tempS.add(temp[1]);
			 tempP.add(temp[0]);
			 userMap.put(temp[0], tempS);
			 productMap.put(temp[1], tempP);
		  }
	}
}
