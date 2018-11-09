package shooterbot.behaviors;

import shooterbot.robot.Robot;

public class RunMapScan extends Behavior {

	public RunMapScan(Robot robot) {
		super(robot);
		Robot.say("Run map scan here" );
	}

	@Override
	public void run() {
		robot.changeBehavior(new TurnLeft(robot));
	}

}
