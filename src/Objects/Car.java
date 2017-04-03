package Objects;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Car extends LaneObject {

	
	public Car(BufferedImage sprite, int initialX, int initialY){
		MovingObject = sprite.getSubimage(300, 480, 145, 75).getScaledInstance(135, 55, Image.SCALE_DEFAULT);
		this.initialX = initialX;
		this.posX=initialX;
		this.posY=initialY;
	}

	@Override
	public void move(int x, int y) {
		this.posX = x;
		this.posY = y;
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
	//TODO: trouver les dimensions exactes du sprite de l'auto pour avoir la bonne boite
	public void setBox(int x, int y){
		this.boundingBox = new Rectangle(x+7, y+9, 24, 24);
	}

	@Override
	public Rectangle getBoundingBox() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
