package piStar2OWL.istar;

public class Dependency extends iStarLink {
	private iStarElm dependum;
	private String id;
	
	public Dependency(iStarElm dependum, String source, String target) {
		super(source, target, "istar.Dependency");
		this.dependum = dependum;
	}
	
	public iStarElm getDependum() {
		return this.dependum;
	}
	
	public String getID() {
		return this.id;
	}
	
}
