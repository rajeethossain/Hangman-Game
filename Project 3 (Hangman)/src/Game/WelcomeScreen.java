package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen extends JFrame {
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();

    JLabel welcome = new JLabel("Let's Play HANGMAN");

    JButton play = new JButton("PLAY");

    public WelcomeScreen() {
        setTitle("Hangman Game");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel1.setLayout(new BorderLayout());
        panel1.setPreferredSize(new Dimension(380, 140));

        Font font = welcome.getFont();
        float size = font.getSize() + 20.0f;
        welcome.setFont( font.deriveFont(size) );
        welcome.setHorizontalAlignment(JLabel.CENTER);


        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new PlayerMode();
            }
        });

        panel1.add(welcome);
        panel2.add(play);

        add(panel1);
        add(panel2);

        setVisible(true);
    }
}
