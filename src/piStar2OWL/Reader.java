package piStar2OWL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//https://www.testingexcellence.com/how-to-parse-json-in-java/
import org.json.*;

public class Reader {
	public static void main(String[] args) {
		String str = "";
		
		//Opens the JSON file and saves it on a String
		try {
			File json = new File("goalModel.txt");
			Scanner input = new Scanner(System.in);
			input = new Scanner(json);
			
			while (input.hasNextLine()) {
	            str += input.nextLine();
	            
	        }
			
			//System.out.println(str);
	        input.close();
	        
		} catch (FileNotFoundException e) {
			System.out.println("Error: file not found.");
			e.printStackTrace();
		}
		
		//JSON Parser
		JSONObject obj = new JSONObject(str);
		
		//Actors
		JSONArray  res = obj.getJSONArray("actors");
		for(int i = 0; i < res.length(); i++) { //for each actor
			System.out.println(res.getJSONObject(i).getJSONArray("nodes"));
		}
		
		//Dependencies
		res = obj.getJSONArray("dependencies");
		for(int i = 0; i < res.length(); i++) { //for each actor
			System.out.println(res.getJSONObject(i).getString("text"));
		}
		
		//Links
		res = obj.getJSONArray("links");
		for(int i = 0; i < res.length(); i++) { //for each actor
			System.out.println(res.getJSONObject(i).getString("source"));
		}
	}
}
