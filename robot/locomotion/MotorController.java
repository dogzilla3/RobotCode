package shooterbot.robot.locomotion;

import lejos.hardware.port.MotorPort;

public class MotorController {

	DriveMotor leftDriveMotor;
	DriveMotor rightDriveMotor;
	
	public MotorController(){
		leftDriveMotor = new DriveMotor(MotorPort.A);
		rightDriveMotor = new DriveMotor(MotorPort.B);
		leftDriveMotor.synchronizeWith(rightDriveMotor);
	}
	
	public void halt() {
		leftDriveMotor.startSynchronization();
		leftDriveMotor.stop(true);
		rightDriveMotor.stop(true);
		leftDriveMotor.endSynchronization();
	}
	
	public void rotateLeft() {
		leftDriveMotor.startSynchronization();
		leftDriveMotor.forward();
		rightDriveMotor.backward();
		leftDriveMotor.endSynchronization();
	}
	
	public void rotateRight() {
		leftDriveMotor.startSynchronization();
		leftDriveMotor.backward();
		rightDriveMotor.forward();
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
