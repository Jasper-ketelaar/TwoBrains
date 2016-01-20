package nl.tudelft.twobrains.client;

import javafx.scene.Scene;
import nl.tudelft.twobrains.client.controller.views.LoginController;
import nl.tudelft.twobrains.client.model.Gebruiker;
import nl.tudelft.twobrains.client.view.SceneWrapper;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Leroy on 30-11-2015.
 */
public class TwoBrainsTest {


    @Test
    public void Tester() throws IOException {

        TwoBrains twoBrains = new TwoBrains();
        String email = "leroyvelzel@gmail.com";


        final JSONObject user = new JSONObject();
        user.put("Voornaam", "testLeroy");
        user.put("Achternaam", "testVelzel");
        user.put("Leeftijd", "testLeeftijd");
        user.put("Geslacht", "testGeslacht");
        user.put("Wachtwoord", "testWachtwoord");
        user.put("Locatie", "testLocatie");
        user.put("Opleiding", "testOpleiding");
        user.put("Vakken", "testVakken");

        Gebruiker testGebruiker = new Gebruiker(email, user);

        twoBrains.setGebruiker(testGebruiker);

        Gebruiker twoBrainsGebruiker = twoBrains.getGebruiker();

        assertTrue(testGebruiker.equals(twoBrainsGebruiker));
    }


}