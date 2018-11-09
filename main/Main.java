package shooterbot.main;

import java.io.File;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.ev3.tools.EV3Console;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import shooterbot.behaviors.*;
import shooterbot.robot.Robot;
import shooterbot.robot.sensors.UltrasonicSensor;

public class Main {
	
	public static void main(String[] args) {
		
		//Setup the screen to start on button press
		displayStartScreen();
		
		//Initialize the robot
		Robot robot = new Robot();
		
		Behavior testing = new TravelToNextSquare(robot);
		//Behavior testing1 = new TurnLeft(robot);
		
		//Initialize the behavior of the robot
		robot.changeBehavior(testing);

		//Main loop of program
		while(!Button.ESCAPE.isDown()) {
			robot.runBehavior();
			System.gc();
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
