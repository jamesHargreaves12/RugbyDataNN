package NNInput;

import java.util.Arrays;

import DataStructures.League;
import DataStructures.LeagueBuilder;
import DataStructures.Match;
import DataStructures.PreviousSeason;
import DataStructures.Score;
import DataStructures.Team;
import neuralNets.InputDataMethods;
import neuralNets.NNPrevSeason;
import neuralNets.NNRecency;

public class FinalLevelNNInput extends InputNNBase{
	/*
	 * Current Form:
	 	* out from NNPrevSeason
	 	* out from NNRecentForm
	 	* home
	 	* match count 
	 	* opponent match count
	 * */
	
	public FinalLevelNNInput(String folder, NNPrevSeason psNN, NNRecency revNN) throws Exception{
		input = new double[totalMatchCount][5];
		output = new double[totalMatchCount][1];

		this.setUpData(folder, psNN, revNN);
		this.inefficientNormalise();
		//System.out.println(Arrays.deepToString(input));
	}

	private void setUpData(String folder, NNPrevSeason psNN, NNRecency revNN) throws Exception {
		// TODO Auto-generated method stub
		League league = LeagueBuilder.buildEnglishLeague(folder);
		PreviousSeasonNNInput psNNIn = new PreviousSeasonNNInput(folder);
		RecentFormNNInput revNNIn = new RecentFormNNInput(folder);
		
		int count = 0;
		for(Team t : league.getTeams()){
			int matchCount = 0;
			for(Match m : t.getMatches()){
				Team oppo = league.getTeamForId(m.againstId);
				
				if(t.getPrevSeason().rank == 12 || oppo.getPrevSeason().rank == 12){
					continue;
				}
				
				double[] getInputForPs = psNNIn.getInputFor(t.getPrevSeason(), oppo.getPrevSeason());
				double[] getInputForRev = revNNIn.getInputFor(t.getMatches(), oppo.getMatches(), matchCount);
				
				double[] inputExtra = new double[3];
				inputExtra[1] = (m.isHome? 1: 0);
				inputExtra[2] = matchCount;
				inputExtra[3] = oppo.getMatchCountOfMatch(m.date);
				
				double[] inputRow = InputDataMethods.concat(psNN.compute(getInputForPs), revNN.compute(getInputForRev));
				input[count] = InputDataMethods.concat(inputRow, inputExtra);
				output[count] = this.getOutputFor(m.fullTimeScore);
				
				count ++;
				matchCount ++;
			}
		}
	}
	
	

}
