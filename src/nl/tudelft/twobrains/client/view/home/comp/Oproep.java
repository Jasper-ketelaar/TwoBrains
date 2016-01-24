package nl.tudelft.twobrains.client.view.home.comp;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 * Created by jasperketelaar on 1/24/16.
 */
public class Oproep extends Pane {

    public Oproep(final String naam, final String vak, final String email) {
        this.setPrefSize(273, 189);

        final Label naamLabel = new Label(naam);
        naamLabel.setLayoutX(81);
        naamLabel.setLayoutY(39);
        naamLabel.setFont(Font.font(23));

        final Label oproepLabel = new Label("Dringende oproep voor: " + vak);
        oproepLabel.setLayoutX(6);
        oproepLabel.setLayoutY(89);
        oproepLabel.setFont(Font.font(17));

        final Label emailLabel = new Label("Email adres: " + email);
        emailLabel.setLayoutX(6);
        emailLabel.setLayoutY(122);
        emailLabel.setFont(Font.font(17));

        this.getChildren().addAll(naamLabel, oproepLabel, emailLabel);
    }
}
