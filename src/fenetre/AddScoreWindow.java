package fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Objects.FroggerGame;

public class AddScoreWindow extends JFrame implements ActionListener {
	
	private int score;
	private boolean doneFlag = false;
	protected FileWriter fw, fwNames;
	protected JTextField scoreName;
	protected JLabel scoreLabel, beauTravail;
	protected JButton confirm;


	public AddScoreWindow(int score) {
		
		setTitle("GAME OVER!");
		super.setSize(400, 250);
		setFocusable(true);
		setLocationRelativeTo(null);
		//setUndecorated(true);

		setLayout(null);
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		scoreName = new JTextField("Votre nom");
		scoreName.setBounds(30, 100, 120, 40);
		add(scoreName);

		scoreLabel = new JLabel("Votre score : "+Integer.toString(score));
		scoreLabel.setBounds(175, 100, 120, 40);
		add(scoreLabel);
		
		beauTravail = new JLabel("Entrer votre nom pour comparer vos resultats");
		beauTravail.setBounds(50, 10, 300, 100);
		add(beauTravail);
		
		this.score = score;

		confirm = new JButton("Confirm");
		confirm.addActionListener(this);
		confirm.setBounds(30, 150, 80, 40);
		confirm.setOpaque(true);
		add(confirm);
		setVisible(true);


	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == confirm) {
			try {
				String nameToAdd = "no name";
				if(!scoreName.getText().equals("Votre nom")) 
				{
					nameToAdd = scoreName.getText();
				}
				fwNames = new FileWriter("src/files/names.txt",true);
				BufferedWriter bwNames = new BufferedWriter(fwNames);
				PrintWriter out = new PrintWriter(bwNames);
				out.println(nameToAdd);
				  
				
				fw = new FileWriter("src/files/scores.txt",true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out2 = new PrintWriter(bw);
				out2.println(score);
				
				out.close();
				out2.close();
				doneFlag = true;
			
			} catch (IOException d) {
				d.printStackTrace();
			}

			dispose();
			ScoreWindow sWindow = new ScoreWindow("Scores");
			sWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			sWindow.setVisible(true);
		}
	}
	
	public boolean getDoneFlag(){
		return doneFlag;
	}

}
