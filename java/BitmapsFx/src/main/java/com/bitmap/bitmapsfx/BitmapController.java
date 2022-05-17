package com.bitmap.bitmapsfx;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.stage.DirectoryChooser;
import org.apache.commons.io.FilenameUtils;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;


public class BitmapController {
    public ImageView selectedPic;
    public ImageView bnPic;

    File filepath;
    Image currentImage;
    Image currentImageBN;

    public void chooseImg(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            FileChooser chooser = new FileChooser();
            filepath = chooser.showOpenDialog(null);

            String fileExtension = FilenameUtils.getExtension(filepath.getAbsolutePath());
            if (!fileExtension.equals("png") && !fileExtension.equals("jpg") && !fileExtension.equals("bmp"))
                throw new InvalidExtensionException();

            currentImage = new Image(filepath.toURI().toString());
            selectedPic.setImage(currentImage);
            currentImageBN = toGrey(ImageIO.read(filepath));
            bnPic.setImage(currentImageBN);

        }catch (IOException | NullPointerException ex ){
            alert.setContentText("Lettura dell'immagine non riuscita.");
            alert.show();
        }
        catch (InvalidExtensionException ex){
            alert.setContentText("Formato del file scelto non valido. Riprovare.");
            alert.show();
        }
    }

    public Image bufferedToImage(BufferedImage image){
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }
        return new ImageView(wr).getImage();
    }
    public Image toGrey(BufferedImage img){

        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        BufferedImage image = op.filter(img, null);

        return bufferedToImage(image);
    }

    public void deleteImages(){
        try {
            selectedPic.getImage().cancel();
            bnPic.getImage().cancel();
        }catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nessuna immagine da eliminare.");
            alert.show();
        }
    }

    private BufferedImage imageToBuffered(Image image){
        return new BufferedImage((int) image.getWidth(), (int) image.getHeight(), BufferedImage.TYPE_INT_ARGB);
    }

    public void saveImage(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Salvataggio effettuato");
        try {
            if (currentImage==null) throw new IOException();
            DirectoryChooser chooser = new DirectoryChooser();
            File directory = chooser.showDialog(null);
            String extension = FilenameUtils.getExtension(filepath.getAbsolutePath());
            File newFile =  new File(directory + "/" + FilenameUtils.getBaseName(filepath.getAbsolutePath()) + "BN."+extension);
            ImageIO.write(imageToBuffered(currentImageBN),extension, newFile);

            alert.show();
        }catch (IOException ex){
            alert.setContentText("Errore nel salvataggio del file");
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.show();
        }
    }

}

class InvalidExtensionException extends RuntimeException{
    public InvalidExtensionException(){
        super("Formato dell'immagine scelta non valido.");
    }
}