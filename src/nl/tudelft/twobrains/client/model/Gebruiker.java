package nl.tudelft.twobrains.client.model;

import nl.tudelft.twobrains.client.model.socket.TwoBrainsSocket;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

    public static Gebruiker parse(final String email, final String data) {
        final JSONParser parser = new JSONParser();
        try {
            final JSONObject obj = (JSONObject) parser.parse(data);
            return new Gebruiker(email, obj);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getAttribuut(String attribuut) {
        return data.get(attribuut).toString();
    }

    public String getEmail() {
        return email;
    }

    public String getVoornaam() {
        return getAttribuut("Voornaam");
    }

    public String getAchternaam() {
        return getAttribuut("Achternaam");
    }

    public String getGeslacht() {
        return getAttribuut("Geslacht");
    }

    public String getLeeftijd() {
        return getAttribuut("Leeftijd");
    }

    public String getWachtwoord() {
        return getAttribuut("Wachtwoord");
    }

    public String getOpleiding() {
        return getAttribuut("Opleiding");
    }

    public String[] getVakken() {
        return getAttribuut("Vakken").split(",");
    }

    public String getLocatie() {
        return getAttribuut("Locatie");
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
