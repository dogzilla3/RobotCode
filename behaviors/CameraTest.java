package shooterbot.behaviors;

import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

import shooterbot.robot.Robot;

public class CameraTest extends Behavior{

	public CameraTest(Robot robot) {
		super(robot);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void run() {
		
		VideoCapture vid = new VideoCapture(0);

		vid.set(Highgui.CV_CAP_PROP_FRAME_WIDTH, 160);

		vid.set(Highgui.CV_CAP_PROP_FRAME_HEIGHT, 120);

		vid.open(0);

		robot.say("Camera open");   
		
	}

}
