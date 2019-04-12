package OWL;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class OWLReader {
	private ArrayList<GOROElement> elements = new ArrayList<GOROElement>();
	private ArrayList<GORORelation> links = new ArrayList<GORORelation>();
	
	
	public void read(String filePath) {
		File xmlFile = new File(filePath);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("ClassAssertion");
			
			for (int i = 0; i < nList.getLength(); i++) {

				Node nNode = nList.item(i);
						
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					
					Element eNamedIndividual = (Element) eElement.getElementsByTagName("NamedIndividual").item(0);
					String individual_iri = eNamedIndividual.getAttribute("IRI").replace("#", "");
										
					Element eClass = (Element) eElement.getElementsByTagName("Class").item(0);
					String class_iri = eClass.getAttribute("IRI").replace("#", "");
					
					GOROElement g = new GOROElement(individual_iri, class_iri);
					
					this.elements.add(g);
				}
			}
			
			NodeList linkList = doc.getElementsByTagName("ObjectPropertyAssertion");
			
			for (int i = 0; i < linkList.getLength(); i++) {

				Node nNode = linkList.item(i);
						
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					
					Element eNamedIndividual = (Element) eElement.getElementsByTagName("NamedIndividual").item(0);
					String target_iri = eNamedIndividual.getAttribute("IRI").replace("#", "");
					eNamedIndividual = (Element) eElement.getElementsByTagName("NamedIndividual").item(1);
					String source_iri = eNamedIndividual.getAttribute("IRI").replace("#", "");
					
					Element eClass = (Element) eElement.getElementsByTagName("ObjectProperty").item(0);
					String class_iri = eClass.getAttribute("IRI").replace("#", "");
					
					GORORelation g = new GORORelation(class_iri, source_iri, target_iri);
					
//					System.out.println("Relation: " + class_iri + "\nSource: " + source_iri + "\nTarget: " + target_iri);
					
					this.links.add(g);
				}
			}
			
			System.out.println("[INFO] OWL File successfully loaded.");
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int lookupByName(String name) {
		for(int i = 0; i < elements.size(); i++) {
			if(this.elements.get(i).getName().equals(name)) {
				return i;
			}
		}
		
		return -1;
	}

	public ArrayList<GOROElement> getElements() {
		return elements;
	}

	public ArrayList<GORORelation> getLinks() {
		return links;
	}
}
