
public class WiggleCheck {

	public static void main(String args[])
	{
		WiggleCheck wiggleCheck=new WiggleCheck();
		SinglyLinkedList<Integer> linkedList=new SinglyLinkedList<Integer>();
		for(int i=0;i<30;i++)
		{
			//linkedList.add(i);
		}
		linkedList.add(1);
		linkedList.add(5);
		linkedList.add(1);
		linkedList.add(5);
		linkedList.add(1);
		linkedList.add(-21);
		linkedList.add(8);
		
		wiggleCheck.wiggleCheking(linkedList);
		linkedList.printList();
	}
	public void wiggleCheking(SinglyLinkedList<Integer> list)
	{
		SinglyLinkedList<Integer>.Entry<Integer> head=list.header;
		SinglyLinkedList<Integer>.Entry<Integer> tail=head.next;
		int start=0;
		int end=0;
		int count=0;
		int last_wiggle=0;
		int last_value=0;
		int longest_wiggle=0;
		int temp_start=0;
		boolean wiggle_there=false;
		while(tail!=null)
		{
	         if(count==0)
	         {
	        	 last_value=tail.element;
	        	 tail=tail.next;
	        	 count++;
	         }
	         else if(count==1)
	         {
	        	 last_wiggle=last_value-tail.element;
	        	 last_value=tail.element;
	        	 tail=tail.next;
	        	 count++;
	         }
	         else
	         {
	        	 int wiggle_now=last_value-tail.element;
	        	 System.out.println(last_wiggle+" "+wiggle_now);
	        	 if(Math.abs(last_wiggle)-Math.abs(wiggle_now)==0)
	        	 {
	        		 if(!wiggle_there)
	        		 {
	        			 temp_start=count;
	        			 wiggle_there=true;
	        		 }
	        		 
	        	 }
	        	 else
	        	 {
	        		 wiggle_there=false;
	        		 if(count-temp_start>longest_wiggle)
	        		 {
	        			 longest_wiggle=count-temp_start;
	        			 start=temp_start;
	        			 temp_start=0;
	        			 end=count;
	        		 }
	        	 }
	        	 count++;
	        	 last_value=tail.element;
	        	 tail=tail.next;
	        	 last_wiggle=wiggle_now;
	         }
		}
		if(wiggle_there)
		{
			 if(count-temp_start>longest_wiggle)
    		 {
    			 longest_wiggle=count-temp_start;
    			 start=temp_start;
    			 temp_start=0;
    			 end=count;
    		 }
		}
        System.out.println(start+" "+end+" "+longest_wiggle);
		
	}
}
