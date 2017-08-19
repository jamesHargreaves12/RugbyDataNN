package DataStructures;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Player {
	public String id;
	public String firstName;
	public String lastName;
	public Integer height = 0; //cm
	public Integer weight = 0; // kg
	public String countryCode = "";
	public String DOB = "";
	
	public Player(String id, String first_name, String last_name){
		this.id = id;
		this.firstName = first_name;
		this.lastName = last_name;
	}
	
	public Player(Node item){
		Element eElement = (Element) item;
		this.id = eElement.getAttribute("id");
		this.firstName = eElement.getAttribute("first_name");
		this.lastName = eElement.getAttribute("last_name");
		if(eElement.hasAttribute("country_code"))this.countryCode = eElement.getAttribute("country_code");
		if(eElement.hasAttribute("birth_date"))this.DOB = eElement.getAttribute("birth_date");
		if(eElement.hasAttribute("weight_kg"))this.weight = Integer.parseInt(eElement.getAttribute("weight_kg"));
		if(eElement.hasAttribute("height_cm"))this.height = Integer.parseInt(eElement.getAttribute("height_cm"));
	}
	
	@Override
	public String toString(){
		String str = "{name: " + this.firstName + " " + this.lastName;
		if(this.DOB.length()>0) str += ", DOB: " + this.DOB;
		if(this.height>0)str += ", height: " + this.height;
		if(this.weight>0)str += ", weight: " + this.weight;
		if(this.countryCode.length() > 0)str += ", country: "+ this.countryCode;
		return str;
	}
}
