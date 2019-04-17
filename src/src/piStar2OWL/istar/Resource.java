package piStar2OWL.istar;

public class Resource extends iStarElm {

	public Resource(String type, String id, String name, String actorID) {
		super(type, id, name, actorID);
	}
	
	public String toString() {
		return super.formatedDeclaration + String.format(super.elm_str, "Resource", this.getName().replaceAll("\\s+","_"));
	}

}

