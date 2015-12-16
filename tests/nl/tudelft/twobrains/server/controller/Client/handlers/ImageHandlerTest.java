package nl.tudelft.twobrains.server.controller.Client.handlers;

import com.sun.deploy.util.ArrayUtil;
import nl.tudelft.twobrains.server.Server;
import nl.tudelft.twobrains.server.controller.client.handlers.ImageHandler;
import nl.tudelft.twobrains.server.model.listeners.client.ClientEvent;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.nio.ByteBuffer;

import static org.junit.Assert.*;

/**
 * Created by Bernard on 10-12-2015.
 */
public class ImageHandlerTest {

    /*
    Hoe test je private methods? + OutputStreams?
     */

    ImageHandler imageH = new ImageHandler();
    ClientEvent event = new ClientEvent("Image", "kvanzeijl@hotmail.com");

    @Test
    public void testOnClientEvent() throws Exception {
        final ByteArrayOutputStream response = new ByteArrayOutputStream();
        final DataOutputStream outputStream = new DataOutputStream(response);

        final ByteArrayOutputStream imageBytes = new ByteArrayOutputStream();
        BufferedImage image = ImageIO.read(new File(".TwoBrains/resources/images/kvanzeijl@hotmail.com.jpg"));
        ImageIO.write(image, "jpg", imageBytes);
        byte[] bytes = imageBytes.toByteArray();

        imageH.onClientEvent(event, outputStream, null);

        final int size = ByteBuffer.wrap(response.toByteArray(), 0, 4).asIntBuffer().get();
        //final byte[] responseImage = ByteBuffer.wrap(response.toByteArray(), 4, response.size() - 1).array();
        //System.out.println(bytes.length);
        System.out.println(imageBytes.toByteArray()[3]);
        System.out.println(response.toByteArray()[3]);
        //System.out.println(trim(response.toByteArray(), 4).length);
        assertArrayEquals(trim(response.toByteArray(), 4), trim(response.toByteArray(), 4));
    }

    public byte[] trim(final byte[] bytes, final int offset) {
        final byte[] response = new byte[bytes.length - offset];
        for (int i = 0; i < response.length; i++) {
            response[i] = bytes[i + (offset - 1)];
        }
        return response;
    }
}