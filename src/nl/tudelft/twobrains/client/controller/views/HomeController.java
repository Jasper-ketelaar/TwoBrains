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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.image.BufferedImage;
import java.io.IOException;
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

    public void oproep(final ActionEvent e) throws IOException, ParseException {
        hbox.getChildren().clear();
        final String vak = this.vak.getSelectionModel().getSelectedItem().toString();
        final String email = twoBrains.getGebruiker().getEmail();
        final String naam = twoBrains.getGebruiker().getVoornaam() + " " + twoBrains.getGebruiker().getAchternaam();
        final String all = twoBrains.getSocket().oproep(vak, email, naam);
        final JSONParser parser = new JSONParser();
        final JSONObject object = (JSONObject) parser.parse(all);
        for (final Object key: object.keySet()) {
            final JSONObject data = (JSONObject) object.get(key);
            final Oproep oproep = new Oproep(data.get("Naam").toString(), data.get("Vak").toString(), key.toString());
            if(hbox.getChildren().size() > 0) {
                final Separator separator = new Separator(Orientation.VERTICAL);
                separator.setPadding(new Insets(0, 50, 0, 50));
                hbox.getChildren().add(separator);
            }
            hbox.getChildren().add(oproep);
        }
    }

    public void ververs(final ActionEvent e) throws ParseException {
        hbox.getChildren().clear();
        final String all = twoBrains.getSocket().oproep("", "", "");
        final JSONParser parser = new JSONParser();
        final JSONObject object = (JSONObject) parser.parse(all);
        for (final Object key: object.keySet()) {
            final JSONObject data = (JSONObject) object.get(key);
            final Oproep oproep = new Oproep(data.get("Naam").toString(), data.get("Vak").toString(), key.toString());
            if(hbox.getChildren().size() > 0) {
                final Separator separator = new Separator(Orientation.VERTICAL);
                separator.setPadding(new Insets(0, 50, 0, 50));
                hbox.getChildren().add(separator);
            }
            hbox.getChildren().add(oproep);
        }
    }

    @Override
    public void initItems() throws Exception {
        final Gebruiker gebruiker = twoBrains.getGebruiker();
        this.vak.getItems().addAll(gebruiker.getVakken());
        final BufferedImage bImage = gebruiker.getUserImage();
        final WritableImage writableImage = new WritableImage(bImage.getWidth(), bImage.getHeight());
        final Image image = SwingFXUtils.toFXImage(bImage, writableImage);
        this.image.setImage(image);
        this.ververs(null);
        text.setText("Welkom op de home pagina, " + gebruiker.getVoornaam() + "!\r\n" +
                    "Hier kun je aangeven dat je op zoek bent naar een huiswerk maatje en je kunt dringende opzoeken doen \r\n" +
                "voor het geval je snel een partner nodig hebt! ");
    }
}
