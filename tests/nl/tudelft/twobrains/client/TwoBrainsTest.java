package nl.tudelft.twobrains.client;

import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.tudelft.twobrains.client.controller.views.LoginController;
import nl.tudelft.twobrains.client.controller.views.MatchController;
import nl.tudelft.twobrains.client.model.Gebruiker;
import nl.tudelft.twobrains.client.model.socket.TwoBrainsSocket;
import nl.tudelft.twobrains.client.view.SceneWrapper;
import nl.tudelft.twobrains.server.Server;
import nl.tudelft.twobrains.server.model.Database;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.BindException;

import static org.junit.Assert.*;

/**
 * Created by Leroy on 30-11-2015.
 */
public class TwoBrainsTest {
    static String ClientTestDatabases;
    static Database testDatabase;
    static Server server;
    static TwoBrains testTwoBrains;

    static {
        ClientTestDatabases = System.getProperty("user.dir") + "/tests/nl/tudelft/twobrains/client/model/TestFiles/DatabaseTestFiles/TestDatabase.json";
        try {
            testDatabase = Database.parse(ClientTestDatabases);
        } catch (Exception e) {
            e.printStackTrace();
        }


        new Thread(() -> {

            while (true) {
                try {

                    server = new Server(testDatabase, 7003);
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
    public void testMatchScene() throws Exception {
        TwoBrains testTwoBrains = new TwoBrains(7003);

        SceneWrapper testRet = new SceneWrapper("match", new MatchController(testTwoBrains));
        testTwoBrains.getSocket().close();
        assertTrue(testTwoBrains.getMatchScene().getScene().equals(testRet.getScene()));
    }

    @Test
    public void testRegisterScene() throws IOException {


        SceneWrapper testRet = new SceneWrapper("registreer", new MatchController(testTwoBrains));
        testTwoBrains.getSocket().close();
        assertTrue(testTwoBrains.getRegisterScene().getScene().equals(testRet.getScene()));
    }

    @Test
    public void testLoginScene() throws Exception {
        TwoBrains testTwoBrains = new TwoBrains(7003);

        SceneWrapper testRet = new SceneWrapper("login", new MatchController(testTwoBrains));
        testTwoBrains.getSocket().close();
        assertTrue(testTwoBrains.getLoginScene().getScene().equals(testRet.getScene()));
    }

    @Test
    public void testTabScene() throws Exception {
        TwoBrains testTwoBrains = new TwoBrains(7003);

        SceneWrapper testRet = new SceneWrapper("tabs", new MatchController(testTwoBrains));
        testTwoBrains.getSocket().close();
        assertTrue(testTwoBrains.getTabScene().getScene().equals(testRet.getScene()));
    }

    @Test
    public void testHomeScene() throws Exception {
        TwoBrains testTwoBrains = new TwoBrains(7003);

        SceneWrapper testRet = new SceneWrapper("home", new MatchController(testTwoBrains));

        testTwoBrains.getSocket().close();
        assertTrue(testTwoBrains.getHomeScene().getScene().equals(testRet.getScene()));
    }

    @Test
    public void testStage() throws Exception {
        TwoBrains testTwoBrains = new TwoBrains(7003);


        Stage testStage = new Stage();
        testTwoBrains.setStage(testStage);

        testTwoBrains.getSocket().close();
        assertTrue(testTwoBrains.getStage().equals(testStage));


    }

    @Test
    public void testGebruiker() throws Exception {
        TwoBrains testTwoBrains = new TwoBrains(7003);

        final JSONObject user = new JSONObject();
        user.put("Voornaam", "testLeroy");
        user.put("Achternaam", "testVelzel");
        user.put("Leeftijd", "testLeeftijd");
        user.put("Geslacht", "testGeslacht");
        user.put("Wachtwoord", "testWachtwoord");
        user.put("Locatie", "testLocatie");
        user.put("Opleiding", "testOpleiding");
        user.put("Vakken", "testVakken");

        final Gebruiker testGebruiker = new Gebruiker("test", user);


        testTwoBrains.setGebruiker(testGebruiker);

        testTwoBrains.getSocket().close();
        assertTrue(testTwoBrains.getGebruiker().equals(testGebruiker));
    }

    @Test
    public void testSocket() throws Exception {
        TwoBrains testTwoBrains = new TwoBrains(7003);

        TwoBrainsSocket testSocket = new TwoBrainsSocket("127.0.0.1", 7003);

        TwoBrainsSocket testReturn = testTwoBrains.getSocket();

        int IDReturn = testReturn.getPort();
        int IDTest = testSocket.getPort();

        testSocket.close();
        testTwoBrains.getSocket().close();
        testReturn.close();
        assertTrue(IDReturn == IDTest);

    }


}