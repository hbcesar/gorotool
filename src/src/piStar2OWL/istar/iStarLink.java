package piStar2OWL.istar;

public abstract class iStarLink {
	public iStarElm source;
	public String sourceID;
	public iStarElm target;
	public String targetID;
	public String type;
	public String label;
	public String str = "\t<ObjectPropertyAssertion>\n"
			+		"\t\t<ObjectProperty IRI=\"#%s\"/>\n"
			+		"\t\t<NamedIndividual IRI=\"#%s\"/>\n"
			+		"\t\t<NamedIndividual IRI=\"#%s\"/>\n"
			+ 	"\t</ObjectPropertyAssertion>";
	
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
	
	public String getType() {
		return this.type;
	}
	
	public abstract String toString();
}
