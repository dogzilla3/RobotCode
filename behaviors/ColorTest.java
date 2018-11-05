package shooterbot.behaviors;

import lejos.utility.Delay;
import shooterbot.robot.Robot;

public class ColorTest extends Behavior {

    float red;
    float green; 
    float blue;
    
	public ColorTest(Robot robot) {
		super(robot);
		
	}

	@Override
	public void run() {
		Robot.say("R:" + robot.getColor()[0]);
		Robot.say("G:" + robot.getColor()[1]);
		Robot.say("B:" + robot.getColor()[2]);
		Robot.say("");
		Delay.msDelay(500);
		
        float[] colorValue = robot.getColor();
        
        red = colorValue[0];
        green = colorValue[1];
        blue = colorValue[2];

//        if (colorValue > .100)
//        {
//            motorA.setPower(40);
//            motorB.setPower(20);
//        }
//        else
//        {
//            motorA.setPower(20);    
//            motorB.setPower(40);
//        }
	}
	
	private boolean checkBlack() {
		if(red < 0.4 && blue < 0.04 && green < 0.04)
			return true;
		else return false;
	}

}
