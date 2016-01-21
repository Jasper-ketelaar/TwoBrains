package nl.tudelft.twobrains.client.view.match.comp;

import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import nl.tudelft.twobrains.client.model.Gebruiker;

import java.awt.image.BufferedImage;

/**
 * Created by Jasper on 11/21/2015.
 * Klasse Userbox
 * Laat de gegevens van een gebruiker zien in een info-Pane
 *
 *
 */
public class UserBox extends HBox {

    /*
     *  Attribuut gebruiker
     */

    private final Gebruiker gebruiker;

    /**
     * Contructor Userbox wordt aangemaakt
     * @param gebruiker
     * Er wordt eerst een imagine van de gebruiker geladen, want het wordt allemaal in een userbox gezet
     * Wordt vastgesteld hoe groot en breed deze box is
     * Vervolgens wordt een pane aangemaakt
     * Hier wordt de naam, text en een lijst ingezet (dus de gebruikersinfo)
     * Aan het einde wordt de pane (met daarin de informatie) en de imagine van de gebruiker in een userbox gezet
     *
     */
    public UserBox(final Gebruiker gebruiker) {
        System.out.println(gebruiker.getEmail());
        this.gebruiker = gebruiker;
        this.setFocusTraversable(false);
        final BufferedImage bImage = gebruiker.getUserImage();
        final WritableImage writableImage = new WritableImage(bImage.getWidth(), bImage.getHeight());
        final Image image = SwingFXUtils.toFXImage(bImage, writableImage);
        final ImageView imgView = new ImageView(image);
        imgView.setFitWidth(210);
        imgView.setFitHeight(155);

        final Pane info = new Pane();
        info.setPrefSize(1079.0, 155.0);

        final Label name = new Label();
        name.setAlignment(Pos.CENTER);
        name.setLayoutX(20.0);
        name.setLayoutY(15.0);
        name.setPrefHeight(20.0);
        name.setPrefWidth(579.0);
        name.setText(gebruiker.getVoornaam() + " " + gebruiker.getAchternaam());
        name.setFont(Font.font("Verdana", 36));

        final TextArea text = new TextArea();
        text.setEditable(false);
        text.setLayoutX(20.0);
        text.setLayoutY(80.0);
        text.setPrefHeight(65.0);
        text.setPrefWidth(576.0);
        text.setFont(Font.font("Verdana", 16));
        text.setText(String.format("%s is een %s van %s jaar oud die %s in %s studeert " +
                        "\nOm contact op te nemen met %s stuur een mail naar %s.", gebruiker.getVoornaam(),
                gebruiker.getGeslacht().contains("M") ? "man" : "vrouw", gebruiker.getLeeftijd(), gebruiker.getOpleiding(),
                gebruiker.getLocatie(), gebruiker.getVoornaam(), gebruiker.getEmail()));


        final ListView list = new ListView(FXCollections.observableArrayList(gebruiker.getVakken()));
        list.setLayoutX(632.0);
        list.setLayoutY(6.0);
        list.setPrefHeight(147.0);
        list.setPrefWidth(409.0);

        info.getChildren().addAll(name, text, list);
        this.getChildren().addAll(imgView, info);
        this.setOnMouseClicked(event -> {

            this.setStyle("-fx-background-color: lightgray");

        });
    }

    /**
     * Klasse getGebruiker
     *      Retourneert een gebruiker-JSONobject
     * @return
     */
    public Gebruiker getGebruiker() {
        return this.gebruiker;
    }
}
