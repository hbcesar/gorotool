package OWL2KAOSObjectiver;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import OWL.GOROElement;
import OWL.GORORelation;
import OWL.OWLReader;
import OWL2KAOSObjectiver.kaos.entities.GoalRequirement;
import OWL2KAOSObjectiver.kaos.entities.KAOSEntity;
import OWL2KAOSObjectiver.kaos.entities.Operation;
import OWL2KAOSObjectiver.kaos.entities.Softgoal;
import OWL2KAOSObjectiver.kaos.relationships.GoalRefinementRelation;
import OWL2KAOSObjectiver.kaos.relationships.KAOSRelationship;
import OWL2KAOSObjectiver.kaos.relationships.OperationRelation;
import piStar2OWL.OWLFile;

public class OWL2KAOS {
	private OWLReader the_reader;
	private ArrayList<KAOSEntity> entities = new ArrayList<KAOSEntity>();
	private ArrayList<KAOSRelationship> relationships = new ArrayList<KAOSRelationship>();
	
	public OWL2KAOS(OWLReader reader) {
		this.the_reader = reader;
	}
	
	public void convert2Objectiver(String filePath) {
		String str = "";
		
		ArrayList<GORORelation> relations = the_reader.getLinks();
		ArrayList<GOROElement> elements = the_reader.getElements();
		
		//Remove any task decomposition and its target elements
		for(int i = 0; i < relations.size(); i++) {
			GORORelation el = relations.get(i);
			if(el.getType().equals("Complex_Task_Decomposition")) {
				System.out.println("Eita");
				int source = the_reader.lookupByName(el.getSource());
				elements.remove(source);
				System.out.println("KAOS does not allows task decomposition. Ignoring decomposition between " + el.getSource() + " and " + el.getTarget());
				relations.remove(el);
			}
		}
		
		//Remove contribution elements
		for(int i = 0; i < elements.size(); i++) {
			GOROElement el = elements.get(i);
			if(el.getType().equals("Contribution") || el.getType().equals("GBRA_Contribution") || el.getType().equals("Task_Contribution")) {
				System.out.println("KAOS does not have the contribution relations. Ignoring the element: " + el.getName());
				elements.remove(i);
			}
		}
		
		//Remove contribution relations
		for(int i = 0; i < relations.size(); i++) {
			GORORelation el = relations.get(i);
			if(el.getType().equals("contribution_mediation")) {
				//System.out.println("KAOS does not have the contribution relation. Ignoring - source: " + el.getSource() + ", target: " + el.getTarget());
				relations.remove(i);
			}
		}
		
		//Elements convertion
		for(int i = 0; i < elements.size(); i++) {
			GOROElement el = elements.get(i);
			String type = el.getType();
			KAOSEntity e;
			System.out.println("Element " + type + " name: " + el.getName());
			
			switch(type) {
				case "Functional_Requirement_Hardgoal":
					e = new GoalRequirement(el.getName(), "Goal");
					entities.add(e);
					break;
					
				case "Non-functional_Requirement_Softgoal":
					e = new Softgoal(el.getName(), "SoftGoal");
					entities.add(e);
					break;
					
				case "Task":
					e = new Operation(el.getName(), "Operation");
					entities.add(e);
					break;
					
			}
		}
		
				
		//Links convertion
		for(int i = 0; i < relations.size(); i++) {
			GORORelation el = relations.get(i);
			String type = el.getType();
			
			KAOSRelationship e;
			
			String target = el.getSource();
			String source  = el.getTarget();
			int sourceID = the_reader.lookupByName(source);
			int targetID = the_reader.lookupByName(target);
			
			boolean aux = true;
			//System.out.println("Relationship " + type + " source: " + source + " target: " + target);
			
			switch(type) {
				case "AND_Goal-Based_Requirement_Artifact":
					for(int j = 0; j < relationships.size(); j++) {
						if(relationships.get(j).getType().equals("GRefinement") && ((GoalRefinementRelation) relationships.get(j)).getRefines() == sourceID) {
							
							((GoalRefinementRelation) relationships.get(j)).addSubGoal(targetID);
							aux = false;
							break;
						}
					}
					
					if(aux) {
						e = new GoalRefinementRelation("GRefinement");
						((GoalRefinementRelation) e).setRefines(sourceID);
						((GoalRefinementRelation) e).addSubGoal(targetID);
						relationships.add(e);
					}
					break;
					
				case "OR_Goal-Based_Requirement_Artifact":
					e = new GoalRefinementRelation("GRefinement");
					((GoalRefinementRelation) e).setRefines(sourceID);
					((GoalRefinementRelation) e).addSubGoal(targetID);
					relationships.add(e);
					break;
					
				case "intends_to_operationalize":
					for(int j = 0; j < relationships.size(); j++) {
						if(relationships.get(j).getType().equals("Operationalization") && ((OperationRelation) relationships.get(j)).getParent() == sourceID) {
							((OperationRelation) relationships.get(j)).addStrengthenings(targetID);
							aux = false;
							break;
						}
					}
					
					if(aux) {
						e = new OperationRelation("Operationalization");
						((OperationRelation) e).setParent(sourceID);
						((OperationRelation) e).addStrengthenings(targetID);
						relationships.add(e);
					}
					break;
			}
			
		}
		
		
		//Elements cross referencing 
		for(int i = 0; i < relationships.size(); i++) {
			KAOSRelationship r = relationships.get(i);
			String type = relationships.get(i).getType();
			int source;
			
			switch(type) {
				case "GRefinement":
					source = ((GoalRefinementRelation) r).getRefines();
					((GoalRequirement) entities.get(source)).addRefinedIn(i);
					
					ArrayList<Integer> subgoals = ((GoalRefinementRelation) r).getSubGoals();
					for(int j = 0; j < subgoals.size(); j++) {
						int sub = subgoals.get(j);
						((GoalRequirement) entities.get(sub)).addRefinement(i);
					}
					
					break;
					
				case "Operationalization":
					source = ((OperationRelation) r).getParent();
					((GoalRequirement) entities.get(source)).addOperationalization(i);
					((GoalRequirement) entities.get(source)).convertToRequirement();
					
					ArrayList<Integer> strengthenings = ((OperationRelation) r).getStrengthenings();
					for(int j = 0; j < strengthenings.size(); j++) {
						int s = strengthenings.get(j);
						((Operation) entities.get(s)).setStrengthenedIn(i);
					}
					
					break;
			}
		}
		
		saveFile(filePath);
	}
	
	public void saveFile(String filePath) {
		System.out.println("Creating the KAOS XMI file");
		
		String str = "";
		
		for(int i = 0; i < entities.size(); i++) {
			str += entities.get(i).toString();
		}
		
		for(int i = 0; i < relationships.size(); i++) {
			str += relationships.get(i).toString();
		}
		
		//extract the string into a OWL file
				PrintWriter writer;
				try {
					writer = new PrintWriter(filePath, "UTF-8");
//					String output = "";
//					output = OWLFile.getOWLHead();
//					output += str;
//					output += "</Ontology>";
					String output = str;
					writer.print(output);
					writer.close();
					System.out.println("KAOS XMI file successfully created.");
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
	}
		
}