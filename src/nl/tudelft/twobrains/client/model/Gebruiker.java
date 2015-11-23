package nl.tudelft.twobrains.client.model;

import nl.tudelft.twobrains.client.model.socket.TwoBrainsSocket;
import org.json.simple.JSONObject;

import java.awt.image.BufferedImage;
import java.io.IOException;

//TODO: Gebruiker attributes aanmaken
public class Gebruiker {

    private final String email;
    private final JSONObject data;

    private TwoBrainsSocket connection;

    public Gebruiker(final String email, final JSONObject data) {
        this.email = email;
        this.data = data;
    }

    public TwoBrainsSocket getConnection() {
        return connection;
    }

    public void setConnection(final TwoBrainsSocket socket) {
        this.connection = socket;
    }

    public BufferedImage getUserImage() {
        return connection.getImage(this.email);
    }

    public boolean isConnected() {
        return connection != null;
    }

    public void disconnect() {
        try {
            this.connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
