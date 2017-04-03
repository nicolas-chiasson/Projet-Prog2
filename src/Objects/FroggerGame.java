
package Objects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import interactionsObjetsGrenouille.TimeCounter;
import interactionsObjetsGrenouille.detecteurCollision;

public class FroggerGame{

	public static final int PLAYING = 0, PLAYER_WINS = 2, MAX_LIFE_TIME = 60;
	public static boolean DEAD = false;
	public static boolean WIN = false;
	public static final int frogInitialX = 270, frogInitialY = 612;
	

	int status, lives;
	protected int startLifeTime;
	boolean reachedMiddle;
	private Frog frog;
	private WaterLane[] waterLanes;
	private RoadLane[] roadLanes;
	private BufferedImage sprite;
	
	

	public FroggerGame() {
		status = FroggerGame.PLAYING;
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
	
		for (int t = 0; t < 10; t++){ // calls update on all lanes before
									// loading game
			update();
			
			}
		
		
		

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
	
	
		// TODO Auto-generated method stub
		
	

	void update() {
		
		//on pense qu'il faut mettre la décrémentation du temps ici mais on sait pas comment.
	
		
		for (int u = 0; u < roadLanes.length; u++)
			roadLanes[u].update();
		for (int y = 0; y < roadLanes.length; y++)
			runChecks();
		for (int z = 0; z < waterLanes.length; z++)
			waterLanes[z].update();
		for (int a = 0; a < waterLanes.length; a++)
			runChecks();
	}

	public int getStatus() {
		return status;
	}

	public int getLives() {
		return lives;
	}

	public Frog getFrog() {
		return frog;
	}

	public int getTimeLeft() {
		return startLifeTime;
		
		}

	void playerDeath() {
		lives--;
		if (lives > 0) {
			frog.move(frogInitialX, frogInitialY);
		} else {
			DEAD = true; // my life, nicolas.
		}
	}

	void carCheck() {
		// todo kills player when contacting car

		if (detecteurCollision.Collision(this.getFrog(), this.getRoadLanes())) {
			playerDeath();
		}

	}

	void logCheck() {
		// todo moves player if on log with log, otherwise kills
	}

	void turtleCheck() {
		// todo moves player with non-down turtle, otherwise kills
	}

	void checkifThePlayerWin() {
		if (this.frog.getPosY() < 10) {
			WIN = true;
			frog.move(frogInitialX, frogInitialY);
		
			System.out.println("YOU WIN");
		}
	}

	void runChecks() {
		// carCheck();
		checkifThePlayerWin();

	}

	

}
