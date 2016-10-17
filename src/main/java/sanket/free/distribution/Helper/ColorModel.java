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

package sanket.free.distribution.Helper;

public class ColorModel {
	private String colorName;
	private Color meanValues;
	private Color stdDevs;

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public Color getMeanValues() {
		return meanValues;
	}

	public void setMeanValues(Color meanValues) {
		this.meanValues = meanValues;
	}

	public Color getStdDevs() {
		return stdDevs;
	}

	public void setStdDevs(Color stdDevs) {
		this.stdDevs = stdDevs;
	}

	public ColorModel(Color mean, Color stdDev) {
		this.meanValues = mean;
		this.stdDevs = stdDev;
	}
}
