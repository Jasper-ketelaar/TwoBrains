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


    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
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
    public void login(final ActionEvent evt) throws Exception {
        final String email = this.email.getText();
        final String pw = this.password.getText();

        if (email.equals("") || pw.equals("")) {
            error.setText("Voer wat in voor beide velden!");
            return;
        }

        final TwoBrainsSocket socket = twoBrains.getSocket();
        final Gebruiker response = socket.login(email, pw);

        if (response != null) {
            twoBrains.setGebruiker(response);
            twoBrains.show(twoBrains.getTabScene());
        } else {
            error.setText("Gegevens verkeerd");
        }
        password.clear();
        password.requestFocus();
    }

    public void register(final ActionEvent evt) throws Exception {
        twoBrains.show(twoBrains.getRegisterScene());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
