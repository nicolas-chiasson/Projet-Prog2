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
			this.boundingBox = new Rectangle(posX, posY + 16, 125, 50);
		}
		else if(size == MEDIUM)
		{
			MovingObject = sprite.getSubimage(380, 240, 200, 75).getScaledInstance(200, 70, Image.SCALE_DEFAULT);
			this.boundingBox = new Rectangle(posX, posY + 16, 200, 50);
		}
		else
		{
			MovingObject = sprite.getSubimage(380, 240, 200, 75).getScaledInstance(275, 70, Image.SCALE_DEFAULT);
			this.boundingBox = new Rectangle(posX, posY + 16, 275, 50);

		}
		
		this.initialX = initialX;
		this.posX=initialX;
		this.posY=initialY;
	}


	
	@Override
	public void move(int x, int y) {
		this.posX = x;
		this.posY = y;
		if(size == SMALL)
			this.boundingBox = new Rectangle(posX, posY + 25, 130, 40);
		else if(size == MEDIUM)
			this.boundingBox = new Rectangle(posX, posY + 25, 200, 40);
		else		
			this.boundingBox = new Rectangle(posX, posY + 25, 275, 40);
		/*if(size == SMALL)
			this.boundingBox = new Rectangle(posX, posY + 16, 125, 50);
		else if(size == MEDIUM)
			this.boundingBox = new Rectangle(posX, posY + 16, 200, 50);
		else		
			this.boundingBox = new Rectangle(posX, posY + 16, 275, 50);
		*/	

	}
	
	public int getPosX() {
		return posX;
	}
	public int getPosY() {
		return posY;
	}
	public int getInitialX()
	{
		return initialX;
	}


	@Override
	public Rectangle getBoundingBox() {
		// TODO Auto-generated method stub
		return boundingBox;
	}

}
