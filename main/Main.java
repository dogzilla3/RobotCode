package shooterbot.main;

import java.io.File;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.ev3.tools.EV3Console;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import shooterbot.behaviors.*;
import shooterbot.robot.Robot;

public class Main {
	
	public static boolean executeProgram = true;
	
	public static void main(String[] args) {
		////This line has to be the first line in the program!!!////
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		//Setup the screen to start on button press
		displayStartScreen();
		
		//Initialize the robot
		Robot robot = new Robot();
		
		//Initialize the start behavior 
		//Behavior acquireTarget = (Behavior) new AcquireTarget(robot);
		
		//Initialize the behavior of the robot
		//robot.changeBehavior(acquireTarget);

		EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);
		SensorMode colorIdMode = colorSensor.getColorIDMode();
		colorSensor.setFloodlight(true);
		float[] samples = new float[1];
		
		String[] testArray = new String[3];
		testArray[0] = "test0";
		testArray[1] = "test1";
		testArray[2] = "test2";
		
		//Main loop of program
		while(executeProgram || !Button.ESCAPE.isDown()) {
			//robot.runBehavior();
			System.gc();
			colorIdMode.fetchSample(samples, 0);
			
			Robot.say(testArray);
			robot.moveForward();
			//Robot.say("" + samples[0]);
		}
		displayEndScreen();
	}
		
	/*
	 *  Requires user input to start the program
	 *  This is to make sure the robot is ready
	 *  to begin
	 */  
	private static void displayStartScreen() {
		LCD.clear();	
		Button.LEDPattern(1);
		Sound.beepSequenceUp();
		LCD.drawString("Program Start!", 1, 1);
		LCD.drawString("Press any key to begin", 1, 2);	
		Button.waitForAnyPress();
		LCD.clearDisplay();
	}
	
	/*
	 *  Signals the end of the program
	 */  
	private static void displayEndScreen() {
		Button.LEDPattern(2);
		Sound.beepSequence();
		Button.waitForAnyPress();
	}
}
