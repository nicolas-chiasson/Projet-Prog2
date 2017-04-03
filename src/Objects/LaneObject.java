package Objects;

import java.awt.Rectangle;
import java.awt.Shape;

public abstract class LaneObject extends MovableObject {

	protected int speed;
	protected int LaneID;
	protected int initialX;
	
	public abstract int getPosX();
	public abstract int getPosY();
	public abstract int getInitialX();
	public abstract Rectangle getBoundingBox(); 
}
