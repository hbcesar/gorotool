package OWL2KAOSObjectiver;

public class KAOSFile {
	private static String XMIHead = "<?xml version=\"1.0\" encoding=\"ASCII\"?>\r\n" + 
			"<objectiver.model:KModel xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:objectiver.model=\"http://kaos\">\r\n" + 
			"  \t<rootPackage >\n";
	
	private static String XMIFoot = "  \t</rootPackage>\r\n" + 
			"</objectiver.model:KModel>\n";
	
	public static String getXMIHead() {
		return XMIHead;
	}
	
	public static String getXMIFoot() {
		return XMIFoot;
	}
}
