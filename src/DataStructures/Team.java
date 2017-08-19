package DataStructures;

import java.io.Console;
import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Team {
	private String id;
	private List<Match> matches = new LinkedList<>();
	private List<Player> players = new LinkedList<>();
	private PreviousSeason prevSeason;
	
	public Team(String id){
		this.id = id;
	}
	
	public String getId(){
		return this.id;
	}
	
	public void printTeam(){
		System.out.println("Team id: " + this.id);
		System.out.println("Rank: " + this.prevSeason.rank);
		System.out.println("Players: ");
		for(Player p : this.players){
			System.out.println(p.toString());
		}
		System.out.println("Matches: " + this.matches.size());
		for(Match m : this.matches){
			System.out.println(m.toString());
		}
		System.out.println();
	}
	
	public void addMatch(Match m){
		this.matches.add(m);
	}
	
	public void reorderMatches(){
		Collections.sort(matches);
	}
	
	public void setPreviousSeason(PreviousSeason ps){
		this.prevSeason = ps;
	}
	
	public List<Match> getMatches(){
		return this.matches;
	}
	
	public PreviousSeason getPrevSeason(){
		return this.prevSeason;
	}
	
	public int getMatchCountOfMatch(Date d) throws Exception{
		int count = 0;
		for(Match m : this.matches){
			if(d.compareTo(m.date) == 0){
				return count;
			}
			count ++;	
		}
		throw new Exception("No Match on date " + d.toString());
	}
	
}
