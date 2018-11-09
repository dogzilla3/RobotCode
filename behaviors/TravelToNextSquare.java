package shooterbot.behaviors;

import shooterbot.robot.Robot;

public class TravelToNextSquare extends Behavior {

	private int lastColor;
	private boolean firstRed = true;

	public TravelToNextSquare(Robot robot) {
		super(robot);
		robot.forward();
		lastColor = 2;
	}

	@Override
	public void run() {
		Robot.say("Id" + robot.getColorId());

		int color = robot.getColorId();

		if (color == 7) {
			robot.setLeftMotorSpeed(200f);
			robot.setRightMotorSpeed(175f);
		} else if (color == 2) {
			robot.setLeftMotorSpeed(175f);
			robot.setRightMotorSpeed(200f);
		} else if (color == 6) {
			if (lastColor == 7) {
				robot.setLeftMotorSpeed(175f);
				robot.setRightMotorSpeed(200f);
			} else if (lastColor == 2) {
				robot.setLeftMotorSpeed(200f);
				robot.setRightMotorSpeed(175f);
			}
		} else if (color == 0 && firstRed == true) {
			
			firstRed = false;
			
		} else if(firstRed == false && lastColor == 0) 
		{
			robot.setLeftMotorSpeed(200f);
			robot.setRightMotorSpeed(200f);
			
		}
			else if (lastColor != 0 && firstRed == false && color == 0) {
			
			robot.halt();
			robot.changeBehavior(new TurnLeft(robot));
			firstRed = true;
			
		}
		else {
			robot.setLeftMotorSpeed(200f);
			robot.setRightMotorSpeed(200f);
		}

		lastColor = color;
	}
}
