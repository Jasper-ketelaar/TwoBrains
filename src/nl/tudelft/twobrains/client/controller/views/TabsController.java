package nl.tudelft.twobrains.client.controller.views;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import nl.tudelft.twobrains.client.TwoBrains;
import nl.tudelft.twobrains.client.controller.AbstractController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jasperketelaar on 1/11/16.
 */
public class TabsController extends AbstractController {

    @FXML
    private TabPane tabPane;

    private final TwoBrains twoBrains;
    private final Tab matches;


    public TabsController(final TwoBrains twoBrains) {
        this.matches = new Tab("Matches");
        this.twoBrains = twoBrains;

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void initItems() {
        tabPane.setFocusTraversable(false);
        tabPane.setTabMinHeight(40);
        tabPane.setTabMinWidth(150);
        twoBrains.getMatchScene().getController().initItems();
        matches.setContent(twoBrains.getMatchScene().getScene().getRoot());


        tabPane.getTabs().add(matches);
    }
}
