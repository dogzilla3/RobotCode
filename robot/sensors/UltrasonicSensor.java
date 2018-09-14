package shooterbot.robot.sensors;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.robotics.RangeFinder;
import lejos.robotics.SampleProvider;

public class UltrasonicSensor implements RangeFinder{
	
	NXTUltrasonicSensor sensor;
	SampleProvider sampleProvider;
	float[] sample;
	
	public UltrasonicSensor(Port port) {
		sensor = new NXTUltrasonicSensor(port);
		sampleProvider = sensor.getDistanceMode();
		sample = new float[3];
	}

	@Override
	public float getRange() {
		sampleProvider.fetchSample(sample, 0);
		sampleProvider.fetchSample(sample, 1);
		sampleProvider.fetchSample(sample, 2);
		return (sample[0] + sample[1] + sample[2]) / 3;
	}

	@Override
	public float[] getRanges() {
		sampleProvider.fetchSample(sample, 0);
		return sample;
	}

	public boolean isEnabled() {
		return sensor.isEnabled();
	}
	
	public void enable() {
		sensor.enable();
	}
	
	public void disable() {
		sensor.disable();
	}
	
	public void close() {
		sensor.close();
	}
}
