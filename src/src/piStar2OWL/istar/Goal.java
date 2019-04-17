package piStar2OWL.istar;

public class Goal extends iStarElm {
	private boolean operationalized;

	public Goal(String type, String id, String name, String actorID) {
		super(type, id, name, actorID);
		this.operationalized = false;
	}
	
	public String toString() {
		return super.formatedDeclaration + String.format(super.elm_str, "Functional_Requirement_Hardgoal", this.getName().replaceAll("\\s+","_"));
	}
	
	public boolean isOperationalized() {
		return this.operationalized;
	}
	
	public void setOperationalized() {
		this.operationalized = true;
	}

}
