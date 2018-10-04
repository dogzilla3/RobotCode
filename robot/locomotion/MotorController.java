package shooterbot.robot.locomotion;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class MotorController {

	EV3LargeRegulatedMotor leftDriveMotor;
	EV3LargeRegulatedMotor rightDriveMotor;
	
	public MotorController(){
		leftDriveMotor = new EV3LargeRegulatedMotor(MotorPort.A);
		rightDriveMotor = new EV3LargeRegulatedMotor(MotorPort.B);
		BaseRegulatedMotor[] array = {rightDriveMotor};
		leftDriveMotor.synchronizeWith(array);
	}
	
	public void halt() {
		leftDriveMotor.startSynchronization();
		leftDriveMotor.stop(true);
		rightDriveMotor.stop(true);
		leftDriveMotor.endSynchronization();
	}
	
	public void rotateLeft() {
		leftDriveMotor.startSynchronization();
		leftDriveMotor.backward();
		rightDriveMotor.forward();
		leftDriveMotor.endSynchronization();
	}
	
	public void rotateRight() {
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
}
