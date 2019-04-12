package OWL2KAOSObjectiver.kaos.relationships;

import java.util.ArrayList;

public class GoalRefinementRelation extends KAOSRelationship {
	private int refines;
	private ArrayList<Integer> subGoals = new ArrayList<>();
	
	public GoalRefinementRelation(String type) {
		super(type);
	}

	public int getRefines() {
		return refines;
	}

	public void setRefines(int refines) {
		this.refines = refines;
	}

	public ArrayList<Integer> getSubGoals() {
		return subGoals;
	}

	public void addSubGoal(int subGoal) {
		this.subGoals.add(subGoal);
	}
	
	public String toString() {
		String str = super.toString();
		
		str += "refines=\"//@rootPackage/@entities." + this.getRefines() + "\"";
		str += " subGoals=\"";
		
		for(int i = 0; i < subGoals.size(); i++) {
			str += "//@rootPackage/@entities." + subGoals.get(i) + " ";
		}
		
		str += "\"";
		str += "/>\n";
		
		return str;
	}
}
