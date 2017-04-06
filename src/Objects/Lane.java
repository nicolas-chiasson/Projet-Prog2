package Objects;

public class Lane {

	public static final int NB_OBJ_PER_LANE = 2;
	public static final int RIGHT = 0, LEFT = 1;
	public static final int MAX_GAP = 450;
	public static final int MIN_GAP = 550;
	public static final int RIGHT_LANE_RESTART = -300;
	public static final int LEFT_LANE_RESTART = 300;
	
	protected int laneID;
	protected int speed;
	protected int direction;
	
	protected LaneObject[] laneObj = new LaneObject[NB_OBJ_PER_LANE];
	
	
	public Lane(int laneID, int speed, int direction)
	{
		this.laneID = laneID;
		this.speed = speed;
		this.direction = direction;
	}
	
	public int getLaneID() {
		return laneID;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed){
		this.speed = speed;
	}

	public int getDirection() {
		return direction;
	}

	
	public LaneObject[] getLaneObj() {
		return laneObj;
	}
	

	
	


	
	
	
}
