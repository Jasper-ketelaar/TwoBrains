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


        final File file = new File(ClientTestDatabases);
        try {
            testDatabase = Database.parse(file.getPath());
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

        try {
            testTwoBrains = new TwoBrains(7003);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testMatchScene() throws IOException {


        SceneWrapper testRet = new SceneWrapper("match", new MatchController(testTwoBrains));
        assertTrue(testTwoBrains.getMatchScene().getScene().equals(testRet.getScene()));
    }

    @Test
    public void testRegisterScene() throws IOException {


        SceneWrapper testRet = new SceneWrapper("registreer", new MatchController(testTwoBrains));
        assertTrue(testTwoBrains.getRegisterScene().getScene().equals(testRet.getScene()));
    }

    @Test
    public void testLoginScene() throws IOException {


        SceneWrapper testRet = new SceneWrapper("login", new MatchController(testTwoBrains));
        assertTrue(testTwoBrains.getLoginScene().getScene().equals(testRet.getScene()));
    }

    @Test
    public void testTabScene() throws IOException {


        SceneWrapper testRet = new SceneWrapper("tabs", new MatchController(testTwoBrains));
        assertTrue(testTwoBrains.getTabScene().getScene().equals(testRet.getScene()));
    }

    @Test
    public void testHomeScene() throws IOException {


        SceneWrapper testRet = new SceneWrapper("home", new MatchController(testTwoBrains));
        assertTrue(testTwoBrains.getHomeScene().getScene().equals(testRet.getScene()));
    }

    @Test
    public void testStage() {
        Stage testStage = new Stage();
        testTwoBrains.setStage(testStage);
        assertTrue(testTwoBrains.getStage().equals(testStage));
    }

    @Test
    public void testGebruiker() {

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
        assertTrue(testTwoBrains.getGebruiker().equals(testGebruiker));
    }


}