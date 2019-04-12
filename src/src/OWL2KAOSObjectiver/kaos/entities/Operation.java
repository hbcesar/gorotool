package OWL2KAOSObjectiver.kaos.entities;

public class Operation extends KAOSEntity {
	private int strengthenedIn;

	public Operation(String name, String type) {
		super(name, type);
	}

	public int getStrengthenedIn() {
		return strengthenedIn;
	}

	public void setStrengthenedIn(int strengthenedIn) {
		this.strengthenedIn = strengthenedIn;
	}
	
	public String toString() {
		String str = super.toString();
		
		str += "strengthenedIn=\"//@rootPackage/@relationships.0/@strengthenings." + this.getStrengthenedIn() + "\"/>\n";
		
		return str;
	}
}
