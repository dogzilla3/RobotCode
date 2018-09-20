package shooterbot.main;

import org.opencv.core.Core;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import shooterbot.behaviors.*;
import shooterbot.robot.Robot;

public class Main {
	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		//Initialize the robot
		Robot shooterBot = new Robot();
		
		//Initialize the behaviors that the robot will do
		Behavior helloWorld = new HelloWorldBehavior(shooterBot);
		Behavior motorTest = new MotorTestBehavior(shooterBot);
		Behavior shootStuff = new ShootStuffBehavior(shooterBot);
		Behavior oldOne = new OurOldBehavior(shooterBot);
		Behavior mattsCode = new MattsBehavior(shooterBot);
		Behavior cameraTest = new CameraTest(shooterBot);
		
		//Setup the screen to start on button press
		LCD.clear();	
		Button.LEDPattern(4);
		Sound.beepSequenceUp();
		LCD.drawString("Program Start!", 1, 1);
		LCD.drawString("Press any key to begin", 1, 2);
			
		Button.waitForAnyPress();
		
		//-----Test Behaviors-----
		
		//Change the behavior to hello world and execute
		/*shooterBot.changeBehavior(helloWorld);
		shooterBot.runBehavior();*/
		
		//Change the behavior to motor test and execute
		/*shooterBot.changeBehavior(motorTest);
		shooterBot.runBehavior();
		
		//Change the behavior to shoot stuff and execute
		shooterBot.changeBehavior(shootStuff);
		shooterBot.runBehavior();*/
		
		//shooterBot.changeBehavior(mattsCode);
		//shooterBot.runBehavior();
		
		shooterBot.changeBehavior(cameraTest);
		shooterBot.runBehavior();
		
		Button.waitForAnyPress();
		
	}

}
