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

public class Color {
	private double r;
	private double g;
	private double b;

	public void printValues() {
		System.out.println(" R = " + Double.toString(r) + " G = " + Double.toString(g) + " B = " + Double.toString(b));
	}

	public Color(double r, double g, double b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	/**
	 * @param rgb
	 *            This method instantiates a Color object given the rgb values.
	 */
	public Color(int[] rgb) {
		if (rgb.length != 3) {
			System.out.println("Please enter an integer array of 3 elements!");
		}
		this.r = rgb[0];
		this.g = rgb[1];
		this.b = rgb[2];
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	public double getG() {
		return g;
	}

	public void setG(double g) {
		this.g = g;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double getProduct() {
		return getR() * getG() * getB();
	}
}
