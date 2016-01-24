package nl.tudelft.twobrains.client.model;

import junit.framework.TestCase;
import nl.tudelft.twobrains.client.model.socket.TwoBrainsSocket;
import nl.tudelft.twobrains.server.Server;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Run Server First
 * Created by Leroy on 25-11-2015.
 */
public class GebruikerTest {


    String email = "leroyvelzel@gmail.com";
    JSONObject testdata = new JSONObject();
    Gebruiker testGebruiker = new Gebruiker(email, testdata);
    private TwoBrainsSocket testTwoBrainsSocket;

    {
        try {
            testTwoBrainsSocket = new TwoBrainsSocket("127.0.0.1", 4444);
        } catch (IOException e) {
            System.out.print("NoConnect");
            e.printStackTrace();
        }
    }


    @Test
    public void testGetAttribuut() {

        testdata.put("testAttribuut", "SomeBullshit");


        assertEquals(testGebruiker.getAttribuut("testAttribuut"), "SomeBullshit");
    }

    @Test
    public void testGetEmail() {

        assertEquals(testGebruiker.getEmail(), email);
    }

    @Test
    public void testGetVoornaam() {
        testdata.put("Voornaam", "Leroy");

        assertEquals(testGebruiker.getVoornaam(), "Leroy");
    }

    @Test
    public void testGetAchternaam() {
        testdata.put("Achternaam", "Velzel");

        assertEquals(testGebruiker.getAchternaam(), "Velzel");
    }

    @Test
    public void testGetGeslacht() {

        testdata.put("Geslacht", "M");

        assertEquals(testGebruiker.getGeslacht(), "M");
    }

    @Test
    public void testGetLeeftijd() {

        testdata.put("Leeftijd", "19");

        assertEquals(testGebruiker.getLeeftijd(), "19");
    }

    @Test
    public void testGetWachtwoord() {

        testdata.put("Wachtwoord", "123456");


        assertEquals(testGebruiker.getWachtwoord(), "123456");
    }

    @Test
    public void testGetOpleiding() {
        testdata.put("Opleiding", "Informatica");


        assertEquals(testGebruiker.getOpleiding(), "Informatica");
    }

    @Test
    public void testGetVakken() {
        testdata.put("Vakken", "Calculus, Web en Database Technology, OOP");


        String alleVakken = "";

        for (int i = 0; i < testGebruiker.getVakken().length; i++) {
            alleVakken = alleVakken + testGebruiker.getVakken()[i];
        }
        assertEquals(alleVakken, "Calculus Web en Database Technology OOP");

    }

    @Test
    public void testGetLocatie() {
        testdata.put("Locatie", "23.23.48,34.87.02");


        assertEquals(testGebruiker.getLocatie(), "23.23.48,34.87.02");
    }

    @Test
    public void testSetConnection() {

        testGebruiker.setConnection(testTwoBrainsSocket);
    }

    @Test
    public void testGetConnection() {

        testGebruiker.setConnection(testTwoBrainsSocket);
        assertTrue(testGebruiker.getConnection().equals(testTwoBrainsSocket));


    }

    @Test
    public void testIsConnected() {

        testGebruiker.setConnection(testTwoBrainsSocket);
        assertTrue(testGebruiker.isConnected());

    }

    @Test
    public void testDisconnect() {

        testGebruiker.setConnection(testTwoBrainsSocket);

        try {
            testGebruiker.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(testGebruiker.isConnected());


    }


    //TODO: Schrijven
    @Test
    public void testParse() {
        testdata.put("Voornaam", "Koen");
        testdata.put("Achternaam", "Zeijl");
        testdata.put("Geslacht", "M");
        testdata.put("Leeftijd", "18");
        testdata.put("Wachtwoord", "000000");
        testdata.put("Opleiding", "Informatica");
        testdata.put("Vakken", "Calculus");
        testdata.put("Locatie", "Delft");


        Gebruiker koen = null;
        try {
            koen = Gebruiker.parse("kvanzeijl@hotmail.com", "{\n" +
                    "    \"Voornaam\": \"Koen\",\n" +
                    "    \"Achternaam\": \"Zeijl\",\n" +
                    "    \"Geslacht\": \"M\",\n" +
                    "    \"Leeftijd\": \"18\",\n" +
                    "    \"Wachtwoord\": \"000000\",\n" +
                    "    \"Opleiding\": \"Informatica\",\n" +
                    "    \"Vakken\": \"Calculus\",\n" +
                    "    \"Locatie\": \"Delft\"\n" +
                    "  }");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertFalse(koen.equals(testGebruiker));
    }

    //TODO: Schrijven
    @Test
    public void testGetUserImage() {


    }

}