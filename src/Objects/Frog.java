package Objects;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Frog extends MovableObject implements ActionListener, KeyListener {

	private int x, y, direction;
	BufferedImage sprite;

	public Frog() {
		
		
		try {
			sprite = ImageIO.read(new File("src/resources/sprite.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		MovingObject = sprite.getSubimage(0, 0, 50, 80).getScaledInstance(70, 70, Image.SCALE_DEFAULT);
		
		JLabel Grenouille = new JLabel(new ImageIcon(MovingObject));
		
		Grenouille.setFocusable(true);
		Grenouille.addKeyListener(this);
		Grenouille.setOpaque(false);

	}

	@Override
	void move(int x, int y) {
		this.posX = x;
		this.posY = y;

	}
	//il faut s'assurer que le mouvement de la grenouille se fait avec le bon nombre de pixels par rapport au GameFrame.
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_UP) {
			move(posX, posY++);
		}
		if (code == KeyEvent.VK_RIGHT) {
			move(posX++, posY);
		}
		if (code == KeyEvent.VK_LEFT) {
			move(posX--, posY);
		}
		if (code == KeyEvent.VK_DOWN) {
			move(posX, posY--);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
