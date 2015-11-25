package nl.tudelft.twobrains.server.model;

import junit.framework.TestCase;
import nl.tudelft.twobrains.server.Server;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;


public class GebruikerTest extends TestCase {
    private final JSONObject database;
    private String email;

    public GebruikerTest(JSONObject database) throws IOException, ParseException {
        Database db = Database.parse(Server.class.getResource("resources/database.json").getFile());
        this.database = database;
      //  System.out.println(db.toString());
    }

    @Test
    public void testGetEmail() throws Exception {
     //   System.out.println(database.toString());
    }

    @Test
    public void testGetVoornaam() throws Exception {

    }

    @Test
    public void testGetAchternaam() throws Exception {

    }

    @Test
    public void testGetWachtwoord() throws Exception {

    }

    @Test
    public void testGetJSONObject() throws Exception {

    }
}