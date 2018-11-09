package shooterbot.robot.sensors;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RangeFinder;
import lejos.robotics.SampleProvider;

public class IRSensor implements RangeFinder{
	
	EV3IRSensor IRSensor;
	SampleProvider rangeProvider;
	float[] range;
	
	public IRSensor(Port port) {
		IRSensor = new EV3IRSensor(port);
		rangeProvider = IRSensor.getDistanceMode();
		range = new float[3];
	}

	@Override
	public float getRange() {
		rangeProvider.fetchSample(range, 0);
		return range[0];
	}

	@Override
	public float[] getRanges() {
		rangeProvider.fetchSample(range, 0);
		return range;
	}
}
