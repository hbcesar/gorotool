package GOROTool;

import java.util.Scanner;

import OWL.OWLReader;
import OWL2KAOSObjectiver.OWL2KAOS;
import piStar2OWL.iStarConverter;

public class Converter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		iStarConverter istar = new iStarConverter();
		OWLReader owl = new OWLReader();
		OWL2KAOS kaos = new OWL2KAOS(owl);
		
		System.out.println("Please, enter the piStar model file path:");
		Scanner scanner = new Scanner(System.in);
		
		istar.read(scanner.nextLine());
		scanner.close();

		istar.extract2OWL("converted_model.owl");
		
		owl.read("converted_model.owl");
		kaos.convert2Objectiver("kaos_converted.xmi");
		
		System.out.println("[INFO] Convertion completed. Some elements were ignored. Please check the log file (GCTlog.txt) for more details.");
	}
}
