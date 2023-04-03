package Game;

import java.util.Random;

public class WordBank {
    public static char[] letters;
    public String word;
    String[] words = {"APPLE", "MANGO", "ORANGE", "BANANA", "GRAPE", "JACKFRUIT", "LICHI", "TOYOTA", "NISSAN", "PYTHON", "JAVA", "PROGRAMMING"};

    public void toArray(String word) {
        letters = word.toUpperCase().toCharArray();
    }

    Random random = new Random();
    public String getWord() {
        int randomWord = random.nextInt(words.length);
        word = words[randomWord];
        letters = word.toCharArray();
        return word;
    }
}
