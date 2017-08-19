package DataStructures;

import java.util.Arrays;

public class Date implements Comparable {
	public int year;
	public int month;
	public int day;
	
	public Date(int y, int m, int d){
		year = y;
		month = m;
		day = d;
	}
	
	public Date(String dateStr){
		String parts[] = dateStr.split("[.]");
		year = Integer.parseInt(parts[0]);
		month = Integer.parseInt(parts[1]);
		day = Integer.parseInt(parts[2]);
	}

	@Override
	public int compareTo(Object o) {
		Date d2 = (Date) o;
		return (year - d2.year)*365 + (month - d2.month)*30 +day - d2.day;
	}
	
	public String toString(){
		return year+"."+month+"."+day;
	}
}
