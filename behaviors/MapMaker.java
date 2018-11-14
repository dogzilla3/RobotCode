public class MapMaker extends Behavior {

	//Container for circles
	private Mat circleContainer;
	
	/*
	 *  Constructor initializes the behavior
	 */  
	public MapMaker(Robot robot) {
		super(robot);
		Robot.playSound(Robot.Wav.CALCULATING);
	}


	@Override
	public void run() {

		robot.halt();
		//get the current orientation

		//get input from IR
		Direction iR = new Direction(/*if wall: true; else: false*/);
		//get input from left Ultrasonic
		Direction lUS = new Direction(/*if wall: true; else: false*/);
		//get input from right Ultrasonic
		Direction rUS =  new Direction(/*if wall: true; else: false*/);

		Direction north, south, east, west;
		
		switch(/*Current orientation*/){
			case NORTH:
				north = iR;
				south = new Direction(false, true);
				east = rUS;
				west = lUS;
				break;
			case SOUTH:
				north = new Direction(false, true);
				south = iR;
				east = lUS;
				west = rUS;
				break;
			case EAST:
				north = lUS;
				south = rUS;
				east = iR;
				west = new Direction(false, true);
				break;
			case WEST:
				north = rUS;
				south = lUS;
				east = new Direction(false, true);
				west = iR;
				break;
			default:
			//you is not supposed to the be here
			break;
		}
		
	}

}
