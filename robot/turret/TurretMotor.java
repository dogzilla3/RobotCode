package shooterbot.robot.turret;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;

/*
 *  Wrapper class for turret motor, kinda a stand it atm,
 *  we might have to do something special with motors here
 */  
public class TurretMotor extends EV3LargeRegulatedMotor{

	public TurretMotor(Port port) {
		super(port);
	}
}
