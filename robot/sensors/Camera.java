package shooterbot.robot.sensors;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;

import shooterbot.robot.Robot;


public class Camera {
	
	private VideoCapture capture;
	private boolean cameraActive;
	private static int cameraId;
	private int cameraCycles;
	
	private Mat lower_red_hue_range = new Mat();
	private Mat upper_red_hue_range = new Mat();
	private Mat red_hue_image = new Mat();
	private Mat hsvImage = new Mat();
	private Mat circleContainer = new Mat();
	private Mat rawCameraImage = new Mat();
	
	/*
	 *  Inverse ratio of the accumulator resolution to the image resolution.
	 *	For example, if dp=1 , the accumulator has the same resolution as the input image. 
	 *	If dp=2 , the accumulator has half as big width and height.
	 */
	double dp  = 1;
	/*
	 *  Minimum distance between the centers of the detected circles. 
		If the parameter is too small, multiple neighbor circles may be falsely detected in 
		addition to a true one. If it is too large, some circles may be missed.
	 */
	double minDist  = red_hue_image.rows();
	
	/*
	 *  First method-specific parameter. In case of CV_HOUGH_GRADIENT , it is the higher
	 	threshold of the two passed to the Canny()edge detector (the lower one is twice smaller).
	 */
	double param1  = 100;
	
	/*
	 *  Second method-specific parameter. In case of CV_HOUGH_GRADIENT , it is the accumulator 
		threshold for the circle centers at the detection stage. The smaller it is, the more false circles 
		may be detected. Circles, corresponding to the larger accumulator values, will be returned first.
	 */
	double param2  = 20;

	/*
	 *  Minimum circle radius.
	 */
	int minRadius  = 0;
	
	/*
	 *  Maximum circle radius.
	 */
	int maxRadius  = 0;
	
	/*
	 *  Constructor sets up the camera and initializes the member variables
	 */
	public Camera() {
		capture = new VideoCapture();
		cameraActive = false;
		cameraId = -1;
		cameraCycles = 4;
		lower_red_hue_range = new Mat();
		upper_red_hue_range = new Mat();
		red_hue_image = new Mat();
		hsvImage = new Mat();
		circleContainer = new Mat();
		rawCameraImage = new Mat();
	}
	
	/*
	 *  Gets an image, processes it, and returns any circles found
	 */
	public Mat getCircles() {
		//Check the camera four times
		for(int i = 0; i < cameraCycles; i++) {
			
			//Start the camera
			startAcquisition();
			
			// check if the capture is open
			if (this.capture.isOpened()) {
				try {

					// read the current frame
					this.capture.read(rawCameraImage);
					
					// if the frame is not empty, process it
					if (!rawCameraImage.empty()) {
						processImage(rawCameraImage);
						printCircles(i);
						break;
					} else {
						Robot.debug("Camera could not be open");
					}
					
				} catch (Exception e) {
					// log the error
					System.err.println("Exception during the image elaboration: " + e);
				}
			}
			
			//Stop the camera
			stopAcquisition();
		}
		
		//Release the matrices
		realeaseAllMats();
		
		//return the circles
		return circleContainer;
	}
	
	
	/*
	 *  Helper function that processes an image finding any circles
	 */
	private void processImage(Mat rawCameraImage) {
		//Blur original image to reduce noise
		Imgproc.medianBlur(rawCameraImage, rawCameraImage, 7);
		
		//Convert input image to HSV
		Imgproc.cvtColor(rawCameraImage, hsvImage, Imgproc.COLOR_BGR2HSV);
			
		//Threshold image and get rid of non red pixels
		Core.inRange(hsvImage, new Scalar(0.0, 100.0, 100.0), new Scalar(10.0, 255.0, 255.0), lower_red_hue_range);
		Core.inRange(hsvImage, new Scalar(160.0, 100.0, 100.0), new Scalar(179.0, 255.0, 255.0), upper_red_hue_range);
			
		//Combine two images and blur to avoid false positives
		Core.addWeighted(lower_red_hue_range, 1.0, upper_red_hue_range, 1.0, 0.0, red_hue_image);
		Imgproc.GaussianBlur(red_hue_image, red_hue_image, new Size(9, 9), 2, 2);
			
		//Detect circles on new image
		Imgproc.HoughCircles(red_hue_image, circleContainer, Imgproc.CV_HOUGH_GRADIENT, dp, minDist, param1, param2, minRadius, maxRadius);
	}
	
	/*
	 *  Helper function that prints frame and circle data to lcd screen
	 */
	private void printCircles(int frame) {
		double[] circle = circleContainer.get(0, 0);
		if(!circleContainer.empty()) {
			Robot.say("C:" + circleContainer.cols() + "F:" + (frame + 1) + "X:" + circle[0]);
		} else {
			Robot.say("Frame: " + (frame + 1));
		}
	}
	
	/*
	 *  Starts the camera
	 */
	public void startAcquisition() {
		if (!this.cameraActive) {
			// start the video capture
			this.capture.open(cameraId);
			capture.set(Highgui.CV_CAP_PROP_FRAME_WIDTH, 640);
			capture.set(Highgui.CV_CAP_PROP_FRAME_HEIGHT, 480);
			this.cameraActive = true;
		}
	}
	
	/*
	 *  Stops the camera
	 */
	private void stopAcquisition() {		
		if (this.capture.isOpened()) {
			// release the camera
			this.capture.release();
			this.cameraActive = false;
		}
	}
	
	/*
	 *  Releases all matrices, this is to avoid memory leaks. 
	 *  See garbage collection issue with Mats and opencv
	 */
	private void realeaseAllMats() {
		lower_red_hue_range.release();
		upper_red_hue_range.release();
		red_hue_image.release();
		hsvImage.release();
	}
}
