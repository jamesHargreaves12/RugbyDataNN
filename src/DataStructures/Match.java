package DataStructures;

public class Match implements Comparable{
	public boolean isHome;
	public String againstId;
	public Score halfTimeScore;
	public Score fullTimeScore;
	public Date date;
	
	public Match(boolean isHome, String againstId, Score fullTimeScore, Date date){
		this.isHome = isHome;
		this.againstId = againstId;
		this.fullTimeScore = fullTimeScore;
		this.date = date;
	}
	
	public String toString(){
		return "{ home: " + isHome + ", against: " + againstId + ", score: "+ fullTimeScore.toString() + ", date: " + date.toString() + " }";
	}

	@Override
	public int compareTo(Object o) {
		Match m2 = (Match) o;
		return this.date.compareTo(m2.date);
	}
}
