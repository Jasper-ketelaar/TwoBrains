package nl.tudelft.twobrains.client;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import nl.tudelft.twobrains.client.controller.views.LoginController;
import nl.tudelft.twobrains.client.controller.views.RegisterController;
import nl.tudelft.twobrains.client.model.socket.TwoBrainsSocket;

//TODO: Javadoc comments
//TODO: Change resource locations to user.home/.TwoBrains
public class TwoBrains extends Application {

    private TwoBrainsSocket socket;

    private Scene loginScene;
    private Scene matchScene;
    private Scene registerScene;

    private Stage stage;

    public static void main(final String[] args) {
        launch(args);
    }

    public TwoBrainsSocket getSocket() {
        return this.socket;
    }

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

    public Scene getMatchScene() {
        return this.matchScene;
    }

    public Scene getRegisterScene() {
        return this.registerScene;
    }

    public void show(final Scene scene) {
        this.stage.setScene(scene);
  //      final FadeTransition ft = new FadeTransition(Duration.millis(800), this.stage)
        this.stage.centerOnScreen();
    }

    public Stage getStage() {
        return this.stage;
    }


}
