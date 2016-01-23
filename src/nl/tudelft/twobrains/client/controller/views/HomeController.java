package nl.tudelft.twobrains.client.controller.views;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import nl.tudelft.twobrains.client.TwoBrains;
import nl.tudelft.twobrains.client.controller.AbstractController;
import nl.tudelft.twobrains.client.model.Gebruiker;

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


    private final TwoBrains twoBrains;

    public HomeController(TwoBrains twoBrains) {
        this.twoBrains = twoBrains;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void initItems() {
        final Gebruiker gebruiker = twoBrains.getGebruiker();
        final BufferedImage bImage = gebruiker.getUserImage();
        final WritableImage writableImage = new WritableImage(bImage.getWidth(), bImage.getHeight());
        final Image image = SwingFXUtils.toFXImage(bImage, writableImage);
        this.image.setImage(image);

        text.setText("Welkom op de home pagina, " + gebruiker.getVoornaam() + "!\r\n" +
                    "Hier kun je aangeven dat je op zoek bent naar een huiswerk maatje en je kunt dringende opzoeken doen \r\n" +
                "voor het geval je snel een partner nodig hebt! ");
    }
}
