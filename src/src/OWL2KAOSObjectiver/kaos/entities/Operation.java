package OWL2KAOSObjectiver.kaos.entities;

public class Operation extends KAOSEntity {
	private int strengthenedIn;
	private int strenghenedSon;

	public Operation(String name, String type) {
		super(name, type);
		this.strengthenedIn = -1;
	}

	public int getStrengthenedIn() {
		return strengthenedIn;
	}

	public void setStrengthenedIn(int strengthenedIn) {
		this.strengthenedIn = strengthenedIn;
	}
	
	public void addOperationalization(int op) {
		return;
	}
	
	public void setStreghtened(int stregthenedSon) {
		this.strenghenedSon = stregthenedSon;
	}
	
	public String toString() {
		String str = super.toString();
		
		str += "strengthenedIn=\"//@rootPackage/@relationships." + this.strengthenedIn + "/@strengthenings." + this.strenghenedSon + "\"/>\n";
		
		return str;
	}

	public boolean isOrphan() {
		if(this.strengthenedIn == -1) return true;
		else return false;
	}
}
