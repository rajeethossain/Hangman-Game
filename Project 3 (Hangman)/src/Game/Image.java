package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    public Image(JPanel panel, String image){
        JLabel img = readImage(image,350,360);
        img.setBounds(30,30,50,50);
        panel.add(img);
    }

    JLabel readImage(String pathName, int width, int height){
        try {
            BufferedImage image = ImageIO.read(new File(pathName));

            java.awt.Image scaleImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

            return new JLabel(new ImageIcon(scaleImage));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
