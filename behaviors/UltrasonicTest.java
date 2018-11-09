package shooterbot.behaviors;

import lejos.utility.Delay;
import shooterbot.robot.Robot;

public class UltrasonicTest extends Behavior {

	public UltrasonicTest(Robot robot) {
		super(robot);
		
	}

	@Override
	public void run() {
		Robot.say("Left: " + robot.getLeftWallDistance());
		Robot.say("Right: " + robot.getRightWallDistance());
		Robot.say("");
		Delay.msDelay(1000);
	}

}
