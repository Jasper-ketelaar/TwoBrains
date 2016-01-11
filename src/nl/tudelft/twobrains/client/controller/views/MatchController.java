package nl.tudelft.twobrains.client.controller.views;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import nl.tudelft.twobrains.client.TwoBrains;
import nl.tudelft.twobrains.client.controller.AbstractController;
import nl.tudelft.twobrains.client.model.Gebruiker;
import nl.tudelft.twobrains.client.view.match.comp.UserBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jasperketelaar on 11/24/15.
 */
public class MatchController extends AbstractController {

    @FXML
    private TextField filter;

    @FXML
    private VBox vBox;


    private final TwoBrains twoBrains;

    /**
     * Class constructor specificeren welke TwoBrains. De MatchController
     * wordt zo aan een specifieke TwoBrains app gekoppeld.
     * @param twoBrains
     */
    public MatchController(final TwoBrains twoBrains) {
        this.twoBrains = twoBrains;
    }

    /**
     * Methode voor het filteren welke Matches worden weergegeven als
     * er een letter in de searchbar wordt toegevoegd of weggehaald.
     * Eerst wordt gecheckd of de key een backspace is of niet.
     * Als het een backspace is wordt de String waarmee wordt
     * vergeleken een character korter, anders wordt de letter
     * toegevoegd.
     * <p>
     * De verschillende Matches (Gebruikers) worden geselecteerd of
     * ze wel of niet de String in hun voor/achternaam bevatten.
     * Zo wel blijven ze staan, anders 'faden' ze weg.
     *
     * @param event De KeyEvent waarop gereageerd moet worden.
     */
    @FXML
    public void filterAction(final KeyEvent event) {

        String input = filter.getText();
        /*if (event.getCode().equals(KeyCode.BACK_SPACE)) {
            input = input.substring(0, input.length() - 1);
        } else {
            input = input + event.getText();
        }*/

        for (final Node child : vBox.getChildren()) {
            if (child instanceof UserBox) {
                final UserBox userBox = (UserBox) child;
                final Gebruiker gebruiker = userBox.getGebruiker();
                final String naam = gebruiker.getVoornaam() + " " + gebruiker.getAchternaam();
                if ((naam).toLowerCase().contains(input.toLowerCase())) {
                    if (userBox.getOpacity() == 1) return;
                    final FadeTransition ft = new FadeTransition(Duration.millis(500), userBox);
                    ft.setToValue(1);
                    ft.setByValue(0.1);
                    ft.setFromValue(0);
                    ft.playFromStart();
                } else {
                    if (userBox.getOpacity() < 1) return;
                    final FadeTransition ft = new FadeTransition(Duration.millis(500), userBox);
                    ft.setToValue(0);
                    ft.setByValue(0.1);
                    ft.setFromValue(1);
                    ft.playFromStart();
                }
            }
        }
    }

    /**
     * Methode die de URL en de ResourceBundle specificeerd voor
     * het opstarten van deze controller.
     *
     * @param location De locatie URL waarna de controller moet
     *                 reageren.
     * @param resources De resources die worden door de controller.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Methode voor het initialiseren van de users in de MatchScene.
     */
    @Override
    public void initItems() {
        vBox.getChildren().addAll(new UserBox(twoBrains.getGebruiker()), new UserBox(twoBrains.getGebruiker()));
    }
}
