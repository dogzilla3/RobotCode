package shooterbot.behaviors;

import shooterbot.robot.Robot;

public class Approach extends Behavior {

	private boolean inRange;
	private boolean moving;
	
	public Approach(Robot robot) {
		super(robot);
		inRange = false;
		moving = true;
	}

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
