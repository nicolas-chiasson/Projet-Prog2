package Objects;

import java.awt.Rectangle;

public abstract class LaneObject extends MovableObject {

	protected int speed;
	protected int initialX;
	
	public abstract int getPosX();
	public abstract int getPosY();

	public abstract int getInitialX();
	public abstract Rectangle getBoundingBox(); 
}
