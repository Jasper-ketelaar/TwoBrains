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
    Database db;

    public GebruikerTest() throws IOException, ParseException {
        db = Database.parse(Server.RESOURCES + "/database.json");
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker Kevin = db.get("kevinvanheel94@hotmail.com");
    }

//    public String getelementoutofarray(){
//
//
//    }

    @Test
    public void testTostringVakkenpositive() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertEquals("Calculus", koen.toStringVakken(koen.getVakken()));
    }

    @Test
    public void TestToStringVakkenPositief() throws Exception {
        Gebruiker Kevin = db.get("kevinvanheel94@hotmail.com");
        assertEquals("Redeneren&Logica", Kevin.toStringVakken(Kevin.getVakken()));

    }

    @Test
    public void testTostringVakkennegative() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertNotEquals("Wiskunde", koen.toStringVakken(koen.getVakken()));
    }

    @Test
    public void TestToStringVakkenNegative() throws Exception {
        Gebruiker Kevin = db.get("kevinvanheel94@hotmail.com");
        assertNotEquals("Wiskunde", Kevin.toStringVakken(Kevin.getVakken()));
    }

    @Test
    public void testGetEmailnegative() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertNotEquals("ibuddyh@gmail.com", koen.getEmail());
    }

    @Test
    public void TestGetEmailNegatief() throws Exception {
        Gebruiker Kevin = db.get("kevinvanheel94@hotmail.com");
        assertEquals("TestEmail@gmail.com", Kevin.getEmail());
    }



    @Test
    public void testGetVoornaampositive() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertEquals("Koen", koen.getVoornaam());
    }

    @Test
    public void TestGetVoornaamPositief() throws Exception {
        Gebruiker Kevin = db.get("kevinvanheel94@hotmail.com");
        assertEquals("Kevin", Kevin.getVoornaam());
    }

    @Test
    public void testGetVoornaamnegative() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertNotEquals("Budi", koen.getVoornaam());
    }

    @Test
    public void testGetVoornaamNegatief() throws Exception {
        Gebruiker Kevin = db.get("kevinvanheel94@hotmail.com");
        assertNotEquals("Test", Kevin.getVoornaam());
    }

    @Test
    public void testGetAchternaampositive() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertEquals("Zeijl", koen.getAchternaam());
    }

    @Test
    public void TestGetAchternaamPositief() throws Exception {
        Gebruiker Kevin = db.get("kevinvanheel94@hotmail.com");
        assertEquals("van Heel", Kevin.getAchternaam());
    }

    @Test
    public void testGetAchternaamnegative() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertNotEquals("Han", koen.getAchternaam());
    }
    @Test
    public void TestGetAchternaamNegatief() throws Exception {
        Gebruiker Kevin = db.get("kevinvanheel94@hotmail.com");
        assertNotEquals("Zeijl", Kevin.getAchternaam());
    }

    @Test
    public void testGetWachtwoordpositive() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertEquals("000000", koen.getWachtwoord());
    }

    @Test
    public void TestGetWachtwoordPositief() throws Exception {
        Gebruiker Kevin = db.get("kevinvanheel94@hotmail.com");
        assertEquals("Test001", Kevin.getWachtwoord());
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
        assertEquals(18, koen.getLeeftijd());
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

    @Test
    public void testGetVakkenpositive() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");

        String alleVakken = "";

        for (int i = 0; i < koen.getVakken().length; i++) {
            alleVakken = alleVakken + koen.getVakken()[i];
        }
        assertEquals("Calculus", alleVakken);
    }

    @Test
    public void testGetVakkennegative() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");

        String alleVakken = "";

        for (int i = 0; i < koen.getVakken().length; i++) {
            alleVakken = alleVakken + koen.getVakken()[i];
        }
        assertNotEquals("wiskunde", alleVakken);
    }

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
        assertEquals("{\"Geslacht\":\"M\",\"Leeftijd\":\"18\",\"Voornaam\":\"Koen\",\"Achternaam\":\"Zeijl\",\"Wachtwoord\":\"000000\",\"Locatie\":\"Delft\",\"Vakken\":\"Calculus\",\"Opleiding\":\"Informatica\"}", koen.getJSONObject().toJSONString());
    }

    @Test
    public void testToString() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertEquals("Gebruiker[Voornaam=Koen, Achternaam=Zeijl, E-mail=kvanzeijl@hotmail.com, Wachtwoord=000000, Geslacht=M, Leeftijd=18, Opleiding=Informatica, Vakken=Calculus, Locatie=Delft]",koen.toString());
    }

    @Test
    public void testEqualspositive() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("kvanzeijl@hotmail.com");
        assertTrue(koen.equals(budi));


    }

    @Test
    public void testEqualsnegative() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");
        assertFalse(koen.equals("koen"));
    }

    @Test
    public void testParse() throws Exception{
        Gebruiker koen = Gebruiker.parse("kvanzeijl@hotmail.com","{\n" +
                "    \"Voornaam\": \"Koen\",\n" +
                "    \"Achternaam\": \"Zeijl\",\n" +
                "    \"Geslacht\": \"M\",\n" +
                "    \"Leeftijd\": \"18\",\n" +
                "    \"Wachtwoord\": \"000000\",\n" +
                "    \"Opleiding\": \"Informatica\",\n" +
                "    \"Vakken\": \"Calculus\",\n" +
                "    \"Locatie\": \"Delft\"\n" +
                "  }");
        Gebruiker koen2 = db.get("kvanzeijl@hotmail.com");
        assertEquals(koen,koen2);
    }

    @Test(expected=ParseException.class)
    public void testParsefail() throws ParseException {
        Gebruiker gebruiker = Gebruiker.parse("", "sdafs");


    }


    @Test
    public void testMatches(){
    }

    @Test
    public void testMatchScore() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");

        assertEquals(7,koen.matchScore(budi));
    }
    @Test
    public void testMatchScore2() throws Exception {
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");

        assertEquals(9,koen.matchScore(koen));
    }
    @Test
    public void testMatchScore3() throws Exception {
        Gebruiker koen2 = db.get("kvanzeijl2@hotmail.com");
        Gebruiker koen = db.get("kvanzeijl@hotmail.com");
        Gebruiker budi = db.get("ibuddyh@gmail.com");

        assertEquals(7,koen.matchScore(koen2));
    }
}