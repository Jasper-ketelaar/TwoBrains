package nl.tudelft.twobrains.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;

//TODO: Javadoc comments
public class TwoBrains extends Application {

    public static void main(final String[] args) {
        try {
            final Socket user = new Socket("127.0.0.1", 4444);
            final DataOutputStream output = new DataOutputStream(user.getOutputStream());
            final DataInputStream inputStream = new DataInputStream(user.getInputStream());
            output.writeUTF("IMAGE:ibuddyh@gmail.com");
            final byte[] sizeB = new byte[4];
            inputStream.read(sizeB);

            final int size = ByteBuffer.wrap(sizeB).asIntBuffer().get();

            final byte[] imageB = new byte[size];
            inputStream.read(imageB);
            final BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageB));
            System.out.println("image = " + image);

            output.writeUTF("IMAGE");

            while(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Parent loader = FXMLLoader.load(getClass().getResource("view/match/AllMatchPage.fxml"));
        final Scene scene = new Scene(loader);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
