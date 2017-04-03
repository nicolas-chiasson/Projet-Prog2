package Objects;

import java.awt.image.BufferedImage;

public class RoadLane extends Lane {

	public static final int[] LaneInitialY = { 592, 540, 488, 430, 372 };

	public RoadLane(BufferedImage sprite, int laneID, int speed, int direction) {
		super(laneID, speed, direction);

		// Speficie la position X initiale dependant de la direction
		int initialX;
		if (direction == Lane.LEFT) {
			initialX = FroggerPanel.WIDTH;
		} else {
			initialX = 0;
		}

		for (int i = 0; i < NB_OBJ_PER_LANE; i++) {
			if (direction == Lane.LEFT)
				initialX += MIN_GAP + (int) (Math.random() * (MAX_GAP - MIN_GAP));
			else
				initialX -= MIN_GAP + (int) (Math.random() * (MAX_GAP - MIN_GAP));

			// int random = (int) (Math.random()*2);
			int random = 0;
			if (random == 0)
				laneObj[i] = new Car(sprite, initialX, LaneInitialY[this.laneID]);
			else
				laneObj[i] = new Truck(sprite, initialX, LaneInitialY[this.laneID]);
		}

	}

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
