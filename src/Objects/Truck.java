package Objects;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Truck extends LaneObject {

	
	public Truck(BufferedImage sprite, int direction, int initialX, int initialY)
	{
		
		if(direction == Lane.RIGHT)
			MovingObject = sprite.getSubimage(200, 400, 300, 75).getScaledInstance(200, 55, Image.SCALE_DEFAULT);
		else
			MovingObject = sprite.getSubimage(130, 80, 145, 75).getScaledInstance(200, 55, Image.SCALE_DEFAULT);

		this.initialX = initialX;
		move(initialX,initialY);
	}

	@Override
	public void move(int x, int y) {
		posX = x;
		posY = y;
		boundingBox = new Rectangle(x, y+8, 200, 50);
	}


	
	public int getPosX() {
		return posX;
	}
	public int getPosY() {
		return posY;
	}
	public int getInitialX(){
		return initialX;
	}
	

	@Override
	public Rectangle getBoundingBox() {
		// TODO Auto-generated method stub
		return boundingBox;
	}
}