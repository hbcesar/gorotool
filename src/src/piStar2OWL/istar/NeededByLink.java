package piStar2OWL.istar;

public class NeededByLink extends iStarLink {

	public NeededByLink(String source, String target, String type) {
		super(source, target, type);
	}
	
	public String toString() {
		str = String.format(str, "requires", super.source.getName().replaceAll("\\s+","_"), super.target.getName().replaceAll("\\s+","_"));
		return str;
	}
}