package NNInput;

import java.util.Arrays;
import java.util.List;

import DataStructures.Date;
import DataStructures.League;
import DataStructures.LeagueBuilder;
import DataStructures.Match;
import DataStructures.PreviousSeason;
import DataStructures.Score;
import DataStructures.Team;
import neuralNets.InputDataMethods;

public class RecentFormNNInput extends InputNNBase{
	League league;
	
	public RecentFormNNInput(String folder) throws Exception{
		input = new double[totalMatchCount][20];
		output = new double[totalMatchCount][1];
		
		this.setUpData(folder);
		this.inefficientNormalise();
		
	}
	public double [][] getInputEntire(){
		int count = 0;
		for(Team t : league.getTeams()){
			PreviousSeason ps = t.getPrevSeason();
			int matchCount = 0;
			
			for(Match m : t.getMatches()){
				Team oppo = league.getTeamForId(m.againstId);
				PreviousSeason ops = oppo.getPrevSeason();
				if(ps.rank == 12 || ops.rank == 12){
					continue;
				}
															
				this.input[count] = this.getInputFor(t.getMatches(), oppo.getMatches(), matchCount);
				this.output[count] = this.getOutputFor(m.fullTimeScore);
				
				count ++;
				matchCount ++;
			}
		}

	}
	private void setUpData(String folder) throws Exception{
		League league = LeagueBuilder.buildEnglishLeague(folder);
		
		int count = 0;
		for(Team t : league.getTeams()){
			PreviousSeason ps = t.getPrevSeason();
			int matchCount = 0;
			
			for(Match m : t.getMatches()){
				Team oppo = league.getTeamForId(m.againstId);
				PreviousSeason ops = oppo.getPrevSeason();
				if(ps.rank == 12 || ops.rank == 12){
					continue;
				}
															
				this.input[count] = this.getInputFor(t.getMatches(), oppo.getMatches(), matchCount);
				this.output[count] = this.getOutputFor(m.fullTimeScore);
				
				count ++;
				matchCount ++;
			}
		}
	}
	
	public double[] getInputFor(List<Match> ms, List<Match> oms, int matchCount) throws Exception {
		Match m = ms.get(matchCount);
		Team opp = league.getTeamForId(m.againstId);
		double[] teamPrevs = this.getPrevMatches(ms, matchCount);
		double[] oppPrevs = this.getPrevMatches(oms, opp.getMatchCountOfMatch(m.date));
		return InputDataMethods.concat(teamPrevs, oppPrevs); 
	} 
	
	private double[] getPrevMatches(List<Match> matches, int matchCount) {
		double[] retArray = new double[10];
		for(int i = 1; i <= 5; i ++){
			if(i <= matchCount){
				Match m = matches.get(matchCount-i);
				retArray[i*2-1] = m.fullTimeScore.score_for-m.fullTimeScore.score_against;
				retArray[i*2] = league.getTeamForId(m.againstId).getPrevSeason().rank;
			}
		}
		return retArray;
	}
	
	private void addOppoPrevMatches(List<Match> matches, int count, int matchCount, Date date) {
		for(int i = 1; i <= 5; i ++) {
			if(i <= matchCount) {
				Match m = matches.get(matchCount-i);
				input[count][10+i*2] = m.fullTimeScore.score_for-m.fullTimeScore.score_against;
				input[count][11+i*2] = m.fullTimeScore.score_for-m.fullTimeScore.score_against;
			}
		}
	}	
}
