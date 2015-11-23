package nl.tudelft.twobrains.client;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.tudelft.twobrains.client.model.Gebruiker;
import nl.tudelft.twobrains.client.model.socket.TwoBrainsSocket;
import nl.tudelft.twobrains.client.view.match.comp.UserBox;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.image.BufferedImage;
import java.io.IOException;

//TODO: Javadoc comments
public class TwoBrains extends Application {

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Parent loader = FXMLLoader.load(getClass().getResource("view/match/AllMatchPage.fxml"));
        final Node scrollPane = ((Parent) loader.getChildrenUnmodifiable().get(1)).getChildrenUnmodifiable().get(0);

        final TwoBrainsSocket user = new TwoBrainsSocket("127.0.0.1", 4444);

        final String email = "ibuddyh@gmail.com";
        user.getOutputStream().writeUTF("Login:;" + email + ":000000");
        final String response = user.getInputStream().readUTF();
        if (response.contains("Succes")) {
            final JSONParser parser = new JSONParser();
            final String json = response.replace("Succes:", "");

            final Gebruiker gebruiker = new Gebruiker(email, (JSONObject) parser.parse(json));
            gebruiker.setConnection(user);
            final UserBox userBox = new UserBox(gebruiker);
            ((VBox)((ScrollPane) scrollPane).getContent()).getChildren().addAll(new Separator(), userBox);
        }

        final Scene scene = new Scene(loader);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
