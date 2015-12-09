package nl.tudelft.twobrains.client.controller.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import nl.tudelft.twobrains.client.TwoBrains;
import nl.tudelft.twobrains.client.controller.AbstractController;
import nl.tudelft.twobrains.client.model.Gebruiker;
import nl.tudelft.twobrains.client.model.socket.TwoBrainsSocket;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller klasse voor de login pagina
 * controleert acties van een event nou en kiest voor een eventuele follow-up ( naar registercontroller of matchcontroller)
 */
public class LoginController extends AbstractController {

    /**
     * Email text field
     */
    @FXML
    private TextField email;

    /**
     * Wachtwoord password field
     */
    @FXML
    private PasswordField password;

    /**
     * Label waar error komt te staan
     */
    @FXML
    private Label error;

    /**
     * TwoBrains instantie
     */
    private final TwoBrains twoBrains;

    /**
     * Constructor logincontroller wordt aangemaakt
     * Bij aanroepen moet een twobrainsobject worden meegegeven en die wordt geinitialiseerd in het privaat attribuut van deze klasse
     * @param twoBrains
     */
    public LoginController(final TwoBrains twoBrains) {
        this.twoBrains = twoBrains;
    }

    /**
     * Een void methode voor het inloggen,
     * haalt uit het twobrains object een event
     * @param evt
     * Kijkt of bij het inlogveld iets ingevuld is of er bij passwoord iets ingevuld.
     * Als er niks staat, komt de error met voer in beide velden iets in
     *
     */
    public void login(final ActionEvent evt) {
        final String email = this.email.getText();
        final String pw = this.password.getText();

        if (email.equals("") || pw.equals("")) {
            error.setText("Voer wat in voor beide velden!");
            return;
        }

        final TwoBrainsSocket socket = twoBrains.getSocket();
        final String response = socket.login(email, pw);

        if (response.contains("Succes")) {
            twoBrains.setGebruiker(Gebruiker.parse(email, response.replace("Succes:", "")));
            twoBrains.show(twoBrains.getMatchScene());
        } else {
            error.setText("Gegevens verkeerd");
        }
        password.clear();
        password.requestFocus();
    }

    public void register(final ActionEvent evt) {
        twoBrains.show(twoBrains.getRegisterScene());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
