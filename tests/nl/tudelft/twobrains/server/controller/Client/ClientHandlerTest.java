package nl.tudelft.twobrains.server.controller.Client;

import nl.tudelft.twobrains.server.Server;
import nl.tudelft.twobrains.server.controller.client.ClientHandler;
import nl.tudelft.twobrains.server.model.Database;
import org.json.simple.JSONObject;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;

import static org.junit.Assert.*;

/**
 * Created by Bernard on 2-12-2015.
 */
public class ClientHandlerTest {

    /*
    Server maken om ClientHandler op te testen. (Kijk naar ServerTest).
     */
    Server testServer;
    Socket socket;

    {
        try {
            testServer = new Server(5555);
            socket = new Socket("a", 4321);
        } catch (BindException e){


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Database database = new Database(new JSONObject());
    ClientHandler clientHandler;

    public ClientHandlerTest() throws IOException {
        clientHandler = new ClientHandler(socket, database);
    }


    @Test
    public void testRun(){
        clientHandler.run();
    }

    @Test
    public void testRun2() throws Exception {

    }

    @Test
    public void testConstructor() throws Exception {
        assertNotNull(clientHandler);
    }
}