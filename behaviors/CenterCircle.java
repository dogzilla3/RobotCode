package shooterbot.behaviors;

import org.opencv.core.Mat;

import shooterbot.robot.Robot;

public class CenterCircle extends Behavior {

	private double[] circle;
	private final int leftBound = 310;
	private final int rightBound = 310;
	private final int center = 320;
	private Mat circleContainer;
	private boolean inRange;
	
	public CenterCircle(Robot robot, boolean inRange) {
		super(robot);
		circleContainer = new Mat();
		this.inRange = inRange;
	}

	@Override
	public void run() {
		//Get the circles from the camera
		circleContainer = robot.getCircles();
		
		//If there are circles center them
		if(!circleContainer.empty()) {
			circle = circleContainer.get(0, 0);
			if(circle[0] < leftBound) {
				Robot.say("Rotate Left");
				robot.rotateLeft((int)(center - circle[0]));
			} else if(circle[0] > rightBound) {
				Robot.say("Rotate Right");
				robot.rotateRight((int)(circle[0] - center));
			} else {
				Robot.say("Centered");
				robot.changeBehavior(new Approach(robot));
			}
		//There are no circles acquire some
		} else {
			robot.changeBehavior(new AcquireTarget(robot));
		}
		
	}
}

