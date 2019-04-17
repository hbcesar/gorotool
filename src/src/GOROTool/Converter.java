package GOROTool;

import java.util.Scanner;

import OWL.OWLReader;
import OWL2KAOSObjectiver.OWL2KAOS;
import piStar2OWL.iStarConverter;

public class Converter {

	public static void main(String[] args) {
		//Converters instantiation
		iStarConverter istar = new iStarConverter();
		OWLReader owl = new OWLReader();
		OWL2KAOS kaos = new OWL2KAOS(owl);
		
		System.out.println("Please, enter the piStar model file path:");
		Scanner scanner = new Scanner(System.in);
		
		//Reads the piStar model
		istar.read(scanner.nextLine());
		
		//Converts the piStar model to the OWL file
		istar.extract2OWL("converted_model.owl");
		
		//reads the owl model
		owl.read("converted_model.owl");
		
		//converts the owl to kaos (objectiver)
		kaos.convert2Objectiver("kaos_converted.xmi");
		
		scanner.close();
		System.out.println("[INFO] Convertion completed. Some elements were ignored. Please check the log file (GCTlog.txt) for more details.");
	}
}
