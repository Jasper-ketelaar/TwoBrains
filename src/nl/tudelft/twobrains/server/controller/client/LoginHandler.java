package nl.tudelft.twobrains.server.controller.client;

import nl.tudelft.twobrains.server.Server;
import nl.tudelft.twobrains.server.controller.EventHandler;
import nl.tudelft.twobrains.server.model.listeners.client.ClientEvent;
import nl.tudelft.twobrains.server.model.listeners.client.ClientListener;

/**
 * Created by jasperketelaar on 11/23/15.
 */
public class LoginHandler implements ClientListener {

    private final Server server;
    
    public LoginHandler(final Server server) {
        this.server = server;
    }

    @Override
    public void onClientEvent(ClientEvent evt) {

    }

}
