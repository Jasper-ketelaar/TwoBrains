package nl.tudelft.twobrains.server.model;

import org.json.simple.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Leroy on 20-1-2016.
 */
public class MatchTest {

    @Test
    public void testConstructor() {


        final String email = "ibuddyh@gmail.com";
        final String email2 = "jasperketelaar@kpnmail.nl";
        final double score = 2.2;

        Match testMatch = new Match(email, email2, score);
        assertEquals(testMatch.getGebruiker1(), email);


    }

    @Test
    public void testConstructor2() {


        final String email = "ibuddyh@gmail.com";
        final String email2 = "jasperketelaar@kpnmail.nl";
        final double score = 2.2;

        Match testMatch = new Match(email, email2, score);
        assertEquals(testMatch.getGebruiker2(), email2);


    }

    @Test
    public void testConstructorScore() {


        final String email = "ibuddyh@gmail.com";
        final String email2 = "jasperketelaar@kpnmail.nl";
        final double score = 2.2;

        Match testMatch = new Match(email, email2, score);
        assertTrue(testMatch.getScore() == score);


    }

    @Test
    public void testParse() {

        final String email = "ibuddyh@gmail.com";
        final String email2 = "jasperketelaar@kpnmail.nl";
        final double score = 2.2;

        final JSONObject user1 = new JSONObject();
        user1.put("user1", "ibuddyh@gmail.com");
        user1.put("user2", "jasperketelaar@kpnmail.nl");
        user1.put("score", "2.2");

        Match k = Match.parse(user1);
        Match l = new Match(email, email2, score);

        assertTrue(k.equals(l));
    }

    @Test
    public void testEquals() {

        final String email = "ibuddyh@gmail.com";
        final String email2 = "jasperketelaar@kpnmail.nl";
        final double score = 2.2;


        Match l = new Match(email, email2, score);
        Match k = new Match(email, email2, score);

        assertTrue(k.equals(l));
    }

    @Test
    public void testToString() {
        final String email = "ibuddyh@gmail.com";
        final String email2 = "jasperketelaar@kpnmail.nl";
        final double score = 2.2;


        Match l = new Match(email, email2, score);
        assertEquals(l.toString(), "ibuddyh@gmail.com:jasperketelaar@kpnmail.nl:2.2");
    }

}