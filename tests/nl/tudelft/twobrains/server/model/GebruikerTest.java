package nl.tudelft.twobrains.server.model;

import junit.framework.TestCase;
import nl.tudelft.twobrains.server.Server;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.io.IOException;

import static org.junit.Assert.*;


public class GebruikerTest {
    String email = "test@email.com";
    JSONObject testdata = new JSONObject();
    JSONObject testdata2 = new JSONObject();

    Gebruiker testGebruiker = new Gebruiker(email, testdata);

    static Gebruiker test1;
    static Gebruiker test2;
    static Gebruiker test3;
    static Gebruiker test4;

    static {
        try {
            test1 = Gebruiker.parse("test1", "{\n" +
                    "    \"Voornaam\": \"testV\",\n" +
                    "    \"Achternaam\": \"testA\",\n" +
                    "    \"Geslacht\": \"M\",\n" +
                    "    \"Leeftijd\": \"18\",\n" +
                    "    \"Wachtwoord\": \"testW\",\n" +
                    "    \"Opleiding\": \"testO\",\n" +
                    "    \"Vakken\": \"testV\",\n" +
                    "    \"Locatie\": \"testL\"\n" +
                    "  }");
            test2 = Gebruiker.parse("test2", "{\n" +
                    "    \"Voornaam\": \"testV\",\n" +
                    "    \"Achternaam\": \"testA\",\n" +
                    "    \"Geslacht\": \"M\",\n" +
                    "    \"Leeftijd\": \"18\",\n" +
                    "    \"Wachtwoord\": \"testW\",\n" +
                    "    \"Opleiding\": \"testO\",\n" +
                    "    \"Vakken\": \"testV\",\n" +
                    "    \"Locatie\": \"testL\"\n" +
                    "  }");
            test3 = Gebruiker.parse("test3", "{\n" +
                    "    \"Voornaam\": \"testV\",\n" +
                    "    \"Achternaam\": \"testA\",\n" +
                    "    \"Geslacht\": \"M\",\n" +
                    "    \"Leeftijd\": \"19\",\n" +
                    "    \"Wachtwoord\": \"testW\",\n" +
                    "    \"Opleiding\": \"testO\",\n" +
                    "    \"Vakken\": \"testV\",\n" +
                    "    \"Locatie\": \"testL\"\n" +
                    "  }");
            test4 = Gebruiker.parse("test4", "{\n" +
                    "    \"Voornaam\": \"testV\",\n" +
                    "    \"Achternaam\": \"testA\",\n" +
                    "    \"Geslacht\": \"M\",\n" +
                    "    \"Leeftijd\": \"22\",\n" +
                    "    \"Wachtwoord\": \"testW\",\n" +
                    "    \"Opleiding\": \"testO\",\n" +
                    "    \"Vakken\": \"testV\",\n" +
                    "    \"Locatie\": \"testL\"\n" +
                    "  }");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testParse() throws ParseException {
        Gebruiker test = Gebruiker.parse(email, "{\n" +
                "    \"Voornaam\": \"testV\",\n" +
                "    \"Achternaam\": \"testA\",\n" +
                "    \"Geslacht\": \"M\",\n" +
                "    \"Leeftijd\": \"18\",\n" +
                "    \"Wachtwoord\": \"testW\",\n" +
                "    \"Opleiding\": \"testO\",\n" +
                "    \"Vakken\": \"testV\",\n" +
                "    \"Locatie\": \"testL\"\n" +
                "  }");

        assertEquals(test.toString(), "Gebruiker[Voornaam=testV, Achternaam=testA, E-mail=test@email.com, Wachtwoord=testW, Geslacht=M, Leeftijd=18, Opleiding=testO, Vakken=testV, Locatie=testL]");
    }

    @Test
    public void testEmail() {


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

        assertEquals(testGebruiker.getLeeftijd(), 19);
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
    public void testJSONString() throws ParseException {

        testGebruiker = Gebruiker.parse(email, "{\n" +
                "    \"Voornaam\": \"testV\",\n" +
                "    \"Achternaam\": \"testA\",\n" +
                "    \"Geslacht\": \"M\",\n" +
                "    \"Leeftijd\": \"18\",\n" +
                "    \"Wachtwoord\": \"testW\",\n" +
                "    \"Opleiding\": \"testO\",\n" +
                "    \"Vakken\": \"testV\",\n" +
                "    \"Locatie\": \"testL\"\n" +
                "  }");
        System.out.println(testGebruiker.getJSONString());
        assertEquals(testGebruiker.getJSONString(), "{\"Geslacht\":\"M\",\"Leeftijd\":\"18\",\"Voornaam\":\"testV\",\"Achternaam\":\"testA\",\"Wachtwoord\":\"testW\",\"Locatie\":\"testL\",\"Vakken\":\"testV\",\"Opleiding\":\"testO\"}");
    }

    @Test
    public void testJSONObject() {
        testdata.put("Voornaam", "Leroy");
        testdata.put("Achternaam", "Velzel");
        testdata.put("Geslacht", "M");
        testdata.put("Leeftijd", "19");
        testdata.put("Wachtwoord", "123456");
        testdata.put("Opleiding", "Informatica");
        testdata.put("Vakken", "Calculus, Web en Database Technology, OOP");

        assertEquals(testGebruiker.getJSONObject(), testdata);

    }

    @Test
    public void testMatcher0() {
        System.out.println(test1.matchScore(test2));
        assertTrue(9 == test1.matchScore(test2));

    }

    @Test
    public void testMatcher1() {
        System.out.println(test1.matchScore(test3));
        assertTrue(7 == test1.matchScore(test3));

    }

    @Test
    public void testMatcher3() {
        System.out.println(test1.matchScore(test4));
        assertTrue(7 == test1.matchScore(test4));

    }

    @Test
    public void testEqualsTrue() {
        assertTrue(test1.equals(test1));
    }

    @Test
    public void testEqualsFalse() {
        assertFalse(test1.equals(test2));
    }


}
