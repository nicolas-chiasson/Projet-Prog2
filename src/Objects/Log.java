package Objects;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Log extends LaneObject {
	
	public static final int SMALL = 0, MEDIUM = 1, LARGE = 2;
	private int size;
	
	public Log(BufferedImage sprite, int initialX, int initialY)
	{
		size = (int) (Math.random()*3);
		if(size == SMALL)
		{
			MovingObject = sprite.getSubimage(380, 240, 200, 75).getScaledInstance(125, 70, Image.SCALE_DEFAULT);
			this.boundingBox = new Rectangle(posX+10, posY + 25, 125, 50);
		}
		else if(size == MEDIUM)
		{
			MovingObject = sprite.getSubimage(380, 240, 200, 75).getScaledInstance(200, 70, Image.SCALE_DEFAULT);
			this.boundingBox = new Rectangle(posX+10, posY + 25, 200, 50);
		}
		else
		{
			MovingObject = sprite.getSubimage(380, 240, 200, 75).getScaledInstance(275, 70, Image.SCALE_DEFAULT);
			this.boundingBox = new Rectangle(posX+10, posY + 25, 275, 50);

		}
		
		this.posX=initialX;
		this.posY=initialY;
	}

	
	@Override
	public void move(int x, int y) {
		this.posX = x;
		this.posY = y;
		if(size == SMALL)
			this.boundingBox = new Rectangle(posX+10, posY + 25, 100, 40);
		else if(size == MEDIUM)
			this.boundingBox = new Rectangle(posX+10, posY + 25, 175, 40);
		else		
			this.boundingBox = new Rectangle(posX+10, posY + 25, 250, 40);

	}
	
	@Override
	public int getPosX() {
		return posX;
	}
	@Override
	public int getPosY() {
		return posY;
	}

	@Override
	public Rectangle getBoundingBox() {
		// TODO Auto-generated method stub
		return boundingBox;
	}


}
