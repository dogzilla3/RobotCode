
package shooterbot.behaviors;

import org.opencv.core.Mat;

import shooterbot.robot.Robot;


/*
 *  This Behavior describes how the robot will
 *  approach a target circle
 */  
public class Approach extends Behavior {

	//Container for circles
	private Mat circleContainer;
	
	/*
	 *  Constructor initializes the behavior
	 */  
	public Approach(Robot robot) {
		super(robot);
		Robot.playSound(Robot.Wav.APPROACHING);
	}

	/*
	 *  Algorithm for approaching a target circle
	 *  this method will be called repeatedly in
	 *  the main loop
	 */  
	@Override
	public void run() {
		float range = robot.findRangeToObject();
		Robot.say("" + range);
		if(range != Float.POSITIVE_INFINITY) {
			robot.halt();
			circleContainer = robot.getCircles();
			if(!circleContainer.empty()) {
				robot.halt();
				robot.changeBehavior(new CenterCircle(robot, true, circleContainer));
			} else {
				robot.halt();
				robot.changeBehavior(new AcquireTarget(robot));
			}

		}else {
			robot.moveForward();
		}
		
	}

}
