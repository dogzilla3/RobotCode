package shooterbot.behaviors;

import shooterbot.robot.Robot;

public class TurnLeft extends Behavior{

	private int lastColor;
	
	
	public TurnLeft(Robot robot) {
		super(robot);
		lastColor = 0;
		robot.setLeftMotorSpeed(200f);
		robot.setRightMotorSpeed(200f);
		robot.turnLeft();
	}

	@Override
	public void run() {
		int color = robot.getColorId();

		if (lastColor != 0 && color == 0) {
			robot.halt();
		}
		
		lastColor = color;
	}

}
