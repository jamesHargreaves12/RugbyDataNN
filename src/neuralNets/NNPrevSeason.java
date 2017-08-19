package neuralNets;

import java.util.Arrays;

import org.encog.Encog;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.back.Backpropagation;

import NNInput.PreviousSeasonNNInput;
import NNInput.RecentFormNNInput;

public class NNPrevSeason extends NNBase {
	final static int NUM_HIDDEN_NODES = 50;

	public NNPrevSeason(double[][] trainIn, double[][] trainOut, int iterations) throws Exception {
		System.out.println("PREV SEASON");
		this.buildNetwork(trainIn[0].length);
		this.trainNetwork(trainIn, trainOut, iterations);
		
		Encog.getInstance().shutdown();
	}
		
	private void trainNetwork(double[][] trainIn, double[][] trainOut, int iterations){
		MLDataSet trainingSet = new BasicMLDataSet(trainIn, trainOut);
		final Backpropagation train = new Backpropagation(network, trainingSet);
		
		for(int epoch = 1; epoch < iterations; epoch++)
		{
			train.iteration();
			if(epoch%1000== 999)System.out.println(epoch+1);
		}
		train.finishTraining();		
	}
	
	private void buildNetwork(int numInputs){
		network = new BasicNetwork();
		network.addLayer(new BasicLayer(null, false, numInputs));
		network.addLayer(new BasicLayer(new ActivationSigmoid(), true, NUM_HIDDEN_NODES));
		network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 1));
		
		network.getStructure().finalizeStructure();
		
		network.reset();
	}
}
