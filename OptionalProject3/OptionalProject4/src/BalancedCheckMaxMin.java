
public class BalancedCheckMaxMin<T> {
	
     public boolean plusInfinityFlag;
     public boolean minusInfinityFlag;
     public T value;
     
     public BalancedCheckMaxMin(boolean plusInfinityFlag,boolean minusInfinityFlag,T value) {
    	 this.plusInfinityFlag=plusInfinityFlag;
    	 this.minusInfinityFlag=minusInfinityFlag;
    	 this.value=value;
	}
}
