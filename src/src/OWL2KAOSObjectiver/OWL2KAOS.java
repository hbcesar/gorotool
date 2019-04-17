package OWL2KAOSObjectiver;

import java.io.FileNotFoundException;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.log4j.*;

import OWL.GOROElement;
import OWL.GORORelation;
import OWL.OWLReader;
import OWL2KAOSObjectiver.kaos.entities.Goal;
import OWL2KAOSObjectiver.kaos.entities.KAOSEntity;
import OWL2KAOSObjectiver.kaos.entities.Operation;
import OWL2KAOSObjectiver.kaos.entities.Softgoal;
import OWL2KAOSObjectiver.kaos.relationships.GoalRefinementRelation;
import OWL2KAOSObjectiver.kaos.relationships.KAOSRelationship;
import OWL2KAOSObjectiver.kaos.relationships.OperationRelation;

public class OWL2KAOS {
	private OWLReader the_reader;
	private ArrayList<KAOSEntity> entities = new ArrayList<KAOSEntity>();
	private ArrayList<KAOSRelationship> relationships = new ArrayList<KAOSRelationship>();
	Logger logger;
	
	public OWL2KAOS(OWLReader reader) {
		this.the_reader = reader;
		logger = Logger.getLogger( "logger");
	}
	
	public void convert2Objectiver(String filePath) {
		//Get the list of OWL Elements to be converted
		ArrayList<GORORelation> relations = the_reader.getLinks();
		ArrayList<GOROElement> elements = the_reader.getElements();
		
		//Remove all elements that does not exist in KAOS, such as 
		//resources, contributions and task decompositions
		clean(elements, relations);
		
		//Elements convertion
		for(int i = 0; i < elements.size(); i++) {
			GOROElement el = elements.get(i);
			String type = el.getType();
			KAOSEntity e;
			
			switch(type) {
				case "Functional_Requirement_Hardgoal":
					e = new Goal(el.getName(), "Goal");
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
			switch(type) {
				case "AND_Goal-Based_Requirement_Artifact":
					//checks wether the relationship link is already defined or not
					//if yes, just adds another element
					for(int j = 0; j < relationships.size(); j++) {
						if(relationships.get(j).getType().equals("GRefinement") && ((GoalRefinementRelation) relationships.get(j)).getRefines() == sourceID) {
							((GoalRefinementRelation) relationships.get(j)).addSubGoal(targetID);
							aux = false;
							break;
						}
					}
					
					//if the relationship is not yet defined, creates a new one
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
					//same as the AND Refinements case
					for(int j = 0; j < relationships.size(); j++) {
						if(relationships.get(j).getType().equals("Operationalization") && ((OperationRelation) relationships.get(j)).getParent() == targetID) {
							((OperationRelation) relationships.get(j)).addStrengthenings(sourceID);
							aux = false;
							break;
						}
					}
					
					if(aux) {
						e = new OperationRelation("Operationalization");
						((OperationRelation) e).setParent(targetID);
						((OperationRelation) e).addStrengthenings(sourceID);
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
					((Goal) entities.get(source)).addRefinement(i);
					
					ArrayList<Integer> subgoals = ((GoalRefinementRelation) r).getSubGoals();
					for(int j = 0; j < subgoals.size(); j++) {
						int sub = subgoals.get(j);
						KAOSEntity sg = entities.get(sub);
						if(sg instanceof Goal) ((Goal) sg).addRefinedIn(i);
						else if(sg instanceof Softgoal) ((Softgoal) sg).addRefinedIn(i);
					}
					
					break;
					
				case "Operationalization":
					source = ((OperationRelation) r).getParent();
					KAOSEntity el_source = entities.get(source);
					
					if(!el_source.getType().equals("Requirement")) {
						el_source.setType("Requirement");
					} 			
					
					el_source.addOperationalization(i);
						
					ArrayList<Integer> strengthenings = ((OperationRelation) r).getStrengthenings();
					for(int j = 0; j < strengthenings.size(); j++) {
						int s = strengthenings.get(j);
						((Operation) entities.get(s)).setStrengthenedIn(i);
						((Operation) entities.get(s)).setStreghtened(j);
					}
					
					break;
			}
		}
		
		//Save the content into the KAOS file
		saveFile(filePath);
	}
	
	private void clean(ArrayList<GOROElement> elements, ArrayList<GORORelation> relations) {
		//Remove any task decomposition and its target elements
		String log_output = "KAOS does not allows task decomposition. The following elements were ignored:\n";
		for(int i = relations.size() -1; i >= 0; i--) {
			GORORelation el = relations.get(i);
			if(el.getType().equals("Complex_Task_Decomposition")) {
				int source = the_reader.lookupByName(el.getSource());
				if(source > -1) {
					elements.remove(source);
					log_output += "Decomposition between " + el.getSource() + " and " + el.getTarget() + "\n";
					relations.remove(el);
				}
			}
		}
		logger.warn(log_output);
		
		//Remove "orphan" tasks
		String output = "";
		boolean aux = false;
		for(int i = 0; i < relations.size(); i++) {
			GORORelation r = relations.get(i);
			if(r.getType().equals("intends_to_operationalize")) {
				int source = the_reader.lookupByName(r.getTarget());
				if(source >= 0) {
					elements.get(source).setOrphan(false);
				}
			}
		}
		for(int i = elements.size() -1; i >= 0; i--) {
			GOROElement g = elements.get(i);
			if(g.getType().equals("Task") && g.isOrphan()) {
				aux = true;
				output += g.getName() + "\n";
				elements.remove(g);
			}
		}
		if(aux) logger.warn("There are unlinked elements in the model. The following Operations were ignored:\n" + output);
		
		//Remove contribution elements
		log_output = "KAOS does not have contribution relations. The following elements were ignored:\n";
		for(int i = elements.size() -1; i >= 0; i--) {
			GOROElement el = elements.get(i);
			if(el.getType().equals("Contribution") || el.getType().equals("GBRA_Contribution") || el.getType().equals("Task_Contribution")) {
				log_output += el.getName() + "\n";
				elements.remove(el);
			}
		}
		logger.warn(log_output);
		
		//Remove contribution relations
		for(int i = relations.size() -1; i >= 0; i--) {
			GORORelation el = relations.get(i);
			if(el.getType().equals("contribution_mediation")) {
				relations.remove(el);
			}
		}
		
		//Remove resources
		for(int i = elements.size() -1; i >= 0; i--) {
			GOROElement el = elements.get(i);
			if(el.getType().equals("Resource")) {
				elements.remove(el);
			}
		}
		
		//Remove unuseful links
		for(int i = relations.size() - 1; i >= 0; i--) {
			GORORelation el = relations.get(i);
			String target = el.getSource();
			String source  = el.getTarget();
			int sourceID = the_reader.lookupByName(source);
			int targetID = the_reader.lookupByName(target);
			
			if(sourceID == -1 || targetID == -1) {
				relations.remove(el);
			}
		}
		
	}

	public void saveFile(String filePath) {
		System.out.println("[INFO] Creating the KAOS XMI file");
		
		String str = "";
		
		for(int i = 0; i < entities.size(); i++) {
			str += entities.get(i).toString();
		}
		
		for(int i = 0; i < relationships.size(); i++) {
			str += relationships.get(i).toString();
		}
		
		//extract the string into a KAOS file
		PrintWriter writer;
		try {
			writer = new PrintWriter(filePath, "UTF-8");
			String output = "";
			output = KAOSFile.getXMIHead();
			output += str;
			output += KAOSFile.getXMIFoot();

			writer.print(output);
			writer.close();
			System.out.println("[INFO] KAOS XMI file successfully created: kaos_converted.xmi");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
