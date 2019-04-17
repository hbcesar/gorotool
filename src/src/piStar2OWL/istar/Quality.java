package piStar2OWL.istar;

public class Quality extends iStarElm {

	public Quality(String type, String id, String name, String actorID) {
		super(type, id, name, actorID);
	}
	
	public String toString() {
		return super.formatedDeclaration + String.format(super.elm_str, "Non-functional_Requirement_Softgoal", this.getName().replaceAll("\\s+","_"));
	}

}
