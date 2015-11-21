package nl.tudelft.twobrains.client.view.match.comp;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import nl.tudelft.twobrains.client.model.Gebruiker;

/**
 * Created by Jasper on 11/21/2015.
 */
public class UserBox extends HBox {

    public UserBox(final Gebruiker gebruiker) {
        this.setPrefHeight(300);

        final Image image = new Image(gebruiker.getImage());
        final ImageView imgView = new ImageView(image);
        this.getChildren().add(imgView);

    }
}
