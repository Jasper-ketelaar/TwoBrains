package nl.tudelft.twobrains.client.view.registreer.comp;

import javafx.geometry.Pos;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import nl.tudelft.twobrains.client.model.interfaces.Validation;

/**
 * Created by jasperketelaar on 12/2/15.
 */
public class TooltipTextField extends StackPane {

    private Validation validation;

    private final Tooltip tooltip;
    private final ImageView imageView;
    private final TextField textField;

    public TooltipTextField(final String tooltip, final boolean password, final double x, final double y, final String prompt) {
        this.setPrefSize(285, 30);
        this.setLayoutX(x);
        this.setLayoutY(y);

        this.textField = password ? new PasswordField() : new TextField();
        this.textField.setMaxSize(255, 30);
        this.textField.setPromptText(prompt);

        this.tooltip = new Tooltip(tooltip);
        System.out.println(getClass().getResource("../info.png"));
        this.imageView = new ImageView(new Image(getClass().getResource("../info.png").toExternalForm()));
        this.imageView.setFitHeight(15);
        this.imageView.setFitWidth(15);
        Tooltip.install(imageView, this.tooltip);

        this.getChildren().addAll(textField, imageView);
        StackPane.setAlignment(textField, Pos.CENTER_LEFT);
        StackPane.setAlignment(imageView, Pos.CENTER_RIGHT);

        this.validation = () -> !textField.getText().equals("");

    }

    public TextField getTextField() {
        return this.textField;
    }

    public void setValidation(final Validation validation) {
        this.validation = validation;
    }

    public Validation getValidation() {
        return this.validation;
    }
}
