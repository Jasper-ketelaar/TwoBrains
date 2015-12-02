package nl.tudelft.twobrains.client.model.socket;

import org.junit.Test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.junit.Assert.*;

/**
 * Created by Leroy on 30-11-2015.
 */
public class TwoBrainsSocketTest {
    TwoBrainsSocket testTwoBrainsSocket;
    Socket testSocket;

    {
        try {
            testTwoBrainsSocket = new TwoBrainsSocket("127.0.0.1", 4444);
            testSocket = new Socket("127.0.0.1", 4444);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @Test
    public void tester(){

       testTwoBrainsSocket.getInputStream();
     assertTrue(true);
 }

    //TODO: Schrijven
}