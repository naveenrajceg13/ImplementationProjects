/*
 * Naveenraj Palanisamy
 * Chandni Shankar
 * Apurva Alekar
 */
package copart;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/*
 * COPART location details are there in the file 'copartLocations.csv'
 * Customer Details - Customer and Nearest Copart Location deatils are there in the file 'CopartCustomerLocation.csv' 
 * 
 * Case 1:
 *         Member Customer searches. Fetch from Customer Details and display
 * Case 2: 
 *         New Customer searches. Get the zipCode and get minimum Distance zipCode(from API) and fetch details and display  
 * Case 3: 
 *         New Customer searches for already mapped minimum zip code, fetch from Map and display.  -- Only if same session 
 */
public class NearestCopartLocation {
	
	Map addressMap;
	Map customerMap;
	Map nearLocation;
	NearestCopartLocation()
	{
		nearLocation=new HashMap<String,String>();
	}
	class CopartLocation
	{
		String stateCode;
		String state;    
		String phoneNumber;
		String zipCode;
		
		public CopartLocation(String stateCode,String state,String phoneNumber,String zipCode) { 
			this.stateCode=stateCode;
			this.state=state;
			this.phoneNumber=phoneNumber;
			this.zipCode=zipCode;
		}
		
		public String toString()
		{
			return this.stateCode+" "+this.state+" "+this.phoneNumber;
		}
	}
	
	public static void main(String args[])throws Exception
	{
		String customerID;
		String zipCode;
		Scanner s=new Scanner(System.in);
		NearestCopartLocation nearCopart=new NearestCopartLocation();
		nearCopart.addressMap=nearCopart.getLocationInputandPutinMap(new File("copartLocations.csv"));
		nearCopart.customerMap=nearCopart.getCustomerLocationMap(new File("CopartCustomerLocation.csv"));
		System.out.println("Enter Customer ID and Zip code");
		while(s.hasNext())
		{
		customerID=s.nextLine();
		zipCode=s.nextLine();
		if(customerID.length()>1 && zipCode.length()>1)
		     System.out.println(nearCopart.getNearestLocation(customerID, zipCode));
		System.out.println("Enter Customer ID and Zip code");
		}
		
	}

	public CopartLocation getNearestLocation(String customerID,String zipCode)
	{
		ZipCodeApi zipCodeApi=new ZipCodeApi();
		if(customerMap.containsKey(customerID))
			return (CopartLocation)addressMap.get(customerMap.get(customerID));
		if(nearLocation.containsKey(zipCode))
		{
			return (CopartLocation)addressMap.get(nearLocation.get(zipCode));
		}
		
		double distanceMin=Double.MAX_VALUE;
		String minZipCode=null;
		System.out.println("Fetching from API............");
		for(Object zip: addressMap.keySet())
		{
			double value=zipCodeApi.getDistance(zipCode, (String)zip); 
			if(value<distanceMin)
			{
				distanceMin=value;
				minZipCode=(String)zip;
			}
		}
		nearLocation.put(zipCode, minZipCode);
		return (CopartLocation)addressMap.get(minZipCode);
	}
	
	public Map getCustomerLocationMap(File f)throws Exception
	{
		Scanner s=new Scanner(f);
		HashMap<String,String> hm=new HashMap<>();
		while(s.hasNextLine())
		{
			try
			{
				String now=s.nextLine();
				String str[]=now.split(",");
				hm.put(str[0], str[1]);
			}
			catch(Exception e)
			{
				//just skip misMatched ones
			}
		}
		
		return hm;
	}

	public Map getLocationInputandPutinMap(File f) throws FileNotFoundException
	{
		Scanner s=new Scanner(f);
		HashMap<String,CopartLocation> hm=new HashMap<>();
		while(s.hasNextLine())
		{
			try
			{
			String now=s.nextLine();
			String str[]=now.split(",");
			CopartLocation copartLoc=new CopartLocation(str[0], str[1], str[str.length-2], str[str.length-4]);
			hm.put(str[str.length-4], copartLoc);
			}
			catch(Exception e)
			{
				//just skip mismatched locations
			}
		}
		
		return hm;
	}
}
