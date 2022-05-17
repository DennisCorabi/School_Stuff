package com.example.bitmapsfx;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Files;

public class BitmapController {
    @FXML
    ImageView selectedPic;

    public void chooseImg() throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Png images", "*.png"));
        File file = chooser.showOpenDialog(null);
        Image image = (file!=null)? new Image(file.getAbsolutePath()): null;
        selectedPic.setImage(image);

        if (file==null) return;
        BufferedImage bufferedImage = ImageIO.read(file);
        toGrey(bufferedImage);
    }

    public void toGrey(BufferedImage img) throws IOException {
        for (int x = 0; x < img.getWidth(); ++x){
            for (int y = 0; y < img.getHeight(); ++y) {
                int rgb = img.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = (rgb & 0xFF);

                // Normalize and gamma correct:
                double rr = Math.pow(r / 255.0, 2.2);
                double gg = Math.pow(g / 255.0, 2.2);
                double bb = Math.pow(b / 255.0, 2.2);

                // Calculate luminance:
                double lum = 0.2126 * rr + 0.7152 * gg + 0.0722 * bb;

                // Gamma compand and rescale to byte range:
                int grayLevel = (int) (255.0 * Math.pow(lum, 1.0 / 2.2));
                int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;
                img.setRGB(x, y, gray);

                File file = new File("grayscale.png");
                System.out.println("Saved file in "+file.getAbsolutePath());
                ImageIO.write(img,"png",file);
            }
        }
    }
}