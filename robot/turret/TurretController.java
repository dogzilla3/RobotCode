package shooterbot.robot.turret;

import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

/*
 *  Turret controller controls the firing mechanism
 */  
public class TurretController {

	//Reference to the motor
	private TurretMotor turretMotor;
	
	//Constant that dictates the amount of ammo the robot has initially
	public final int INITAL_AMMO = 10;
	
	//Real world ammo count
	int ammo;
	
	public TurretController() {
		turretMotor = new TurretMotor(MotorPort.C);
		turretMotor.setSpeed(1000);
		ammo = INITAL_AMMO;
	}
	
	public void fire() {
		if(ammo > 0) {
			turretMotor.rotate(-95);
			ammo--;
			turretMotor.rotate(95);
			Delay.msDelay(1000);
		}
	}
	
	public int getAmmo() {
		return ammo;
	}
}
