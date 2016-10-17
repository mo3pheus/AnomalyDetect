This directory contains the version 0.0.1 release of ANOMALY Detect. 

ANOMALY Detect is free software. See the folder README&LICENSE for reuse conditions. ANOMALY Detect is copyright by the Free Software Foundation. Copyright notices condense sequential years into a range; e.g. "1987-1994" means all years from 1987 to 1994 inclusive. 

-------------
Author:
This software is written by Sanket Korgaonkar. You can contact the author via email at - sanket.korgaonkar@gmail.com 

-------------
Downloading 
You can download ANOMALY Detect from the web here
https://skorgaonkar@bitbucket.org/skorgaonkar/gaussiananomalydetection.git

-------------
How it works!
Anomaly Detect generates random input samples by simulating (r,g,b) values between (0-255) for training. For testing it simulates values that go beyond the normal input range (-150-400). The size of the test data set is 1/10th that of the training data set.

-------------
Running the Software
Navigate to the directory where the sanket-freeDist-anomaly-detection-0.0.1.jar file is and execute the following command
java -cp sanket-freeDist-anomaly-detection-0.0.1.jar sanket.free.distribution.driver.DetectionEngineDriver 10000 0.5

sanket.free.distribution.driver.DetectionEngineDriver is the name of the class that contains the main() method. 

Args[0] – is the population size – range =>( 10000 – 100000)
Args[1] – is the scale factor valid range is 0.1 – 0.9,
recommended range is 0.5 – 0.9 for best results.

-------------
System Requirements
This software requires that a version of java is installed on your computer. You can download java at https://www.java.com/en/download/ and you can check which version of java you have by typing java -version on your command line or terminal. Java version 1.7 or greater should give best results although earlier installations of java might work too.

-------------
Documentation
Documentation should have been supplied with this distribution of the software. Documentation is also available at
https://skorgaonkar@bitbucket.org/skorgaonkar/gaussiananomalydetection.git under GaussianAnomalyDetection / doc /

-------------
Development 
Development has been hosted at https://skorgaonkar@bitbucket.org/skorgaonkar/gaussiananomalydetection.git – at this point there are no plans for any continued development of this software.


-------------
Bug Reporting
You can report any bugs found here:
https://bitbucket.org/skorgaonkar/gaussiananomalydetection/issues/new

-------------
Git Access
https://skorgaonkar@bitbucket.org/skorgaonkar/gaussiananomalydetection.git

-------------
Copyright (C) 2016 Free Software Foundation, Inc. This file is part of ANOMALY Detect. ANOMALY Detect is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version. ANOMALY Detect is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>. 
