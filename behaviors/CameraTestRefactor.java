package shooterbot.behaviors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;

import shooterbot.robot.Robot;

public class CameraTestRefactor extends Behavior{

	private VideoCapture capture = new VideoCapture();
	private boolean cameraActive = false;
	private static int cameraId = 0;
	private ScheduledExecutorService timer;
	
	public CameraTestRefactor(Robot robot) {
		super(robot);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {	
		// TODO Auto-generated method stub
	}
	
	public void startCamera()
	{
		if (!this.cameraActive)
		{
			robot.Debug("Start of Camera Method");
			// start the video capture
			capture.set(Highgui.CV_CAP_PROP_FRAME_WIDTH, 160);

			capture.set(Highgui.CV_CAP_PROP_FRAME_HEIGHT, 120);
			this.capture.open(cameraId);
			
			// is the video stream available?
			if (this.capture.isOpened())
			{
				this.cameraActive = true;

				// grab a frame every 33 ms (30 frames/sec)
				Runnable frameGrabber = new Runnable() {
					@Override
					public void run()
					{
						
						// effectively grab and process a single frame
						Mat frameCircles = getCircles();
						
					}
				};
				
				this.timer = Executors.newSingleThreadScheduledExecutor();
				this.timer.scheduleAtFixedRate(frameGrabber, 0, 200, TimeUnit.MILLISECONDS);
			} else {
				// log the error
				robot.say("Impossible to open the camera connection...");
			}
		}
		else
		{
			// the camera is not active at this point
			this.cameraActive = false;
			
			// stop the timer
			this.stopAcquisition();
		}
	}
	
	public void stopService() {
		timer.shutdown();
	}
	
	private void stopAcquisition()
	{
		if (this.timer!=null && !this.timer.isShutdown())
		{
			try
			{
				// stop the timer
				this.timer.shutdown();
				this.timer.awaitTermination(200, TimeUnit.MILLISECONDS);
			}
			catch (InterruptedException e)
			{
				// log any exception
				robot.say("Exception in stopping the frame capture, trying to release the camera now... " + e);
			}
		}
		
		if (this.capture.isOpened())
		{
			// release the camera
			this.capture.release();
		}
	}
	
	private Mat getCircles()
	{
		// init everything
		Mat bgrImage = new Mat();
		Mat circles = new Mat();
		robot.Debug("In get circles");
		// check if the capture is open
		robot.Debug(Boolean.toString(this.capture.isOpened()));
		if (this.capture.isOpened())
		{
			//try
			//{
				robot.Debug("Before read image");
				// read the current frame
				this.capture.read(bgrImage);
				robot.Debug("After Read image");
				// if the frame is not empty, process it
				if (!bgrImage.empty())
				{
					
					//System.out.println(capture.get(Highgui.CV_CAP_PROP_FRAME_WIDTH));
					//System.out.println(capture.get(Highgui.CV_CAP_PROP_FRAME_HEIGHT));
					Mat hsvImage = new Mat();
					
					robot.Debug("Before blur");
					Imgproc.medianBlur(bgrImage, bgrImage, 15);
					robot.Debug("After blur");	
					
					// Convert input image to HSV
					robot.Debug("Before color");
					Imgproc.cvtColor(bgrImage, hsvImage, Imgproc.COLOR_BGR2HSV);
					robot.Debug("After color");
						
					robot.Debug("Before check color");
					Mat lower_red_hue_range = new Mat();
					Mat upper_red_hue_range = new Mat();
					Core.inRange(hsvImage, new Scalar(0.0, 100.0, 100.0), new Scalar(10.0, 255.0, 255.0), lower_red_hue_range);
					Core.inRange(hsvImage, new Scalar(160.0, 100.0, 100.0), new Scalar(179.0, 255.0, 255.0), upper_red_hue_range);
					robot.Debug("After check color");
					
					Mat red_hue_image = new Mat();
					
					robot.Debug("Before GBlur");
					Core.addWeighted(lower_red_hue_range, 1.0, upper_red_hue_range, 1.0, 0.0, red_hue_image);
					Imgproc.GaussianBlur(red_hue_image, red_hue_image, new Size(9, 9), 2, 2);
					robot.Debug("After GBlur");
					
					robot.Debug("Before circles");
					Imgproc.HoughCircles(red_hue_image, circles, Imgproc.CV_HOUGH_GRADIENT, 1, red_hue_image.rows()/2, 100, 20, 30, 50);
					robot.Debug("After circles");	
					
					/*if(circles.size().height != 0) {
						//System.out.println("Circles: " + circles.cols());
					    for (int i = 0; i < circles.cols(); i++) {
					        double[] vCircle = circles.get(0, i);

					        Point pt = new Point(Math.round(vCircle[0]), Math.round(vCircle[1]));
					        int radius = (int)Math.round(vCircle[2]);

					        Core.circle(bgrImage, pt, radius, new Scalar(255, 0, 0), 2);
					    }*/
						
					//}
					
					if(!circles.empty()) {
						robot.say("Circles: " + circles.cols());
					}
					else {
						robot.say("No circle found");
					}
				}
				
			//}
			/*catch (Exception e)
			{
				// log the error
				robot.say("Exception during the image elaboration: " + e);
				
			}*/
		}
		
		//Float[] circlesCenters = new Float[circles.cols()];
		
		////////////////////////////////////////////////////////
		
		return circles;
	}
}
