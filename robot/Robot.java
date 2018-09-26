package shooterbot.robot;


//Lejos imports
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import shooterbot.behaviors.Behavior;
import lejos.hardware.Button;
import lejos.hardware.Sound;

//Regular imports
import shooterbot.robot.locomotion.MotorController;
import shooterbot.robot.sensors.UltrasonicSensor;
import shooterbot.robot.turret.TurretController;

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
	private UltrasonicSensor ultrasonicSensor;
	
	//A reference to the current behavior
	private Behavior behavior;
	
	/* 
	 * The robot constructor sets up the parts of our robot
	 * - Motor Controller
	 * - Ultrasonic sensor
	 * - Turret Controller
	 */
	public Robot(){
		this.motorController = new MotorController();
		this.ultrasonicSensor = new UltrasonicSensor(SensorPort.S1);
		this.turretController = new TurretController(); 
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
	
	public void rotateLeft() {
		motorController.rotateLeft();
	}
	
	public void rotateRight() {
		motorController.rotateRight();
	}
	
	public void halt() {
		motorController.halt();
	}
	
	public void say(String message) {
		LCD.clearDisplay();
		LCD.drawString(message, 1, 1);
	}
	
	public void say(String[] messages) {
		LCD.clear();
		for (int i=0; i < messages.length; i++) { 
			LCD.drawString(messages[i], 1, i+1);
		}
	}
	
	public static enum Sounds{
		UP,
		DOWN;
	}
	
	public void Beep(Sounds sound) {
        switch (sound) { 
        case UP: Sound.beepSequenceUp(); break; 
        case DOWN: Sound.beepSequence(); break; 
        default: Sound.beep(); break; 
        } 
	}
	
	public void Beep() {
		Sound.beep();
	}
	
	public float findRangeToObject() {
		return ultrasonicSensor.getRange();
	}
	
	
	public float[] findMultipleRanges() {
		return ultrasonicSensor.getRanges();
	}
	
	public void fire() {
		turretController.fire();
		turretController.reload();
	}
	
	public void reload() {
		turretController.reload();
	}
	
	public void Debug(String message) {
		LCD.clearDisplay();
		say(message);
		Button.LEDPattern(9);
		Button.waitForAnyPress();
		Button.LEDPattern(1);
	}
}
