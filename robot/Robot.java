package shooterbot.robot;


import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import shooterbot.behaviors.Behavior;
import shooterbot.robot.sensors.ColorSensor;
import shooterbot.robot.sensors.IRSensor;
import shooterbot.robot.sensors.UltrasonicSensor;
import java.io.File;


public class Robot {

	
	private EV3LargeRegulatedMotor leftDriveMotor;
	private EV3LargeRegulatedMotor rightDriveMotor;	
	private final float DEFAULT_SPEED = 200f;
	
	private UltrasonicSensor leftWallSensor;
	private UltrasonicSensor rightWallSensor;
	private IRSensor frontWallSensor;
	private ColorSensor colorSensor;
	
	private Behavior behavior;

	private static File searchingSound = new File("zSearching.wav");

	public static enum Sounds{ UP, DOWN; }
	public static enum Wav{ SEARCHING, CENTERING, APPROACHING, ENGAGING; }
	public static enum Orientation{ NORTH, SOUTH, EAST, WEST; }
	
	public Orientation currentOrientation;
	
	public Robot(){
		this.colorSensor = new ColorSensor(SensorPort.S1);
		this.leftWallSensor = new UltrasonicSensor(SensorPort.S3);
		this.rightWallSensor = new UltrasonicSensor(SensorPort.S4);
		//this.frontWallSensor = new IRSensor(SensorPort.S4);
		leftDriveMotor = new EV3LargeRegulatedMotor(MotorPort.A);
		rightDriveMotor = new EV3LargeRegulatedMotor(MotorPort.B);
		BaseRegulatedMotor[] array = {rightDriveMotor};
		leftDriveMotor.synchronizeWith(array);
		leftDriveMotor.setSpeed(200f);
		rightDriveMotor.setSpeed(200f);
		currentOrientation = Orientation.NORTH;
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
	
	public static void say(String message) {
		System.out.println(message);
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
	
	public float getLeftWallDistance() {
		return leftWallSensor.getRange();
	}
	
	
	public float getRightWallDistance() {
		return rightWallSensor.getRange();
	}
	
	public float getFrontWallDistance() {
		//THis needs to change
		return 0f;
	}
	
	public float[] getColorSample() {
		return colorSensor.getColorSample();
	}
	
	public int getColorId() {
		return colorSensor.getColor();
	}
	
	public void halt() {
		leftDriveMotor.startSynchronization();
		leftDriveMotor.stop(true);
		rightDriveMotor.stop(true);
		leftDriveMotor.endSynchronization();
	}
	
	public void turnLeft() {
		leftDriveMotor.startSynchronization();
		leftDriveMotor.backward();
		rightDriveMotor.forward();
		leftDriveMotor.endSynchronization();
	}
	
	public void turnRight() {
		leftDriveMotor.startSynchronization();
		leftDriveMotor.forward();
		rightDriveMotor.backward();
		leftDriveMotor.endSynchronization();
	}
	
	public void forward() {
		leftDriveMotor.startSynchronization();
		leftDriveMotor.forward();
		rightDriveMotor.forward();
		leftDriveMotor.endSynchronization();
	}
	
	public void backward() {
		leftDriveMotor.startSynchronization();
		leftDriveMotor.backward();
		rightDriveMotor.backward();
		leftDriveMotor.endSynchronization();
	}
	
	public void setLeftMotorSpeed(float leftSpeed) {
		leftDriveMotor.setSpeed(leftSpeed);
	}
	
	public void setRightMotorSpeed(float rightSpeed) {
		rightDriveMotor.setSpeed(rightSpeed);
	}
	
	public void leftReverse() {
		leftDriveMotor.backward();
	}
	
	public void rightReverse() {
		rightDriveMotor.backward();
	}
	
	public void resetSpeed() {
		leftDriveMotor.setSpeed(DEFAULT_SPEED);
		rightDriveMotor.setSpeed(DEFAULT_SPEED);
	}
	
	public static void debugPause(String message) {
		LCD.clearDisplay();
		Sound.beep();
		say(message);
		Button.LEDPattern(9);
		Button.waitForAnyPress();
		Button.LEDPattern(1);
	}
	
	public static void playSound(Wav wav) {
		switch(wav) {
//			case SEARCHING: Sound.playSample(searchingSound, Sound.VOL_MAX); break; 
//	        case CENTERING: Sound.playSample(centeringSound, Sound.VOL_MAX); break; 
//			case APPROACHING: Sound.playSample(approachingSound, Sound.VOL_MAX); break; 
//	        case ENGAGING: Sound.playSample(engagingSound, Sound.VOL_MAX); break; 
	        default: Sound.beep(); break; 
		}
	}
	
	public static void clearDisplay(int lines) {
		for (int i=0; i < lines; i++) { 
			System.out.println("");
		}
	}
}
