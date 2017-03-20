package Objects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FroggerPanel extends JPanel implements KeyListener, Runnable {

	public static int HEIGHT = 600;
	public static int WIDTH = 720;

	Image truck, car, frog, sLog, mLog, bLog;
	private BufferedImage sprite;

	public FroggerPanel() {

		setSize(HEIGHT, WIDTH);

		try {
			sprite = ImageIO.read(new File("src/resources/sprite.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		truck = sprite.getSubimage(200, 400, 300, 75).getScaledInstance(280, 55, Image.SCALE_DEFAULT);
		car = sprite.getSubimage(300, 480, 145, 75).getScaledInstance(135, 55, Image.SCALE_DEFAULT);
		sLog = sprite.getSubimage(380, 240, 200, 75).getScaledInstance(125, 70, Image.SCALE_DEFAULT);
		mLog = sprite.getSubimage(380, 240, 200, 75).getScaledInstance(175, 70, Image.SCALE_DEFAULT);
		bLog = sprite.getSubimage(380, 240, 200, 75).getScaledInstance(250, 70, Image.SCALE_DEFAULT);

		setOpaque(false);

	}

	public void paint(Graphics g) {
		g.drawImage(truck, 300, 372, null);
		g.drawImage(car, 80, 425, null);

		g.drawImage(sLog, 80, 30, null);
		g.drawImage(sLog, 80, 85, null);
		g.drawImage(bLog, 80, 140, null);
		g.drawImage(sLog, 80, 195, null);
		g.drawImage(mLog, 80, 250, null);

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}