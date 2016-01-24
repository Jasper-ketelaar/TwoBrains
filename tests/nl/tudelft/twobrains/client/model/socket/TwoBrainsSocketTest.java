package nl.tudelft.twobrains.client.model.socket;

import nl.tudelft.twobrains.client.model.Gebruiker;
import nl.tudelft.twobrains.server.Server;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
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
    Server server;

    public void initializeServer() {
        new Thread(() -> {

            while (true) {
                try {
                    try {
                        server = new Server(8887);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    server.run();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Test
    public void testConstructor() throws IOException {

        initializeServer();


        final TwoBrainsSocket testTwoBrainsSocket = new TwoBrainsSocket("127.0.0.1", 8887);


        assertTrue(testTwoBrainsSocket.isBound());
    }

    @Test
    public void testImage() throws IOException {

        initializeServer();


        final TwoBrainsSocket testTwoBrainsSocket = new TwoBrainsSocket("127.0.0.1", 8887);
        BufferedImage k = testTwoBrainsSocket.getImage("kvanzeijl@hotmail.com.jpg");
        File fl = new File(Server.IMAGES + "/kvanzeijl@hotmail.com.jpg");
        BufferedImage g = ImageIO.read(fl);
        assertTrue(k.getHeight() == g.getHeight() && k.getColorModel().equals(g.getColorModel()) && k.getWidth() == g.getWidth());
    }

    @Test
    public void testOngeldigeLogin() throws IOException {
        initializeServer();


        final TwoBrainsSocket testTwoBrainsSocket = new TwoBrainsSocket("127.0.0.1", 8887);
        Gebruiker k = null;
        try {
            k = testTwoBrainsSocket.login("testEmail", "testWachtwoord");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(k, null);
    }

    @Test
    public void testMessage() throws IOException {
        initializeServer();


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

        final TwoBrainsSocket testTwoBrainsSocket = new TwoBrainsSocket("127.0.0.1", 8887);
        String k = testTwoBrainsSocket.register("testEmail", user, g);

        assertTrue(k.equals("Succes"));

    }

  /*
  TODO: Bestere AssertNULL
   */

    @Test
    public void testMatches() throws IOException {


        initializeServer();

        final TwoBrainsSocket testTwoBrainsSocket = new TwoBrainsSocket("127.0.0.1", 8887);
        ArrayList<String> k = testTwoBrainsSocket.getMatches("kvanzeijl@hotmail.com");

        assertNull(k);
    }


}