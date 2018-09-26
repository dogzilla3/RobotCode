package shooterbot.robot.sensors;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;

import lejos.hardware.lcd.LCD;


public class Camera {
	
	private VideoCapture capture = new VideoCapture();
	private boolean cameraActive = false;
	private static int cameraId = 0;
	
	public Camera() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void startCamera() {	
		if (!this.cameraActive) {
			// start the video capture and set resolution
			this.capture.open(cameraId);
			capture.set(Highgui.CV_CAP_PROP_FRAME_WIDTH, 160);
			capture.set(Highgui.CV_CAP_PROP_FRAME_HEIGHT, 120);
			
			this.cameraActive = true;
		}
	}
	
	public Mat findCircles() throws Exception{
		// init everything
		Mat bgrImage = new Mat();
		Mat circles = new Mat();

		// check if the capture is open
		
		if (this.capture.isOpened()) {
			try {
				// read the current frame
				this.capture.read(bgrImage);

				// if the frame is not empty, process it
				if (!bgrImage.empty()) {
					//System.out.println(capture.get(Highgui.CV_CAP_PROP_FRAME_WIDTH));
					//System.out.println(capture.get(Highgui.CV_CAP_PROP_FRAME_HEIGHT));
					Mat hsvImage = new Mat();
					Imgproc.medianBlur(bgrImage, bgrImage, 15);

					
					// Convert input image to HSV
					Imgproc.cvtColor(bgrImage, hsvImage, Imgproc.COLOR_BGR2HSV);
						
					Mat lower_red_hue_range = new Mat();
					Mat upper_red_hue_range = new Mat();
					Core.inRange(hsvImage, new Scalar(0.0, 100.0, 100.0), new Scalar(10.0, 255.0, 255.0), lower_red_hue_range);
					Core.inRange(hsvImage, new Scalar(160.0, 100.0, 100.0), new Scalar(179.0, 255.0, 255.0), upper_red_hue_range);
					
					Mat red_hue_image = new Mat();
					
					Core.addWeighted(lower_red_hue_range, 1.0, upper_red_hue_range, 1.0, 0.0, red_hue_image);
					Imgproc.GaussianBlur(red_hue_image, red_hue_image, new Size(9, 9), 2, 2);
					
					Imgproc.HoughCircles(red_hue_image, circles, Imgproc.CV_HOUGH_GRADIENT, 1, red_hue_image.rows()/2, 100, 20, 30, 50);
					
					if(!circles.empty()) {
						
					}
					else {
						
					}
				} else {
					throw new ImageException("Cannot do image processing on an emty image");
				}
				
			} catch (Exception e) {
				// log the error
				LCD.drawString("Exception during the image elaboration: " + e, 1, 1);		
			}
		}
		return circles;
	}

	
	public void stopCamera()
	{	
		if (this.capture.isOpened())
		{
			// release the camera
			this.capture.release();
		}
	}
	

	public class ImageException extends Exception {
		private static final long serialVersionUID = 1211306267579622944L;

		public ImageException(String msg) {
	        super(msg);
	    }
	}
}
