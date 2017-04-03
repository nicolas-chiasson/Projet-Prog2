package Objects;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Frog extends MovableObject  {


	public Frog(BufferedImage sprite) {
		
		MovingObject = sprite.getSubimage(0, 0, 50,75).getScaledInstance(80, 90, Image.SCALE_DEFAULT);
		
	}

	@Override
	public void move(int x, int y) {
		this.posX = x;
		this.posY = y;

	}
	//il faut s'assurer que le mouvement de la grenouille se fait avec le bon nombre de pixels par rapport au GameFrame.
	
	void up(){
		
		move(posX,posY-GAP_BETWEEN_LANES);
	}
	void down(){
		move(posX,posY+GAP_BETWEEN_LANES);
	}	
	void right(){
		move(posX+LEFT_RIGHT_SHIFT,posY);
	}	
	void left(){
		move(posX-LEFT_RIGHT_SHIFT,posY);
	}
	
	public int getPosX() {
		return posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setBox(int x, int y){
		this.boundingBox = new Rectangle(x+7, y+9, 24, 24);
	}

	public Rectangle getBoundingBox() {
		return boundingBox;
	}
}
