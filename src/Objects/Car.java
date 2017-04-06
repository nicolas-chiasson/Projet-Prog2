package Objects;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Car extends LaneObject {

	
	public Car(BufferedImage sprite, int direction, int initialX, int initialY){
		
		int randomCar = (int) (Math.random()*3);
		if(direction == Lane.RIGHT)
		{
			
			if(randomCar == 0)
				MovingObject = sprite.getSubimage(300, 480, 145, 75).getScaledInstance(135, 55, Image.SCALE_DEFAULT);
			else if(randomCar == 1)
				MovingObject = sprite.getSubimage(160, 480, 145, 75).getScaledInstance(135, 55, Image.SCALE_DEFAULT);
			else 
				MovingObject = sprite.getSubimage(10, 490, 145, 60).getScaledInstance(135, 55, Image.SCALE_DEFAULT);

		}
		else
		{

			if(randomCar == 0)
				MovingObject = sprite.getSubimage(160, 7, 145, 75).getScaledInstance(135, 55, Image.SCALE_DEFAULT);
			else if(randomCar == 1)
				MovingObject = sprite.getSubimage(300, 7, 145, 75).getScaledInstance(135, 55, Image.SCALE_DEFAULT);
			else
				MovingObject = sprite.getSubimage(445, 9, 145, 60).getScaledInstance(135, 55, Image.SCALE_DEFAULT);

		}
		
		move(initialX,initialY);
	}

	@Override
	public void move(int x, int y) {
		this.posX = x;
		this.posY = y;
		this.boundingBox = new Rectangle(posX+15, posY+7, 100, 45);
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
