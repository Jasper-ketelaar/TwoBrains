package nl.tudelft.twobrains.server.controller;

import nl.tudelft.twobrains.server.Server;
import nl.tudelft.twobrains.server.model.Database;
import nl.tudelft.twobrains.server.model.Match;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Leroy on 9-12-2015.
 */
public class MatchFinderTest {

    Database testDb;


    @Test
    public void testGetMatches() throws ParseException, IOException {
        final File dir = new File(Server.RESOURCES);
        final File file = new File(dir.getAbsolutePath() + "/databasetest.json");
        testDb = Database.parse(file.getPath());

        MatchFinder testMatch = new MatchFinder(testDb);

        assertTrue(testMatch.getMatches().equals("[ibuddyh@gmail.com:jasperketelaar@kpnmail.nl:2.2]"));
    }

    @Test
    public void testGetMatchesFromUser() throws IOException, ParseException {
        final File dir = new File(Server.RESOURCES);
        final File file = new File(dir.getAbsolutePath() + "/databasetest.json");
        testDb = Database.parse(file.getPath());

        MatchFinder testMatch = new MatchFinder(testDb);

        testMatch.getMatchesForUser("ibuddyh@gmail.com");

        final String email = "ibuddyh@gmail.com";
        final String email2 = "Jasperketelaar@kpnmail.nl";
        final double score = 2.2;

        Match l = new Match(email, email2, score);
        ArrayList<Match> am = new ArrayList<>();
        am.add(l);

        assertEquals(testMatch.getMatchesForUser("ibuddyh@gmail.com"), am);

    }
}
