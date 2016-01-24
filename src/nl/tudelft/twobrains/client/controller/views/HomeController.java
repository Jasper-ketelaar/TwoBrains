package nl.tudelft.twobrains.client.controller.views;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import nl.tudelft.twobrains.client.TwoBrains;
import nl.tudelft.twobrains.client.controller.AbstractController;
import nl.tudelft.twobrains.client.model.Gebruiker;
import nl.tudelft.twobrains.client.view.home.comp.Oproep;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jasperketelaar on 1/23/16.
 */
public class HomeController extends AbstractController {

    @FXML
    private ImageView image;

    @FXML
    private TextArea text;

    @FXML
    private CheckBox opZoek;

    @FXML
    private ComboBox vak;

    @FXML
    private HBox hbox;


    private final TwoBrains twoBrains;

    public HomeController(TwoBrains twoBrains) {
        this.twoBrains = twoBrains;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void opZoek(final ActionEvent e) {
        ((TabsController)twoBrains.getTabScene().getController()).getMatches().disableProperty().setValue(!opZoek.isSelected());
    }

    public void oproep(final ActionEvent e) {
        final String vak = this.vak.getSelectionModel().getSelectedItem().toString();
        final String email = twoBrains.getGebruiker().getEmail();
        final String naam = twoBrains.getGebruiker().getVoornaam() + " " + twoBrains.getGebruiker().getAchternaam();
        if(hbox.getChildren().size() > 0) {
            final Separator separator = new Separator(Orientation.VERTICAL);
            separator.setPadding(new Insets(0, 20, 0, 20));
            hbox.getChildren().add(separator);
        }
        hbox.getChildren().add(new Oproep(naam, vak, email));
        final String all = twoBrains.getSocket().oproep(vak, email, naam);
    }

    @Override
    public void initItems() throws Exception {
        final Gebruiker gebruiker = twoBrains.getGebruiker();
        this.vak.getItems().addAll(gebruiker.getVakken());
        final BufferedImage bImage = gebruiker.getUserImage();
        final WritableImage writableImage = new WritableImage(bImage.getWidth(), bImage.getHeight());
        final Image image = SwingFXUtils.toFXImage(bImage, writableImage);
        this.image.setImage(image);

        text.setText("Welkom op de home pagina, " + gebruiker.getVoornaam() + "!\r\n" +
                    "Hier kun je aangeven dat je op zoek bent naar een huiswerk maatje en je kunt dringende opzoeken doen \r\n" +
                "voor het geval je snel een partner nodig hebt! ");
    }
}
