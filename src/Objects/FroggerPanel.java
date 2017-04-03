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
import javax.swing.JLabel;
import javax.swing.JPanel;

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
/*
		truck = sprite.getSubimage(200, 400, 300, 75).getScaledInstance(280, 55, Image.SCALE_DEFAULT);
		car = sprite.getSubimage(300, 480, 145, 75).getScaledInstance(135, 55, Image.SCALE_DEFAULT);
		sLog = sprite.getSubimage(380, 240, 200, 75).getScaledInstance(125, 70, Image.SCALE_DEFAULT);
		mLog = sprite.getSubimage(380, 240, 200, 75).getScaledInstance(175, 70, Image.SCALE_DEFAULT);
		bLog = sprite.getSubimage(380, 240, 200, 75).getScaledInstance(250, 70, Image.SCALE_DEFAULT);
*/
		setOpaque(false);
		addKeyListener(this);	
		setFocusable(true);
		requestFocus();
		
		
	}

	//on dessine les �l�ments sur le terrain, suffit de les faire bouger maintenant
	public void paint(Graphics g) {
		

		g.setFont(new Font("Arial",Font.CENTER_BASELINE,32));
        int time = game.getTimeLeft();
        g.drawString("Time Left: "+time, 10, getHeight() - 50);
        
  		g.drawString("Lives: ", 400, getHeight()-50);
		g.setColor(Color.RED);
	    for (int i = 0; i < game.getLives(); i++) 
	    {
	    	g.drawString("♥", 500 + i * 30, getHeight() - 50);
	    }
	   
	    g.drawImage(game.getFrog().MovingObject, game.getFrog().getPosX(), game.getFrog().getPosY(), null);
		Rectangle fr = game.getFrog().getBoundingBox();
		g.drawRect((int) fr.getX(),(int) fr.getY(),(int) fr.getWidth(),(int) fr.getHeight());
	    
		for(int i = 0 ; i<5 ; i++){
			//show cars in roadlanes
			for(int j = 0; j < Lane.NB_OBJ_PER_LANE ; j++)
			{
				if(game.getRoadLanes()[i].getDirection() == Lane.LEFT && game.getRoadLanes()[i].laneObj[j].getBoundingBox().getX()+game.getRoadLanes()[i].laneObj[j].getBoundingBox().getWidth()  < 0) 
					game.getRoadLanes()[i].laneObj[j].move(game.getRoadLanes()[i].getLaneObj()[j].getInitialX() + (int)game.getRoadLanes()[i].laneObj[j].getBoundingBox().getWidth(), game.getRoadLanes()[i].laneObj[j].getPosY());
				
				
				if(game.getRoadLanes()[i].getDirection() == Lane.RIGHT && game.getRoadLanes()[i].laneObj[j].getPosX() > FroggerPanel.WIDTH)
					game.getRoadLanes()[i].laneObj[j].move(game.getRoadLanes()[i].getLaneObj()[j].getInitialX()-(int)game.getRoadLanes()[i].laneObj[j].getBoundingBox().getWidth(), game.getRoadLanes()[i].laneObj[0].getPosY());
				
				g.drawImage(game.getRoadLanes()[i].getLaneObj()[j].MovingObject, game.getRoadLanes()[i].laneObj[j].getPosX(), game.getRoadLanes()[i].LaneInitialY[i], null);
				//System.out.print( game.getRoadLanes()[i].laneObj[j].getPosX()+"," +game.getRoadLanes()[i].LaneInitialY[i] +"   ");
				Rectangle r = game.getRoadLanes()[i].getLaneObj()[j].getBoundingBox();
				g.drawRect((int) r.getX(),(int) r.getY(),(int) r.getWidth(),(int) r.getHeight());

			}
				//show logs in water lanes
			for(int d = 0; d < Lane.NB_OBJ_PER_LANE; d++){
				if(game.getWaterLanes()[i].getDirection() == Lane.LEFT && game.getWaterLanes()[i].laneObj[d].getPosX() < 0)
					game.getWaterLanes()[i].laneObj[d].move(game.getWaterLanes()[i].getLaneObj()[d].getInitialX(), game.getWaterLanes()[i].getLaneObj()[d].getPosY());
				if(game.getWaterLanes()[i].getDirection() == Lane.RIGHT && game.getWaterLanes()[i].laneObj[d].getPosX() > FroggerPanel.WIDTH)
					game.getWaterLanes()[i].laneObj[d].move(game.getWaterLanes()[i].getLaneObj()[d].getInitialX(), game.getWaterLanes()[i].laneObj[0].getPosY());
					
				g.drawImage(game.getWaterLanes()[i].getLaneObj()[d].MovingObject, game.getWaterLanes()[i].laneObj[d].getPosX(), game.getWaterLanes()[i].LaneInitialY[i], null);
			}
			//System.out.println();
		}
		
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
			if(game.DEAD){
				fenetre.ScoreWindow score = new fenetre.ScoreWindow("Frogger Score");
			}
			if(game.WIN){
				System.out.println("YOU WIN");
				fenetre.ScoreWindow score = new fenetre.ScoreWindow("Frogger Score");

				game.WIN =false;
			}
			try {
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