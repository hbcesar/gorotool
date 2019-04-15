package piStar2OWL;

public class OWLFile {
	private static String owlHead = "<?xml version=\"1.0\"?>\r\n" + 
			"<Ontology xmlns=\"http://www.w3.org/2002/07/owl#\"\r\n" + 
			"     xml:base=\"http://www.semanticweb.org/NEMO/ontologies/2019/2/GORO\"\r\n" + 
			"     xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\r\n" + 
			"     xmlns:xml=\"http://www.w3.org/XML/1998/namespace\"\r\n" + 
			"     xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\"\r\n" + 
			"     xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"\r\n" + 
			"     ontologyIRI=\"http://www.semanticweb.org/césar/ontologies/2019/2/GORO\">\r\n" + 
			"    <Prefix name=\"\" IRI=\"http://www.semanticweb.org/césar/ontologies/2019/2/GORO\"/>\r\n" + 
			"    <Prefix name=\"owl\" IRI=\"http://www.w3.org/2002/07/owl#\"/>\r\n" + 
			"    <Prefix name=\"rdf\" IRI=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"/>\r\n" + 
			"    <Prefix name=\"xml\" IRI=\"http://www.w3.org/XML/1998/namespace\"/>\r\n" + 
			"    <Prefix name=\"xsd\" IRI=\"http://www.w3.org/2001/XMLSchema#\"/>\r\n" + 
			"    <Prefix name=\"rdfs\" IRI=\"http://www.w3.org/2000/01/rdf-schema#\"/>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <Class IRI=\"#Assumption_Artifact\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <Class IRI=\"#Contribution\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <Class IRI=\"#Functional_Requirement\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <Class IRI=\"#Functional_Requirement_Hardgoal\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <Class IRI=\"#Functional_Requirement_Softgoal\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <Class IRI=\"#GBRA_Contribution\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <Class IRI=\"#Goal-Based_Requirement_Artifact\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <Class IRI=\"#Hardgoal\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <Class IRI=\"#Non-functional_Requirement\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <Class IRI=\"#Non-functional_Requirement_Hardgoal\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <Class IRI=\"#Non-functional_Requirement_Softgoal\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <Class IRI=\"#Obstacle\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <Class IRI=\"#Requirements_Stakeholder\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <Class IRI=\"#Softgoal\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <Class IRI=\"#Task\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <Class IRI=\"#Resource\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <Class IRI=\"#Task_Contribution\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <ObjectProperty IRI=\"#AND_Goal-Based_Requirement_Artifact\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <ObjectProperty IRI=\"#Complex_Task_Decomposition\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <ObjectProperty IRI=\"#OR_Goal-Based_Requirement_Artifact\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <ObjectProperty IRI=\"#conflicts_with\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <ObjectProperty IRI=\"#contingency\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <ObjectProperty IRI=\"#contribution_mediation\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <ObjectProperty IRI=\"#intends_to_operationalize\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <ObjectProperty IRI=\"#interest\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <ObjectProperty IRI=\"#mitigation\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <ObjectProperty IRI=\"#obstruction\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <DataProperty IRI=\"#intensity\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <DataProperty IRI=\"#type\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"	 <Declaration>\r\n" + 
			"        <ObjectProperty IRI=\"#produces\"/>\r\n" + 
			"    </Declaration>\r\n" + 
			"    <Declaration>\r\n" + 
			"        <ObjectProperty IRI=\"#requires\"/>\r\n" + 
			"    </Declaration>\r\n" +
			"    <SubClassOf>\r\n" + 
			"        <Class IRI=\"#Functional_Requirement\"/>\r\n" + 
			"        <Class IRI=\"#Goal-Based_Requirement_Artifact\"/>\r\n" + 
			"    </SubClassOf>\r\n" + 
			"    <SubClassOf>\r\n" + 
			"        <Class IRI=\"#Functional_Requirement_Hardgoal\"/>\r\n" + 
			"        <Class IRI=\"#Functional_Requirement\"/>\r\n" + 
			"    </SubClassOf>\r\n" + 
			"    <SubClassOf>\r\n" + 
			"        <Class IRI=\"#Functional_Requirement_Hardgoal\"/>\r\n" + 
			"        <Class IRI=\"#Hardgoal\"/>\r\n" + 
			"    </SubClassOf>\r\n" + 
			"    <SubClassOf>\r\n" + 
			"        <Class IRI=\"#Functional_Requirement_Softgoal\"/>\r\n" + 
			"        <Class IRI=\"#Functional_Requirement\"/>\r\n" + 
			"    </SubClassOf>\r\n" + 
			"    <SubClassOf>\r\n" + 
			"        <Class IRI=\"#Functional_Requirement_Softgoal\"/>\r\n" + 
			"        <Class IRI=\"#Softgoal\"/>\r\n" + 
			"    </SubClassOf>\r\n" + 
			"    <SubClassOf>\r\n" + 
			"        <Class IRI=\"#GBRA_Contribution\"/>\r\n" + 
			"        <Class IRI=\"#Contribution\"/>\r\n" + 
			"    </SubClassOf>\r\n" + 
			"    <SubClassOf>\r\n" + 
			"        <Class IRI=\"#Hardgoal\"/>\r\n" + 
			"        <Class IRI=\"#Goal-Based_Requirement_Artifact\"/>\r\n" + 
			"    </SubClassOf>\r\n" + 
			"    <SubClassOf>\r\n" + 
			"        <Class IRI=\"#Non-functional_Requirement\"/>\r\n" + 
			"        <Class IRI=\"#Goal-Based_Requirement_Artifact\"/>\r\n" + 
			"    </SubClassOf>\r\n" + 
			"    <SubClassOf>\r\n" + 
			"        <Class IRI=\"#Non-functional_Requirement_Hardgoal\"/>\r\n" + 
			"        <Class IRI=\"#Hardgoal\"/>\r\n" + 
			"    </SubClassOf>\r\n" + 
			"    <SubClassOf>\r\n" + 
			"        <Class IRI=\"#Non-functional_Requirement_Hardgoal\"/>\r\n" + 
			"        <Class IRI=\"#Non-functional_Requirement\"/>\r\n" + 
			"    </SubClassOf>\r\n" + 
			"    <SubClassOf>\r\n" + 
			"        <Class IRI=\"#Non-functional_Requirement_Softgoal\"/>\r\n" + 
			"        <Class IRI=\"#Non-functional_Requirement\"/>\r\n" + 
			"    </SubClassOf>\r\n" + 
			"    <SubClassOf>\r\n" + 
			"        <Class IRI=\"#Non-functional_Requirement_Softgoal\"/>\r\n" + 
			"        <Class IRI=\"#Softgoal\"/>\r\n" + 
			"    </SubClassOf>\r\n" + 
			"    <SubClassOf>\r\n" + 
			"        <Class IRI=\"#Softgoal\"/>\r\n" + 
			"        <Class IRI=\"#Goal-Based_Requirement_Artifact\"/>\r\n" + 
			"    </SubClassOf>\r\n" + 
			"    <SubClassOf>\r\n" + 
			"        <Class IRI=\"#Task_Contribution\"/>\r\n" + 
			"        <Class IRI=\"#Contribution\"/>\r\n" + 
			"    </SubClassOf>\r\n" + 
			"    <SubObjectPropertyOf>\r\n" + 
			"        <ObjectProperty IRI=\"#OR_Goal-Based_Requirement_Artifact\"/>\r\n" + 
			"        <ObjectProperty abbreviatedIRI=\"owl:topObjectProperty\"/>\r\n" + 
			"    </SubObjectPropertyOf>\r\n" + 
			"    <SubObjectPropertyOf>\r\n" + 
			"        <ObjectProperty IRI=\"#obstruction\"/>\r\n" + 
			"        <ObjectProperty abbreviatedIRI=\"owl:topObjectProperty\"/>\r\n" + 
			"    </SubObjectPropertyOf>\r\n" + 
			"    <ObjectPropertyDomain>\r\n" + 
			"        <ObjectProperty IRI=\"#AND_Goal-Based_Requirement_Artifact\"/>\r\n" + 
			"        <Class IRI=\"#Goal-Based_Requirement_Artifact\"/>\r\n" + 
			"    </ObjectPropertyDomain>\r\n" + 
			"    <ObjectPropertyDomain>\r\n" + 
			"        <ObjectProperty IRI=\"#Complex_Task_Decomposition\"/>\r\n" + 
			"        <Class IRI=\"#Task\"/>\r\n" + 
			"    </ObjectPropertyDomain>\r\n" + 
			"    <ObjectPropertyDomain>\r\n" + 
			"        <ObjectProperty IRI=\"#OR_Goal-Based_Requirement_Artifact\"/>\r\n" + 
			"        <Class IRI=\"#Goal-Based_Requirement_Artifact\"/>\r\n" + 
			"    </ObjectPropertyDomain>\r\n" + 
			"    <ObjectPropertyDomain>\r\n" + 
			"        <ObjectProperty IRI=\"#conflicts_with\"/>\r\n" + 
			"        <Class IRI=\"#Goal-Based_Requirement_Artifact\"/>\r\n" + 
			"    </ObjectPropertyDomain>\r\n" + 
			"    <ObjectPropertyDomain>\r\n" + 
			"        <ObjectProperty IRI=\"#contingency\"/>\r\n" + 
			"        <Class IRI=\"#Task\"/>\r\n" + 
			"    </ObjectPropertyDomain>\r\n" + 
			"    <ObjectPropertyDomain>\r\n" + 
			"        <ObjectProperty IRI=\"#contribution_mediation\"/>\r\n" + 
			"        <Class IRI=\"#Contribution\"/>\r\n" + 
			"    </ObjectPropertyDomain>\r\n" + 
			"    <ObjectPropertyDomain>\r\n" + 
			"        <ObjectProperty IRI=\"#intends_to_operationalize\"/>\r\n" + 
			"        <Class IRI=\"#Task\"/>\r\n" + 
			"    </ObjectPropertyDomain>\r\n" + 
			"    <ObjectPropertyDomain>\r\n" + 
			"        <ObjectProperty IRI=\"#interest\"/>\r\n" + 
			"        <Class IRI=\"#Requirements_Stakeholder\"/>\r\n" + 
			"    </ObjectPropertyDomain>\r\n" + 
			"    <ObjectPropertyDomain>\r\n" + 
			"        <ObjectProperty IRI=\"#mitigation\"/>\r\n" + 
			"        <Class IRI=\"#Task\"/>\r\n" + 
			"    </ObjectPropertyDomain>\r\n" + 
			"    <ObjectPropertyDomain>\r\n" + 
			"        <ObjectProperty IRI=\"#obstruction\"/>\r\n" + 
			"        <Class IRI=\"#Obstacle\"/>\r\n" + 
			"    </ObjectPropertyDomain>\r\n" + 
			"	 <ObjectPropertyDomain>\r\n" + 
			"        <ObjectProperty IRI=\"#produces\"/>\r\n" + 
			"        <Class IRI=\"#Task\"/>\r\n" + 
			"    </ObjectPropertyDomain>\r\n" + 
			"    <ObjectPropertyDomain>\r\n" + 
			"        <ObjectProperty IRI=\"#requires\"/>\r\n" + 
			"        <Class IRI=\"#Task\"/>\r\n" + 
			"    </ObjectPropertyDomain>" +
			"    <ObjectPropertyRange>\r\n" + 
			"        <ObjectProperty IRI=\"#AND_Goal-Based_Requirement_Artifact\"/>\r\n" + 
			"        <Class IRI=\"#Goal-Based_Requirement_Artifact\"/>\r\n" + 
			"    </ObjectPropertyRange>\r\n" + 
			"    <ObjectPropertyRange>\r\n" + 
			"        <ObjectProperty IRI=\"#Complex_Task_Decomposition\"/>\r\n" + 
			"        <Class IRI=\"#Task\"/>\r\n" + 
			"    </ObjectPropertyRange>\r\n" + 
			"    <ObjectPropertyRange>\r\n" + 
			"        <ObjectProperty IRI=\"#OR_Goal-Based_Requirement_Artifact\"/>\r\n" + 
			"        <Class IRI=\"#Goal-Based_Requirement_Artifact\"/>\r\n" + 
			"    </ObjectPropertyRange>\r\n" + 
			"    <ObjectPropertyRange>\r\n" + 
			"        <ObjectProperty IRI=\"#conflicts_with\"/>\r\n" + 
			"        <Class IRI=\"#Goal-Based_Requirement_Artifact\"/>\r\n" + 
			"    </ObjectPropertyRange>\r\n" + 
			"    <ObjectPropertyRange>\r\n" + 
			"        <ObjectProperty IRI=\"#contingency\"/>\r\n" + 
			"        <Class IRI=\"#Obstacle\"/>\r\n" + 
			"    </ObjectPropertyRange>\r\n" + 
			"    <ObjectPropertyRange>\r\n" + 
			"        <ObjectProperty IRI=\"#contribution_mediation\"/>\r\n" + 
			"        <Class IRI=\"#Non-functional_Requirement\"/>\r\n" + 
			"    </ObjectPropertyRange>\r\n" + 
			"    <ObjectPropertyRange>\r\n" + 
			"        <ObjectProperty IRI=\"#intends_to_operationalize\"/>\r\n" + 
			"        <Class IRI=\"#Goal-Based_Requirement_Artifact\"/>\r\n" + 
			"    </ObjectPropertyRange>\r\n" + 
			"    <ObjectPropertyRange>\r\n" + 
			"        <ObjectProperty IRI=\"#interest\"/>\r\n" + 
			"        <Class IRI=\"#Assumption_Artifact\"/>\r\n" + 
			"    </ObjectPropertyRange>\r\n" + 
			"    <ObjectPropertyRange>\r\n" + 
			"        <ObjectProperty IRI=\"#interest\"/>\r\n" + 
			"        <Class IRI=\"#Goal-Based_Requirement_Artifact\"/>\r\n" + 
			"    </ObjectPropertyRange>\r\n" + 
			"    <ObjectPropertyRange>\r\n" + 
			"        <ObjectProperty IRI=\"#interest\"/>\r\n" + 
			"        <Class IRI=\"#Obstacle\"/>\r\n" + 
			"    </ObjectPropertyRange>\r\n" + 
			"    <ObjectPropertyRange>\r\n" + 
			"        <ObjectProperty IRI=\"#interest\"/>\r\n" + 
			"        <Class IRI=\"#Requirements_Stakeholder\"/>\r\n" + 
			"    </ObjectPropertyRange>\r\n" + 
			"    <ObjectPropertyRange>\r\n" + 
			"        <ObjectProperty IRI=\"#mitigation\"/>\r\n" + 
			"        <Class IRI=\"#Obstacle\"/>\r\n" + 
			"    </ObjectPropertyRange>\r\n" + 
			"    <ObjectPropertyRange>\r\n" + 
			"        <ObjectProperty IRI=\"#obstruction\"/>\r\n" + 
			"        <Class IRI=\"#Goal-Based_Requirement_Artifact\"/>\r\n" + 
			"    </ObjectPropertyRange>\r\n" + 
			"    <ObjectPropertyRange>\r\n" + 
			"        <ObjectProperty IRI=\"#produces\"/>\r\n" + 
			"        <Class IRI=\"#Resource\"/>\r\n" + 
			"    </ObjectPropertyRange>\r\n" + 
			"    <ObjectPropertyRange>\r\n" + 
			"        <ObjectProperty IRI=\"#requires\"/>\r\n" + 
			"        <Class IRI=\"#Resource\"/>\r\n" + 
			"    </ObjectPropertyRange>\r\n" +
			"    <SubDataPropertyOf>\r\n" + 
			"        <DataProperty IRI=\"#intensity\"/>\r\n" + 
			"        <DataProperty abbreviatedIRI=\"owl:topDataProperty\"/>\r\n" + 
			"    </SubDataPropertyOf>\r\n" + 
			"    <DataPropertyDomain>\r\n" + 
			"        <DataProperty IRI=\"#intensity\"/>\r\n" + 
			"        <Class IRI=\"#Contribution\"/>\r\n" + 
			"    </DataPropertyDomain>\r\n" + 
			"    <DataPropertyDomain>\r\n" + 
			"        <DataProperty IRI=\"#type\"/>\r\n" + 
			"        <Class IRI=\"#Contribution\"/>\r\n" + 
			"    </DataPropertyDomain>\n";
	
	public static String getOWLHead() {
		return owlHead;
	}
	
}
