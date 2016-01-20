package nl.tudelft.twobrains.server.model;

import org.json.simple.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Leroy on 20-1-2016.
 */
public class MatchTest {

    @Test
    public void testConstructor(){

        final JSONObject user = new JSONObject();
        user.put("Voornaam", "testLeroy");
        user.put("Achternaam", "testVelzel");
        user.put("Leeftijd", "testLeeftijd");
        user.put("Geslacht", "testGeslacht");
        user.put("Wachtwoord", "testWachtwoord");
        user.put("Locatie", "testLocatie");
        user.put("Opleiding", "testOpleiding");
        user.put("Vakken", "testVakken");

    }

}