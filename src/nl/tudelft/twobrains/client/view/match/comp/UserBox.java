package nl.tudelft.twobrains.client.view.match.comp;

import com.sun.corba.se.spi.ior.Writeable;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import nl.tudelft.twobrains.client.model.Gebruiker;

import java.awt.image.BufferedImage;

/**
 * Created by Jasper on 11/21/2015.
 */
public class UserBox extends HBox {

    public UserBox(final Gebruiker gebruiker) {
        this.setPrefHeight(300);

        final BufferedImage bImage = gebruiker.getImage();
        final WritableImage writableImage = new WritableImage(bImage.getWidth(), bImage.getHeight());
        final Image image = SwingFXUtils.toFXImage(bImage, writableImage);
        final ImageView imgView = new ImageView(image);
        this.getChildren().add(imgView);

    }
}
