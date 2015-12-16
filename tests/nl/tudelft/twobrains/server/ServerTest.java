package nl.tudelft.twobrains.server;

import com.sun.org.glassfish.external.arc.Taxonomy;
import junit.framework.TestCase;
import nl.tudelft.twobrains.server.controller.client.ClientHandler;
import nl.tudelft.twobrains.server.model.Database;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.net.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Leroy on 30-11-2015.
 */
public class ServerTest extends TestCase {

    Server testServer;
    {
        try {
            testServer = new Server(5555);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testClose() {

        try {
            testServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(testServer.getSocket().isClosed());
    }

    @Test
    public void testDatabase(){
        JSONObject testObj = new JSONObject();
        Database DB = new Database(testObj);

        testServer.setDatabase(DB);

        assertEquals(DB, testServer.getDatabase());
    }


    @Test
    public void testHandler(){
        JSONObject testObj = new JSONObject();
        Database DB = new Database(testObj);
        System.out.println("TEst");


        Socket testSocket;
        ClientHandler testHandler = null;
        try {
            this.testServer = new Server(4444);
            new Socket("127.0.0.1", 4444);
            testSocket = testServer.getSocket().accept();
            testHandler = new ClientHandler(testSocket, DB);
        } catch (IOException e) {
            e.printStackTrace();
        }

        testServer.setHandler(testHandler);
        assertEquals(testHandler, testServer.getHandler());
    }
    @Test
    public void testRun(){
       // testServer.run();
    }
}