package nl.tudelft.twobrains.server.model.listeners.client;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
//TODO: Test voor SetData en GetData
/**
 * Created by Budi on 02/12/2015.
 */
public class ClientEventTest {

    ClientEvent clientEvent;
    byte[] b;

    @Before
    public void setUp() throws Exception {

        clientEvent = new ClientEvent("test1", "test2");
        b = new byte[4];

    }
    @Test
    public void testGetEvent() throws Exception {
        assertEquals("test1", clientEvent.getEvent());
    }

    @Test
    public void testGetArguments() throws Exception {
        assertEquals("test2", clientEvent.getArguments());
    }

    @Test
    public void testSetData() throws Exception {
        clientEvent.setData(b);
        assertTrue(b.equals(clientEvent.getData()));

    }


}