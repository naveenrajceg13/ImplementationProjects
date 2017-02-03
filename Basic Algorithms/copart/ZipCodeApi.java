package copart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;



public class ZipCodeApi {
	public static void main(String[] args) {

		ZipCodeApi zipCode=new ZipCodeApi();
		Scanner in = new Scanner(System.in);
		String zip=in.next();
		System.out.println(zipCode.getPlaceFromZipCode(zip));
	}
	
	
	public double getDistance(String zip1,String zip2)
	{
		String API_Key="SH8Be5Im18StbtqknXgm9a9aoJZsukum2CXhHGDJOvd0ZDknBAJKLSV9CVC6g6si";
		String url="https://www.zipcodeapi.com/rest/"+API_Key+"/distance.json/"+zip1+"/"+zip2+"/km";
		String inputLine;
		try {
			URL ur= new URL(url);
			HttpURLConnection con = (HttpURLConnection)ur.openConnection();
			con.setRequestMethod("GET");
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			inputLine = reader.readLine();
			JSONObject json =new JSONObject(inputLine);
			String distance=json.getString("distance");
			return Double.parseDouble(distance);
		} catch (Exception e) {
			return Integer.MAX_VALUE;
		}
	}
	
	public String getPlaceFromZipCode(String zipCode)
	{
		String clientKey="c4FYHlXPYV9BIH5qdvbJYc4YPgMbuI1GNGFu2Ck0ytBgbHGocS8b5caVsW7XF8Et";

		String url = "https://www.zipcodeapi.com/rest/"+clientKey+"/info.json/" + zipCode + "/radians";
		
		try {
			URL ur= new URL(url);
			HttpURLConnection con = (HttpURLConnection)ur.openConnection();
			
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String inputLine;
			StringBuffer response = new StringBuffer();

			inputLine = reader.readLine();
				
		
			JSONObject json =new JSONObject(inputLine);
			String city=json.getString("city");
			String state= json.getString("state");
			return city+","+state;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
