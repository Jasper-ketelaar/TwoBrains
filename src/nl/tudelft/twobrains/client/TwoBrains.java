package nl.tudelft.twobrains.client;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import nl.tudelft.twobrains.client.controller.views.*;
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
    private SceneWrapper homeScene;

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
     * @param socket wordt de nieuwe TwoBrainsSocket
     */
    public void setSocket(TwoBrainsSocket Socket) {
        this.socket = Socket;
    }

    /**
     * Alle Scene's worden geladen en geinitialiseerd.
     * En de LoginScene wordt geshowed.
     *
     * @param primaryStage De ingevoerde Stage zal de primaryStage worden
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Platform.setImplicitExit(false);
        this.stage = primaryStage;


        this.loginScene = new SceneWrapper("login", new LoginController(this));
        this.matchScene = new SceneWrapper("match", new MatchController(this));
        this.registerScene = new SceneWrapper("registreer", new RegisterController(this), getClass().getResource("view/registreer/registreer.css").toExternalForm());
        this.tabScene = new SceneWrapper("tabs", new TabsController(this));
        this.homeScene = new SceneWrapper("home", new HomeController(this));

        this.socket = new TwoBrainsSocket("127.0.0.1", 4444);

        primaryStage.setScene(loginScene.getScene());
        primaryStage.show();
    }

    public TwoBrains() throws Exception {


        this.loginScene = new SceneWrapper("login", new LoginController(this));
        this.matchScene = new SceneWrapper("match", new MatchController(this));
        this.registerScene = new SceneWrapper("registreer", new RegisterController(this), getClass().getResource("view/registreer/registreer.css").toExternalForm());
        this.tabScene = new SceneWrapper("tabs", new TabsController(this));
        this.homeScene = new SceneWrapper("home", new HomeController(this));

        this.socket = new TwoBrainsSocket("127.0.0.1", 4444);


    }

    public TwoBrains(int port) throws Exception {
        this.loginScene = new SceneWrapper("login", new LoginController(this));
        this.matchScene = new SceneWrapper("match", new MatchController(this));
        this.registerScene = new SceneWrapper("registreer", new RegisterController(this), getClass().getResource("view/registreer/registreer.css").toExternalForm());
        this.tabScene = new SceneWrapper("tabs", new TabsController(this));
        this.homeScene = new SceneWrapper("home", new HomeController(this));

        this.socket = new TwoBrainsSocket("127.0.0.1", port);
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
     * @return De HomeScene wordt geretouneerd
     */
    public SceneWrapper getHomeScene() {
        return this.homeScene;
    }

    /**
     * @param scene wordt geshowed op het beeld.
     */
    public void show(final SceneWrapper scene) throws Exception {
        scene.getController().initItems();
        this.stage.setScene(scene.getScene());
        this.stage.centerOnScreen();
        if (scene.getController() instanceof TabsController) {
            ((MatchController) getMatchScene().getController()).update();
        }

    }

    /**
     * @return Retouneert de huidige Stage
     */
    public Stage getStage() {
        return this.stage;
    }

    public void setStage(Stage Stage) {
        this.stage = Stage;
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
