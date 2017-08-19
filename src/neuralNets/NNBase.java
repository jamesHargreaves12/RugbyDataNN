package neuralNets;

import java.util.Arrays;

import org.encog.ml.data.basic.BasicMLData;
import org.encog.neural.networks.BasicNetwork;

public abstract class NNBase {
	BasicNetwork network;
	
	public double[] compute(double[] in) {
		return network.compute(new BasicMLData(in)).getData();
	}
	
	public double[][] compute(double[][] in) {
		double[][] result = new double[in.length][1];
		for(int row = 0; row < in.length; row ++){
			result[row] = this.compute(in[row]);
		}
		return result;
	}
	
	public void doAndPrintTest(double[][] testIn, double[][]testOut){
		boolean wasWin, wasLoss, predWin, predLoss;
		double drawError = 0.005;
		double drawPos = 0.5;
		int[][]resultsTable = new int[3][3];
		double[] output = new double[testIn.length];
		double[] expected = new double[testIn.length];
		
		for(int row = 0; row < testIn.length; row ++){
			double result = network.compute(new BasicMLData(testIn[row])).getData(0);
			double real = testOut[row][0];
			output[row] = result;
			expected[row] = real;
			wasWin = false;
			wasLoss = false;
			predWin = false;
			predLoss = false;
			
			if(real > 0.9){
				//win 
				wasWin = true;
			}
			else if(real < 0.1){
				wasLoss = true;
			}
			
			if(result > drawPos+drawError){
				predWin = true;
			}
			else if (result < drawPos-drawError){
				predLoss = true;
			}
			resultsTable[(wasWin? 0 : (wasLoss?  2: 1))][(predWin? 0 : (predLoss?  2: 1))] ++;			
		}
		System.out.println(Arrays.deepToString(resultsTable));
		System.out.println(output.length);
	}

}
