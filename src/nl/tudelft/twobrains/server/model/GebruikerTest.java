package nl.tudelft.twobrains.server.model;

import junit.framework.TestCase;
import nl.tudelft.twobrains.server.Server;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotEquals;


public class GebruikerTest extends TestCase {
    Database db;

    public GebruikerTest() throws IOException, ParseException {
        db = Database.parse(Server.RESOURCES + "/database.json");
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
    }
//    public String getelementoutofarray(){
//
//
//    }

    @Test
    public void testGetEmailpositive() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertEquals("kvanzeijl@hotmail.com", koen.getEmail());
    }

    @Test
    public void testGetEmailnegative() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertNotEquals("ibuddyh@gmail.com", koen.getEmail());
    }

    @Test
    public void testGetVoornaampositive() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertEquals("Koen", koen.getVoornaam());
    }
    @Test
    public void testGetVoornaamnegative() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertNotEquals("Budi", koen.getVoornaam());
    }

    @Test
    public void testGetAchternaampositive() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertEquals("Zeijl", koen.getAchternaam());
    }
    @Test
    public void testGetAchternaamnegative() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertNotEquals("Han", koen.getAchternaam());
    }

    @Test
    public void testGetWachtwoordpositive() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertEquals("000000", koen.getWachtwoord());
    }
    @Test
    public void testGetWachtwoordnegative() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertNotEquals("hahah grapje", koen.getWachtwoord());
    }

    @Test
    public void testGetGeslachtpositive() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertEquals("M", koen.getGeslacht());
    }

    @Test
    public void testGetGeslachtnegative() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertNotEquals("V", koen.getGeslacht());
    }

    @Test
    public void testGetLeeftijdpositive() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertEquals("18", koen.getLeeftijd());
    }

    @Test
    public void testGetLeeftijdnegative() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertNotEquals("10", koen.getLeeftijd());
    }

    @Test
    public void testGetOpleidingpositive() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertEquals("Informatica", koen.getOpleiding());
    }

    @Test
    public void testGetOpleidingnegative() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertNotEquals("Technische Wiskunde", koen.getOpleiding());
    }
//vakken werken nog niet
//    @Test
//    public void testGetVakkenpositive() throws Exception {
//        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
//        Gebruiker budi = db.get("ibuddyh@gmail.com");
//        assertEquals("Calculus", koen.getVakken());
//    }
//
//    @Test
//    public void testGetVakkennegative() throws Exception {
//        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
//        Gebruiker budi = db.get("ibuddyh@gmail.com");
//        assertNotEquals("wiskunde", koen.getVakken());
//    }

    @Test
    public void testGetLocatiepositive() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertEquals("Delft", koen.getLocatie());
    }
    @Test
    public void testGetLocatienegative() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertNotEquals("Den Haag", koen.getLocatie());
    }
    @Test
    public void testGetJSONObject() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertEquals("{\"Geslacht\":\"M\",\"Leeftijd\":\"18\",\"Voornaam\":\"Koen\",\"Achternaam\":\"Zeijl\",\"Wachtwoord\":\"000000\",\"Locatie\":\"Delft\",\"Vakken\":\"Calculus\",\"Opleiding\":\"Informatica\"}",koen.getJSONObject().toJSONString());
    }
}