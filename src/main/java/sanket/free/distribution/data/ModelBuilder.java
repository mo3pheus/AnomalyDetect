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

package sanket.free.distribution.data;

/**
 * Import Section
 */
import java.util.ArrayList;

import sanket.free.distribution.Helper.Color;
import sanket.free.distribution.Helper.ColorModel;

public class ModelBuilder {
	private ColorModel sampleModel;

	private ColorModel generateModel(ArrayList<Color> input) {
		Color mean = new Color(0, 0, 0);
		double i = 0.0d;
		double r = 0.0d;
		double g = 0.0d;
		double b = 0.0d;
		for (Color current : input) {
			r += current.getR();
			g += current.getG();
			b += current.getB();
			i++;
		}

		mean.setR(r / i);
		mean.setG(g / i);
		mean.setB(b / i);

		double stdDevR = 0.0d;
		double stdDevG = 0.0d;
		double stdDevB = 0.0d;
		for (Color current : input) {
			stdDevR += Math.pow((current.getR() - mean.getR()), 2);
			stdDevG += Math.pow((current.getG() - mean.getG()), 2);
			stdDevB += Math.pow((current.getB() - mean.getB()), 2);
		}
		stdDevR = stdDevR / i;
		stdDevG = stdDevG / i;
		stdDevB = stdDevB / i;

		stdDevR = Math.pow(stdDevR, 0.5d);
		stdDevG = Math.pow(stdDevG, 0.5d);
		stdDevB = Math.pow(stdDevB, 0.5d);

		Color stdDev = new Color(stdDevR, stdDevG, stdDevB);

		return new ColorModel(mean, stdDev);
	}

	public ColorModel getSampleModel() {
		return sampleModel;
	}

	public void setSampleModel(ColorModel sampleModel) {
		this.sampleModel = sampleModel;
	}

	/**
	 * This methods prints out the mean values and standard deviations of the
	 * model it holds.
	 */
	public void printModelData() {
		System.out.println(" Model Data");
		System.out.println(" Mean Values :: ");
		System.out.println(" RMean = " + Double.toString(sampleModel.getMeanValues().getR()) + " GMean = "
				+ Double.toString(sampleModel.getMeanValues().getG()) + " BMean = "
				+ Double.toString(sampleModel.getMeanValues().getB()));
		System.out.println(" StdDev Values :: ");
		System.out.println(" RStdDev = " + Double.toString(sampleModel.getStdDevs().getR()) + " GStdDev = "
				+ Double.toString(sampleModel.getStdDevs().getG()) + " BStdDev = "
				+ Double.toString(sampleModel.getStdDevs().getB()));
	}

	/**
	 * Constructor: This takes in a sample data set and fits a model over it.
	 * 
	 * @param dataSet
	 *            - sample population to be fitted.
	 */
	public ModelBuilder(ArrayList<Color> dataSet) {
		this.sampleModel = generateModel(dataSet);
	}
}
