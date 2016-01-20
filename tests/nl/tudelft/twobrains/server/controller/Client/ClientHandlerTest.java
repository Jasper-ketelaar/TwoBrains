package nl.tudelft.twobrains.server.controller.client;

import junit.framework.TestCase;
import nl.tudelft.twobrains.client.TwoBrains;
import nl.tudelft.twobrains.client.model.socket.TwoBrainsSocket;
import nl.tudelft.twobrains.server.Server;
import nl.tudelft.twobrains.server.model.Database;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.net.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * TODO: Lukt mij niet
 * Created by Leroy on 30-11-2015.
 */
public class ClientHandlerTest extends TestCase {
    Server sr;

    public void initializeServer() {
        new Thread(() -> {

            while (true) {
                try {
                    sr = new Server(4444);
                    sr.run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Test
    public void testRun() throws IOException {

        initializeServer();

        final ServerSocket testHandler;
        final ClientHandler[] testHandler2 = new ClientHandler[1];

        TwoBrainsSocket sk = new TwoBrainsSocket("127.0.0.1", 4444);

        new Thread(() -> {

            while (true) {
                try {
                    testHandler2[0] = sr.getHandler();

                }catch (NullPointerException e){

                }

            }
        }).start();
        TwoBrainsSocket sk2 = new TwoBrainsSocket("127.0.0.1", 4444);

        assertEquals(testHandler2[0], null);
    }
}