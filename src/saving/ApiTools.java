package saving;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class ApiTools {
	private static String API_KEY = "aan8yb6sysb2jbbefujccpth";
	private static int year = 2015;
	
//	static void loadTeamProfileToFile(String teamId) throws Exception
//	{
//		URL source = new URL("http://api.sportradar.us/rugby-t1/teams/" + teamId + "/profile.xml?api_key="+API_KEY);
//		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//		DocumentBuilder db = dbf.newDocumentBuilder();
//		saveToFile("profiles", db.parse(source.openStream()), teamId);	
//	}
//	
	private static String format(int num){
		if (num<10) return "0"+num;
		else return "" + num;
	}
	
	static void loadMatchDayToFile(String folder, int year, int month, int day) throws Exception
	{
		URL source = new URL("http://api.sportradar.us/rugby-t1/matches/" + year+"/"+ format(month) + "/" + format(day) + "/boxscore.xml?api_key="+API_KEY);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		saveToFile(folder, "matches", db.parse(source.openStream()), year + "." + month + "." + day);	
	}
	
	private static void saveToFile(String folder, String location,Document d, String id) throws TransformerFactoryConfigurationError, TransformerException, IOException{
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		
		File outputFile = new File(folder+"/" + location + "/" + id + ".xml");
		outputFile.createNewFile();
		Result output = new StreamResult(outputFile);
		Source input = new DOMSource(d);

		transformer.transform(input, output);
	}

}
