package piStar2OWL.istar;

public class iStarElm {
	private String id;
	private String name;
	private String type;
	private String actorID; //aqui tem que ser o proprio ator, que vai ser passado para referenciar no modelo depois

	public iStarElm(String type, String id, String name, String actorID) {
		this.type = type;
		this.id = id;
		this.name = name;
		this.actorID = actorID;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getActorID() {
		return actorID;
	}
	
	public String toString() {
		String declaration = "\t<Declaration>\n"
						+		"\t\t<NamedIndividual IRI=\"#%s\"/>\n"
						+	"\t</Declaration>\n";
		
		String formatedDeclaration = String.format(declaration, this.getName().replaceAll("\\s+","_"));
		
		
		String str = "\t<ClassAssertion>\n"
					+	"\t\t<Class IRI=\"#%s\"/>\n"
					+		"\t\t<NamedIndividual IRI=\"#%s\"/>\n"
					+ "\t</ClassAssertion>";
				
		switch(this.getType()){
			case "istar.Goal":
				str = String.format(str, "Functional_Requirement_Hardgoal", this.getName().replaceAll("\\s+","_"));
				return formatedDeclaration + str;
				
			case "istar.Quality":
				str = String.format(str, "Non-functional_Requirement_Softgoal", this.getName().replaceAll("\\s+","_"));
				return formatedDeclaration + str;
				
			case "istar.Task":
				str = String.format(str, "Task", this.getName().replaceAll("\\s+","_"));
				return formatedDeclaration + str;
				
			case "istar.Resource":
				//str = String.format(str, "Task", "Provide_" + this.getName().replaceAll("\\s+","_"));
				//formatedDeclaration = String.format(declaration, "Provide_" + this.getName().replaceAll("\\s+","_"));
				str = String.format(str, "Resource", this.getName().replaceAll("\\s+","_"));
				return formatedDeclaration + str;
				
			default:
				return "";
				
		}
	}
}
