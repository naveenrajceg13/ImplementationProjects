public class Google2 {
	public static String commonSplit(String s, int k){
		String temp[] = s.split("-");
		StringBuilder inter = new StringBuilder();
		for(String t:temp){
			inter = inter.append(t.toUpperCase());
		}
		int firstSplit = inter.length() % k;
		int n = inter.length() / k;
		int nextSplit;
		if(firstSplit != 0){
			inter.insert(firstSplit, '-');
			nextSplit = firstSplit + k + 1;
		}
		else
			nextSplit = firstSplit + k;
		
		for(int i=0;i<n-1;i++){
			inter.insert(nextSplit, '-');
			nextSplit += k + 1;
		}
		
		return new String(inter);

	}
	
	public static void main(String args[]){
		String str = "2-qs";
		int k = 2;
		String result = commonSplit(str,k);
		System.out.println(result);
	}
}