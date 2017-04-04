package Objects;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import collision.detecteurCollision;
import fenetre.ScoreWindow;

public class FroggerPanel extends JPanel implements KeyListener, Runnable {

	public static final int HEIGHT = 600;
	public static final int WIDTH = 720;

	Image truck, car, sLog, mLog, bLog;
	
    private FroggerGame game;
	private BufferedImage sprite;
    private int dx = 0;
    private int dy = 10;
	private int posX,posY = 50;
	private int VelX,VelY =0;
	
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

	//on dessine les �l�ments sur le terrain, suffit de les faire bouger maintenant
	public void paint(Graphics g) {
		

		g.setFont(new Font("Arial",Font.CENTER_BASELINE,20));
        int time = game.getTimeLeft();
        g.drawString("Level "+game.getLevel()+": Time Left: "+time, 10, getHeight() - 50);
        
  		g.drawString("Lives: ", 400, getHeight()-50);
		g.setColor(Color.RED);
	    for (int i = 0; i < game.getLives(); i++) 
	    {
	    	g.drawString("♥", 500 + i * 30, getHeight() - 50);
	    }
	   
	    
		for(int i = 0 ; i<5 ; i++){
			//show cars in roadlanes
			for(int j = 0; j < Lane.NB_OBJ_PER_LANE ; j++)
			{
				if(game.getRoadLanes()[i].getDirection() == Lane.LEFT && game.getRoadLanes()[i].laneObj[j].getBoundingBox().getX()+game.getRoadLanes()[i].laneObj[j].getBoundingBox().getWidth()  < 0) 
					game.getRoadLanes()[i].laneObj[j].move(game.getRoadLanes()[i].getLaneObj()[j].getInitialX() + (int)game.getRoadLanes()[i].laneObj[j].getBoundingBox().getWidth(), game.getRoadLanes()[i].laneObj[j].getPosY());
				
				
				if(game.getRoadLanes()[i].getDirection() == Lane.RIGHT && game.getRoadLanes()[i].laneObj[j].getPosX() > FroggerPanel.WIDTH)
					game.getRoadLanes()[i].laneObj[j].move(game.getRoadLanes()[i].getLaneObj()[j].getInitialX()-(int)game.getRoadLanes()[i].laneObj[j].getBoundingBox().getWidth(), game.getRoadLanes()[i].laneObj[j].getPosY());
				
				g.drawImage(game.getRoadLanes()[i].getLaneObj()[j].MovingObject, game.getRoadLanes()[i].laneObj[j].getPosX(), game.getRoadLanes()[i].LaneInitialY[i], null);

//				Rectangle r = game.getRoadLanes()[i].getLaneObj()[j].getBoundingBox();
//				g.drawRect((int) r.getX(),(int) r.getY(),(int) r.getWidth(),(int) r.getHeight());

			}
				//show logs in water lanes
			for(int d = 0; d < Lane.NB_OBJ_PER_LANE; d++){
				if(game.getWaterLanes()[i].getDirection() == Lane.LEFT && game.getWaterLanes()[i].laneObj[d].getBoundingBox().getX()+game.getWaterLanes()[i].laneObj[d].getBoundingBox().getWidth() < 0)
					game.getWaterLanes()[i].laneObj[d].move(game.getWaterLanes()[i].getLaneObj()[d].getInitialX() + (int)game.getWaterLanes()[i].laneObj[d].getBoundingBox().getWidth(), game.getWaterLanes()[i].laneObj[d].getPosY());
				if(game.getWaterLanes()[i].getDirection() == Lane.RIGHT && game.getWaterLanes()[i].laneObj[d].getPosX() > FroggerPanel.WIDTH)
					game.getWaterLanes()[i].laneObj[d].move(game.getWaterLanes()[i].getLaneObj()[d].getInitialX(), game.getWaterLanes()[i].laneObj[d].getPosY());
					
				g.drawImage(game.getWaterLanes()[i].getLaneObj()[d].MovingObject, game.getWaterLanes()[i].laneObj[d].getPosX(), game.getWaterLanes()[i].LaneInitialY[i], null);
				Rectangle rl = game.getWaterLanes()[i].getLaneObj()[d].getBoundingBox();
				g.drawRect((int) rl.getX(),(int) rl.getY(),(int) rl.getWidth(),(int) rl.getHeight());

			}
			if(!detecteurCollision.inter.isEmpty()){
	//			g.drawRect((int)detecteurCollision.inter.getBounds().getX(), (int)detecteurCollision.inter.getBounds().getY(), (int)detecteurCollision.inter.getBounds().getWidth(), (int)detecteurCollision.inter.getBounds().getHeight());
			}
			
		}
		
		g.drawImage(game.getFrog().MovingObject, game.getFrog().getPosX(), game.getFrog().getPosY(), null);
		Rectangle fr = game.getFrog().getBoundingBox();
		g.drawRect((int) fr.getX(),(int) fr.getY(),(int) fr.getWidth(),(int) fr.getHeight());
		
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
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			update();
			repaint();
			try {
			if(game.DEAD)
			{
				ScoreWindow sWindow = new ScoreWindow("Scores");
				sWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sWindow.setVisible(true);	
                reset();

				game.DEAD = false;
			}
			if(game.WIN)
			{
				System.out.println("YOU WIN");
				game.setLevel(game.getLevel()+1);
	
				game.WIN =false;
			}
		
			Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}
	}
	
	public void update(){
		game.update();
	}
	public void reset(){
		game = new FroggerGame();
	}

}