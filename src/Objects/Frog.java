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
	
	

	private int x,y,direction;

	public Frog(BufferedImage sprite) { // créé un objet grenouille ayant pour
										// position
		// de départ les coordonées

		MovingObject = sprite.getSubimage(200, 400, 300, 75).getScaledInstance(280, 55, Image.SCALE_DEFAULT);
		
		JLabel Grenouille = new JLabel(new ImageIcon(this.MovingObject));


		Grenouille.setFocusable(true);
		Grenouille.addKeyListener(this);

	}

	@Override
	void move(int x, int y) {
		this.posX = x;
		this.posY = y;

	}

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