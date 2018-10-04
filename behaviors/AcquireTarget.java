package shooterbot.behaviors;

import org.opencv.core.Mat;

import shooterbot.robot.Robot;

public class AcquireTarget extends Behavior {

	private int delay;
	private Mat circleContainer;
	
	public AcquireTarget(Robot robot) {
		super(robot);
		delay = 350;
		circleContainer = new Mat();
	}

	@Override
	public void run() {
		//Rotate Left for 350ms
		robot.rotateLeft(delay);
		
		//Check for circles
		circleContainer = robot.getCircles();
		
		//If there is a circle change the robot behavior
		if(!circleContainer.empty()) {
			//Center Circle with the inRange Flag false
			robot.changeBehavior(new CenterCircle(robot, false));
		}
		
		circleContainer.release();
	}
}
