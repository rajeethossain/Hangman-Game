package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GuessingGame extends JFrame{
    int frameId;

    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    JPanel panel5 = new JPanel();
    JPanel panel6 = new JPanel();

    JLabel length = new JLabel("Length");
    JLabel attemptsLeft = new JLabel("Attempts Left");
    JLabel wrongGuess = new JLabel("Wrong Guess");

    JLabel displayLabel = new JLabel();
    JLabel lengthDisplay = new JLabel();
    JLabel attemptDisplay = new JLabel();
    JLabel guessDisplay = new JLabel();

    JButton playAgain = new JButton("Play Again");
    JButton changeMode = new JButton("CHANGE MODE");
    JButton quit = new JButton("QUIT");

    int size = WordBank.letters.length;

    String[] guess = new String[size];

    public void setBlank(){
        for(int i=0; i<size; i++){
            guess[i] = "___";
        }
    }

    public void setPartial(){
        for(int i = 0; i < size; i++) {
            if(String.valueOf(WordBank.letters[i]).equals(String.valueOf(WordBank.letters[0]))){
                guess[i] = String.valueOf(WordBank.letters[i]);
            }
            if(String.valueOf(WordBank.letters[i]).equals(String.valueOf(WordBank.letters[size - 1]))){
                guess[i] = String.valueOf(WordBank.letters[i]);
            }
        }
    }

    public void updateGuess(){
        displayLabel.setText("");
        for(int i=0; i<size; i++){
            displayLabel.setText(displayLabel.getText() + guess[i] + " ");
        }
    }

    String input;

    public GuessingGame(String title){
        setTitle(title);
        setSize(820,500);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel1.setLayout(new GridLayout(3,2));
        panel1.setPreferredSize(new Dimension(380, 120));
        panel1.setBorder(BorderFactory.createTitledBorder("Status"));
        panel2.setBorder(BorderFactory.createTitledBorder("Display"));
        panel2.setPreferredSize(new Dimension(380, 400));
        panel3.setLayout(new BorderLayout());
        panel3.setPreferredSize(new Dimension(380, 105));
        panel3.setBorder(BorderFactory.createTitledBorder("Guess The Word"));
        panel4.setPreferredSize(new Dimension(380, 160));
        panel4.setBorder(BorderFactory.createTitledBorder("Input"));
        panel6.setPreferredSize(new Dimension(400, 400));

        attemptDisplay.setText("6");
        guessDisplay.setText("");

        Font font = displayLabel.getFont();
        float size = font.getSize() + 8.0f;
        displayLabel.setFont( font.deriveFont(size) );
        displayLabel.setHorizontalAlignment(JLabel.CENTER);

        float size1 = font.getSize() + 5.0f;
        length.setFont( font.deriveFont(size1) );
        attemptsLeft.setFont( font.deriveFont(size1) );
        wrongGuess.setFont( font.deriveFont(size1) );
        lengthDisplay.setFont( font.deriveFont(size1) );
        attemptDisplay.setFont( font.deriveFont(size1) );
        guessDisplay.setFont( font.deriveFont(size1) );

        setBlank();
        setPartial();
        updateGuess();
        addKeyboard();

        panel2.removeAll();
        new Image(panel2, "0.png");
        revalidate();

        playAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(frameId == 0){
                    WordBank bank = new WordBank();
                    bank.getWord();
                    dispose();
                    GuessingGame game = new GuessingGame("Hangman Game - Single Player");
                    game.lengthDisplay.setText(bank.word.length() + " ");
                    game.input = String.valueOf(bank.letters);

                    frameId = 0;
                }

                if(frameId == 1) {
                    dispose();
                    new WordInput();
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


        panel1.add(length);
        panel1.add(lengthDisplay);
        panel1.add(attemptsLeft);
        panel1.add(attemptDisplay);
        panel1.add(wrongGuess);
        panel1.add(guessDisplay);

        panel3.add(displayLabel);

        panel5.add(changeMode);
        panel5.add(playAgain);
        panel5.add(quit);

        panel6.add(panel1);
        panel6.add(panel3);
        panel6.add(panel4);

        add(panel6);
        add(panel2);
        add(panel5);

        setVisible(true);
    }

    int count = 1;
    boolean check = false;
    void addKeyboard(){

        Keyboard keyboard = new Keyboard(panel4);

        int j = 0;
        for (int i = 0; i < keyboard.key.length; i++) {
            String x = keyboard.key[i].getText();
            keyboard.key[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (count <= 6 && check==false) {
                        String letter = x;
                        int flag = 0;
                        for (int i = 0; i < size; i++) {
                            if (letter.equals(String.valueOf(WordBank.letters[i]))) {
                                flag = 1;
                                guess[i] = letter;
                            }
                        }
                        updateGuess();
                        check = check();

                        if (flag == 0) {
                            panel2.removeAll();
                            new Image(panel2, count + ".png");
                            revalidate();
                            int attempt = Integer.parseInt(attemptDisplay.getText());
                            attempt--;
                            attemptDisplay.setText(attempt + "");
                            guessDisplay.setText(guessDisplay.getText() + letter + ", ");
                            count++;
                        }
                    }

                    if(check==true){
                        panel2.removeAll();
                        new Image(panel2, "7.png");
                        revalidate();
                    }
                }
            });
        }
    }

    StringBuilder builder;
    String str;
    boolean check() {
        builder = new StringBuilder();
        for(String s : guess) {
            builder.append(s);
        }
        str = builder.toString();

        if(input.equals(str)){
            return true;
        }
        return false;
    }
}
