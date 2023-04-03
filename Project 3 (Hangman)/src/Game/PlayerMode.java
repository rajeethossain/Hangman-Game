package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerMode extends JFrame {
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();

    JLabel welcome = new JLabel("Select Player Mode");

    JButton singleP = new JButton("SINGLE PLAYER");
    JButton doubleP = new JButton("DOUBLE PLAYER");

    public PlayerMode() {
        setTitle("Hangman Game");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel1.setLayout(new BorderLayout());
        panel1.setPreferredSize(new Dimension(380, 140));

        Font font = welcome.getFont();
        float size = font.getSize() + 10.0f;
        welcome.setFont( font.deriveFont(size) );
        welcome.setHorizontalAlignment(JLabel.CENTER);

        singleP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WordBank bank = new WordBank();
                bank.getWord();
                dispose();
                GuessingGame game = new GuessingGame("Hangman Game - Single Player");
                game.lengthDisplay.setText(bank.word.length() + " ");
                game.input = String.valueOf(bank.letters);
                game.frameId = 0;
            }
        });

        doubleP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new WordInput();
            }
        });

        panel1.add(welcome);
        panel2.add(singleP);
        panel2.add(doubleP);

        add(panel1);
        add(panel2);

        setVisible(true);
    }
}
