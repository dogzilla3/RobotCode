package shooterbot.behaviors;



import java.io.IOException;

import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

import lejos.hardware.video.Video;
import lejos.hardware.video.YUYVImage;
import lejos.utility.Delay;
import shooterbot.robot.Robot;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.lcd.GraphicsLCD;

public class CameraTest extends Behavior{

	public CameraTest(Robot robot) {
		super(robot);
		// TODO Auto-generated constructor stub
		
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
		
		robot.say("Uncomment the selfie code!");
		
		try {
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
        }
		
        robot.say("picture displayed");
        
		
	}

}
