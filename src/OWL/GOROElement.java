package OWL;

public class GOROElement {
	private String name;
	private String type;
	private boolean isOrphan;

	public GOROElement(String name, String type) {
		this.name = name;
		this.type = type;
		this.isOrphan = true;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}
	
	public void setOrphan(boolean b) {
		this.isOrphan = b;
	}
	
	public boolean isOrphan() {
		return this.isOrphan;
	}
}
