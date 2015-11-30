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
import nl.tudelft.twobrains.client.controller.views.RegisterController;
import nl.tudelft.twobrains.client.model.socket.TwoBrainsSocket;


//TODO: Change resource locations to user.home/.TwoBrains
public class TwoBrains extends Application {

    private TwoBrainsSocket socket;

    private Scene loginScene;
    private Scene matchScene;
    private Scene registerScene;

    private Stage stage;

    /**
     * De Application wordt gestart.
     * @param args
     */
    public static void main(final String[] args) {
        launch(args);
    }

    /**
     *
     * @return De huidige TwoBrainsSocket wordt geretouneerd
     */
    public TwoBrainsSocket getSocket() {
        return this.socket;
    }

    /**
     *
     * @param socket wordt de nieuwe TwoBrainsSocket
     */
    public void setSocket(TwoBrainsSocket socket) {
        this.socket = socket;
    }

    /**
     *  Alle Scene's worden geladen en geinitialiseerd.
     *  En de LoginScene wordt geshowed.
     *
     * @param primaryStage De ingevoerde Stage zal de primaryStage worden
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/login/login.fxml"));
        loader.setController(new LoginController(this));
        final Parent loginParent = loader.load();
        this.loginScene = new Scene(loginParent);

        loader = new FXMLLoader(getClass().getResource("view/match/match.fxml"));
        final Parent matchParent = loader.load();
        this.matchScene = new Scene(matchParent);

        loader = new FXMLLoader(getClass().getResource("view/registreer/registreer.fxml"));
        loader.setController(new RegisterController(this));
        final Parent registreerParent = loader.load();
        this.registerScene = new Scene(registreerParent);

        this.socket = new TwoBrainsSocket("127.0.0.1", 4444);

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    /**
     *
     * @return De MatchScene wordt geretouneerd
     */
    public Scene getMatchScene() {
        return this.matchScene;
    }

    /**
     *
     * @return De RegistreerScene wordt geretouneerd
     */
    public Scene getRegisterScene() {
        return this.registerScene;
    }

    /**
     *
     * @return De LoginScene wordt geretouneerd
     */
    public Scene getLoginScene() {
        return this.loginScene;
    }

    /**
     *
     * @param loginScene wordt de nieuwe LoginScene
     */
    public void setLoginScene(Scene loginScene) {
        this.loginScene = loginScene;
    }

    /**
     *
     * @param matchScene wordt de nieuwe MatchScene
     */
    public void setMatchScene(Scene matchScene) {
        this.matchScene = matchScene;
    }

    /**
     *
     * @param registerScene wordt de nieuwe MatchScene
     */
    public void setRegisterScene(Scene registerScene) {
        this.registerScene = registerScene;
    }

    /**
     *
     * @param scene wordt geshowed op het beeld.
     */
    public void show(final Scene scene) {
        this.stage.setScene(scene);
        this.stage.centerOnScreen();

    }

    /**
     *
     * @return Retouneert de huidige Stage
     */
    public Stage getStage() {
        return this.stage;
    }
}
