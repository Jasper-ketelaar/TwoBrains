package nl.tudelft.twobrains.client.controller.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import nl.tudelft.twobrains.client.TwoBrains;
import nl.tudelft.twobrains.client.model.socket.TwoBrainsSocket;

import javax.swing.*;

/**
 * Created by Leroy on 13-1-2016.
 */
public class ChatController {

    @FXML
    private TextArea chatWindow;

    @FXML
    private TextField message;

    @FXML
    private Label error;


    private final TwoBrains Ontvanger;
    private final TwoBrains Verstuurder;

    private final String emailOntvanger;


    public ChatController(final TwoBrains twoBrainsOntvanger, final TwoBrains twoBrainsVerstuurder) {
        this.Verstuurder = twoBrainsVerstuurder;
        this.Ontvanger = twoBrainsOntvanger;
        emailOntvanger = this.Ontvanger.getGebruiker().getEmail();

    }


    public void verzend(final ActionEvent evt) {
        final String bericht = message.getText();

        if (bericht.equals("")) {
            error.setText("Voer wat in voor het textveld!");
            return;
        }

        final TwoBrainsSocket socket = Verstuurder.getSocket();
        final String response = socket.message(emailOntvanger, bericht);

        if(response.contains("Success")){
            chatWindow.appendText(bericht);
        } else {
            error.setText("Verzenden mislukt, probeer opnieuw of check internet-verbinding");
            return;
        }
        message.clear();
        message.requestFocus();
    }

    public void goMatchPage(ActionEvent evt) {
        Verstuurder.show(Verstuurder.getRegisterScene());
    }


}
