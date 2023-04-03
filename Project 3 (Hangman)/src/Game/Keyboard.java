package Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Keyboard {
    JButton[] key;

    public  Keyboard(JPanel panel){
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] keys = letters.toCharArray();
        key = new JButton[letters.length()];
        for (int i = 0; i < keys.length; i++){
            key[i] = new JButton(keys[i]+"");
            panel.add(key[i]);
        }
    }
}
