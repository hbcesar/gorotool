package piStar2OWL.istar;

import java.util.ArrayList;

public class Actor {
	private String id;
	private String name;
	private String type;
	private ArrayList<iStarElm> nodes = new ArrayList<iStarElm>();
	
	public Actor(String type, String id, String name) {
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public void addElement(iStarElm el) {
		nodes.add(el);
	}
	
	public ArrayList<iStarElm> getNodes() {
		return this.nodes;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getType() {
		return this.type;
	}
}
