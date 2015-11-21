package nl.tudelft.twobrains.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//TODO: Javadoc comments
public class TwoBrains extends Application {

    public static void main(final String[] args) {
       /* try {
            final Socket test = new Socket("127.0.0.1", 4444);
            final DataOutputStream output = new DataOutputStream(test.getOutputStream());
            output.writeUTF("LOGIN:ibuddyh@gmail.com");

        } catch (IOException e) {
            e.printStackTrace();
        }*/
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Parent loader = FXMLLoader.load(getClass().getResource("view/match/AllMatchPage.fxml"));
        final Scene scene = new Scene(loader);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
