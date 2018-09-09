package shooterbot.main;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import shooterbot.behaviors.*;
import shooterbot.robot.Robot;

public class Main {
	
	public static void main(String[] args) {
		
		//Initialize the robot
		Robot shooterBot = new Robot();
		
		//Initialize the behaviors that the robot will do
		Behavior helloWorld = new HelloWorldBehavior(shooterBot);
		Behavior motorTest = new MotorTestBehavior(shooterBot);
		Behavior shootStuff = new ShootStuffBehavior(shooterBot);
		
		//Setup the screen to start on button press
		LCD.clear();	
		Button.LEDPattern(4);
		Sound.beepSequenceUp();
		LCD.drawString("Program Start!", 1, 1);
		LCD.drawString("Press any key to begin", 1, 2);
		
		
		//-----Test Behaviors-----
		
		//Change the behavior to hello world and execute
		shooterBot.changeBehavior(helloWorld);
		shooterBot.runBehavior();
		
		//Change the behavior to motor test and execute
		shooterBot.changeBehavior(motorTest);
		shooterBot.runBehavior();
		
		//Change the behavior to shoot stuff and execute
		shooterBot.changeBehavior(shootStuff);
		shooterBot.runBehavior();
	}

}
