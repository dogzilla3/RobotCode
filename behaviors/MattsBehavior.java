package shooterbot.behaviors;

import shooterbot.robot.Robot;

public class MattsBehavior extends Behavior{

	public MattsBehavior(Robot robot) {
		super(robot);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(robot.findRangeToObject() >= .2) {
			getTarget();
			moveToTarget();
		}
		backAwayFromTarget();
		while(robot.findRangeToObject() >= .2) {
			getTarget();
			moveToTarget();
		}
		backAwayFromTarget();
		while(robot.findRangeToObject() >= .2) {
			getTarget();
			moveToTarget();
		}
		backAwayFromTarget();
		
	}
	
	public void getTarget() {
		boolean noTarget = true;
		
		while(noTarget) {
			robot.rotateLeft();
			robot.say(Float.toString(robot.findRangeToObject()));
			if(robot.findRangeToObject() != Float.POSITIVE_INFINITY) {
				noTarget = false;
			}
		}
		
	}
	
	public void moveToTarget() {
		boolean noTarget = false;
		
		while(!noTarget) {
			robot.moveForward();
			robot.say(Float.toString(robot.findRangeToObject()));
			if(robot.findRangeToObject() == Float.POSITIVE_INFINITY) {
				noTarget = true;
			}
			else if (robot.findRangeToObject() <= .2) {
				noTarget = true;
			}
		}
	}
	
	public void backAwayFromTarget() {
		boolean isRanged = false;
		robot.halt();
		while(!isRanged) {
			robot.moveBackward();
			robot.say(Float.toString(robot.findRangeToObject()));
			if(robot.findRangeToObject() >= 1) {
				robot.halt();
				isRanged = true;
				robot.fire();
			}
		}
		
		
	}
}
