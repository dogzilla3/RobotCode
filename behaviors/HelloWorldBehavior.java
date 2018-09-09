package shooterbot.behaviors;

import shooterbot.robot.Robot;

//Test hello world behavior
public class HelloWorldBehavior extends Behavior{

	public HelloWorldBehavior(Robot robot) {
		super(robot);
	}

	@Override
	public void run() {
		robot.say("Hello World");
	}
}
