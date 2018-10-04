
package shooterbot.behaviors;

import shooterbot.robot.Robot;


/*
 *  This Behavior describes how the robot will
 *  approach a target circle
 */  
public class Approach extends Behavior {

	//Boolean that describes if the robot is inRange of a circle
	private boolean inRange;
	
	//Boolean that describes whether the robot is moving or not
	private boolean moving;
	
	/*
	 *  Constructor initializes the behavior
	 */  
	public Approach(Robot robot) {
		super(robot);
		inRange = false;
		moving = true;
		Robot.playSound(Robot.Wav.APPROACHING);
	}

	/*
	 *  Algorithm for approaching a target circle
	 *  this method will be called repeatedly in
	 *  the main loop
	 */  
	@Override
	public void run() {
		if(robot.findRangeToObject() != Float.POSITIVE_INFINITY) {
			robot.halt();
			moving = false;
			inRange = true;		
			robot.changeBehavior(new CenterCircle(robot, true));
		} else if(moving == false && inRange == false) {
			robot.moveForward();
			moving = true;
		}		
	}

}
