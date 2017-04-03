package Objects;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Truck extends LaneObject {

	
	public Truck(BufferedImage sprite, int initialX, int initialY)
	{
		MovingObject = sprite.getSubimage(200, 400, 300, 75).getScaledInstance(280, 55, Image.SCALE_DEFAULT);
		this.initialX = initialX;
		this.posX=initialX;
		this.posY=initialY;
	}

	@Override
	public void move(int x, int y) {
		posX = x;
		posY = y;
	}

	public void restart() {
		
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
	
	
	//TODO: trouver les dimensions exactes du sprite
	public void setBox(int x, int y){
		this.boundingBox = new Rectangle(x+7, y+9, 60, 60);
	}

	@Override
	public Rectangle getBoundingBox() {
		// TODO Auto-generated method stub
		return null;
	}
}