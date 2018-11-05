package shooterbot.main;

import java.io.File;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.ev3.tools.EV3Console;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import shooterbot.behaviors.*;
import shooterbot.robot.Robot;

public class Main {
    static UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
    static UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);
    static EV3ColorSensor        color = new EV3ColorSensor(SensorPort.S1);
    
	public static boolean executeProgram = true;
	
	public static void main(String[] args) {
//		////This line has to be the first line in the program!!!////
//		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//		
//		//Setup the screen to start on button press
//		displayStartScreen();
//		
//		//Initialize the robot
//		Robot robot = new Robot();
//		
//		//Initialize the start behavior 
//		Behavior colorTest = (Behavior) new ColorTest(robot);
//		
//		//Initialize the behavior of the robot
//		robot.changeBehavior(colorTest);
//
//		//Main loop of program
//		while(executeProgram || !Button.ESCAPE.isDown()) {
//			robot.runBehavior();
//			System.gc();
//		}
//		displayEndScreen();
		
		SampleProvider    colorValue;
        float[] lineSample = new float[3];
        float[] colorSample = new float[3];
        System.out.println("Line Follower\n");
        
        color.getRedMode();
        color.setFloodlight(Color.RED);
        color.setFloodlight(true);

        Button.LEDPattern(4);    // flash green led and 
        Sound.beepSequenceUp();  // make sound when ready.

        System.out.println("Press any key to start");
        
        Button.waitForAnyPress();
        
        motorA.setPower(40);
        motorB.setPower(40);
       
        // drive waiting for touch sensor or escape key to stop driving.

        boolean side = false;
        while (Button.ESCAPE.isUp()) 
        {
            colorValue = color.getRGBMode();
            colorValue.fetchSample(colorSample, 0);
            color.setFloodlight(Color.WHITE);
            color.setFloodlight(true);
            Robot.say("R " + colorSample[0]);
            Robot.say("G " + colorSample[1]);
            Robot.say("B " + colorSample[2]);
            
            Robot.say("");
            
            motorA.setPower(50);
            motorB.setPower(-50);
            
//            if(colorSample[0] < 0.06) {
//            	colorValue = color.getRedMode();
//                colorValue.fetchSample(lineSample, 0);
//
//                if (lineSample[0] > .100)
//                {
//                    motorA.setPower(50);
//                    motorB.setPower(47);
//                }
//                else
//                {
//                    motorA.setPower(-50);    
//                    motorB.setPower(50);
//                }
//                
//                colorValue = color.getRGBMode();
//                colorValue.fetchSample(lineSample, 0);
//                color.setFloodlight(Color.WHITE);
//                color.setFloodlight(true);
//                Robot.say("R " + lineSample[0]);
//                Robot.say("G " + lineSample[1]);
//                Robot.say("B " + lineSample[2]);
//                
//                Robot.say("");
//            }
//            else {
//            	motorA.setPower(100);
//                motorB.setPower(-100);
//            }
        }
       
        // stop motors with brakes on.
        motorA.stop();
        motorB.stop();

        // free up resources.
        motorA.close();
        motorB.close();
        color.close();
       
        Sound.beepSequence(); // we are done.
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
