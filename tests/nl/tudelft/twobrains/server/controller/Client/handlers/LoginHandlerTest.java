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
public class LoginHandlerTest {
    @Test
    public void LoginHandlerTest() {
        LoginHandler testLoginHandler = new LoginHandler();

    }

    @Test
    public void onClientEventTestNIET() {
        LoginHandler testLoginHandler = new LoginHandler();

        ClientEvent testClientEvent = new ClientEvent("NIETLogin", "argument");
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

        testLoginHandler.onClientEvent(testClientEvent, dataOutputStream, testDatabase);


    }

    @Test
    public void onClientEventTestVerkeerdWachtwoord() throws IOException {
        LoginHandler testLoginHandler = new LoginHandler();
        Database testDatabase = null;
        try {
            testDatabase = Database.parse(Server.RESOURCES + "/database.json");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(testDatabase.get("ibuddyh@gmail.com").getJSONString());

        ClientEvent testClientEvent = new ClientEvent("Login", "ibuddyh@gmail.com:" + testDatabase.get("ibuddyh@gmail.com").getJSONString());
        BufferedImage image = ImageIO.read(new File(".TwoBrains/resources/images/ibuddyh@gmail.com.jpg"));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ImageIO.write(image, "jpg", outputStream);
        testClientEvent.setData(outputStream.toByteArray());

        outputStream.reset();

        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        testLoginHandler.onClientEvent(testClientEvent, dataOutputStream, testDatabase);

        System.out.println(ByteBuffer.wrap(outputStream.toByteArray()).get(0));

    }
    @Test
    public void onClientEventTestGeenGeldigeEmail() throws IOException {
        LoginHandler testLoginHandler = new LoginHandler();
        Database testDatabase = null;
        try {
            testDatabase = Database.parse(Server.RESOURCES + "/database.json");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(testDatabase.get("ibuddyh@gmail.com").getJSONString());

        ClientEvent testClientEvent = new ClientEvent("Login", "ibuddy2h@gmail.com:" + testDatabase.get("ibuddyh@gmail.com").getJSONString());
        BufferedImage image = ImageIO.read(new File(".TwoBrains/resources/images/ibuddyh@gmail.com.jpg"));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ImageIO.write(image, "jpg", outputStream);
        testClientEvent.setData(outputStream.toByteArray());

        outputStream.reset();

        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        testLoginHandler.onClientEvent(testClientEvent, dataOutputStream, testDatabase);

        System.out.println(ByteBuffer.wrap(outputStream.toByteArray()).get(0));

    }
}