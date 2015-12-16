package nl.tudelft.twobrains.server.controller;

import nl.tudelft.twobrains.server.Server;
import nl.tudelft.twobrains.server.model.Database;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Leroy on 9-12-2015.
 */
public class MatchFinderTest {

    Database testDb;



    @Test
    public void testConstructor(){

        MatchFinder m = new MatchFinder(new Database(new JSONObject()));

        assertNotNull(m);
    }

    @Test
    public void testRun(){
        try {
            testDb = Database.parse(Server.RESOURCES + "/databasetest.json");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        MatchFinder m = new MatchFinder(testDb);
        m.start();


    }
}
