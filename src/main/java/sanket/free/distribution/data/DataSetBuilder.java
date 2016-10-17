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
import java.util.Random;

import sanket.free.distribution.Helper.Color;

public class DataSetBuilder {
	public static final int MIN_TRAIN_VAL = 0;
	public static final int MAX_TRAIN_VAL = 255;
	public static final int MIN_TEST_VAL = -255;
	public static final int MAX_TEST_VAL = 400;
	private long sampleCount = 0l;
	private ArrayList<Color> samples;

	private int generateRandomRGB(String type) {
		int min = 0;
		int max = 0;
		Random rand = new Random();
		if (type.compareTo("Train") == 0) {
			min = MIN_TRAIN_VAL;
			max = MAX_TRAIN_VAL;
		} else if (type.compareTo("Test") == 0) {
			min = MIN_TEST_VAL;
			max = MAX_TEST_VAL;
		}

		return rand.nextInt((max - min) + 1) + min;
	}

	private ArrayList<Color> generateRandomData(String dataType) {
		ArrayList<Color> dataSet = new ArrayList<Color>();
		for (int i = 0; i < sampleCount; i++) {
			dataSet.add(
					new Color(generateRandomRGB(dataType), generateRandomRGB(dataType), generateRandomRGB(dataType)));
		}
		return dataSet;
	}

	public ArrayList<Color> getSamples() {
		return samples;
	}

	public void setSamples(ArrayList<Color> samples) {
		this.samples = samples;
	}

	/**
	 * @param input
	 *            - inputSample
	 * @return "Anomaly"/ "Normal"
	 * 
	 *         This method acts as a ground truth for detecting anomalies, RGB
	 *         values should be between 0 and 255 for the sample to be
	 *         classified as normal. Anything outside these bounds is considered
	 *         an anomaly.
	 */
	public static String identifySampleType(Color input) {
		return ((input.getR() >= 0) && (input.getR() <= 255) && (input.getG() >= 0) && (input.getG() <= 255)
				&& (input.getB() >= 0) && (input.getB() <= 255)) ? "Normal" : "Anomaly";
	}

	public DataSetBuilder(String dataType, Long sampleCount) {
		this.sampleCount = sampleCount;
		samples = generateRandomData(dataType);
	}
}
