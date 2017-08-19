package neuralNets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.encog.Encog;
import org.encog.engine.network.activation.ActivationElliott;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.freeform.FreeformLayer;
import org.encog.neural.freeform.FreeformNetwork;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.ContainsFlat;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.Propagation;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.util.simple.EncogUtility;

import NNInput.PreviousSeasonNNInput;
import NNInput.RecentFormNNInput;


public class NNRecency extends NNBase {
	final static int NUM_HIDDEN_NODES = 50;
	
	public NNRecency(double[][] in, double[][] out, int iterations) throws Exception {
		System.out.println("RECENCY");

		this.buildNetwork(in[0].length);
		this.trainNetwork(in, out, iterations);
		
		Encog.getInstance().shutdown();
	}
			
	private void trainNetwork(double[][] in, double[][] out, int iterations){
		
		MLDataSet trainingSet = new BasicMLDataSet(in , out);
		final Backpropagation train = new Backpropagation(network, trainingSet);
		
		for(int epoch = 1; epoch < iterations; epoch++)
		{
			train.iteration();
			if(epoch%1000== 999)System.out.println(epoch+1);
		}
		
		train.finishTraining();		
	}
		
	private void buildNetwork(int inputSize){
		network = new BasicNetwork();
		network.addLayer(new BasicLayer(null, false, inputSize));
		network.addLayer(new BasicLayer(new ActivationSigmoid(), true, NUM_HIDDEN_NODES));
		network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 1));
		
		network.getStructure().finalizeStructure();
		
		network.reset();
	}

}
