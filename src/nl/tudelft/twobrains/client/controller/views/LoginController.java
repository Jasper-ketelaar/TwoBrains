package nl.tudelft.twobrains.client.controller.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import nl.tudelft.twobrains.client.TwoBrains;
import nl.tudelft.twobrains.client.model.socket.TwoBrainsSocket;

/**
 * Created by jasperketelaar on 11/25/15.
 */
public class LoginController {

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private Label error;

    private final TwoBrains twoBrains;

    public LoginController(final TwoBrains twoBrains) {
        this.twoBrains = twoBrains;
    }

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
            twoBrains.show(twoBrains.getMatchScene());
        } else {
            error.setText("Gegevens verkeerd");
        }
        password.clear();
    }

    public void register(final ActionEvent evt) {
        twoBrains.show(twoBrains.getRegisterScene());
    }
}
