package Objects;

import java.awt.Rectangle;

public abstract class LaneObject extends MovableObject {

	protected int speed;
	
	public abstract int getPosX();
	public abstract int getPosY();

	public abstract Rectangle getBoundingBox(); 
}
