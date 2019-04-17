package piStar2OWL.istar;

public class QualificationLink extends iStarLink {

	public QualificationLink(String source, String target, String type) {
		super(source, target, type);
	}
	
	public String toString() {
		str = String.format(str, "OR_Goal-Based_Requirement_Artifact", super.source.getName().replaceAll("\\s+","_"), super.target.getName().replaceAll("\\s+","_"));
		return str;
	}
}