package Objects;

import java.awt.Image;
import java.awt.Rectangle;


public abstract class MovableObject {
	
	protected static int GAP_BETWEEN_LANES = 53;
	protected static int LEFT_RIGHT_SHIFT = 50;
	protected int posX, posY;
	protected Image MovingObject;
	protected int direction;
	protected Rectangle boundingBox;

	public void move(int x, int y) {
		// TODO Auto-generated method stub

	}



}