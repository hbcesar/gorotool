package OWL2KAOSObjectiver.kaos.entities;

import java.util.ArrayList;

public class Softgoal extends KAOSEntity {
	private ArrayList<Integer> refinements = new ArrayList<>();
	private ArrayList<Integer> refinedIn = new ArrayList<>();

	public Softgoal(String name, String type) {
		super(name, type);
	}
	
	public void addRefinement(int refinement) {
		this.refinements.add(refinement);
	}
	
	public ArrayList<Integer> getRefinements(){
		return this.refinements;
	}
	
	public void addRefinedIn(int refinement) {
		this.refinements.add(refinement);
	}
	
	public ArrayList<Integer> getRefinedIn(){
		return this.refinements;
	}
	
	public String toString() {
		String str = super.toString();
		
		if(refinements.size() > 0) {
			str+= " refinements=\"";
			
			for(int i = 0; i < refinements.size(); i++) {
				str += "//@rootPackage/@relationships." + refinements.get(i);
			}
			
			str += "\"";
		}
		
		if(refinedIn.size() > 0) {
			str+= " refinedIn=\"";
			
			for(int i = 0; i < refinedIn.size(); i++) {
				str += "//@rootPackage/@relationships." + refinedIn.get(i);
			}
			
			str += "\"";
		}
		
		str += "/>\n";
		
		return str;
	}
}
