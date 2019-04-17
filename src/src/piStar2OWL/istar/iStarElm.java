package piStar2OWL.istar;

public abstract class iStarElm {
	private String id;
	private String name;
	private String type;
	private String actorID;
	public String declaration = "\t<Declaration>\n"
			+		"\t\t<NamedIndividual IRI=\"#%s\"/>\n"
			+	"\t</Declaration>\n";
	public String elm_str = "\t<ClassAssertion>\n"
			+	"\t\t<Class IRI=\"#%s\"/>\n"
			+		"\t\t<NamedIndividual IRI=\"#%s\"/>\n"
			+ "\t</ClassAssertion>";
	public String formatedDeclaration;
	

	public iStarElm(String type, String id, String name, String actorID) {
		this.type = type;
		this.id = id;
		this.name = name;
		this.actorID = actorID;
		formatedDeclaration = String.format(declaration, this.name.replaceAll("\\s+","_"));
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
	
	public  abstract String toString();
}
