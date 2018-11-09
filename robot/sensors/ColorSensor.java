package shooterbot.robot.sensors;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;


public class ColorSensor {

    private EV3ColorSensor ev3ColorSensor;
	private SampleProvider colorValue;
    private float[] lineSample = new float[1];
    private float[] colorSample = new float[3];
	
	public ColorSensor(Port port) {
		ev3ColorSensor = new EV3ColorSensor(port);
	}

	public int getColor() {
		ev3ColorSensor.setFloodlight(Color.WHITE); 	
		return ev3ColorSensor.getColorID();
	}
	
	public float[] getColorSample() {
		ev3ColorSensor.setFloodlight(Color.WHITE);
    	colorValue = ev3ColorSensor.getRGBMode();
        colorValue.fetchSample(colorSample, 0);
		return colorSample;
	}
}
