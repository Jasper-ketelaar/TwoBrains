package nl.tudelft.twobrains.server.controller.Client.handlers;

import nl.tudelft.twobrains.server.Server;
import nl.tudelft.twobrains.server.controller.client.handlers.RegistreerHandler;
import nl.tudelft.twobrains.server.model.Database;
import nl.tudelft.twobrains.server.model.listeners.client.ClientEvent;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Bernard on 10-12-2015.
 */
public class RegistreerHandlerTest {


    @Test
    public void RegstreerHandlerTest() {
        RegistreerHandler testRegistreerHandler = new RegistreerHandler();

    }

    @Test
    public void onClientEventTestNIET() {
        RegistreerHandler testRegistreerHandler = new RegistreerHandler();

        ClientEvent testClientEvent = new ClientEvent("NIETRegistreer", "argument");
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

        testRegistreerHandler.onClientEvent(testClientEvent, dataOutputStream, testDatabase);


    }

    @Test
    public void onClientEventTestWEL() {
        RegistreerHandler testRegistreerHandler = new RegistreerHandler();

        ClientEvent testClientEvent = new ClientEvent("Registreer", "argument");
        //testClientEvent.setData();

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

        testRegistreerHandler.onClientEvent(testClientEvent, dataOutputStream, testDatabase);


    }
}