package nl.tudelft.twobrains.client.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import nl.tudelft.twobrains.client.controller.AbstractController;

import java.io.IOException;

/**
 * Created by Jasper on 12/1/2015.
 * Deze klasse zorgt ervoor dat dat we een controller aan een scene kunnen hangen
 * Ook vermakkelijkt deze klasse het maken van een scene
 * Een scene is de javaFX display, hier staan alle componenten op (GUI)
 * Klasse wordt aangemaakt met 3 constructors verschillend van parameter.
 * Reden hiervoor is dat niet alle scene's een controller en/of een sheet nodig heeft
 */
public class SceneWrapper {

    /**
     * Initialiseert 2 attributen:
     * @attrib Abstractcontroller:controller
     * @attrib Scene:scene
     */
    private final AbstractController controller;
    private final Scene scene;


    /**
     * Situatie waarin een scene een controller en een sheet nodig heeft
     * Creëert de eerste constructor Scenewrappen met 3 parameters:
     * @param name
     * @param controller
     * @param sheets
     * @throws IOException
     * @param controller : wordt geinitialiseert op attribuut
     * @param name wordt meteen uitgeprint
     */
    public SceneWrapper(final String name, final AbstractController controller, final String... sheets) throws IOException {
        this.controller = controller;
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(name + "/" + name + ".fxml"));
        if (controller != null)
            loader.setController(controller);

        final Parent parent = loader.load();
        this.scene = new Scene(parent);

        if (sheets.length > 0 && sheets[0].length() > 0)
            this.scene.getStylesheets().addAll(sheets);
    }

    /**
     * Situatie waarin de scene alleen de controller nodig heeft en geen sheets
     * Tweede constructor wordt aangemaakt met 2 param:
     * @param name
     * @param controller
     * @throws IOException
     */

    public SceneWrapper(final String name, final AbstractController controller) throws IOException {
        this(name, controller, "");
    }

/**
 * Situatie waarbij een scene geen controller én geen sheet nodig heeft
 * Laatste constructor wordt aangemaakt met alleen name parameter
 * @param name
 */

    public SceneWrapper(final String name) throws IOException {
        this(name, null);
    }

    /**
     * getMethode voor de scene
     * @return scene
     */
    public Scene getScene() {
        return this.scene;
    }

    /**
     * Getmethode voor de controller
     * @return controller
     */
    public AbstractController getController() {
        return this.controller;
    }
}
