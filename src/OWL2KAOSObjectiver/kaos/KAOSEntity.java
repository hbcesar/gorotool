package OWL2KAOSObjectiver.kaos;

import java.util.ArrayList;

public class KAOSEntity {
	private String type;
	private String name;
	private int id;
	private ArrayList<Integer> refinedIn = new ArrayList<>();
	private ArrayList<Integer> refinements = new ArrayList<>();
	private ArrayList<Integer> assignments = new ArrayList<>();
	private ArrayList<Integer> obstructions = new ArrayList<>();
	
	public KAOSEntity(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Integer> getRefinements() {
		return refinements;
	}
	
	public void addRefinements(int id) {
		this.refinements.add(id);
	}
	
	public ArrayList<Integer> getRefinedIn() {
		return refinedIn;
	}
	
	public void addRefinedIn(int id) {
		this.refinedIn.add(id);
	}
	
	public void addAssignment(int id) {
		this.assignments.add(id);
	}
	
	public void addObstruction(int id) {
		this.obstructions.add(id);
	}
	
	public String toString() {
		String str = "";
		
		String s = "<entities xsi:type=\"objectiver.model:%s\" name=\"%s\"";
		
		str = String.format(s, type, name);
		
		if(this.refinedIn.size() > 0) {
			str += " refinedIn=\"";
			
			for(int i = 0; i < refinedIn.size(); i++) {
				str += "//rootpackage/relationships." + refinedIn.get(i) + " ";
			}
			
			str += "\" ";
		}
		
		if(this.refinements.size() > 0) {
			str += " refinements=\"";
			
			for(int i = 0; i < refinements.size(); i++) {
				str += "//rootpackage/relationships." + refinements.get(i) + " ";
			}
			
			str += "\" ";
		}
		
		if(this.assignments.size() > 0) {
			str += " assignment=\"";
			
			for(int i = 0; i < assignments.size(); i++) {
				str += "//rootpackage/relationships." + assignments.get(i) + " ";
			}
			
			str += "\" ";
		}
		
		if(this.obstructions.size() > 0) {
			str += " obstruction=\"";
			
			for(int i = 0; i < obstructions.size(); i++) {
				str += "//rootpackage/relationships." + obstructions.get(i) + " ";
			}
			
			str += "\" ";
		}
		
		str += "/>\n";
		
		return str;
	}
}
