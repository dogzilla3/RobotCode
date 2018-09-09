package shooterbot.robot.locomotion;

import lejos.hardware.motor.*;
import lejos.hardware.port.*;

public class DriveMotor extends EV3LargeRegulatedMotor{

	DriveMotor(Port port){
		super(port);	
	}

	public void synchronizeWith(EV3LargeRegulatedMotor motor) {
		this.synchronizeWith(motor);
	}
}
