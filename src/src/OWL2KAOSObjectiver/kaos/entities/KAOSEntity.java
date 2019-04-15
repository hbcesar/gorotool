package OWL2KAOSObjectiver.kaos.entities;

public abstract class KAOSEntity {
	private String type;
	private String name;
	
	public KAOSEntity(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return this.name;
	}
	
	public abstract void addOperationalization(int op);
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String toString() {
		String str = "";
		
		String s = "\t\t<entities xsi:type=\"objectiver.model:%s\" name=\"%s\" ";
		
		str = String.format(s, type, name);
		
		return str;
	}
}
