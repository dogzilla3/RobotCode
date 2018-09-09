package shooterbot.robot.turret;

import lejos.hardware.port.MotorPort;

public class TurretController {

	private TurretMotor turretMotor;
	public static final int INITAL_AMMO = 3;
	int ammo;
	
	public TurretController() {
		turretMotor = new TurretMotor(MotorPort.C);
		turretMotor.setSpeed(100);
		ammo = INITAL_AMMO;
	}
	
	public void fire() {
		if(ammo > 0) {
			turretMotor.rotate(-75);
			ammo--;
		}
	}
	
	public void reload() {
		turretMotor.rotate(75);
	}
}
