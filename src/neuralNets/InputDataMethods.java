package neuralNets;

import NNInput.InputNNBase;

public class InputDataMethods {

	public static double[][] getTrainInput(InputNNBase data16, InputNNBase data17){
		// first half of 2017 with 2016
		double[][] firstHalf2017in = new double[data17.input.length/2][data17.input[0].length];
		System.arraycopy(data17.input, 0, firstHalf2017in, 0, firstHalf2017in.length);
		return concat(data16.input, firstHalf2017in);
	}
	
	public static double[][] getTrainOutput(InputNNBase data16, InputNNBase data17){
		// first half of 2017 with 2016
		double[][] firstHalf2017out = new double[data17.output.length/2][1];
		System.arraycopy(data17.output, 0, firstHalf2017out, 0, firstHalf2017out.length);
		return concat(data16.output, firstHalf2017out);
	}
	
	public static double[][] getTestInput(InputNNBase data16, InputNNBase data17){
//		double[][] secHalf2017in = new double[data17.input.length/2][data17.input[0].length];
//		System.arraycopy(data17.input, secHalf2017in.length, secHalf2017in, 0, secHalf2017in.length);
//		return secHalf2017in;
		return getTrainInput(data16, data17);
	}
	
	public static double[][] getTestOutput(InputNNBase data16, InputNNBase data17){
//		double[][] secHalf2017out = new double[data17.output.length/2][data17.output[0].length];
//		System.arraycopy(data17.output, secHalf2017out.length, secHalf2017out, 0, secHalf2017out.length);
//		return secHalf2017out;
		return getTrainOutput(data16, data17);

	}

	public static double[][] concat(double[][] a, double[][] b){
		double[][] result = new double[a.length + b.length][a[0].length];
		System.arraycopy(a, 0, result, 0, a.length);
		System.arraycopy(b, 0, result, a.length, b.length);
		return result;
	}

	public static double[] concat(double[] a, double[] b) {
		double[] result = new double[a.length + b.length];
		System.arraycopy(a, 0, result, 0, a.length);
		System.arraycopy(b, 0, result, a.length, b.length);
		return result;
	}

	
}
