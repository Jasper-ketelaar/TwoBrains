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
    String testDatabaseFile = System.getProperty("user.dir") + "/tests/nl/tudelft/twobrains/server/model/DatabaseTestFiles/TestDatabase.json";
    String testDatabaseFile2 = System.getProperty("user.dir") + "/tests/nl/tudelft/twobrains/server/model/DatabaseTestFiles/TestDatabase2.json";
    String testMatches = System.getProperty("user.dir") + "/tests/nl/tudelft/twobrains/server/model/DatabaseTestFiles/TestMatches.json";
    String testEmptyFile = System.getProperty("user.dir") + "/tests/nl/tudelft/twobrains/server/model/DatabaseTestFiles/EmptyDatabase.json";


    @Test
    public void testGetDatabase() throws IOException, ParseException {
        MatchFinder testMatchFinder = new MatchFinder(Database.parse(testDatabaseFile));

        assertEquals(testMatchFinder.getDatabase(), Database.parse(testDatabaseFile));
    }

    @Test
    public void testGetMatches() throws ParseException, IOException {
        Match m = new Match("ff", "ff", 3.3);

        MatchFinder testMatch = new MatchFinder(Database.parse(testDatabaseFile), testMatches);

        assertEquals(testMatch.getMatches().toString(), "[testMail@hotmail.com:kevinvanheel94@hotmail.com:3.3]");
    }

    @Test
    public void testGetMatchesFromUser() throws IOException, ParseException {


        MatchFinder testMatch = new MatchFinder(Database.parse(testDatabaseFile), testMatches);

        testMatch.getMatchesForUser("ibuddyh@gmail.com");

        final String email = "testMail@hotmail.com";
        final String email2 = "kevinvanheel94@hotmail.com";
        final double score = 3.3;

        Match l = new Match(email, email2, score);
        ArrayList<Match> am = new ArrayList<>();
        am.add(l);

        assertEquals(testMatch.getMatchesForUser("testMail@hotmail.com"), am);

    }
}
