package shooterbot.robot.sensors;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.robotics.RangeFinder;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class UltrasonicSensor implements RangeFinder{
	
	NXTUltrasonicSensor sensor;
	SampleProvider rangeProvider;
	float[] range;
	
	public UltrasonicSensor(Port port) {
		sensor = new NXTUltrasonicSensor(port);
		rangeProvider = sensor.getDistanceMode();
		range = new float[5];
	}
	
	public void close() {
		sensor.close();
	}

	@Override
	public float getRange() {
		rangeProvider.fetchSample(range, 0);
		Delay.msDelay(40);
		rangeProvider.fetchSample(range, 1);
		Delay.msDelay(40);
		rangeProvider.fetchSample(range, 2);
		Delay.msDelay(40);
		rangeProvider.fetchSample(range, 3);
		Delay.msDelay(40);
		rangeProvider.fetchSample(range, 4);
		Delay.msDelay(40);
		
		float sum = 0;
		
		for(int i = 0; i < 5; i++) {
			sum += range[i];
		}
		
		return (sum / 5);
	}

	@Override
	public float[] getRanges() {
		rangeProvider.fetchSample(range, 0);
		return range;
	}
}
