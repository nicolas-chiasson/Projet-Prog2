
package Objects;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import collision.detecteurCollision;

public class FroggerGame {

	public static final int MAX_LIFE_TIME = 50;
	public static boolean DEAD = false;
	public static boolean WIN = false;
	public static final int frogInitialX = 270, frogInitialY = 612;
	private int lives, startLifeTime, laneSpeed, level;

	private Frog frog;
	private WaterLane[] waterLanes;
	private RoadLane[] roadLanes;
	private BufferedImage sprite, sprite_r;

	public FroggerGame() {
		
		lives = 3;

		try {
			sprite = ImageIO.read(new File("src/resources/sprite.png"));
			sprite_r = ImageIO.read(new File("src/resources/sprite_reverse.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		frog = new Frog(sprite,sprite_r);
		frog.move(frogInitialX, frogInitialY);

		// log and car lanes -------------
		roadLanes = new RoadLane[5];
		waterLanes = new WaterLane[5];
		
		// Creation des lanes
		for (int i = 0; i < roadLanes.length; i++) {
			if (i % 2 == 1) {
				waterLanes[i] = new WaterLane(sprite, i, 1, Lane.RIGHT);
				roadLanes[i] = new RoadLane(sprite, i, 1, Lane.RIGHT);
			} else {
				waterLanes[i] = new WaterLane(sprite, i, 1, Lane.LEFT);
				roadLanes[i] = new RoadLane(sprite_r, i, 1, Lane.LEFT);
			}
		}
		
		setLevel(1);
		
		for (int t = 0; t < 500; t++) // calls update on all lanes before
			update();
		
		startLifeTime = (int) System.currentTimeMillis();


	}

	public void setLevel(int lvl){
		level = lvl;
		laneSpeed = lvl+2;
		
		for (int i = 0; i < roadLanes.length; i++) 
		{
			int rand_speed_shift = -2 + (int) (Math.random()*5);
			waterLanes[i].setSpeed(laneSpeed+rand_speed_shift);
			roadLanes[i].setSpeed(laneSpeed+rand_speed_shift);
			
		}
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public WaterLane[] getWaterLanes() {
		return waterLanes;
	}

	public void setWaterLanes(WaterLane[] waterLanes) {
		this.waterLanes = waterLanes;
	}

	public RoadLane[] getRoadLanes() {
		return roadLanes;
	}

	public void setRoadLanes(RoadLane[] roadLanes) {
		this.roadLanes = roadLanes;
	}

	void update() {

		for (int u = 0; u < roadLanes.length; u++)
		{
			roadLanes[u].update();
			runChecks();
		}
		for (int z = 0; z < waterLanes.length; z++)
		{
			waterLanes[z].update();
			runChecks();
		}
	}

	public int getLives() {
		return lives;
	}

	public Frog getFrog() {
		return frog;
	}

	public int getTimeLeft() {
		int time = (int) (MAX_LIFE_TIME - 0.001*((int) System.currentTimeMillis()-startLifeTime));
		if(time<=0)
			time = 0;
		return time;
	
	}

	void playerDeath() {
		lives--;		
		frog.move(frogInitialX, frogInitialY);
		frog.setFrogImageUp();
		frog.setCurrentLane(0);

		if (lives <= 0) {
			DEAD = true; // my life, nicolas.
		}
	}

	void carCheck() {
		//  Tue le joueur quand une collision n'est pas detectee
		if (detecteurCollision.carCheck(this.getFrog(), this.getRoadLanes())) 
		{
			playerDeath();
		}
	}

	void logCheck() {
		if (!detecteurCollision.logCheck(this.getFrog(), this.getWaterLanes())) 
		{
			// Empeche de mourir pour le moment
			playerDeath();
		}
	}


	void checkifThePlayerWin() {
		if (this.frog.getPosY() < 10) {
			WIN = true;
			frog.move(frogInitialX, frogInitialY);
			frog.setFrogImageUp();
			frog.setCurrentLane(0);
		}
	}

	void runChecks() {
		carCheck();
		logCheck();
		checkifThePlayerWin();

	}

}
