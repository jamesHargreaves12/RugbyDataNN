package DataStructures;

import org.w3c.dom.Element;

public class PreviousSeason {
	public int rank;
	public int won;
	public int drawn;
	public int lost;
	public int points_for;
	public int points_against;
	public int points_difference;
	public int tries_for;
	public int tries_against;
	public int tries_bonus;
	public int losing_bonus;
	public int points;
	
	public PreviousSeason(Element team){
		rank = Integer.parseInt(team.getAttribute("rank"));
		won = Integer.parseInt(team.getAttribute("Won"));
		drawn = Integer.parseInt(team.getAttribute("Drawn"));
		lost = Integer.parseInt(team.getAttribute("Lost"));
		points_for = Integer.parseInt(team.getAttribute("Points_for"));
		points_against = Integer.parseInt(team.getAttribute("Points_against"));
		points_difference = Integer.parseInt(team.getAttribute("Points_difference"));
		tries_for = Integer.parseInt(team.getAttribute("Tries_for"));
		tries_against = Integer.parseInt(team.getAttribute("Tries_against"));
		tries_bonus = Integer.parseInt(team.getAttribute("Tries_bonus"));
		losing_bonus = Integer.parseInt(team.getAttribute("Losing_bonus"));
		points = Integer.parseInt(team.getAttribute("Points"));
	}
	
}
