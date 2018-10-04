package shooterbot.behaviors;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;

import lejos.utility.Delay;
import shooterbot.robot.Robot;

public class CameraTest extends Behavior{

	public VideoCapture vid;
	public CameraTest(Robot robot) {
		super(robot);
		// TODO Auto-generated constructor stub
		
	}
	
	public void initialize() {
		vid = new VideoCapture();
		vid.open(-1);
		Robot.debug("isOpen" + vid.isOpened());
		vid.set(Highgui.CV_CAP_PROP_FRAME_WIDTH, 160);
		vid.set(Highgui.CV_CAP_PROP_FRAME_HEIGHT, 120);
		Robot.say("Initializing...");
		Delay.msDelay(3000);
	}

	public void stop() {
		vid.release();
	}
	@Override
	public void run() {
		/*VideoCapture vid = new VideoCapture(0);

		vid.set(Highgui.CV_CAP_PROP_FRAME_WIDTH, 160);

		vid.set(Highgui.CV_CAP_PROP_FRAME_HEIGHT, 120);

		vid.open(0);

		robot.say("Camera open");   
		
		Mat testFrame = new Mat();

		vid.read(testFrame);     

		if (!testFrame.empty()) {
			
			robot.say("Image Captured");
            Delay.msDelay(5000);

		}*/
		
		Robot.say("Uncomment the selfie code!");
		
		/*try {
            Video wc = BrickFinder.getDefault().getVideo();
            wc.open(160,120);
            byte[] frame = wc.createFrame();
            YUYVImage img = new YUYVImage(frame, wc.getWidth(), wc.getHeight());
            GraphicsLCD g = BrickFinder.getDefault().getGraphicsLCD();
            int threshold = 128;
            boolean auto = true;
            while (!Button.ESCAPE.isDown()) {
                wc.grabFrame(frame);
                if (auto)
                    threshold = img.getMeanY();
                img.display(g, 0, 0, threshold);
                if (Button.UP.isDown()) {
                    threshold++;
                    if (threshold > 255)
                        threshold = 255;
                    auto = false;
                }
                if (Button.DOWN.isDown()) {
                    threshold--;
                    if (threshold < 0)
                        threshold = 0;
                    auto = false;
                }
                if (Button.ENTER.isDown()) {
                    auto = true;
                }
            }
            wc.close();
            g.clear();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            robot.say("You messed up a a ron");
            System.out.println("Driver exception: " + ioe.getMessage());
        }*/
		
		//try {
		Robot.debug("Before video code");


			Robot.say("Camera open"); 
			
			Mat circles = new Mat();
			Mat bgrImage = new Mat();
			Robot.debug("isOpen" + vid.isOpened());
				vid.read(bgrImage);     

				Mat hsvImage = new Mat();
				
				// Convert input image to HSV
				if(!bgrImage.empty()) {
					Robot.debug("The image is not empty");
					Imgproc.cvtColor(bgrImage, hsvImage, Imgproc.COLOR_BGR2HSV);
					
					Mat lower_red_hue_range = new Mat();
					Mat upper_red_hue_range = new Mat();
					Core.inRange(hsvImage, new Scalar(0.0, 100.0, 100.0), new Scalar(10.0, 255.0, 255.0), lower_red_hue_range);
					Core.inRange(hsvImage, new Scalar(160.0, 100.0, 100.0), new Scalar(179.0, 255.0, 255.0), upper_red_hue_range);
					
					Mat red_hue_image = new Mat();
					Core.addWeighted(lower_red_hue_range, 1.0, upper_red_hue_range, 1.0, 0.0, red_hue_image);
					Imgproc.GaussianBlur(red_hue_image, red_hue_image, new Size(9, 9), 2, 2);
					
					

					Imgproc.HoughCircles(red_hue_image, circles, Imgproc.CV_HOUGH_GRADIENT, 1, red_hue_image.rows()/8, 100, 20, 0, 0);
					
					if(!circles.empty()) {
						Robot.say("Circles: " + circles.cols());
					}
					else {
						Robot.say("No circle found");
					}
					circles = new Mat();
			}

			//robot.say("Passed the camera code");
			
			/*if (!hsvImage.empty()) {
				robot.say("Image Captured");
	            Delay.msDelay(5000);

			}
			else {
				robot.say("Image Not Captured");
			}
			

			
            /*Video wc = BrickFinder.getDefault().getVideo();
            
            int x = 160;
            int y = 120;
            
            //The bounding box for our camera sensor
            int xMin = 70;
            int xMax = 90;
            int yMin = 50;
            int yMax = 70;
            int ySum = 0;
            int uSum = 0;
            int vSum = 0;
            wc.open(x,y);
            byte[] frame = wc.createFrame();
            YUYVImage img = new YUYVImage(frame, wc.getWidth(), wc.getHeight());
            while (!Button.ESCAPE.isDown()) {
            	wc.grabFrame(frame);
            	for(int i = xMin; i <= xMax; i++) {
            		for(int j = yMin; j <= yMax; j++) {
            			uSum += img.getU(i, j);
            			vSum += img.getV(i, j);
            			ySum += img.getY(i,j);
            		}
            	}
            	
            	uSum = uSum / 400;
            	vSum = vSum / 400;
            	ySum = ySum / 400;
            	Double B = 1.164 * (ySum - 16) + 2.018 * (uSum - 128);
            	Double G = 1.164 * (ySum - 16) - 0.813 * (vSum - 128) - 0.391 * (uSum - 128);
            	Double R = 1.164 * (ySum - 16) + 1.596* (vSum - 128);
            	String[] output = {"R:" + R,
            					   "G:" + G,
            					   "B:" + B};
                robot.say(output);
                Delay.msDelay(1000);
                uSum = 0;
                vSum = 0;
            }
            
            wc.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            robot.say("You messed up a a ron");
            System.out.println("Driver exception: " + ioe.getMessage());
        }*/
		
        //robot.say("picture displayed");
        
		
	}
	
	

}
