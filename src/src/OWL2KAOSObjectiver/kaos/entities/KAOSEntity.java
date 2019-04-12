package OWL2KAOSObjectiver.kaos.entities;

import java.util.ArrayList;

public class KAOSEntity {
	private String type;
	private String name;
	
	public KAOSEntity(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String toString() {
		String str = "";
		
		String s = "<entities xsi:type=\"objectiver.model:%s\" name=\"%s\" ";
		
		str = String.format(s, type, name);
		
		return str;
	}
}
