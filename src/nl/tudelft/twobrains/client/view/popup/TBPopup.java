package nl.tudelft.twobrains.client.view.popup;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Created by Jasper on 11/30/2015.
 * Deze klasse wordt gebruikt op het moment er een pop-up moet verschijnen
 * In de pop-up wordt veelal informatie gezet voor de gebruiker
 */
public class TBPopup extends Stage {

    public static final int WARNING = 0;

    /**
     * Hier wordt de pop-up constructor aangemaakt, hij krijgt een aantal parameters mee:
     * In de constructor wordt dmv de parameters vastgezet hoe de pop er precies uit moet zien
     * Wat de waarschuwing is, wat de hoogte en de  breedte is etc:
     * @param owner
     * @param warning
     * @param width
     * @param height
     * @param type
     */
    public TBPopup(final Stage owner, final String warning, final double width, final double height, final int type) {
        this.initModality(Modality.APPLICATION_MODAL);
        this.initOwner(owner);
        this.setTitle(decodeTitle(type));
        final StackPane stackPane = new StackPane();

        final Label warningText = new Label(warning);
        warningText.setFont(Font.font(20));
        warningText.setAlignment(Pos.CENTER);
        warningText.setLayoutX(width / 2);
        warningText.setWrapText(true);
        warningText.setTextFill(decodeType(type));
        warningText.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 20));
        stackPane.getChildren().add(warningText);
        StackPane.setAlignment(warningText, Pos.TOP_CENTER);

        final Button confirm = new Button("OK");
        confirm.setPrefWidth(0.90 * width);
        confirm.setOnAction(e -> this.hide());
        stackPane.getChildren().add(confirm);
        stackPane.setPadding(new Insets(30, 10, 10, 10));
        StackPane.setAlignment(confirm, Pos.BOTTOM_CENTER);

        final Scene scene = new Scene(stackPane, width + 20, height + 10);
        this.setScene(scene);
    }

    /**
     * Methode waarbij gezorgd wordt indien de pop-up een waarschuwing is hij de kleur rood krijgt
     * @param type
     * @return
     */
    private Paint decodeType(final int type) {
        if(type == WARNING) {
            return Color.RED;
        }
        return Color.BLACK;
    }

    /**
     * Methode waarbij ervoor gezorgd wordt indien de pop-up een waarschuwing is de gebruiker ook te zien krijgt dat het een waarschuwing is
     * @param type
     * @return
     */
    private String decodeTitle(final int type) {
        if(type == WARNING) {
            return "Warning";
        }
        return "Default";
    }
}
