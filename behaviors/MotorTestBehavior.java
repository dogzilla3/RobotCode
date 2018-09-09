package shooterbot.behaviors;

import lejos.utility.Delay;
import shooterbot.robot.Robot;

//Test behavior for the motors
public class MotorTestBehavior extends Behavior{

	public MotorTestBehavior(Robot robot) {
		super(robot);
	}

	@Override
	public void run() {
		robot.moveForward();
		Delay.msDelay(5000);
		robot.moveBackward();
		Delay.msDelay(5000);
		robot.rotateLeft();
		Delay.msDelay(5000);
		robot.rotateRight();
		Delay.msDelay(5000);
		robot.halt();
		Delay.msDelay(5000);
	}
}
