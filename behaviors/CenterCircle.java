
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
	private final int leftBound = 310;
	
	//This right bound describes the right most edge of the center band
	private final int rightBound = 330;
	
	//This represents the center of the camera frame
	private final int center = 320;
	
	//This matrix contains holds a circle
	private Mat circleContainer;
	
	//This boolean represents whether or not the robot is in range of a target
	private boolean inRange;
	
	/*
	 *  Constructor that initializes the behavior
	 */  
	public CenterCircle(Robot robot, boolean inRange) {
		super(robot);
		circleContainer = new Mat();
		this.inRange = inRange;
	}

	/*
	 *  Algorithm to center the robot on a circle
	 *  this method will be called repeatedly in 
	 *  the main loop
	 */  
	@Override
	public void run() {
		//Get the circles from the camera
		circleContainer = robot.getCircles();
		
		//If there are circles center them
		if(!circleContainer.empty()) {
			circle = circleContainer.get(0, 0);
			if(circle[0] < leftBound) {
				Robot.say("Rotate Left");
				robot.rotateLeft((int)(center - circle[0]));
			} else if(circle[0] > rightBound) {
				Robot.say("Rotate Right");
				robot.rotateRight((int)(circle[0] - center));
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
	}
}

