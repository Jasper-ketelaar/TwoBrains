package nl.tudelft.twobrains.server.controller.client.handlers;

import nl.tudelft.twobrains.server.Server;
import nl.tudelft.twobrains.server.model.Database;
import nl.tudelft.twobrains.server.model.listeners.client.ClientEvent;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by Bernard on 10-12-2015.
 */
public class ImageHandlerTest {
    @Test
    public void ImageHandlerTest() {
        ImageHandler testImageHandler = new ImageHandler();

    }

    @Test
    public void onClientEventTestNIET() {
        ImageHandler testImageHandler = new ImageHandler();

        ClientEvent testClientEvent = new ClientEvent("NIETImage", "argument");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        Database testDatabase = null;
        try {
            testDatabase = Database.parse(Server.RESOURCES + "/databasetest.json");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        testImageHandler.onClientEvent(testClientEvent, dataOutputStream, new Server(testDatabase));


    }

    @Test
    public void onClientEventTestGeenImage() throws IOException {
        ImageHandler testImageHandler = new ImageHandler();
        Database testDatabase = null;
        try {
            testDatabase = Database.parse(Server.RESOURCES + "/database.json");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        ClientEvent testClientEvent = new ClientEvent("Image", "ibuddyh@gmail.com");
        BufferedImage image = ImageIO.read(new File(".TwoBrains/resources/images/ibuddyh@gmail.com.jpg"));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ImageIO.write(image, "jpg", outputStream);
        testClientEvent.setData(outputStream.toByteArray());

        outputStream.reset();

        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        testImageHandler.onClientEvent(testClientEvent, dataOutputStream, new Server(testDatabase));



    }

}