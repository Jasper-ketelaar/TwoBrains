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
        } catch (BindException e){


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testMain() {



    }

    @Test
    public void testDatabase(){
        JSONObject testObj = new JSONObject();
        Database DB = new Database(testObj);

        testServer.setDatabase(DB);

        assertEquals(DB, testServer.getDatabase());
    }

    //TODO: Fixen(geeft timeout)
    @Test
    public void testHandler(){
        JSONObject testObj = new JSONObject();
        Database DB = new Database(testObj);


        Socket testSocket;
        ServerSocket testServerSocket;
        SocketAddress me = new InetSocketAddress("127.0.0.1", 5555);
        ClientHandler testHandler = null;
        try {
            testServerSocket = new ServerSocket(4444);
            testSocket = testServerSocket.accept();

            testHandler = new ClientHandler(testSocket, DB);
        } catch (IOException e) {
            e.printStackTrace();
        }


        testServer.setHandler(testHandler);

        assertEquals(testHandler, testServer.getHandler());
    }
}