package nl.tudelft.twobrains.client.model;

import junit.framework.TestCase;
import org.json.simple.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Leroy on 25-11-2015.
 */
public class GebruikerTest extends TestCase {

    String email = "leroyvelzel@gmail.com";
    JSONObject testdata = new JSONObject();
    Gebruiker testGebruiker = new Gebruiker(email, testdata);


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


}