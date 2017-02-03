
public class Equlabirum {
	
	public static void main(String args[])
	{
		int a[]={-7,1,5,2,-4,3,0};
	    for(int i=1;i<a.length;i++)
	    {
	    	a[i]=a[i]+a[i-1];
	    }
	    for(int i=0;i<a.length-1;i++)
	    {
	    	int till=a[i];
	    	int after=a[a.length-1]-a[i];
	    	if(till==after){
	    		System.out.println(i);break;}
	    }
	}
}
