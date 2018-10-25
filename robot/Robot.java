package shooterbot.robot;


import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.utility.Delay;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import shooterbot.behaviors.Behavior;
import shooterbot.robot.locomotion.MotorController;
import shooterbot.robot.sensors.Camera;
import shooterbot.robot.sensors.UltrasonicSensor;
import shooterbot.robot.turret.TurretController;
import java.io.File;
import org.opencv.core.Mat;


public class Robot {
	
	/*
	 * Motor ports: left drive 	A
	 * 				right drive B
	 * 				turret 		C
	 * 
	 * Sensor ports: Ultrasonic 1
	 */
	
	
	/* TODO:
	 * We may be able to open a new thread to run behaviors.
	 * This would allow us to run concurrent behaviors, ie
	 * move the robot and use the sensor, shoot the turret.
	 */
	
	/* 
	 * Controls the motors, need to hide the two different 
	 * motors and control them both through this
	 */
	private MotorController motorController;
	
	//Controls the turret
	private TurretController turretController;
	
	//The Ultrasonic sensor
	private EV3ColorSensor ultrasonicSensor;
	
	//A reference to the current behavior
	private Behavior behavior;
	
	//A reference to camera
	private Camera camera;
	private static File searchingSound = new File("zSearching.wav");
	private static File centeringSound = new File("zCentering.wav");
	private static File approachingSound = new File("zApproaching.wav");
	private static File engagingSound = new File("zEngaging.wav");
	
	public static enum Sounds{
		UP,
		DOWN;
	}
	
	public static enum Wav{
		SEARCHING,
		CENTERING,
		APPROACHING,
		ENGAGING;
	}
	/* 
	 * The robot constructor sets up the parts of our robot
	 * - Motor Controller
	 * - Turret Controller
	 * - Ultrasonic sensor
	 * - Camera
	 */
	public Robot(){
		this.motorController = new MotorController();
		//this.ultrasonicSensor = new EV3ColorSensor(SensorPort.S1);
		//this.turretController = new TurretController(); 
		this.camera = new Camera();
	}
	
	
	
	public void runBehavior() {
		if(behavior != null) {
			behavior.run();
		} else {
			say("I dont know what to do!");
		}
	}
	
	public void changeBehavior(Behavior newBehavior) {
		this.behavior = newBehavior;
	}
	
	public void moveForward() {
		motorController.forward();
	}
	
	public void moveBackward() {
		motorController.backward();
	}
	
	public void halt() {
		motorController.halt();
	}
	
	public void rotateLeft() {
		motorController.rotateLeft();
	}
	
	public void rotateRight() {
		motorController.rotateRight();
	}
	
	public void rotateLeft(int delay) {
		motorController.rotateLeft();
		Delay.msDelay(delay);
		motorController.halt();
	}
	
	public void rotateRight(int delay) {
		motorController.rotateRight();
		Delay.msDelay(delay);
		motorController.halt();
	}
	
	public static void say(String message) {
		System.out.println(message);
		clearDisplay(7);
	}
	
	public static void say(String[] messages) {
		for (int i=0; i < messages.length; i++) { 
			System.out.println("- " + messages[i]);
		}
		if(8 - messages.length > 0) {
			clearDisplay(8 - messages.length);
		}
	}

	
	public void beep(Sounds sound) {
        switch (sound) { 
        case UP: Sound.beepSequenceUp(); break; 
        case DOWN: Sound.beepSequence(); break; 
        default: Sound.beep(); break; 
        } 
	}
	
	public static void beep() {
		Sound.beep();
	}
	
	public float findRangeToObject() {
		return 1f;
	}
	
	
	public float[] findMultipleRanges() {
		return new float[3];
	}
	
	public void fire() {
		turretController.fire();
	}
	
	public static void debug(String message) {
		LCD.clearDisplay();
		Sound.beep();
		say(message);
		Button.LEDPattern(9);
		Button.waitForAnyPress();
		Button.LEDPattern(1);
	}
	
	public static void playSound(Wav wav) {
		switch(wav) {
			case SEARCHING: Sound.playSample(searchingSound, Sound.VOL_MAX); break; 
	        case CENTERING: Sound.playSample(centeringSound, Sound.VOL_MAX); break; 
			case APPROACHING: Sound.playSample(approachingSound, Sound.VOL_MAX); break; 
	        case ENGAGING: Sound.playSample(engagingSound, Sound.VOL_MAX); break; 
	        default: Sound.beep(); break; 
		}
	}
	
	public Mat getCircles() {
		return camera.getCircles();
	}
	
	public int getAmmo() {
		return turretController.getAmmo();
	}
	
	public static void clearDisplay(int lines) {
		for (int i=0; i < lines; i++) { 
			System.out.println("");
		}
	}
}
