package Objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import collision.detecteurCollision;
import fenetre.ScoreWindow;
import fenetre.AddScoreWindow;

public class FroggerPanel extends JPanel implements KeyListener, Runnable {

	public static final int HEIGHT = 600;
	public static final int WIDTH = 720;

	Image truck, car, sLog, mLog, bLog;

	private FroggerGame game;
	private BufferedImage sprite;
	private int dx = 0;
	private int dy = 10;
	private int posX, posY = 50;
	private int VelX, VelY = 0;

	Timer time = new Timer();

	public FroggerPanel() {

		setSize(HEIGHT, WIDTH);
		reset();

		Thread pThread;

		try {
			pThread = new Thread(this);
			pThread.start();
		} catch (Exception e) {
			System.err.println("Error creating thread.");
			e.printStackTrace();
			System.exit(-2);
		}

		try {
			sprite = ImageIO.read(new File("src/resources/sprite.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		setOpaque(false);
		addKeyListener(this);
		setFocusable(true);
		requestFocus();

	}


	@Override
	public void paint(Graphics g) {

		g.setFont(new Font("Arial Unicode MS", Font.CENTER_BASELINE, 20));
		int time = game.getTimeLeft();
		g.drawString("Level " + game.getLevel() + ": Time Left: " + time, 10, getHeight() - 50);

		g.drawString("Lives: ", 400, getHeight() - 50);
		g.setColor(Color.RED);
		for (int i = 0; i < game.getLives(); i++) {
			g.drawString("\u2764", 500 + i * 30, getHeight() - 50);
		}
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.CENTER_BASELINE, 32));

		g.drawString("Score: "+ game.getScore(), 15,35);

		for (int i = 0; i < 5; i++) {
			
			for (int j = 0; j < Lane.NB_OBJ_PER_LANE; j++) {
				
				LaneObject currentObj = game.getRoadLanes()[i].laneObj[j];
				
				if (game.getRoadLanes()[i].getDirection() == Lane.LEFT && currentObj.getBoundingBox().getX() + currentObj.getBoundingBox().getWidth()  < -30)
					currentObj.move(FroggerPanel.WIDTH+Lane.LEFT_LANE_RESTART+(int)currentObj.getBoundingBox().getWidth(), currentObj.getPosY());

				if (game.getRoadLanes()[i].getDirection() == Lane.RIGHT && currentObj.getPosX() > FroggerPanel.WIDTH)
					currentObj.move(Lane.RIGHT_LANE_RESTART, currentObj.getPosY());

			
				g.drawImage(currentObj.MovingObject,currentObj.getPosX(), currentObj.getPosY(), null);
				

			}
			
			for (int d = 0; d < Lane.NB_OBJ_PER_LANE; d++) {
				
				LaneObject currentObj = game.getWaterLanes()[i].laneObj[d];

				if (game.getWaterLanes()[i].getDirection() == Lane.LEFT && currentObj.getBoundingBox().getX()+ currentObj.getBoundingBox().getWidth() < -30)
					currentObj.move(FroggerPanel.WIDTH+Lane.LEFT_LANE_RESTART+(int)currentObj.getBoundingBox().getWidth(), currentObj.getPosY());
				
				
				if (game.getWaterLanes()[i].getDirection() == Lane.RIGHT && currentObj.getPosX() > FroggerPanel.WIDTH)
					currentObj.move(Lane.RIGHT_LANE_RESTART, currentObj.getPosY());

				g.drawImage(game.getWaterLanes()[i].getLaneObj()[d].MovingObject,game.getWaterLanes()[i].laneObj[d].getPosX(), game.getWaterLanes()[i].LaneInitialY[i], null);
				

			}

		}

	    g.drawImage(game.getFrog().MovingObject, game.getFrog().getPosX(), game.getFrog().getPosY(), null);
	

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_UP) {
			game.getFrog().up();
		}
		if (code == KeyEvent.VK_RIGHT) {
			game.getFrog().right();
		}
		if (code == KeyEvent.VK_LEFT) {
			game.getFrog().left();
		}
		if (code == KeyEvent.VK_DOWN) {
			game.getFrog().down();
		}
		repaint();
	}
	

	@Override
	public void run() {

		while (true) {
			update();
			repaint();
		
				if (FroggerGame.DEAD) {
					
					new AddScoreWindow(game.getScore());

					FroggerGame.DEAD = false;
					reset();
					
				}
				if (FroggerGame.WIN) {
					
					game.updateScore();
					game.setLevel(game.getLevel() + 1);
					game.resetTime((int) System.currentTimeMillis());
					
					FroggerGame.WIN = false;
				}
				
		
				
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public void update() {
		game.update();
	}

	public void reset() {
		game = new FroggerGame();
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}


}
