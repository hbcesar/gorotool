package piStar2OWL.istar;

public class OrRefinementLink extends iStarLink {

	public OrRefinementLink(String source, String target, String type) {
		super(source, target, type);
	}
	
	public String toString() {
		if(super.source.getType().equals("istar.Goal") && super.target.getType().equals("istar.Task")) {
			str = String.format(str, "intends_to_operationalize", super.target.getName().replaceAll("\\s+","_"), super.source.getName().replaceAll("\\s+","_"));
		} else if(super.source.getType().equals("istar.Task") && super.target.getType().equals("istar.Task")) {
			str = String.format(str, "OR_Complex_Task_Decomposition", super.source.getName().replaceAll("\\s+","_"), super.target.getName().replaceAll("\\s+","_"));
		} else {
			str = String.format(str, "OR_Goal-Based_Requirement_Artifact", super.source.getName().replaceAll("\\s+","_"), super.target.getName().replaceAll("\\s+","_"));
		}
		return str;
	}
}