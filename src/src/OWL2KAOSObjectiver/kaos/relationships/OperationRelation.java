package OWL2KAOSObjectiver.kaos.relationships;

import java.util.ArrayList;

public class OperationRelation extends KAOSRelationship {
	private int parent;
	private ArrayList<Integer> strengthenings = new ArrayList<>();
	
	public OperationRelation(String type) {
		super(type);
	}
	
	public void setParent(int parent) {
		this.parent = parent;
	}
	
	public void addStrengthenings(int strengthening) {
		this.strengthenings.add(strengthening);
	}
	
	public int getParent() {
		return this.parent;
	}
	
	public ArrayList<Integer> getStrengthenings(){
		return this.strengthenings;
	}
	
	public String toString() {
		String str = "<relationships xsi:type=\"objectiver.model:%s\" parent=\"//@rootPackage/@entities.%s\">\n";
		
		str = String.format(str, super.getType(), this.getParent());
		
		for(int i = 0; i < strengthenings.size(); i++) {
			str += "\t<strengthenings operation=\"//@rootPackage/@entities." + strengthenings.get(i) + "\"/>\n";
		}
		
		str += "</relationships>\n";
		
		return str;
	}
}
