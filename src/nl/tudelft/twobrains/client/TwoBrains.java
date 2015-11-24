package nl.tudelft.twobrains.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.tudelft.twobrains.client.model.Gebruiker;
import nl.tudelft.twobrains.client.model.socket.TwoBrainsSocket;
import nl.tudelft.twobrains.client.view.match.comp.UserBox;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

//TODO: Javadoc comments
//TODO: Change resource locations to system.home/.TwoBrains
public class TwoBrains extends Application {

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Parent loader = FXMLLoader.load(getClass().getResource("view/match/matchpage.fxml"));
        final Node scrollPane = loader.getChildrenUnmodifiable().get(1);
        final TwoBrainsSocket user = new TwoBrainsSocket("127.0.0.1", 4444);

        final String email = "ibuddyh@gmail.com";
        user.getOutputStream().writeUTF("Login:;" + email + ":000000");
        final String response = user.getInputStream().readUTF();
        setUserAgentStylesheet(STYLESHEET_MODENA);

        if (response.contains("Succes")) {
            final JSONParser parser = new JSONParser();
            final String json = response.replace("Succes:", "");

            final Gebruiker gebruiker = new Gebruiker(email, (JSONObject) parser.parse(json));
            gebruiker.setConnection(user);

            final Separator separator = new Separator();
            separator.setMaxSize(1265.0, 3.0);
            separator.setPadding(new Insets(15.0));

            final UserBox userBox = new UserBox(gebruiker);

            ((VBox) ((ScrollPane) scrollPane).getContent()).getChildren().addAll(separator, userBox);
        }

        final Scene scene = new Scene(loader);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
