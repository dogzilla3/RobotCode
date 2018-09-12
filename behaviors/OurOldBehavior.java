package shooterbot.behaviors;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import shooterbot.robot.Robot;

public class OurOldBehavior extends Behavior{

	public OurOldBehavior(Robot robot) {
		super(robot);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		boolean running = true;
        while(running == true) {
        	LCD.clear();
        	LCD.drawString(Float.toString(robot.findRangeToObject()),1, 1);
        	
        	robot.rotateLeft();
        	
        	float range = robot.findRangeToObject();
            if(range >= 0.15 && range <= 0.3) {
                robot.halt();

                robot.fire();
                
                while(robot.findRangeToObject() <= 0.3) {
                    robot.rotateLeft();
                }
            }
            if(Button.ESCAPE.isDown()) {
            	running = false;
            }
        }     
        Sound.beepSequence(); // we are done.
	}

}
