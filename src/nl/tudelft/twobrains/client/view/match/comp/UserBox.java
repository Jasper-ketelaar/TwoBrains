package nl.tudelft.twobrains.client.view.match.comp;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nl.tudelft.twobrains.client.model.Gebruiker;

import java.awt.image.BufferedImage;

/**
 * Created by Jasper on 11/21/2015.
 */
public class UserBox extends HBox {

    public UserBox(final Gebruiker gebruiker) {


        final BufferedImage bImage = gebruiker.getUserImage();
        final WritableImage writableImage = new WritableImage(bImage.getWidth(), bImage.getHeight());
        final Image image = SwingFXUtils.toFXImage(bImage, writableImage);
        final ImageView imgView = new ImageView(image);
        imgView.setFitWidth(210);
        imgView.setFitHeight(155);

        final VBox vb1 = new VBox();
        vb1.setPrefSize(520, 155);
        vb1.setSpacing(5.0);

        final Insets insets = new Insets(0, 0, 0, 5);

        final Label email = new Label("E-mail adress:");
        
        email.setPadding(insets);

        final Label voornaam = new Label("Voornaam:");
        voornaam.setPadding(insets);

        vb1.getChildren().addAll(email, voornaam);


        this.getChildren().addAll(imgView, vb1);

    }
}
