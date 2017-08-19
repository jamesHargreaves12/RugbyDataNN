package saving;

import java.util.LinkedList;
import java.util.List;

import DataStructures.Date;

public class saveFileMain {
	public static void main(String args[]) throws Exception{
		List<Date> dates = new LinkedList<>();

		dates.add(new Date(2015,10,16));
		dates.add(new Date(2015,10,17));
		dates.add(new Date(2015,10,18));

		dates.add(new Date(2015,10,23));
		dates.add(new Date(2015,10,24));
		dates.add(new Date(2015,10,25));
		
		dates.add(new Date(2015,10,31));
		dates.add(new Date(2015,11,1));
		
		dates.add(new Date(2015,11,6));
		dates.add(new Date(2015,11,7));
		dates.add(new Date(2015,11,8));
		
		dates.add(new Date(2015,11,27));
		dates.add(new Date(2015,11,28));
		dates.add(new Date(2015,11,29));
		
		dates.add(new Date(2015,12,4));
		dates.add(new Date(2015,12,5));
		dates.add(new Date(2015,12,6));
		
		dates.add(new Date(2015,12,26));
		dates.add(new Date(2015,12,27));
				
		dates.add(new Date(2016,1,1));
		dates.add(new Date(2016,1,2));
		dates.add(new Date(2016,1,3));
		
		dates.add(new Date(2016,1,8));
		dates.add(new Date(2016,1,9));
		dates.add(new Date(2016,1,10));
		
		dates.add(new Date(2016,1,29));
		dates.add(new Date(2016,1,30));
		dates.add(new Date(2016,1,31));
		
		dates.add(new Date(2016,2,5));
		dates.add(new Date(2016,2,6));
		dates.add(new Date(2016,2,7));
		
		dates.add(new Date(2016,2,12));
		dates.add(new Date(2016,2,13));
		dates.add(new Date(2016,2,14));
		
		dates.add(new Date(2016,2,19));
		dates.add(new Date(2016,2,20));
		dates.add(new Date(2016,2,21));
		
		dates.add(new Date(2016,2,27));
		dates.add(new Date(2016,2,28));
		
		dates.add(new Date(2016,3,4));
		dates.add(new Date(2016,3,5));
		dates.add(new Date(2016,3,6));
		
		dates.add(new Date(2016,3,11));
		dates.add(new Date(2016,3,12));
		
		dates.add(new Date(2016,3,18));
		dates.add(new Date(2016,3,19));
		dates.add(new Date(2016,3,20));
		
		dates.add(new Date(2016,3,26));
		dates.add(new Date(2016,3,27));
	
		dates.add(new Date(2016,4,1));
		dates.add(new Date(2016,4,2));
		dates.add(new Date(2016,4,3));
		
		dates.add(new Date(2016,4,15));
		dates.add(new Date(2016,4,16));
		dates.add(new Date(2016,4,17));
		
		dates.add(new Date(2016,4,23));
		
		dates.add(new Date(2016,4,29));
		dates.add(new Date(2016,4,30));
		dates.add(new Date(2016,5,1));

		dates.add(new Date(2016,5,7));

		int count = 1;
		for(Date d: dates){
			ApiTools.loadMatchDayToFile("2016", d.year, d.month, d.day);
			Thread.sleep(1000);
			System.out.println(count);
			count ++;
		}
	}
	
	
}
