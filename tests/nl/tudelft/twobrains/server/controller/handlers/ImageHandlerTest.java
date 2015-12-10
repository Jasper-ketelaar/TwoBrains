package nl.tudelft.twobrains.server.controller.handlers;

import nl.tudelft.twobrains.server.controller.client.handlers.ImageHandler;
import nl.tudelft.twobrains.server.model.listeners.client.ClientEvent;
import org.junit.Test;
import org.omg.CORBA.DataOutputStream;
import org.omg.CORBA_2_3.portable.OutputStream;

import static org.junit.Assert.*;

/**
 * Created by Bernard on 10-12-2015.
 */
public class ImageHandlerTest {

    /*
    Hoe test je private methods? + OutputStreams?
     */

    ImageHandler imageH = new ImageHandler();
    ClientEvent event = new ClientEvent("event", "arguments");

    @Test
    public void testOnClientEvent() throws Exception {

    }
}