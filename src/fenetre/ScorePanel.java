package fenetre;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Objects.FroggerGame;

@SuppressWarnings("serial")
public class ScorePanel extends JPanel implements ActionListener {

	private JButton back;

	public ScorePanel() {
		setOpaque(false);
		setLayout(null);

		JLabel team = new JLabel("High Scores", SwingConstants.CENTER);
		team.setFont(new Font("Arial", Font.BOLD, 32));
		team.setBounds(0, 0, 300, 100);
		team.setLocation(640 / 2 - team.getWidth() / 2 + 50, 45);
		team.setForeground(new Color(251, 12, 8));
		add(team);

		ArrayList<Integer> allScoresInt = new ArrayList<>();
		ArrayList<String> allScores = new ArrayList<>();
		ArrayList<String> allNames = new ArrayList<>();

		try {
			FileReader fr = new FileReader("src/files/scores.txt");
			BufferedReader br = new BufferedReader(fr);
			FileReader frNames = new FileReader("src/files/names.txt");
			BufferedReader brNames = new BufferedReader(frNames);
			String line;
			while((line = br.readLine()) != null) {
				allScores.add(line);
				allScoresInt.add(Integer.parseInt(line));
			}

			while((line = brNames.readLine()) != null) {
				allNames.add(line);
			}
			
			frNames.close();
			brNames.close();
			br.close();
			fr.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		
		// tri des scores
		ArrayList<Integer> sortedIndices = scoreSort(allScoresInt);
		
		
		for(int i = 0; i < allNames.size() && i<10 ; i++)
		{
			JLabel newName = new JLabel(allNames.get(sortedIndices.get(i)), SwingConstants.LEFT);
			newName.setBounds(0,0,400,50);
			newName.setLocation(140, 120 + i * 60);
			newName.setFont(new Font("Arial", Font.BOLD, 20));
			add(newName);
		}

		for(int i = 0 ; i < allScores.size() && i<10 ; i++)
		{
			JLabel newScore = new JLabel(allScores.get(sortedIndices.get(i)), SwingConstants.CENTER);
			newScore.setBounds(0,0,400,50);
			newScore.setLocation(300,(120 + i * 60));
			newScore.setFont(new Font("Arial", Font.BOLD, 20));
			add(newScore);
		}		
		
	
		back = new JButton("Back");
		back.addActionListener(this);
		back.setBounds(0, 0, 120, 40);
		back.setLocation(500, 700);
		add(back);

	}

	@Override
	public void actionPerformed(ActionEvent action) {
		if (action.getSource() == back) {
			SwingUtilities.getWindowAncestor(this).dispose();
		}
	}

	
	public static ArrayList<Integer> scoreSort(ArrayList<Integer> scores)
	{
		ArrayList<Integer> sortedIndices = new ArrayList<Integer>();
		
		// Initialisation du sortedIndices 
		for(int count = 0 ; count<scores.size() ; count++)
			sortedIndices.add(count);
		
		
		for(int i = 0 ; i<scores.size()-1; i++)
		{
			int maxValue = scores.get(i);
			int maxI =i;
			int maxIndex = sortedIndices.get(i);
			
			// On trouve le plus grand score et on retient son index (initial et actuel)
			for(int j = i ; j<scores.size(); j++)
			{
				if(scores.get(j) > maxValue)
				{
					maxValue = scores.get(j);
					maxIndex = sortedIndices.get(j); // Index initial
					maxI = j; //Index actuel
				}
			}
			// Swap le max avec la premiere valeur
			int temp = scores.get(i);
			scores.set(i, maxValue);
			scores.set(maxI, temp);
			
			// Swap des index du max et de la premier valeur
			int tempIndex = sortedIndices.get(i);
			sortedIndices.set(i, maxIndex);
			sortedIndices.set(maxI, tempIndex);

		}

		
		
		return sortedIndices;
	}
}
