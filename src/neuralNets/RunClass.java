package neuralNets;

import NNInput.FinalLevelNNInput;
import NNInput.InputNNBase;
import NNInput.PreviousSeasonNNInput;
import NNInput.RecentFormNNInput;

public class RunClass {
	
	public static InputNNBase rec17;
	public static InputNNBase rec16;
	public static InputNNBase prev17;
	public static InputNNBase prev16;
	public static InputNNBase final16;
	public static InputNNBase final17;
	public static int iterationCount = 10000;
	
	public static void main(String args[]) throws Exception{
		rec17 = new PreviousSeasonNNInput("2017");
		rec16 = new PreviousSeasonNNInput("2016");
		prev17 = new PreviousSeasonNNInput("2017");
		prev16 = new PreviousSeasonNNInput("2016");
		
		NNRecency recNN = buildAndTestRecency();
		NNPrevSeason prevNN = buildAndTestPreviousSeason();
		
		final16 = new FinalLevelNNInput("2016", prevNN, recNN);
		final17 = new FinalLevelNNInput("2017", prevNN, recNN);
		
		NNFinalLevel finalNN = buildAndTestFinal();

	}
	
	private static NNFinalLevel buildAndTestFinal() throws Exception{
		double[][] inRecTrain = InputDataMethods.getTrainInput(final16, final17);
		double[][] outRecTrain = InputDataMethods.getTrainOutput(final16, final17);
		double[][] inRecTest = InputDataMethods.getTestInput(rec16, final17);
		double[][] outRecTest = InputDataMethods.getTestOutput(rec16, final17);
		
		NNFinalLevel nnfinal =  new NNFinalLevel(inRecTrain, outRecTrain, iterationCount);
		nnfinal.doAndPrintTest(inRecTest, outRecTest);
		return nnfinal;
	}

		
	private static NNRecency buildAndTestRecency() throws Exception{
		double[][] inRecTrain = InputDataMethods.getTrainInput(rec16, rec17);
		double[][] outRecTrain = InputDataMethods.getTrainOutput(rec16, rec17);
		double[][] inRecTest = InputDataMethods.getTestInput(rec16, rec17);
		double[][] outRecTest = InputDataMethods.getTestOutput(rec16, rec17);
		
		NNRecency nnrec =  new NNRecency(inRecTrain, outRecTrain, iterationCount);
		nnrec.doAndPrintTest(inRecTest, outRecTest);
		return nnrec;
	}
		
	private static NNPrevSeason buildAndTestPreviousSeason() throws Exception{
		double[][] inPrevTrain = InputDataMethods.getTrainInput(prev16, prev17);
		double[][] outPrevTrain = InputDataMethods.getTrainOutput(prev16, prev17);
		double[][] inPrevTest = InputDataMethods.getTestInput(prev16, prev17);
		double[][] outPrevTest = InputDataMethods.getTestOutput(prev16, prev17);
		
		NNPrevSeason nnrec =  new NNPrevSeason(inPrevTrain, outPrevTrain, iterationCount);
		nnrec.doAndPrintTest(inPrevTest, outPrevTest);
		return nnrec;
	}
	
}
