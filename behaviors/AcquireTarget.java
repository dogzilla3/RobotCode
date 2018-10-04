
package shooterbot.behaviors;

import org.opencv.core.Mat;

import shooterbot.robot.Robot;


/*
 *  This Behavior describes how the robot will
 *  acquire a target circle
 */  
public class AcquireTarget extends Behavior {

	//Delay in milli-seconds
	private int delay;
	
	//Container for circles
	private Mat circleContainer;
	
	/*
	 *  Constructor that initializes the behavior
	 */  
	public AcquireTarget(Robot robot) {
		super(robot);
		delay = 350;
		circleContainer = new Mat();
	}

	/*
	 *  Algorithm to acquire target this method will 
	 *  be called repeatedly in the main loop
	 */  
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
		
		//Release the matrix due to the Mat memory leak, itll get gcd in the main loop
		circleContainer.release();
	}
}
