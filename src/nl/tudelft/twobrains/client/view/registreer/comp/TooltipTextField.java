package nl.tudelft.twobrains.client.view.registreer.comp;

import javafx.geometry.Pos;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * Created by jasperketelaar on 12/2/15.
 */
public class TooltipTextField extends StackPane {

    private final Tooltip tooltip;
    private final ImageView imageView;
    private final TextField textField;

    public TooltipTextField(final String tooltip, final boolean password) {
        this.setPrefSize(285, 30);

        this.textField = password ? new PasswordField() : new TextField();

        this.tooltip = new Tooltip(tooltip);
        System.out.println(getClass().getResource("../info.png"));
        this.imageView = new ImageView(new Image(getClass().getResource("../info.png").toExternalForm()));
        this.imageView.setFitHeight(20);
        this.imageView.setFitWidth(20);
        Tooltip.install(imageView, this.tooltip);

        this.getChildren().addAll(textField, imageView);
        StackPane.setAlignment(textField, Pos.CENTER_LEFT);
        StackPane.setAlignment(imageView, Pos.CENTER_RIGHT);

    }

    public TextField getTextField() {
        return this.textField;
    }
}
