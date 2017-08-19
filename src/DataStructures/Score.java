package DataStructures;

public class Score {
	public int score_for;
	public int score_against;
	
	public Score(int score_for, int score_against){
		this.score_for = score_for;
		this.score_against = score_against;
	}
	
	public String toString(){
		return score_for + ":" + score_against;
	}
}
