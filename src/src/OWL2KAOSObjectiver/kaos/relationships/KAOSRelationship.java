package OWL2KAOSObjectiver.kaos.relationships;

import java.util.ArrayList;

public class KAOSRelationship {
	private String type;
	
	public KAOSRelationship(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}

	public String toString() {
		String str = "<relationships xsi:type=\"objectiver.model:%s\" ";
		
		str = String.format(str, this.type);

		return str;
	}
}
