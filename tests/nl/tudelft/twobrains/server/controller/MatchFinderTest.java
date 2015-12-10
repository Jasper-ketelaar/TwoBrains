package nl.tudelft.twobrains.server.controller;

import nl.tudelft.twobrains.server.model.Database;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Leroy on 9-12-2015.
 */
public class MatchFinderTest {

    @Test
    public void testConstructor(){

        MatchFinder m = new MatchFinder(new Database(new JSONObject()));

        assertNotNull(m);
    }
}
