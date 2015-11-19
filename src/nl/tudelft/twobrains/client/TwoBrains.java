package nl.tudelft.twobrains.client;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class TwoBrains extends Application {

    public static void main(final String[] args) {
        try {
            final Socket test = new Socket("127.0.0.1", 4444);
            final DataOutputStream output = new DataOutputStream(test.getOutputStream());
            output.writeUTF("LOGIN:ibuddyh@gmail.com");

        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true) {

        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
