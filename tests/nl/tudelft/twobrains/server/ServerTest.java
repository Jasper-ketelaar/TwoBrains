package nl.tudelft.twobrains.server;

import com.sun.org.glassfish.external.arc.Taxonomy;
import junit.framework.TestCase;
import nl.tudelft.twobrains.server.controller.MatchFinder;
import nl.tudelft.twobrains.server.controller.client.ClientHandler;
import nl.tudelft.twobrains.server.model.Database;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Leroy on 30-11-2015.
 */
public class ServerTest extends TestCase {

    static String testDatabaseFile = System.getProperty("user.dir") + "/tests/nl/tudelft/twobrains/server/model/DatabaseTestFiles/TestDatabaseServer.json";

    Server testServer1;
    Server testServer2;
    Server testServer3;
    static Database db;

    static {
        try {
            db = Database.parse(testDatabaseFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testGetDatabase() throws IOException, ParseException {


        testServer2 = new Server(db);


        new Thread(() -> {
            try {
                testServer2.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();


        assertTrue(testServer2.getDatabase().equals(db));

    }

    @Test
    public void testSetDatabase() throws IOException, ParseException {

        testServer3 = new Server();
        testServer3.setDatabase(db);

        assertEquals(db, testServer3.getDatabase());

    }

    @Test
    public void testGetSocket() throws IOException, ParseException {
        testServer1 = new Server(6002);


        assertTrue(testServer1.getSocket().getLocalPort() == 6002);
    }

    @Test
    public void testGetMatchfinder() throws IOException, ParseException {

        testServer2 = new Server(db, 6003);
        new Thread(() -> {
            try {
                testServer2.run();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
        Socket sk = new Socket("127.0.0.1", 6003);

        
        MatchFinder k = testServer2.getMatchFinder();

        assertEquals(k.getMatches(), new MatchFinder(db).getMatches());


    }

    public void testClose() throws IOException {
        try {
            testServer2 = new Server(6004);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final ServerSocket testServ = testServer2.getSocket();
        testServer2.close();

        assertTrue(testServ.isClosed());
    }

    public void testHandler() throws IOException, ParseException {

        testServer2 = new Server(null, 6005);
        new Thread(() -> {
            try {
                testServer2.run();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
        Socket sk = new Socket("127.0.0.1", 6005);


        assertNull(testServer2.getHandler());


    }

}