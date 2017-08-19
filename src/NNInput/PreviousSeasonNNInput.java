package NNInput;

import java.util.Arrays;
import java.util.List;

import DataStructures.League;
import DataStructures.LeagueBuilder;
import DataStructures.Match;
import DataStructures.PreviousSeason;
import DataStructures.Score;
import DataStructures.Team;
import neuralNets.InputDataMethods;
import neuralNets.NNPrevSeason;
import neuralNets.NNRecency;

public class PreviousSeasonNNInput extends InputNNBase {
	
	
	/*
	 * Current form:
	 * 	Team:
		 * 	rank
		 * 	won
		 * 	lost
		 * 	drawn
		 * 	points_for
		 * 	points_against
		 * 	points_difference
		 * 	tries_for
		 * 	tries_against
		 * 	tries_bonus
		 * 	losing_bonus
		 * 	points
	 * 	Opposition: 
		 * 	rank
		 * 	won
		 * 	lost
		 * 	drawn
		 * 	points_for
		 * 	points_against
		 * 	points_difference
		 * 	tries_for
		 * 	tries_against
		 * 	tries_bonus
		 * 	losing_bonus
		 * 	points
*/
	public PreviousSeasonNNInput(String folder) throws Exception{
		input = new double[totalMatchCount][24];
		output = new double[totalMatchCount][1];

		this.extractData(folder);
		this.inefficientNormalise();
	}
	
	private void extractData(String folder) throws Exception{
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
				
				this.input[count] = this.getInputFor(ps, ops);
				this.output[count] = this.getOutputFor(m.fullTimeScore);
								
				count ++;
				matchCount ++;
			}
		}
	}	
	
	public double[] getOutputFor(Score result){
		double[] retArr = new double[1];
		
		if(result.score_for > result.score_against) retArr[0] = 1;
		else if (result.score_for == result.score_against) retArr[0] = 0.5;
		else retArr[0] = 0;
		
		return retArr;
	}
	
	public double[] getInputFor(PreviousSeason ps, PreviousSeason ops){
		return InputDataMethods.concat(this.getPrevSeasonStats(ps), this.getPrevSeasonStats(ops));
	}
	
	private double[] getPrevSeasonStats(PreviousSeason ps){
		double[] returnArray = new double[12];
		returnArray[0] = ps.rank;
		returnArray[1] = ps.won;
		returnArray[2] = ps.lost;
		returnArray[3] = ps.drawn;
		returnArray[4] = ps.points_for;
		returnArray[5] = ps.points_against;
		returnArray[6] = ps.points_difference;
		returnArray[7] = ps.tries_for;
		returnArray[8] = ps.tries_against;
		returnArray[9] = ps.tries_bonus;
		returnArray[10] = ps.losing_bonus;
		returnArray[11] = ps.points;
		
		return returnArray;
	}
		
}


