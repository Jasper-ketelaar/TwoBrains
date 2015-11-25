package nl.tudelft.twobrains.client.controller.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import nl.tudelft.twobrains.client.TwoBrains;
import nl.tudelft.twobrains.server.model.Gebruiker;
import org.json.simple.JSONObject;

/**
 * Created by jasperketelaar on 11/25/15.
 */
public class RegisterController {

    @FXML
    private TextField email;

    @FXML
    private PasswordField wachtwoord;

    @FXML
    private TextField voornaam;

    @FXML
    private TextField achternaam;

    @FXML
    private DatePicker geboorte;

    @FXML
    private TextField locatie;

    @FXML
    private TextField opleiding;

    @FXML
    private TextField vakken;

    @FXML
    private ImageView profielfoto;

    @FXML
    private RadioButton man;

    @FXML
    private RadioButton vrouw;

    @FXML
    private HBox hBox;

    private final TwoBrains twoBrains;

    public RegisterController(final TwoBrains twoBrains) {
        this.twoBrains = twoBrains;
    }

    public void upload(final ActionEvent evt) {
        final FileChooser chooser = new FileChooser();
        chooser.setTitle("Kies een plaatje");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image", ".jpg", ".png"));
        chooser.showOpenDialog(twoBrains.getStage());
    }

    public void register(final ActionEvent evt) {

        final JSONObject user = new JSONObject();
        user.put("Voornaam", voornaam.getText());
        user.put("Achternaam", achternaam.getText());
        user.put("Leeftijd", "" + (2015 - geboorte.getValue().getYear()));
        user.put("Geslacht", man.isSelected() ? "M" : "V");
        user.put("Wachtwoord", wachtwoord.getText());
        user.put("Locatie", locatie.getText());
        user.put("Opleiding", opleiding.getText());
        user.put("Vakken", vakken.getText());

        final String response = twoBrains.getSocket().register(email.getText(), user);
        if (response.equals("Succes")) {
            twoBrains.show(twoBrains.getLoginScene());
        }

    }
}
