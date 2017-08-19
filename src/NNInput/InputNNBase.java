package NNInput;

import DataStructures.Score;
import DataStructures.Team;

public class InputNNBase {
	public int totalMatchCount = 220;
	public double input[][];
	public double output[][];
	
	public void inefficientNormalise(){
		double min, max;
		for(int col = 0; col < input[0].length; col ++){
			min = input[0][col];
			max = input[0][col];
			
			for(int row = 0; row < input.length; row ++){
				if(input[row][col] < min){
					min = input[row][col];
				}
				else if(input[row][col] > max){
					max = input[row][col];
				}
			}
			
			for(int row = 0; row < input.length; row ++){
				input[row][col] = normalise(min, max, input[row][col]);
			}
		}
		
		int col = 0;
		min = output[0][0];
		max = output[0][0];
		for(int row = 0; row < output.length; row ++){
			if(output[row][col] < min){
				min = output[row][col];
			}
			else if(output[row][col] > max){
				max = output[row][col];
			}
		}
		for(int row = 0; row < output.length; row ++){
			output[row][col] = normalise(min, max, output[row][col]);
		}

	}	

	private double normalise(double min, double max, double val){
		return (val-min)/(max-min);
	}	
	
	public double[] getOutputFor(Score result){
		double[] retArr = new double[1];
		
		if(result.score_for > result.score_against) retArr[0] = 1;
		else if (result.score_for == result.score_against) retArr[0] = 0.5;
		else retArr[0] = 0;
		
		return retArr;
	}
}
