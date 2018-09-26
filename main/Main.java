package shooterbot.main;

import org.opencv.core.Core;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import shooterbot.behaviors.*;
import shooterbot.robot.Robot;

public class Main {
	
	public static void main(String[] args) {
		
		////This line has to be the first line in the program!!!//////
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		//Initialize the robot
		Robot shooterBot = new Robot();
		
		//Initialize the behaviors that the robot will do
		CameraTestRefactor cameraTestRefactor = new CameraTestRefactor(shooterBot);
		
		//Setup the screen to start on button press
		LCD.clear();	
		Button.LEDPattern(1);
		Sound.beepSequenceUp();
		LCD.drawString("Program Start!", 1, 1);
		LCD.drawString("Press any key to begin", 1, 2);	
		Button.waitForAnyPress();

		shooterBot.changeBehavior(cameraTestRefactor);
		
		cameraTestRefactor.startCamera();
		
		while(true) {
			if(Button.ESCAPE.isDown()) {
				cameraTestRefactor.stopService();
				break;
			}
		}
		
		
		Button.LEDPattern(2);
		Sound.beepSequence();
		LCD.drawString("Program end!", 1, 1);
		LCD.drawString("Press any key to begin", 1, 2);
		Button.waitForAnyPress();
	}

}
