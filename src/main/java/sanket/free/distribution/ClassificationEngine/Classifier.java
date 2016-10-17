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

package sanket.free.distribution.ClassificationEngine;

/**
 * Import Section
 */
import java.util.ArrayList;

import sanket.free.distribution.Helper.Color;
import sanket.free.distribution.Helper.ColorModel;

public class Classifier {

	private ColorModel model = null;

	private double findGaussianProbability(Color mean, Color stdDev, Color x) {
		double termConst = 1.0d / Math.pow((2 * Math.PI), 0.5d);
		termConst *= 1 / stdDev.getProduct();
		double termR = ((mean.getR() - x.getR()) / stdDev.getR());
		termR = termR * termR * 0.5d;
		double termG = ((mean.getG() - x.getG()) / stdDev.getG());
		termG = termG * termG * 0.5d;
		double termB = ((mean.getB() - x.getB()) / stdDev.getB());
		termB = termB * termB * 0.5d;
		double exp = -1.0d * (termR + termG + termB);
		return termConst * Math.exp(exp);
	}

	public Classifier(ColorModel model) {
		this.model = model;
	}

	/**
	 * @param x
	 *            - test sample
	 * @param epsilon
	 *            - test sample should score below this value for it to be
	 *            classified as an anomaly.
	 * @return Returns the classification of the test sample as Anomaly/ Normal
	 */
	public String getClassification(Color x, double epsilon) {
		return (findGaussianProbability(model.getMeanValues(), model.getStdDevs(), x) < epsilon) ? "Anomaly" : "Normal";
	}

	/**
	 * @param samples
	 * @return - minimumProbability
	 * 
	 *         This method accepts a set of samples and returns the lowest
	 *         probability calculated for class membership. In other words it
	 *         gives a measure of how far spread out the farthest normal samples
	 *         are.
	 */
	public double getMinProbability(ArrayList<Color> samples) {
		double minProb = 1.0d;
		for (Color x : samples) {
			double prob = findGaussianProbability(model.getMeanValues(), model.getStdDevs(), x);
			if (prob < minProb) {
				minProb = prob;
			}
		}
		return minProb;
	}
}
