import fenetre.FenetreMenu;

import javax.swing.*;
import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException {
        FenetreMenu frame = new FenetreMenu("Frogger");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
