package nl.tudelft.twobrains.server.controller.Client.handlers;

import nl.tudelft.twobrains.server.controller.client.handlers.LoginHandler;
import nl.tudelft.twobrains.server.model.Database;
import nl.tudelft.twobrains.server.model.listeners.client.ClientEvent;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.omg.CORBA.DataOutputStream;

import static org.junit.Assert.*;

/**
 * Created by Bernard on 10-12-2015.
 */
public class LoginHandlerTest {

    LoginHandler loginHandler = new LoginHandler();
    Database database = new Database(new JSONObject());
   // DataOutputStream dataStream = new
    ClientEvent eventLogin = new ClientEvent("Login", "A");
    ClientEvent eventNotLogin = new ClientEvent("NotLogin", "B");

    @Test
    public void testOnClientEvent() throws Exception {
    //   loginHandler.onClientEvent(eventLogin, dataStream, database);
    }
}