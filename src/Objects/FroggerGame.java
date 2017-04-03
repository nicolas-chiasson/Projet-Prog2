
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

	int lives, startLifeTime;
	boolean reachedMiddle;
	private Frog frog;
	private WaterLane[] waterLanes;
	private RoadLane[] roadLanes;
	private BufferedImage sprite;

	public FroggerGame() {
		
		reachedMiddle = false;
		lives = 3;

		try {
			sprite = ImageIO.read(new File("src/resources/sprite.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		frog = new Frog(sprite);
		frog.move(frogInitialX, frogInitialY);

		// log and car lanes -------------
		roadLanes = new RoadLane[5];
		waterLanes = new WaterLane[5];

		int laneSpeed = 5;

		// Creation des lanes
		for (int i = 0; i < roadLanes.length; i++) {
			if (i % 2 == 1) {
				waterLanes[i] = new WaterLane(sprite, i, laneSpeed, Lane.RIGHT);
				roadLanes[i] = new RoadLane(sprite, i, laneSpeed, Lane.RIGHT);
			} else {
				waterLanes[i] = new WaterLane(sprite, i, laneSpeed, Lane.LEFT);
				roadLanes[i] = new RoadLane(sprite, i, laneSpeed, Lane.LEFT);
			}
		}

		for (int t = 0; t < 1000; t++) // calls update on all lanes before
										// loading game
			update();
		
		startLifeTime = (int) System.currentTimeMillis();


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
		
		//on pense qu'il faut mettre la d�cr�mentation du temps ici mais on sait pas comment.
		
		getTimeLeft();
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

		if (lives > 0) {
			frog.move(frogInitialX, frogInitialY);
		} else {
			DEAD = true; // my life, nicolas.
		}
	}

	void carCheck() {
		// todo kills player when contacting car

		if (detecteurCollision.collisionCheck(this.getFrog(), this.getRoadLanes())) 
		{
			playerDeath();
			System.out.println("OUCH");
		}
		else
		{

		}

	}

	void logCheck() {
		// todo moves player if on log with log, otherwise kills
	}


	void checkifThePlayerWin() {
		if (this.frog.getPosY() < 10) {
			WIN = true;
			frog.move(frogInitialX, frogInitialY);
		}
	}

	void runChecks() {
		carCheck();
		checkifThePlayerWin();

	}

}
