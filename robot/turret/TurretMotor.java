package shooterbot.robot.turret;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;

public class TurretMotor extends EV3LargeRegulatedMotor{

	public TurretMotor(Port port) {
		super(port);
	}
}
