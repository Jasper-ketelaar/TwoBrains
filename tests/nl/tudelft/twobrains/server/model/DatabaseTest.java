package nl.tudelft.twobrains.server.model;

import junit.framework.Assert;
import nl.tudelft.twobrains.server.Server;
import org.json.simple.JSONObject;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;

import static org.junit.Assert.*;

/**
 * Created by koen on 2-12-2015.
 */
public class DatabaseTest {
    String testDatabaseFile = System.getProperty("user.dir") + "/tests/nl/tudelft/twobrains/server/model/DatabaseTestFiles/TestDatabase.json";
    String testDatabaseFile2 = System.getProperty("user.dir") + "/tests/nl/tudelft/twobrains/server/model/DatabaseTestFiles/TestDatabase2.json";
    String testDatabaseFile3 = System.getProperty("user.dir") + "/tests/nl/tudelft/twobrains/server/model/DatabaseTestFiles/TestDatabase3.json";
    String testEmptyFile = System.getProperty("user.dir") + "/tests/nl/tudelft/twobrains/server/model/DatabaseTestFiles/TestWriteBase.json";

    static JSONObject testObj1 = new JSONObject();
    static JSONObject testObj2 = new JSONObject();
    static JSONObject testDataBaseObj1 = new JSONObject();
    static JSONObject testDataBaseObj2 = new JSONObject();


    static {
        testObj1.put("Geslacht", "M");
        testObj1.put("Leeftijd", "18");
        testObj1.put("Voornaam", "TestVoornaam");
        testObj1.put("Achternaam", "TestAchternaam");
        testObj1.put("Wachtwoord", "000000");
        testObj1.put("Locatie", "TestDelft");
        testObj1.put("Vakken", "TestCalculus");
        testObj1.put("Opleiding", "TestInformatica");


        testDataBaseObj1.put("testMail@hotmail.com", testObj1);

        testObj2.put("Geslacht", "M");
        testObj2.put("Leeftijd", "21");
        testObj2.put("Voornaam", "Kevin");
        testObj2.put("Achternaam", "van Heel");
        testObj2.put("Wachtwoord", "Test001");
        testObj2.put("Locatie", "Rotterdam");
        testObj2.put("Vakken", "Redeneren&Logica");
        testObj2.put("Opleiding", "Informatica");


        testDataBaseObj2.put("testMail@hotmail.com", testObj2);
    }


    Database db;
    Gebruiker koen;
    Gebruiker buddy;
    Gebruiker jan = new Gebruiker("jan@hotmail.com", new JSONObject());
    Database dbEmpty = new Database(new JSONObject());


    @Test
    public void testParse() throws IOException, org.json.simple.parser.ParseException {
        Database testDatabase = Database.parse(testDatabaseFile2);


        assertEquals(testDatabase, new Database(testDataBaseObj1));

    }

    @Test
    public void testParse0() throws IOException, org.json.simple.parser.ParseException {
        Database testDatabase = Database.parse(testDatabaseFile3);

        assertTrue(testDatabase.equals(new Database(new JSONObject())));


    }

    @Test
    public void testGetAlleGebruikers() throws IOException, org.json.simple.parser.ParseException {
        Database testDatabase = Database.parse(testDatabaseFile);

        Gebruiker[] RetArray = testDatabase.getAlleGebruikers();
        Gebruiker[] NewArray = {new Gebruiker("testMail@hotmail.com", testObj1), new Gebruiker("kevinvanheel94@hotmail.com", testObj2)};


        assertArrayEquals(RetArray, NewArray);
    }

    @Test
    public void testAdd() throws IOException, org.json.simple.parser.ParseException {
        Gebruiker testGeb = new Gebruiker("testNew", testObj2);

        Database testDatabase = Database.parse(testDatabaseFile);
        testDatabase.add(testGeb);

        testDatabase.getAlleGebruikers()[2].equals(testGeb);

    }

    @Test
    public void testWrite() throws IOException, org.json.simple.parser.ParseException {
        File EmptyFile = new File(testEmptyFile, " ");

        Database testDatabase = Database.parse(testDatabaseFile);

        testDatabase.write(testEmptyFile);

        File modifiedFile = new File(testEmptyFile);

        assertFalse(modifiedFile.equals(EmptyFile));

    }

    @Test
    public void testFilter() {
        //TODO: Schrijven
    }

    @Test
    public void testToString() throws IOException, org.json.simple.parser.ParseException {
        Database testDatabase = Database.parse(testDatabaseFile);

        assertEquals(testDatabase.toString(), "Database[testMail@hotmail.com=Gebruiker[Voornaam=TestVoornaam, Achternaam=TestAchternaam, E-mail=testMail@hotmail.com, Wachtwoord=000000, Geslacht=M, Leeftijd=18, Opleiding=TestInformatica, Vakken=TestCalculus, Locatie=TestDelft], kevinvanheel94@hotmail.com=Gebruiker[Voornaam=Kevin, Achternaam=van Heel, E-mail=kevinvanheel94@hotmail.com, Wachtwoord=Test001, Geslacht=M, Leeftijd=21, Opleiding=Informatica, Vakken=Redeneren&Logica, Locatie=Rotterdam]]");
    }


}

