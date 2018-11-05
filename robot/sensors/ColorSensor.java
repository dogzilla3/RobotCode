package shooterbot.robot.sensors;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import shooterbot.behaviors.Behavior;
import shooterbot.robot.Robot;


//0 = No Color
//
//1 = Black
//
//2 = Blue
//
//3 = Green
//
//4 = Yellow
//
//5 = Red
//
//6 = White
//
//7 = Brown

public class ColorSensor {

	EV3ColorSensor ev3ColorSensor;
	SensorMode sensorMode;
	SampleProvider sampleProvider;
	float[] colorValue;
	
	public ColorSensor(Port port) {
		ev3ColorSensor = new EV3ColorSensor(port);
		sensorMode = ev3ColorSensor.getRGBMode();
		ev3ColorSensor.setFloodlight(Color.WHITE);
		colorValue = new float[3];
		//ev3ColorSensor.setFloodLight(true);
	}

	public float[] getColor() {
		sensorMode.fetchSample(colorValue, 0);
		return colorValue;
	}
}
