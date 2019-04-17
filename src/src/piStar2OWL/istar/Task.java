package piStar2OWL.istar;

public class Task extends iStarElm {

	public Task(String type, String id, String name, String actorID) {
		super(type, id, name, actorID);
	}
	
	public String toString() {
		return super.formatedDeclaration + String.format(super.elm_str, "Task", this.getName().replaceAll("\\s+","_"));
	}

}

