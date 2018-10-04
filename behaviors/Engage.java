
package shooterbot.behaviors;

import shooterbot.robot.Robot;


/*
 *  This Behavior describes how the robot will
 *  engage a target circle
 */  
public class Engage extends Behavior{

	public Engage(Robot robot) {
		super(robot);	
	}

	@Override
	public void run() {
		robot.fire();
		robot.rotateLeft(700);
		if(robot.getAmmo() > 0) {
			robot.changeBehavior(new AcquireTarget(robot));
		} else {
			robot.changeBehavior(new AcquireTarget(robot));
		}
	}

}
