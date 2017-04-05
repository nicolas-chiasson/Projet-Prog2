package Objects;

import java.awt.image.BufferedImage;

public class WaterLane extends Lane {

	protected int[] LaneInitialY = { 250, 195, 140, 85, 30 };
	

	public WaterLane(BufferedImage sprite, int laneID, int speed, int direction) {
		super(laneID, speed, direction);

		// specifie la position X initiale dependant de la direction
		int initialX;
		if (direction == Lane.LEFT) {
			initialX = FroggerPanel.WIDTH;
		} else {
			initialX = 0;

		}
		for (int i = 0; i < NB_OBJ_PER_LANE; i++) {
			if (direction == Lane.LEFT) {
				initialX += MIN_GAP + (int) (Math.random() * (MAX_GAP - MIN_GAP));
			} else {
				initialX -= MIN_GAP + (int) (Math.random() * (MAX_GAP - MIN_GAP));
			}
			
			int random = 0;
			if(random == 0){
				
				//classe Log qui ressemble a Car
				laneObj[i] = new Log(sprite, initialX, LaneInitialY[this.laneID]);
			}else{
				//deuxi�me type d'objet aquatique a implementer
				laneObj[i] = new Log(sprite, initialX, LaneInitialY[this.laneID]);
								
			}
			
		}
	}
	
	@Override
	public LaneObject[] getLaneObj() {
		return laneObj;
	}

	public void setLaneObj(LaneObject[] laneObj) {
		this.laneObj = laneObj;
	}

	public void update() {
		for (int i = 0; i < NB_OBJ_PER_LANE; i++) {
			if (direction == Lane.RIGHT){
				laneObj[i].move(laneObj[i].getPosX() + speed, laneObj[i].getPosY());}
			else{
				laneObj[i].move(laneObj[i].getPosX() - speed, laneObj[i].getPosY());}
		}
	}

	}

