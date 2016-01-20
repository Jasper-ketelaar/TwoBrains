package nl.tudelft.twobrains.client;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import nl.tudelft.twobrains.client.controller.views.LoginController;
import nl.tudelft.twobrains.client.controller.views.MatchController;
import nl.tudelft.twobrains.client.controller.views.RegisterController;
import nl.tudelft.twobrains.client.controller.views.TabsController;
import nl.tudelft.twobrains.client.model.Gebruiker;
import nl.tudelft.twobrains.client.model.socket.TwoBrainsSocket;
import nl.tudelft.twobrains.client.view.SceneWrapper;
import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Klasse waarin het programma wordt gecreëerd en opgestart
 */

//TODO: Change resource locations to user.home/.TwoBrains
public class TwoBrains extends Application {

    /**
     * Iniliateerst 5 attributen
     *
     * @attrib Socket
     * @attrib Scenewrapper: scene voor login
     * @attrib Scenewrapper: scene voor matchen
     * @attrib Scenewrapper: scene voor registeren
     * @attrib Stage:stage
     * @attrib Gebruiker:gebruiker
     */
    private TwoBrainsSocket socket;

    private SceneWrapper loginScene;
    private SceneWrapper matchScene;
    private SceneWrapper registerScene;
    private SceneWrapper tabScene;

    private Stage stage;

    private Gebruiker gebruiker;


    /**
     * De Application wordt gestart.
     *
     * @param args
     */
    public static void main(final String[] args) {
        launch(args);
    }

    /**
     * @return De huidige TwoBrainsSocket wordt geretouneerd
     */
    public TwoBrainsSocket getSocket() {
        return this.socket;
    }

    /**
     *
     * @param socket wordt de nieuwe TwoBrainsSocket
     */


    /**
     * Alle Scene's worden geladen en geinitialiseerd.
     * En de LoginScene wordt geshowed.
     *
     * @param primaryStage De ingevoerde Stage zal de primaryStage worden
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;


        this.loginScene = new SceneWrapper("login", new LoginController(this));
        this.matchScene = new SceneWrapper("match", new MatchController(this));
        this.registerScene = new SceneWrapper("registreer", new RegisterController(this), getClass().getResource("view/registreer/registreer.css").toExternalForm());
        this.tabScene = new SceneWrapper("tabs", new TabsController(this));

        this.socket = new TwoBrainsSocket("127.0.0.1", 4444);

        primaryStage.setScene(loginScene.getScene());
        primaryStage.show();
    }

    public void TwoBrains() throws Exception {


        this.loginScene = new SceneWrapper("login", new LoginController(this));
        this.matchScene = new SceneWrapper("match", new MatchController(this));
        this.registerScene = new SceneWrapper("registreer", new RegisterController(this), getClass().getResource("view/registreer/registreer.css").toExternalForm());
        this.tabScene = new SceneWrapper("tabs", new TabsController(this));

        this.socket = new TwoBrainsSocket("127.0.0.1", 4444);


    }


    /**
     * @return De MatchScene wordt geretouneerd
     */
    public SceneWrapper getMatchScene() {
        return this.matchScene;
    }

    /**
     * @return De RegistreerScene wordt geretouneerd
     */
    public SceneWrapper getRegisterScene() {
        return this.registerScene;
    }

    /**
     * @return De LoginScene wordt geretouneerd
     */
    public SceneWrapper getLoginScene() {
        return this.loginScene;
    }

    /**
     * @return De TabScene wordt geretouneerd
     */
    public SceneWrapper getTabScene() {
        return this.tabScene;
    }

    /**
     * @param scene wordt geshowed op het beeld.
     */
    public void show(final SceneWrapper scene) {
        scene.getController().initItems();
        this.stage.setScene(scene.getScene());
        this.stage.centerOnScreen();

    }

    /**
     * @return Retouneert de huidige Stage
     */
    public Stage getStage() {
        return this.stage;
    }

    /**
     * Set de gebruiker naar een gegeven gebruiker
     */
    public void setGebruiker(final Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
        this.gebruiker.setConnection(socket);
    }

    /**
     * Verkrijgt de gebruiker
     *
     * @return de gebruiker
     */
    public Gebruiker getGebruiker() {
        return this.gebruiker;
    }
}
