import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

//Driver program for skip list implementation.

public class TreeMapDriver {
    public static void main(String[] args) throws FileNotFoundException {
	Scanner sc;
	if (args.length > 0) {
	    File file = new File(args[0]);
	    sc = new Scanner(file);
	} else {
	    sc = new Scanner(System.in);
	}
	String operation = "";
	long operand = 0;
	int modValue = 999983;
	long result = 0;
	Long returnValue = null;
	
	// Initialize the timer
	Timer timer = new Timer();

	TreeMap<Long,Long> treeMap = new TreeMap<>();
	while (!((operation = sc.next()).equals("End"))) {
	    switch (operation) {
	    case "Add": {
		operand = sc.nextLong();
		boolean alreadyPresent=treeMap.containsKey(operand);
		treeMap.put(operand,operand);
		if(!alreadyPresent) {
		    result = (result + 1) % modValue;
		}
		break;
	    }
	    case "Ceiling": {
		operand = sc.nextLong();
		returnValue = treeMap.ceilingKey(operand);
		if (returnValue != null) {
		    result = (result + returnValue) % modValue;
		}
		break;
	    }
	    case "FindIndex": {
		int intOperand = sc.nextInt();
		break;
	    }
	    case "First": {
		returnValue = treeMap.firstKey();
		if (returnValue != null) {
		    result = (result + returnValue) % modValue;
		}
		break;
	    }
	    case "Last": {
		returnValue = treeMap.lastKey();
		if (returnValue != null) {
		    result = (result + returnValue) % modValue;
		}
		break;
	    }
	    case "Floor": {
		operand = sc.nextLong();
		returnValue = treeMap.floorKey(operand);
		if (returnValue != null) {
		    result = (result + returnValue) % modValue;
		}
		break;
	    }
	    case "Remove": {
		operand = sc.nextLong();
		if (treeMap.remove(operand) != null) {
		    result = (result + 1) % modValue;
		}
		break;
	    }
	    case "Contains":{
		operand = sc.nextLong();
		if (treeMap.containsKey(operand)) {
		    result = (result + 1) % modValue;
		}
		break;
	    }
		
	    }
	}
	// End Time
	timer.end();

	System.out.println(result);
	System.out.println(timer);
    }
}
