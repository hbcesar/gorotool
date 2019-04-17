package piStar2OWL.istar;

public class ContributionLink extends iStarLink {
	private String label;

	public ContributionLink(String source, String target, String type, String label) {
		super(source, target, type);
		this.label = label;
	}
	
	public String toString() {
		String elmStr = "\t<ClassAssertion>\n"
				+	"\t\t<Class IRI=\"#%s\"/>\n"
				+		"\t\t<NamedIndividual IRI=\"#%s\"/>\n"
				+ "\t</ClassAssertion>";
		String name = super.source.getName().replaceAll("\\s+","_") + "_to_" + super.target.getName().replaceAll("\\s+","_") + "_contribution";
		elmStr = String.format(elmStr, "Contribution", name);
		elmStr += "\n";
		
		elmStr += String.format(str, "contribution_mediation", name, super.source.getName().replaceAll("\\s+","_"));
		elmStr += "\n";
		elmStr += String.format(str, "contribution_mediation", name, super.target.getName().replaceAll("\\s+","_"));
		
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
	}
}