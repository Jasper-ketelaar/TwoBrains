package nl.tudelft.twobrains.server.controller;

import nl.tudelft.twobrains.server.model.Database;
import org.json.simple.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Leroy on 9-12-2015.
 */
public class MatchFinderTest {

    JSONObject testJson = new JSONObject();
    Database testDatabase = new Database(testJson);

    @Test
    public void testName(){

        MatchFinder testMatchFinder = new MatchFinder(testDatabase);

        assertTrue(true);
    }
}
