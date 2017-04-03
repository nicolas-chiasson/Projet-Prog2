package Objects;

public class Lane {

	public static final int NB_OBJ_PER_LANE = 3;
	public static final int RIGHT = 0, LEFT = 1;
	public static final int MAX_GAP = 1000;
	public static final int MIN_GAP = 800;
	
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


	public int getDirection() {
		return direction;
	}

	
	public LaneObject[] getLaneObj() {
	return laneObj;
	}
	

	
	


	
	
	
}
