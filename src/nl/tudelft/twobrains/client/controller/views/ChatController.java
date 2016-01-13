package nl.tudelft.twobrains.client.controller.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import nl.tudelft.twobrains.client.TwoBrains;

import javax.swing.*;

/**
 * Created by Leroy on 13-1-2016.
 */
public class ChatController {

    @FXML
    private TextField message;

    @FXML
    private Label error;


    private final TwoBrains twoBrains;


    public ChatController(final TwoBrains twoBrains) {
        this.twoBrains = twoBrains;
    }


    public void verzend(final ActionEvent evt) {
        final String bericht = message.getText();

        if (bericht.equals("")) {
            error.setText("Voer wat in voor het textveld!");
            return;
        }

    }

    public void goMatchPage(ActionEvent evt) {
        twoBrains.show(twoBrains.getRegisterScene());
    }


}
