package shooterbot.behaviors;

import shooterbot.robot.Robot;

public class ShootStuffBehavior extends Behavior{

	public ShootStuffBehavior(Robot robot) {
		super(robot);
	}

	@Override
	public void run() {
		robot.fire();
		robot.reload();
	}
}