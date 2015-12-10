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
import java.util.function.Predicate;

import static org.junit.Assert.*;

/**
 * Created by koen on 2-12-2015.
 */
public class DatabaseTest {

    Database db;
    Gebruiker koen;
    Gebruiker buddy;
    Gebruiker jan = new Gebruiker("jan@hotmail.com", new JSONObject());
    Database dbEmpty = new Database(new JSONObject());

    public DatabaseTest() throws IOException, org.json.simple.parser.ParseException {
        db = Database.parse(Server.RESOURCES + "/database.json");
        koen = db.get("kvanzeijl@hotmail.com");
        buddy = db.get("ibuddyh@gmail.com");
    }

    @Test
    public void testWrite_Empty() throws Exception {
        dbEmpty.write("");
        assertEquals(dbEmpty.size(), 0);
    }

    @Test
    public void testParse_FileLengthZero() throws Exception {
        assertEquals(new Database(new JSONObject()), Database.parse(
                "tests\\nl\\tudelft\\twobrains\\server\\model\\DatabaseTestFiles\\fileLengthZero"));
    }

    @Test
    public void testParse_Empty() throws Exception {
        dbEmpty.write("tests\\nl\\tudelft\\twobrains\\server\\model\\DatabaseTestFiles\\parseFileTest");
        assertEquals(dbEmpty, Database.parse(
                "tests\\nl\\tudelft\\twobrains\\server\\model\\DatabaseTestFiles\\parseFileTest"));
    }
    @Test
    public void testGetAlleGebruikers_WithTwo() throws Exception {
        Gebruiker[] gebruikerArray = {koen, buddy};
        assertEquals(gebruikerArray, db.getAlleGebruikers());
    }
    @Test
    public void testGetAlleGebruikers_Empty() throws Exception {
        Gebruiker[] gebruikerArray = {};
        assertEquals(gebruikerArray, dbEmpty.getAlleGebruikers());
    }
    //gebruiker wordt [0] van de arraylist gepleurd met add + getAlleGebruikers
    @Test
    public void testAdd_TwoGebruiker() throws Exception {
        dbEmpty.add(koen);
        dbEmpty.add(buddy);
        assertEquals(db, dbEmpty);
    }
    @Test
    public void testWrite_Parse() throws Exception {
        db.write("tests\\nl\\tudelft\\twobrains\\server\\model\\DatabaseTestFiles\\writeFileTest");
        assertEquals(db, Database.parse(
                "tests\\nl\\tudelft\\twobrains\\server\\model\\DatabaseTestFiles\\writeFileTest"));
    }
    //gebruik een labda expression voor het krijgen van de juiste gebruikers
    @Test
    public void testFilter_OneOut() throws Exception {
        ArrayList<Gebruiker> filterList = new ArrayList<>();
        filterList.add(koen);
        assertEquals(filterList, db.filter(p -> p.getEmail().equals("kvanzeijl@hotmail.com")));
    }
    @Test
    public void testFilter_EmptyDatabase() throws Exception {
        assertEquals(new ArrayList<>(),
                dbEmpty.filter(p -> p.getEmail().equals("example@hotmail.com")));
    }
    @Test
    public void testFilter_EmptyResult() throws Exception {
        assertEquals(new ArrayList<>(),
                db.filter(p -> p.getEmail().equals("notexist@hotmail.com")));
    }
    @Test
    public void testToString_EmptyDatabase() throws Exception {
        assertEquals("Database[]", dbEmpty.toString());
    }

    @Test
    public void testToString_TwoGebruikerDatabase() throws Exception {
        assertEquals("Database[kvanzeijl@hotmail.com=Gebruiker[Voornaam=Koen, Achternaam=Zeijl," +
                " E-mail=kvanzeijl@hotmail.com, Wachtwoord=000000, Geslacht=M, Leeftijd=18," +
                " Opleiding=Informatica, Vakken=Calculus, Locatie=Delft], ibuddyh@gmail.com=Gebruiker[Voornaam=Budi," +
                " Achternaam=Han, E-mail=ibuddyh@gmail.com, Wachtwoord=000000, Geslacht=M, Leeftijd=21," +
                " Opleiding=Informatica, Vakken=Calculus, Locatie=Delft]]", db.toString());
    }

}

