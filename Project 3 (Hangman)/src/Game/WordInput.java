package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordInput extends JFrame {
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    JPanel panel5 = new JPanel();

    JLabel inputLabel = new JLabel();

    JButton clear = new JButton("Clear");
    JButton start = new JButton("START");
    JButton quit = new JButton("QUIT");
    JButton changeMode = new JButton("CHANGE MODE");


    public WordInput() {
        setTitle("Hangman Game - Double Player");
        setSize(400, 380);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel1.setLayout(new BorderLayout());
        panel1.setPreferredSize(new Dimension(380, 60));
        panel1.setBorder(BorderFactory.createTitledBorder("Enter a Word"));
        panel2.setPreferredSize(new Dimension(380, 210));
        panel2.setBorder(BorderFactory.createTitledBorder("Input"));
        panel4.setPreferredSize(new Dimension(370, 130));

        Font font = inputLabel.getFont();
        float size = font.getSize() + 5.0f;
        inputLabel.setFont( font.deriveFont(size) );
        inputLabel.setHorizontalAlignment(JLabel.CENTER);

        addKeyboard();

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!inputLabel.getText().equals("")) {
                    WordBank bank = new WordBank();
                    bank.toArray(inputLabel.getText());
                    if(bank.letters.length>3) {
                        if(bank.letters.length<11) {
                            dispose();
                            GuessingGame game = new GuessingGame("Hangman Game - Double Player");
                            game.lengthDisplay.setText(inputLabel.getText().length() + " ");
                            game.input = String.valueOf(bank.letters);
                            game.frameId = 1;
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Word too big!");
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Word too short!");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Please enter a word!");
                }
            }
        });

        changeMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new PlayerMode();

            }
        });

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new WelcomeScreen();
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputLabel.setText("");
            }
        });

        panel1.add(inputLabel);
        panel2.add(panel4);
        panel2.add(panel5);
        panel3.add(changeMode);
        panel3.add(start);
        panel3.add(quit);
        panel5.add(clear);

        add(panel1);
        add(panel2);
        add(panel3);

        setVisible(true);
    }

    void addKeyboard() {
        Keyboard keyboard = new Keyboard(panel4);

        int j = 0;
        for (int i = 0; i < keyboard.key.length; i++) {
            String x = keyboard.key[i].getText();
            keyboard.key[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    inputLabel.setText(inputLabel.getText() + x);
                }
            });
        }
    }
}
