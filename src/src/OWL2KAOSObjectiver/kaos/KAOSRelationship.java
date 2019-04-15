package OWL2KAOSObjectiver.kaos;

import java.util.ArrayList;

public class KAOSRelationship {
	private String type;
	private int refines;
	private ArrayList<Integer> subGoals = new ArrayList<>();
	private int assigned;
	private int objective;
	private int obstructionBy;
	private int obstructionOf;
	
	public KAOSRelationship(String type) {
		this.type = type;
		this.refines = this.assigned = this.objective = this.obstructionBy = this.obstructionOf = -1;
	}
	
	public String getType() {
		return this.type;
	}
	
	public int getSourceID() {
		return this.refines;
	}
	
	public void setRefines(int id) {
		this.refines = id;
	}
	
	public int getRefines() {
		return this.refines;
	}
	
	public void addSubGoal(int id) {
		this.subGoals.add(id);
	}
	
	public ArrayList<Integer> getSubGoals() {
		return this.subGoals;
	}
	
	public int getAssigned() {
		return assigned;
	}

	public void setAssigned(int assigned) {
		this.assigned = assigned;
	}

	public int getObjective() {
		return objective;
	}

	public void setObjective(int objective) {
		this.objective = objective;
	}

	public int getObstructionBy() {
		return obstructionBy;
	}

	public void setObstructionBy(int obstructionBy) {
		this.obstructionBy = obstructionBy;
	}

	public int getObstructionOf() {
		return obstructionOf;
	}

	public void setObstructionOf(int obstructionOf) {
		this.obstructionOf = obstructionOf;
	}

	public String toString() {
		String str = "<relationships xsi:type=\"objectiver.model:%s\"";
		
		str = String.format(str, this.type);
		
		if(this.refines >= 0 && !this.type.equals("Operationalization")) {
			str += "refines=\"//@rootPackage/@entities." + refines + "\" ";
		} else {
			str += "parent=\"//@rootPackage/@entities." + refines + "\">\n";
		}
		
		if(this.assigned >= 0) {
			str += "assigned=\"//rootPackage/@entities." + assigned + "\" ";
		}
		
		if(this.objective >= 0) {
			str += "objective=\"//rootPackage/@entities." + objective + "\" ";
		}
		
		if(this.obstructionBy >= 0) {
			str += "obstructionBy=\"//rootPackage/@entities." + obstructionBy + "\" ";
		}
		
		if(this.obstructionOf >= 0) {
			str += "obstructionOf=\"//rootPackage/@entities." + obstructionOf + "\" ";
		}
		
		if(this.subGoals.size() > 0) {
			if(this.type.equals("Operationalization")) {
				for(int i = 0; i < subGoals.size(); i++) {
					str += "\t<strengthenings operation=\"//@rootPackage/@entities." + subGoals.get(i) + "\"/>\n";
				}
				
				str += "</relationships>\n";
				
			} else {
				str = "subgoals=\"";
				
				for(int i = 0; i < subGoals.size(); i++) {
					str += "//@rootPackage/@entities." + subGoals.get(i) + " ";
				}
				
				str = "\" ";	
			}
		}
		
		if(!this.type.equals("Operationalization")) {
			str += "/>\n";
		}
		
		return str;
	}
}
