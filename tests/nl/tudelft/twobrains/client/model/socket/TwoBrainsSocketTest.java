package nl.tudelft.twobrains.client.model.socket;

import nl.tudelft.twobrains.client.model.Gebruiker;
import nl.tudelft.twobrains.server.Server;
import nl.tudelft.twobrains.server.model.Database;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;


import static org.junit.Assert.*;

/**
 * Created by Leroy on 30-11-2015.
 */
public class TwoBrainsSocketTest {
    static String ClientTestDatabases;
    static Database testDatabase;
    static Server server;
    // static String ClientTestImages;

    static {
        ClientTestDatabases = System.getProperty("user.dir") + "/tests/nl/tudelft/twobrains/client/model/TestFiles/DatabaseTestFiles/TestDatabase.json";
        //  ClientTestImages = System.getProperty("user.dir") + "/.TwoBrains/tests/nl/tudelft/twobrains/client/model/TestFiles/ImageTestFiles";


        final File file = new File(ClientTestDatabases);
        try {
            testDatabase = Database.parse(file.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }


        new Thread(() -> {

            while (true) {
                try {

                    server = new Server(testDatabase, 7001);
                    server.run();
                } catch (BindException e) {
                    System.out.println("Port in use");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    @Test
    public void testConstructor() throws IOException {


        final TwoBrainsSocket testTwoBrainsSocket = new TwoBrainsSocket("127.0.0.1", 7001);
        assertTrue(testTwoBrainsSocket.isBound());
    }

    @Test
    public void testImage() throws IOException {


        final TwoBrainsSocket testTwoBrainsSocket = new TwoBrainsSocket("127.0.0.1", 7001);
        BufferedImage k = testTwoBrainsSocket.getImage("kvanzeijl@hotmail.com.jpg");
        File fl = new File(Server.IMAGES + "/kvanzeijl@hotmail.com.jpg");
        BufferedImage g = ImageIO.read(fl);
        assertTrue(k.getHeight() == g.getHeight() && k.getColorModel().equals(g.getColorModel()) && k.getWidth() == g.getWidth());
    }

    @Test
    public void testOngeldigeLogin() throws Exception {


        final TwoBrainsSocket testTwoBrainsSocket = new TwoBrainsSocket("127.0.0.1", 7001);
        Gebruiker k = null;
        k = testTwoBrainsSocket.login("blbla", "testWachtwoord");

        assertEquals(k, null);
    }

    @Test
    public void testGeldigeLogin() throws Exception {


        final TwoBrainsSocket testTwoBrainsSocket = new TwoBrainsSocket("127.0.0.1", 7001);
        Gebruiker k = testTwoBrainsSocket.login("testEmail", "testWachtwoord");


        assertEquals(k.getEmail(), "testEmail");
    }

    @Test
    public void testVerkrijgInfo() throws Exception {

        final TwoBrainsSocket testTwoBrainsSocket = new TwoBrainsSocket("127.0.0.1", 7001);
        Gebruiker k = testTwoBrainsSocket.verkrijgInfo("testEmail");


        assertEquals(k.getEmail(), "testEmail");
    }

    @Test
    public void testMessage() throws IOException {
        final TwoBrainsSocket testTwoBrainsSocket = new TwoBrainsSocket("127.0.0.1", 7001);


        assertEquals(testTwoBrainsSocket.message("testMail", "testBericht"), "Email/Gebruiker bestaat niet");
    }


    @Test
    public void testRegister() throws IOException {


        final JSONObject user = new JSONObject();
        user.put("Voornaam", "testLeroy");
        user.put("Achternaam", "testVelzel");
        user.put("Leeftijd", "testLeeftijd");
        user.put("Geslacht", "testGeslacht");
        user.put("Wachtwoord", "testWachtwoord");
        user.put("Locatie", "testLocatie");
        user.put("Opleiding", "testOpleiding");
        user.put("Vakken", "testVakken");

        File fl = new File(Server.IMAGES + "/kvanzeijl@hotmail.com.jpg");
        BufferedImage g = ImageIO.read(fl);

        final TwoBrainsSocket testTwoBrainsSocket = new TwoBrainsSocket("127.0.0.1", 7001);
        String k = testTwoBrainsSocket.register("testEmail", user, g);

        assertTrue(k.equals("Email bestaat al"));

    }

    @Test
    public void testOproep() throws IOException {

        final TwoBrainsSocket testTwoBrainsSocket = new TwoBrainsSocket("127.0.0.1", 7001);
        String k = testTwoBrainsSocket.oproep("testVakken", "testEmail", "testLeroy");

        assertEquals(k, "{\"Jasperketelaar@kpnmail.nl\":{\"Vak\":\"Calculus\",\"Naam\":\"Jasper Ketelaar\"},\"testEmail\":{\"Vak\":\"testVakken\",\"Naam\":\"testLeroy\"},\"ibuddyh@gmail.com\":{\"Vak\":\"Calculus\",\"Naam\":\"Budi Han\"}}");
    }


    @Test
    public void testMatches() throws IOException {


        final TwoBrainsSocket testTwoBrainsSocket = new TwoBrainsSocket("127.0.0.1", 7001);
        ArrayList<String> k = testTwoBrainsSocket.getMatches("kvanzeijl@hotmail.com");

        assertTrue(k.toString().equals("[]"));
    }


}