package DataStructures;

import java.io.File;

public class LeagueBuilder {
	public static League buildEnglishLeague(String year) throws Exception{
		League premiership = new League(year, "6db01793-299d-4283-930f-35c2f796fee4");
		
		File folder = new File(year + "/matches");
		for(File date : folder.listFiles()){
			if(!date.getName().equals(".DS_Store")){
				premiership.addMatchesForLeagueFrom(date);
			}
		}
		
		premiership.getTeamsToOrderMatches();
		premiership.addPreviousSeasontoTeamsFromFile(new File(year + "/standings/leagueTable.xml"));
		return premiership;
	}
}
