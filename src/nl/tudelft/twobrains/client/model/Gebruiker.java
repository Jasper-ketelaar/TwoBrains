package nl.tudelft.twobrains.client.model;

import nl.tudelft.twobrains.client.model.socket.TwoBrainsSocket;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.image.BufferedImage;
import java.io.IOException;

//TODO: Gebruiker attributes aanmaken
public class Gebruiker {

    /**
     * Initialiseert 3 attributen
     *
     * @attrib String:email
     * @attrib JSONobject:data
     * @attrib Twobrainsocket:connection
     */
    private final String email;
    private final JSONObject data;
    private TwoBrainsSocket connection;

    /**
     * Constructor Gebruiker
     * Creëert Gebruiker-object
     *
     * @param email
     * @param data
     */
    public Gebruiker(final String email, final JSONObject data) {
        this.email = email;
        this.data = data;
    }

    /**
     * Klasse isConnected
     *
     * @return true als connected
     */

    public boolean isConnected() {
        return ((connection != null) && (connection.isConnected()));
    }

    /**
     * Klasse disconnect
     * Disconnect
     */
    public void disconnect() throws IOException {
        this.connection.close();
    }

    public static Gebruiker parse(final String email, final String data) throws ParseException {
        final JSONParser parser = new JSONParser();
        final JSONObject obj = (JSONObject) parser.parse(data);
        return new Gebruiker(email, obj);


    }

    /**
     * Klasse getAttribuut
     * retourneert String van data-JSONobject
     *
     * @param attribuut
     * @return String
     */
    public String getAttribuut(String attribuut) {
        return data.get(attribuut).toString();
    }

    /**
     * Klasse getEmail
     * Retourneert Email-attribuut als string
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Klasse getVoornaam
     * Retourneert Voornaam-JSONobject-attribuut als string
     *
     * @return String
     */
    public String getVoornaam() {
        return getAttribuut("Voornaam");
    }

    /**
     * Klasse getAchternaam
     * Retourneert Achternaam-JSONobject-attribuut als string
     *
     * @return String
     */
    public String getAchternaam() {
        return getAttribuut("Achternaam");
    }

    /**
     * Klasse getGeslacht
     * Retourneert Geslacht-JSONobject-attribuut als string
     *
     * @return String
     */
    public String getGeslacht() {
        return getAttribuut("Geslacht");
    }

    /**
     * Klasse getLeeftijd
     * Retourneert Leeftijd-JSONobject-attribuut als string
     *
     * @return String
     */
    public String getLeeftijd() {
        return getAttribuut("Leeftijd");
    }

    /**
     * Klasse getWAachtwoord
     * Retourneert Wachtwoord-JSONobject-attribuut als string
     *
     * @return String
     */
    public String getWachtwoord() {
        return getAttribuut("Wachtwoord");
    }

    /**
     * Klasse getOpleiding
     * Retourneert Opleiding-JSONobject-attribuut als string
     *
     * @return String
     */
    public String getOpleiding() {
        return getAttribuut("Opleiding");
    }

    /**
     * klasse getVakken
     * Retourneert Vakken-JSONobject-attribuut als string
     *
     * @return String
     */
    public String[] getVakken() {
        return getAttribuut("Vakken").split(",");
    }

    /**
     * Klasse getLocatie
     * Retourneert Locatie-JSONobject-attribuut als string
     *
     * @return String
     */
    public String getLocatie() {
        return getAttribuut("Locatie");
    }

    /**
     * Klasse getConnection
     *
     * @return connection
     */
    public TwoBrainsSocket getConnection() {
        return connection;
    }

    /**
     * Klasse setConnection
     *
     * @param socket
     */
    public void setConnection(final TwoBrainsSocket socket) {
        this.connection = socket;
    }

    /**
     * Klasse getUserImage
     *
     * @return image
     */
    public BufferedImage getUserImage() throws IOException {
        return connection.getImage(this.email);
    }


}
