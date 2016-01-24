package nl.tudelft.twobrains.server.model.listeners.client;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Leroy on 24-1-2016.
 */
public class ClientEventTest {
    byte TestByte1 = (byte) 0xe0;
    byte TestByte2 = (byte) 0xe1;
    byte TestByte3 = (byte) 0xe2;
    byte[] byteArray = {TestByte1, TestByte2, TestByte3};

    @Test
    public void testEvent() {
        ClientEvent testEvent = new ClientEvent("testEvent", "testArgument");

        assertEquals("testEvent", testEvent.getEvent());

    }

    @Test
    public void testArgument() {
        ClientEvent testEvent = new ClientEvent("testEvent", "testArgument");

        assertEquals("testArgument", testEvent.getArguments());

    }

    @Test
    public void testData() {
        ClientEvent testEvent = new ClientEvent("testEvent", "testArgument");

        testEvent.setData(byteArray);
        assertTrue(testEvent.getData().equals(byteArray));

    }

}