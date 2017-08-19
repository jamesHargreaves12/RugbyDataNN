package testProdject;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class ApiDataService {
	private static String API_KEY = "aan8yb6sysb2jbbefujccpth";
	
	public static Document loadTeamProfile(String teamId) throws Exception
	{
		URL source = new URL("http://api.sportradar.us/rugby-t1/teams/" + teamId + "/profile.xml?api_key="+API_KEY);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		return db.parse(source.openStream());	
	}

}
