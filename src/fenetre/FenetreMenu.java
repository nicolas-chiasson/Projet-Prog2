package fenetre;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

import Objects.FroggerFrame;

public class FenetreMenu extends JFrame implements ActionListener {

	private JButton Start;
	private JButton HighScore;
	private JButton Exit;
	private Image backgroundIMG;
	private int width;
	private int height;

	public FenetreMenu(String titre) {
		super(titre);
		width = 360;
		height = 480;
		setSize(width, height);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
		setLayout(null);

		try {
			this.backgroundIMG = ImageIO.read(new File("src/resources/startscreen.jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		JLabel background = new JLabel(new ImageIcon(backgroundIMG));
		add(background);
		background.setSize(width, height);

		Border emptyBorder = BorderFactory.createEmptyBorder();

		this.Start = new JButton("Start");
		this.Start.addActionListener(this);
		this.Start.setBorder(emptyBorder);
		this.Start.setBounds(200, 200, 120, 40);
		add(this.Start);

		this.HighScore = new JButton("HighScore");
		this.HighScore.addActionListener(this);
		this.HighScore.setBorder(emptyBorder);
		this.HighScore.setBounds(200, 240, 120, 40);
		add(this.HighScore);

		this.Exit = new JButton("Exit");
		this.Exit.addActionListener(this);
		this.Exit.setBorder(emptyBorder);
		this.Exit.setBounds(200, 280, 120, 40);
		add(this.Exit);

	}

	public void actionPerformed(ActionEvent action) {
		if (action.getSource() == this.Exit) {
			System.exit(0);
		} else if (action.getSource() == this.Start) {
			// start a new game
			new FroggerFrame();
		}

		else if (action.getSource() == HighScore) {
			/*
			 * ScoreWindow sWindow = new ScoreWindow("Scores");
			 * sWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 * sWindow.setVisible(true);
			 */
		}
	}

}
