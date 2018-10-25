
package shooterbot.behaviors;

import org.opencv.core.Mat;

import shooterbot.robot.Robot;


/*
 *  This Behavior describes how the robot will
 *  center itself on a target circle
 */  
public class CenterCircle extends Behavior {

	//This double array holds the x, y, r components of a circle
	private double[] circle;
																
	//This left bound describes the left most edge of the center band
	private final int leftBound = 280;
	
	//This right bound describes the right most edge of the center band
	private final int rightBound = 360;
	
	//This represents the center of the camera frame
	private final int center = 320;
	
	//This matrix contains holds a circle
	private Mat circleContainer;
	
	//This boolean represents whether or not the robot is in range of a target
	private boolean inRange;
	
	/*
	 *  Constructor that initializes the behavior
	 */  
	public CenterCircle(Robot robot, boolean inRange, Mat foundCircle) {
		super(robot);
		circleContainer = foundCircle;
		this.inRange = inRange;
		Robot.playSound(Robot.Wav.CENTERING);
		//Robot.debug("C" + circleContainer.cols());
	}

	/*
	 *  Algorithm to center the robot on a circle
	 *  this method will be called repeatedly in 
	 *  the main loop
	 */  
	@Override
	public void run() {
	
		circleContainer = new Mat();
		//Get the circles from the camera
		circleContainer = robot.getCircles();
		circle = circleContainer.get(0, 0);
		//Robot.debug("X: " + circle[0] + "Y: " + circle[1]);
		
		if(!circleContainer.empty()) {
			//If there are circles center them
			if(circle[0] < leftBound) {
				Robot.say("Rotate Left");
				robot.rotateLeft((int)((center - circle[0]))/2);
				//Get the circles from the camera
				circleContainer = robot.getCircles();
			} else if(circle[0] > rightBound) {
				Robot.say("Rotate Right");
				robot.rotateRight((int)((circle[0] - center))/2);
				//Get the circles from the camera
				circleContainer = robot.getCircles();
			} else {
				Robot.say("Centered");
				if(inRange) {
					robot.changeBehavior(new Engage(robot));
				} else {
					robot.changeBehavior(new Approach(robot));
				}		
			}
		//If there are no circles acquire some
		} else {
			robot.changeBehavior(new AcquireTarget(robot));
		}	
		
		circleContainer.release();
	}
}

