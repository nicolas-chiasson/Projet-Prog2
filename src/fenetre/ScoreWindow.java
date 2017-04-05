package fenetre;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class ScoreWindow extends JFrame {

	// on d�clare la taille de la fenetre pour changer ces valeurs plus
	// facilement
	protected int width = 700;
	protected int height = 800;

	public ScoreWindow(String title) {
		super(title);
		setSize(width, height);
		setUndecorated(true);
		setResizable(false);
		setLocationRelativeTo(null);

		// appuyer sur escape ferme la fenetre
		InputMap im = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = getRootPane().getActionMap();
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");
		am.put("Cancel", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JLayeredPane lpane = new JLayeredPane();
		setLayout(new BorderLayout());
		add(lpane, BorderLayout.CENTER);
		lpane.setBounds(0, 0, width, height);

		ScorePanel cp = new ScorePanel();
		cp.setBounds(0, 0, width, height);
		add(cp);
		lpane.add(cp, new Integer(0), 0);

	}

}
