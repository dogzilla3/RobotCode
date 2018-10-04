package shooterbot.main;

import java.io.File;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import shooterbot.behaviors.*;
import shooterbot.robot.Robot;

public class Main {
	
	public static boolean executeProgram = true;
	
	public static void main(String[] args) {
		////This line has to be the first line in the program!!!//////
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		//Initialize the robot
		Robot bot = new Robot();
		
		//Initialize the start behavior 
		//CameraTestRefactor cameraTestRefactor = new CameraTestRefactor(shooterBot);
		
		File soundFile = new File("assets/test.wav");
		
		//Setup the screen to start on button press
		LCD.clear();	
		Button.LEDPattern(1);
		Sound.beepSequenceUp();
		LCD.drawString("Program Start!", 1, 1);
		LCD.drawString("Press any key to begin", 1, 2);	
		Button.waitForAnyPress();
		LCD.clearDisplay();

		//Old camera code
		int delay = 350;

		//Main loop of program
		while(executeProgram || !Button.ESCAPE.isDown()) {
			
		}

		while(true) {
			if(soundFile.exists()) {
				Robot.playSound(soundFile);
			} else {
				Robot.beep();
				Robot.beep();
				Robot.beep();
				Robot.beep();
			}
			
			
			Mat circles = new Mat();
			
			

			while(circles.empty()) {
				bot.rotateLeft(delay);
				for (int i = 0; i < 4; i++) {
					System.gc();
					circles = bot.getCircles();
					if(!circles.empty()) {
						double[] circle = circles.get(0, 0);
						
						Robot.say("C:" + circles.cols() + "F:" + (i + 1) + "X:" + circle[0]);
						//center here
						
					
						
						while(circle[0] < 310 || circle[0] > 330) {
							if(circle[0] < 310) {
								Robot.say("Rotate Left");
								bot.rotateLeft((int)(320 - circle[0]));
								Robot.say("C:" + circles.cols() + "F:" + (i + 1) + "X:" + circle[0]);
							} else if(circle[0] > 330) {
								Robot.say("Rotate Right");
								bot.rotateRight((int)(circle[0] - 320));
								Robot.say("C:" + circles.cols() + "F:" + (i + 1) + "X:" + circle[0]);
							}
							for (int j = 0; j < 4; j++) {
								circles = bot.getCircles();
								if(!circles.empty()) {
									Robot.say("Break");
									break;
								}
								
							}
							
						}

						
						//center here
					} else {
						Robot.say(new String[] {"Frame: " + (i + 1)} );
					}
				}
			}
			
			
			
			/*for (int i = 0; i < 4; i++) {
				System.gc();
				circles = shooterBot.getCircles();
				
				if(!circles.empty()) {
					Robot.say("C: " + circles.cols() + "F: " + (i + 1));
					//shooterBot.Beep();
					
					//shooterBot.fire();
					break;
				} else {
					Robot.say(new String[] {"Frame: " + (i + 1)} );
				}
			}*/
			
			while(bot.findRangeToObject() == Float.POSITIVE_INFINITY) {
				bot.moveForward();
				Robot.say(Float.toString(bot.findRangeToObject()));
				
			}
			bot.halt();
			bot.fire();

			//shooterBot.rotateLeft(350);
			
		}
		
		/*Robot.Debug("Circles: " + circles.cols());
		//New camera sensor code********************************************
		//Mat circles = shooterBot.getCircles();
		//shooterBot.say("" + circles.cols());
		
		/*if(circles.empty()) {
			aquireTarget.aquire();
			do {
				circles = shooterBot.getCircles();
			}while(circles.empty());
			aquireTarget.aquired();
		}*/
		
		
		/*Button.LEDPattern(2);
		Sound.beepSequence();
		Button.waitForAnyPress();*/
		
		
	}

}
