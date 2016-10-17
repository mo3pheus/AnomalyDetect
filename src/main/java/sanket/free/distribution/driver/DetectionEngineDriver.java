/**
   Copyright (C) 2016  Sanket Korgaonkar

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package sanket.free.distribution.driver;

/**
 * Import Section
 */
import sanket.free.distribution.ClassificationEngine.Classifier;
import sanket.free.distribution.Helper.Color;
import sanket.free.distribution.data.DataSetBuilder;
import sanket.free.distribution.data.ModelBuilder;

/**
 * @author Sanket This class drives the Anomaly Detection Engine.
 */
public class DetectionEngineDriver {

	public static final String DIVIDER = "***********************************************************************************************";
	public static final double TEST_PROPORTION = 10.0d;

	public static void main(String[] args) {
		System.out.println("\n\n");
		System.out.println(DIVIDER);
		System.out.println(" Copyright (C) 2016  Sanket Korgaonkar. \n"
				+ " This program comes with ABSOLUTELY NO WARRANTY. "
				+ "\n This is free software, and you are welcome to redistribute it under certain conditions."
				+ "\n For more information please go to the README.txt file attached" + "\n with this source code.\n");
		System.out.println(DIVIDER);
		System.out.println(" Welcome! This is a simple anomaly detection algorithm that learns what the normal\n"
				+ " RGB color values look like and then tries to detect anomalies.");
		verifyArgsAndRun(args);
		System.out.println("\n" + DIVIDER);
	}

	/**
	 * @param args
	 *            args[0] - training Population args[1] - scaleFactor.
	 * 
	 *            This function accepts the user given arguments and checks them
	 *            for validity. If the arguments are valid, it runs the Anomaly
	 *            Detection Engine.
	 */
	public static void verifyArgsAndRun(String[] args) {
		if (args.length < 2) {
			System.out.println(" Insufficient arguments!");
			return;
		}

		String trainCount = args[0];
		String scaleFactor = args[1];
		long trainingCount = 1000;
		double scaleFctr = 0.5d;
		try {
			trainingCount = Long.parseLong(trainCount);
			if (trainingCount < 1000) {
				System.out.println("  Incorrect argument for trainCount.");
				System.out.println("  Expected argument is between 1000 - 100000");
				System.out.println("  Entered argument ::  " + trainCount);
				return;
			} else if (trainingCount > 100000) {
				System.out.println(" The training set is quite large.");
				System.out.println(" This will take some time and memory!");
			}
		} catch (NumberFormatException nfe) {
			System.out.println("  Incorrect argument for trainCount.");
			System.out.println("  Expected argument is between 1000 - 100000");
			System.out.println("  Entered argument ::  " + trainCount);
			return;
		}

		try {
			scaleFctr = Double.parseDouble(scaleFactor);
			if (scaleFctr < 0.1d || scaleFctr > 1.0d) {
				System.out.println("\n Incorrect value for scaleFactor! Please enter a value between 0.1 and 1.0 ");
				return;
			} else if (scaleFctr < 0.5d || scaleFctr > 0.9d) {
				System.out.println("\n Recommended range for scaleFactor is 0.5 - 0.9 ");
			}
		} catch (NumberFormatException nfe) {
			System.out.println("Incorrect argument for scaleFactor.");
			System.out.println("Expected argument e.g. 0.5 or 0.2 ");
			System.out.println("Entered argument ::  " + scaleFactor);
			return;
		}
		runAnomalyDetection(trainingCount, scaleFctr);
	}

	/**
	 * @param trainCount
	 *            - size of the training population.
	 * @param scaleFactor
	 *            - factor by which the minimum training probability needs to
	 *            exceed in order for the Anomaly Detection Engine to classify a
	 *            sample as an anomaly.
	 */
	private static void runAnomalyDetection(long trainCount, double scaleFactor) {
		/*
		 * 1. Generate training and testing sets
		 */
		DataSetBuilder trainingSet = new DataSetBuilder("Train", trainCount);
		DataSetBuilder testSet = new DataSetBuilder("Test", (long) (trainCount / TEST_PROPORTION));

		/*
		 * 2. Fit the training set to generate a model for "normal" data
		 */
		ModelBuilder fittedModel = new ModelBuilder(trainingSet.getSamples());
		fittedModel.printModelData();

		/*
		 * 3. Feed the models generated to the classifier to get an epsilon
		 * recommendation.
		 */
		Classifier anomalyDetect = new Classifier(fittedModel.getSampleModel());
		double epsilon = scaleFactor * anomalyDetect.getMinProbability(trainingSet.getSamples());

		/*
		 * 4. Run each sample in the testSet through the classifier and record
		 * error percentage
		 */
		System.out.println("\n" + DIVIDER);
		System.out.println(" Here are a few test cases that I classified !");
		int errorCount = 0;
		int successCount = 0;
		for (Color testSample : testSet.getSamples()) {
			String predictedClass = anomalyDetect.getClassification(testSample, epsilon);
			String actualClass = DataSetBuilder.identifySampleType(testSample);
			if (predictedClass.compareTo(actualClass) != 0) {
				if (errorCount < 2) {
					System.out.println("\n========== ERROR ========== ");
					testSample.printValues();
					System.out.println(" Actual Class = " + actualClass);
					System.out.println(" Predicted Class = " + predictedClass);
				}
				errorCount++;
			} else if ((predictedClass.compareTo("Anomaly") == 0) && (successCount < 2)) {
				System.out.println("\n==========SUCCESS========== ");
				testSample.printValues();
				System.out.println(" Actual Class = " + actualClass);
				System.out.println(" Predicted Class = " + predictedClass);
				successCount++;
			}
		}

		/*
		 * 5. Report the results and conclude the program.
		 */
		double success = (trainCount / TEST_PROPORTION) - errorCount;
		success /= ((trainCount / TEST_PROPORTION) / 100.0d);
		System.out.println("\n" + DIVIDER);
		System.out.println("The Anomaly Detection Engine has finished classifying "
				+ Long.toString((long) (trainCount / TEST_PROPORTION)) + " test samples.");
		System.out.println("Success percentage :: " + (success) + " % ");
		System.out.println("Why don't you experiment with different training set sizes and scaleFactor values"
				+ "\nand run the program again! Thanks for trying out Anomaly Detect!");
	}
}
