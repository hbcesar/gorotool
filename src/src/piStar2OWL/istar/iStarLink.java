package piStar2OWL.istar;

public class iStarLink {
	private iStarElm source;
	private String sourceID;
	private iStarElm target;
	private String targetID;
	private String type;
	private String label;
	
	public iStarLink(String source, String target, String type) {
		this.sourceID = source;
		this.targetID = target;
		this.type = type;
	}
	
	public iStarLink(String source, String target, String type, String label) {
		this.sourceID = source;
		this.targetID = target;
		this.type = type;
		this.label = label;
	}
	
	public void setSource(iStarElm source) {
		this.source = source;
	}

	public void setTarget(iStarElm target) {
		this.target = target;
	}
	
	public String getSourceID() {
		return this.sourceID;
	}
	
	public String getTargetID() {
		return this.targetID;
	}
	
	
	public String toString() {
		
		String str = "\t<ObjectPropertyAssertion>\n"
				+		"\t\t<ObjectProperty IRI=\"#%s\"/>\n"
				+		"\t\t<NamedIndividual IRI=\"#%s\"/>\n"
				+		"\t\t<NamedIndividual IRI=\"#%s\"/>\n"
				+ 	"\t</ObjectPropertyAssertion>";
		
		switch(this.type) {
			case "istar.AndRefinementLink":
				if(this.source.getType().equals("istar.Goal") && this.target.getType().equals("istar.Task")) {
					str = String.format(str, "intends_to_operationalize", this.source.getName().replaceAll("\\s+","_"), this.target.getName().replaceAll("\\s+","_"));
				} else if(this.source.getType().equals("istar.Task") && this.target.getType().equals("istar.Task")) {
					str = String.format(str, "Complex_Task_Decomposition", this.source.getName().replaceAll("\\s+","_"), this.target.getName().replaceAll("\\s+","_"));
				} else {
					str = String.format(str, "AND_Goal-Based_Requirement_Artifact", this.source.getName().replaceAll("\\s+","_"), this.target.getName().replaceAll("\\s+","_"));
				}
				return str;
				
			case "istar.OrRefinementLink":
				if(this.source.getType().equals("istar.Goal") && this.target.getType().equals("istar.Task")) {
					str = String.format(str, "intends_to_operationalize", this.source.getName().replaceAll("\\s+","_"), this.target.getName().replaceAll("\\s+","_"));
				} else if(this.source.getType().equals("istar.Task") && this.target.getType().equals("istar.Task")) {
					str = String.format(str, "Complex_Task_Decomposition", this.source.getName().replaceAll("\\s+","_"), this.target.getName().replaceAll("\\s+","_"));
				} else {
					str = String.format(str, "OR_Goal-Based_Requirement_Artifact", this.source.getName().replaceAll("\\s+","_"), this.target.getName().replaceAll("\\s+","_"));
				}
				return str;
				
				
			case "istar.DependencyLink":
				if(this.source.getType().equals("istar.Goal") && this.target.getType().equals("istar.Task")) {
					str = String.format(str, "intends_to_operationalize", this.source.getName().replaceAll("\\s+","_"), this.target.getName().replaceAll("\\s+","_"));
				} else if(this.source.getType().equals("istar.Task") && this.target.getType().equals("istar.Task")) {
					str = String.format(str, "Complex_Task_Decomposition", this.source.getName().replaceAll("\\s+","_"), this.target.getName().replaceAll("\\s+","_"));
				} else {
					str = String.format(str, "OR_Goal-Based_Requirement_Artifact", this.source.getName().replaceAll("\\s+","_"), this.target.getName().replaceAll("\\s+","_"));
				}
				return str;
				
			case "istar.QualificationLink":
				str = String.format(str, "OR_Goal-Based_Requirement_Artifact", this.source.getName().replaceAll("\\s+","_"), this.target.getName().replaceAll("\\s+","_"));
				return str;
				
			case "istar.NeededByLink":
				str = String.format(str, "Complex_Task_Decomposition", "Provide_" + this.source.getName().replaceAll("\\s+","_"), this.target.getName().replaceAll("\\s+","_"));
				return str;
				
			case "istar.ContributionLink":
				String elmStr = "\t<ClassAssertion>\n"
						+	"\t\t<Class IRI=\"#%s\"/>\n"
						+		"\t\t<NamedIndividual IRI=\"#%s\"/>\n"
						+ "\t</ClassAssertion>";
				String name = this.source.getName().replaceAll("\\s+","_") + "_to_" + this.target.getName().replaceAll("\\s+","_") + "_contribution";
				elmStr = String.format(elmStr, "Contribution", name);
				elmStr += "\n";
				
				elmStr += String.format(str, "contribution_mediation", name, this.source.getName().replaceAll("\\s+","_"));
				elmStr += "\n";
				elmStr += String.format(str, "contribution_mediation", name, this.target.getName().replaceAll("\\s+","_"));
				
				String elmProp =  "\t<DataPropertyAssertion>\n"
							+		"\t\t<DataProperty IRI=\"#%s\"/>\n"
							+		"\t\t<NamedIndividual IRI=\"#%s\"/>\n"
							+		"\t\t<Literal>%s</Literal>\n"
							+	"\t</DataPropertyAssertion>\n";
				
				if(this.label.equals("make")) {
					elmStr += String.format(elmProp, "type", name, "positive");
					elmStr += String.format(elmProp, "intensity", name, "total");
				} else if(this.label.equals("help")) {
					elmStr += String.format(elmProp, "type", name, "positive");
					elmStr += String.format(elmProp, "intensity", name, "partial");
				} else if(this.label.equals("hurt")) {
					elmStr += String.format(elmProp, "type", name, "negative");
					elmStr += String.format(elmProp, "intensity", name, "partial");
				} else if(this.label.equals("break")) {
					elmStr += String.format(elmProp, "type", name, "negative");
					elmStr += String.format(elmProp, "intensity", name, "total");
				}
				
				return elmStr;
				
			default:
				return "";
			
		}
	}
}
