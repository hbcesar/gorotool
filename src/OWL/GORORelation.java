package OWL;

public class GORORelation {
	private String type;
	private String source;
	private String target;
	
	public GORORelation(String type, String source, String target) {
		this.type = type;
		this.source = source;
		this.target = target;
	}

	public String getType() {
		return type;
	}

	public String getSource() {
		return source;
	}

	public String getTarget() {
		return target;
	}
}
