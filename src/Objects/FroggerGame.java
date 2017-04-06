
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
	protected int lives, startLifeTime, laneSpeed, level;
	protected static int score;

	private Frog frog;
	private WaterLane[] waterLanes;
	private RoadLane[] roadLanes;
	private BufferedImage sprite, sprite_r;

	public FroggerGame() {

		lives = 3;
		score = 0;
		
		// Ouverture des fichiers sprite pour aller chercher les images des objets
		try {
			sprite = ImageIO.read(new File("src/resources/sprite.png"));
			sprite_r = ImageIO.read(new File("src/resources/sprite_reverse.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		// Creation de la grenouille et placement a la case initiale
		frog = new Frog(sprite, sprite_r);
		frog.move(frogInitialX, frogInitialY);

		// Creation des lanes
		roadLanes = new RoadLane[5];
		waterLanes = new WaterLane[5];

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

		// Mise a jour des lanes 20 fois avant le depart du jeu
		for (int t = 0; t < 20; t++) {
			update();
		}

		// Depart du jeu, le temps initial est note
		startLifeTime = (int) System.currentTimeMillis();

	}


	public int getLevel() {
		return level;
	}

	public WaterLane[] getWaterLanes() {
		return waterLanes;
	}

	public RoadLane[] getRoadLanes() {
		return roadLanes;
	}


	public int getLives() {
		return lives;
	}

	public Frog getFrog() {
		return frog;
	}

	
	public int getTimeLeft() {
		//timeLeft = (int) (MAX_LIFE_TIME - 0.001 * ((int) System.currentTimeMillis() - startLifeTime));
		return (int) (MAX_LIFE_TIME - 0.001 * ((int) System.currentTimeMillis() - startLifeTime));

	}

	public int getScore() {

		return score;
	}
	
	public void setLevel(int lvl) {
		level = lvl;
		laneSpeed = lvl + 2;

		for (int i = 0; i < roadLanes.length; i++) {
			int rand_speed_shift = -2 + (int) (Math.random() * 5);
			waterLanes[i].setSpeed(laneSpeed + rand_speed_shift);
			roadLanes[i].setSpeed(laneSpeed + rand_speed_shift);

		}
	}
	
	void setScore(int score) {
		FroggerGame.score = score;
	}
	
	public void resetTime(int newStartTime) {
		startLifeTime = newStartTime;
	}
	
	
	void update() {
		{
			// Mise a jour de tous les water lanes et road lanes
			for (int u = 0; u < roadLanes.length; u++) {
				roadLanes[u].update();
				waterLanes[u].update();
			}
			// Verification de collision
			carCheck();
			logCheck();
			
			// Verification si le joueur a remporte le niveau
			checkifThePlayerWin();
		}
	}
	
	public void updateScore(){
		score += getTimeLeft()*level;
	}
	
	void playerDeath() {
		lives--;
		frog.move(frogInitialX, frogInitialY);
		frog.setFrogImageUp();
		frog.setCurrentLane(0);

		if ( lives <= 0 || getTimeLeft() <= 0 ) {
			DEAD = true;
		}
	}

	void carCheck() {
		// Tue le joueur quand une collision n'est pas detectee (et que la grenouille est dans les water lanes)
		if (detecteurCollision.carCheck(this.getFrog(), this.getRoadLanes())) {
			playerDeath();
		}
	}

	void logCheck() {
		if (!detecteurCollision.logCheck(this.getFrog(), this.getWaterLanes())) {
			playerDeath();
		}
	}

	void checkifThePlayerWin() {
		if (frog.getPosY() < 10) {
			WIN = true;
			frog.move(frogInitialX, frogInitialY);
			frog.setFrogImageUp();
			frog.setCurrentLane(0);
		}
	}




}
