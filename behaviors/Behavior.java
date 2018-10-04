
package shooterbot.behaviors;

import shooterbot.robot.Robot;


/*
 *  The super class for behaviors, this is the implementation
 *  of the strategy design pattern. All behaviors are 
 *  interchangeable. They are swapped at run time using
 *  robot.changeBehavior()
 */  
public abstract class Behavior {
	
	//Keep an instance variable for the robot
	public Robot robot;
	
	/*
	 *  Constructor for a behavior ensures the robot instance
	 */  
	public Behavior(Robot robot) {
		this.robot = robot;
	}
	
	//This method will hold the program logic
	public abstract void run();
}
