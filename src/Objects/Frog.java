package Objects;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Frog extends MovableObject {

	public static final int[] frogPosY = { 612, 562, 510, 452, 395, 342, 292, 232, 182, 125, 72, 15, 0 };
	private static int LEFT_RIGHT_SHIFT = 20;
	private int currentLane;
	private Image frogUp, frogDown;

	public Frog(BufferedImage sprite, BufferedImage sprite_r) {

		frogDown = sprite.getSubimage(0, 0, 50, 75).getScaledInstance(80, 90, Image.SCALE_DEFAULT);
		frogUp = sprite_r.getSubimage(205, 470, 50, 60).getScaledInstance(75, 85, Image.SCALE_DEFAULT);

		MovingObject = frogUp;
		currentLane = 0;
	}
	
	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}


	public Rectangle getBoundingBox() {
		return this.boundingBox;
	}
	
	public int getCurrentLane() {
		return currentLane;
	}

	public void setCurrentLane(int currentLane) {
		this.currentLane = currentLane;
	}

	public void setFrogImageUp() {
		MovingObject = frogUp;
	}

	public void setFrogImageDown() {
		MovingObject = frogDown;
	}


	@Override
	public void move(int x, int y) {
		this.posX = x;
		this.posY = y;
		this.boundingBox = new Rectangle(x + 15, y + 40, 50, 40);
	}

	void up() {

		if (currentLane < frogPosY.length - 1) {
			currentLane++;
		}
		setFrogImageUp();
		move(posX, frogPosY[currentLane]);

	}

	void down() {

		if (currentLane > 0) {
			currentLane--;
		}
		setFrogImageDown();
		move(posX, frogPosY[currentLane]);

	}

	public void right() {
		if (posX + boundingBox.getWidth() +  LEFT_RIGHT_SHIFT < FroggerPanel.WIDTH-120) {
			move(posX + LEFT_RIGHT_SHIFT, posY);
		}
	}

	public void left() {
		if (posX - LEFT_RIGHT_SHIFT > -10) {
			move(posX - LEFT_RIGHT_SHIFT, posY);
		}
	}

	public void moveLeftOnLog(int speed) {
		this.posX -= speed;
		this.boundingBox = new Rectangle(posX + 15, posY + 40, 50, 40);

	}

	public void moveRightOnLog(int speed) {
		this.posX += speed;
		this.boundingBox = new Rectangle(posX + 15, posY + 40, 50, 40);

	}


}
