package DataStructures;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import teamsBuilder.ApiFiles;

public class League {
	private List<Team> teamList = new LinkedList<>();
	private String id;
	private String folder;
	
	public League(String folder, String id){
		this.id = id;
		this.folder = folder;
	}
	
	private void addMatch(Element item, Date date) {
		NodeList homeList = item.getElementsByTagName("home");
		Element home = (Element )homeList.item(0);
		String idHome = home.getAttribute("id");
		int fullTimeScoreHome = Integer.parseInt(home.getAttribute("score"));
		
		NodeList awayList = item.getElementsByTagName("away");
		Element away = (Element )awayList.item(0);
		String idAway = away.getAttribute("id");
		int fullTimeScoreAway = Integer.parseInt(away.getAttribute("score"));
		
		Team homeTeam = getTeamForId(idHome);
		Team awayTeam = getTeamForId(idAway);
		homeTeam.addMatch(new Match(true, idAway, new Score(fullTimeScoreHome, fullTimeScoreAway),date));
		awayTeam.addMatch(new Match(false, idHome, new Score(fullTimeScoreAway, fullTimeScoreHome),date));
	}
	
	public Team getTeamForId(String id){
		for(Team t : teamList){
			if(t.getId().equals(id))
				return t;
		}
		Team t = new Team(id);
		this.teamList.add(t);
		return t;
	}
	
	public void printLeague(){
		System.out.println("League: ");
		for(Team t : teamList){
			t.printTeam();
		}
	}
	
	public void addMatchesForLeagueFrom(File f) throws Exception{
		Document doc = ApiFiles.loadDoc(f);
		NodeList nList = doc.getElementsByTagName("match");
		for (int i = 0; i < nList.getLength(); i++) {
			Element match = (Element) nList.item(i);
			Element tourn = (Element) match.getElementsByTagName("tournament").item(0);
			Element home = (Element) match.getElementsByTagName("home").item(0);
			if(tourn.getAttribute("id").equals(this.id)){
				if(home.getElementsByTagName("scoring").getLength() > 0){//this is how we account for postponed matches
					this.addMatch(match,new Date(f.getName()));
				}
			}
		}
	}
	
	public void getTeamsToOrderMatches(){
		for(Team t : teamList){
			t.reorderMatches();
		}
	}
	
	public void addPreviousSeasontoTeamsFromFile(File f) throws Exception{
		Document doc = ApiFiles.loadDoc(f);
		NodeList nList = doc.getElementsByTagName("team");
		for (int i = 0; i < nList.getLength(); i++) {
			Element team = (Element) nList.item(i);
			Team teamObj = this.getTeamForId(team.getAttribute("id"));
			PreviousSeason ps = new PreviousSeason(team);
			teamObj.setPreviousSeason(ps);
		}
	}
	
	public List<Team> getTeams() {
		return this.teamList;
	}
}

