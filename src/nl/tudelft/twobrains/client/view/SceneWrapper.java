package nl.tudelft.twobrains.client.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import nl.tudelft.twobrains.client.controller.AbstractController;

import java.io.IOException;

/**
 * Created by Jasper on 12/1/2015.
 */
public class SceneWrapper {

    private final AbstractController controller;
    private final Scene scene;


    public SceneWrapper(final String name, final AbstractController controller, final String... sheets) throws IOException {
        this.controller = controller;
        System.out.println(name);
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(name + "/" + name + ".fxml"));
        if (controller != null)
            loader.setController(controller);

        final Parent parent = loader.load();
        this.scene = new Scene(parent);

        if (sheets.length > 0 && sheets[0].length() > 0)
            this.scene.getStylesheets().addAll(sheets);
    }

    public SceneWrapper(final String name, final AbstractController controller) throws IOException {
        this(name, controller, "");
    }

    public SceneWrapper(final String name) throws IOException {
        this(name, null);
    }

    public Scene getScene() {
        return this.scene;
    }

    public AbstractController getController() {
        return this.controller;
    }
}
