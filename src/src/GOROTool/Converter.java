package GOROTool;

import OWL.OWLReader;
import OWL2KAOSObjectiver.OWL2KAOS;
import piStar2OWL.iStarConverter;

public class Converter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		iStarConverter istar = new iStarConverter();
		OWLReader owl = new OWLReader();
		OWL2KAOS kaos = new OWL2KAOS(owl);
		
//		istar.read("iStar Model.txt");
		istar.read("task.txt");
		istar.extract2OWL("converted_model.owl");
		
		owl.read("converted_model.owl");
		kaos.convert2Objectiver("kaos.xmi");
		
		
	}

}
