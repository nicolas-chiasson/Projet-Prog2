package fenetre;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class ScorePanel extends JPanel implements ActionListener {

	private JButton back;

	public ScorePanel() {
		setOpaque(false);
		setLayout(null);

		JLabel team = new JLabel("High Scores", JLabel.CENTER);
		team.setFont(new Font("Arial", Font.BOLD, 32));
		team.setBounds(0, 0, 300, 100);
		team.setLocation(640 / 2 - team.getWidth() / 2 + 50, 45);
		team.setForeground(new Color(251, 12, 8));
		add(team);

		ArrayList<String> allScores = new ArrayList<>();
		ArrayList<String> allNames = new ArrayList<>();

		try {
			FileReader fr = new FileReader("src/files/scores.txt");
			BufferedReader br = new BufferedReader(fr);
			FileReader frNames = new FileReader("src/files/names.txt");
			BufferedReader brNames = new BufferedReader(frNames);
			for (int i = 0; i < 10; i++) {
				allScores.add(br.readLine());
			}

			for (int i = 0; i < 10; i++) {
				allNames.add(brNames.readLine());
			}

			frNames.close();
			brNames.close();
			br.close();
			fr.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		ArrayList<JLabel> labels = new ArrayList<>();
		labels.add(new JLabel(allNames.get(0), JLabel.LEFT));
		labels.add(new JLabel(allNames.get(1), JLabel.LEFT));
		labels.add(new JLabel(allNames.get(2), JLabel.LEFT));
		labels.add(new JLabel(allNames.get(3), JLabel.LEFT));
		labels.add(new JLabel(allNames.get(4), JLabel.LEFT));
		labels.add(new JLabel(allNames.get(5), JLabel.LEFT));
		labels.add(new JLabel(allNames.get(6), JLabel.LEFT));
		labels.add(new JLabel(allNames.get(7), JLabel.LEFT));
		labels.add(new JLabel(allNames.get(8), JLabel.LEFT));
		labels.add(new JLabel(allNames.get(9), JLabel.LEFT));

		labels.add(new JLabel(allScores.get(0), JLabel.LEFT));
		labels.add(new JLabel(allScores.get(1), JLabel.LEFT));
		labels.add(new JLabel(allScores.get(2), JLabel.LEFT));
		labels.add(new JLabel(allScores.get(3), JLabel.LEFT));
		labels.add(new JLabel(allScores.get(4), JLabel.LEFT));
		labels.add(new JLabel(allScores.get(5), JLabel.LEFT));
		labels.add(new JLabel(allScores.get(6), JLabel.LEFT));
		labels.add(new JLabel(allScores.get(7), JLabel.LEFT));
		labels.add(new JLabel(allScores.get(8), JLabel.LEFT));
		labels.add(new JLabel(allScores.get(9), JLabel.LEFT));

		for (int i = 0; i < labels.size(); i++) {
			JLabel j1 = labels.get(i);
			int X = 140;
			int Y = (150 + i * 60);

			if (i > 9) {
				X = 300;
				Y = (150 + (i - 10) * 60);
			}
			j1.setBounds(0, 0, 400, 50);
			j1.setLocation(X, Y);
			j1.setFont(new Font("Arial", Font.BOLD, 24));
			add(j1);
		}

		back = new JButton("Back");
		back.addActionListener(this);
		back.setBounds(0, 0, 120, 40);
		back.setLocation(500, 700);
		add(back);

	}

	public void actionPerformed(ActionEvent action) {
		if (action.getSource() == back) {
			SwingUtilities.getWindowAncestor(this).dispose();
		}
	}

}
