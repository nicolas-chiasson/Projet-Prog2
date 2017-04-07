package Objects;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class FroggerFrame extends JFrame implements ActionListener {

	private Image backgroundIMG;
    private FroggerPanel panel;
    
	public FroggerFrame() {
		//on commence par dessiner le "terrain" de jeu
		setTitle("Frogger!");
		setResizable(false);
		setSize(600, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		try {
			this.backgroundIMG = ImageIO.read(new File("src/resources/grid.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		setContentPane(new JLabel(new ImageIcon(backgroundIMG)));
		setLocationRelativeTo(null);

		// Creation du Panel contenant le background et les objects
		panel = new FroggerPanel();
		
		setLayout(null);
		add(panel);
		pack();
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}


}

