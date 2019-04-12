package piStar2OWL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;
//https://www.testingexcellence.com/how-to-parse-json-in-java/
import org.json.*;

import piStar2OWL.istar.*;

public class iStarConverter {
	private ArrayList<Actor> actors = new ArrayList<Actor>();
	private ArrayList<iStarElm> dependencies = new ArrayList<iStarElm>();
	private ArrayList<iStarLink> links = new ArrayList<iStarLink>();
	Logger logger;
	
	public iStarConverter() {
		logger = Logger.getLogger( "logger");
	}
	
	public ArrayList<Actor> getActors() {
		return actors;
	}
	
	public ArrayList<iStarLink> getLinks() {
		return links;
	}

	public void read(String filePath) {
		System.out.println("[INFO] Reading the PiStar file");
		
		String str = "";
		
		
		//Opens the JSON file and saves it on a String
		try {
			File json = new File(filePath);
			Scanner input = new Scanner(System.in);
			input = new Scanner(json);
			
			while (input.hasNextLine()) {
	            str += input.nextLine();
	            
	        }
			
	        input.close();
	        
		} catch (FileNotFoundException e) {
			System.out.println("[ERROR] Error: file not found.");
			e.printStackTrace();
		}
		
		//JSON Parser
		JSONObject obj = new JSONObject(str);
		
		//Actors
		JSONArray  res = obj.getJSONArray("actors");
		for(int i = 0; i < res.length(); i++) { //for each actor
			//create the actor element
			String actorType = res.getJSONObject(i).getString("type");
			String actorName = res.getJSONObject(i).getString("text");
			String actorID = res.getJSONObject(i).getString("id");
			Actor actor = new Actor(actorType, actorID, actorName);
			
			//create actor's nodes
			JSONArray nodes = res.getJSONObject(i).getJSONArray("nodes");
			for(int j = 0; j < nodes.length(); j++) {
				JSONObject node = nodes.getJSONObject(j);
				
				if(node.has("type") && node.has("id")) {

					String type = node.getString("type");
					String id = node.getString("id");
					String name = (node.has("text"))? node.getString("text") : type.replace("istar.", "") + " " + j;  
					iStarElm el = new iStarElm(type, id, name, actorID);
					actor.addElement(el);
				} else {
					System.out.println("[ERROR] There is an element with a missing field.");
				}
			}
			
			actors.add(actor);
		}
		
		//Dependencies
		res = obj.getJSONArray("dependencies");
		String output = "";
		if(res.length() > 0) output = "GORO does not support iStar dependencies. The following elements were ignored:\n";
		for(int i = 0; i < res.length(); i++) { //for each dependency
			JSONObject node = res.getJSONObject(i);
			
//			if(node.has("type") && node.has("source") && node.has("target") && node.has("id")) {
				
//				String dependumID = node.getString("id");
//				
				if(node.has("type")) { //&& node.has("id")) {
//
					String type = node.getString("type");
//					String id = node.getString("id");
					String name = (node.has("text"))? node.getString("text") : type.replace("istar.", "") + " " + i;  
//					iStarElm el = new iStarElm(type, id, name, dependumID);
//					dependencies.add(el);
//				} else {
//					System.out.println("[ERROR] There is a dependency with a missing field");
					output += name + "\n";
				}
//			}
		}
		if(res.length() > 0) logger.warn(output);
		
		//Links
		res = obj.getJSONArray("links");
		for(int i = 0; i < res.length(); i++) { //for each actor
			JSONObject node = res.getJSONObject(i);
			
			if(node.has("type") && node.has("source") && node.has("target") && node.has("id")) {
				String type = node.getString("type");
				if(!(type.equals("istar.DependencyLink") || type.equals("istar.ParticipatesInLink") || type.equals("istar.IsALink"))) {
					String target = node.getString("source");
					String source = node.getString("target");
					
					iStarLink d;
					if(node.has("label")) {
						d = new iStarLink(source, target, type, node.getString("label"));
					} else {
						d = new iStarLink(source, target, type);
					}
					
					links.add(d);
				} 
			}
		}
		
		//sets the actual source and target for each link 
		for(int i = 0; i < links.size(); i++) {
			iStarLink l = links.get(i);
			
			l.setSource(lookupByID(l.getSourceID()));
			l.setTarget(lookupByID(l.getTargetID()));
		}
		
		System.out.println("[INFO] PiStar file successfully read");
	}
	
	public void extract2OWL(String filePath) {
		System.out.println("[INFO] Creating the OWL file from PiStar");
		
		String str = "";
		
		for(int i = 0; i < this.actors.size(); i++) {
			Actor actor = this.actors.get(i);
			ArrayList<iStarElm> nodes = actor.getNodes();
			
			for(int j = 0; j < nodes.size(); j++) {
				str += nodes.get(j).toString();
				str += "\n";
			}
		}
		
		for(int i = 0; i < this.links.size(); i++) {
			str += this.links.get(i).toString();
			str += "\n";
		}
		
//		for(int i = 0; i < this.dependencies.size(); i++) {
//			str += this.dependencies.get(i).toString();
//			str += "\n";
//		}
		
		//extract the string into a OWL file
		PrintWriter writer;
		try {
			writer = new PrintWriter(filePath, "UTF-8");
			String output = "";
			output = OWLFile.getOWLHead();
			output += str;
			output += "</Ontology>";
			writer.print(output);
			writer.close();
			System.out.println("[INFO] Intermediary OWL file successfully created: converted_model.owl");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String log_output = "As GORO does not support actors boundaries such as iStar does. The following actors were ignored:\n";
		for(int i = 0; i < actors.size(); i++) {
			log_output += "Type: " + actors.get(i).getType().replace("istar.", "") + ", name: " + actors.get(i).getName() + "\n";
		}
		
		logger.warn(log_output);

	}
	
	public iStarElm lookupByID(String id) {
		for(int i = 0; i < this.actors.size(); i++){
			Actor actor = this.actors.get(i);
			ArrayList<iStarElm> nodes = actor.getNodes();
			
			for(int j = 0; j < nodes.size(); j++) {
				if(nodes.get(j).getId().equals(id)){
					return nodes.get(j);
				}
			}
		}
		
		for(int i = 0; i < this.dependencies.size(); i++) {
			if(dependencies.get(i).getId().equals(id)) {
				return dependencies.get(i);
			}
		}
		
		System.out.println("[ERROR] inconsistency in PiStar file. The follow ID do not exist: " + id);
		
		System.exit(0);
		return null;
	}
}
