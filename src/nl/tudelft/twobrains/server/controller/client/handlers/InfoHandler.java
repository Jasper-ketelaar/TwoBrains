package nl.tudelft.twobrains.server.controller.client.handlers;

import nl.tudelft.twobrains.server.Server;
import nl.tudelft.twobrains.server.model.Database;
import nl.tudelft.twobrains.server.model.Gebruiker;
import nl.tudelft.twobrains.server.model.listeners.client.ClientEvent;
import nl.tudelft.twobrains.server.model.listeners.client.ClientListener;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by jasperketelaar on 11/23/15.
 */
public class InfoHandler implements ClientListener {

    /**
     * Method for reacting to client login events (overrides interface method).
     * The method checks if the evt argument is of type 'Login'. The content
     * (argument) of the evt argument is put into a String array by splitting.
     * The database is checked for containing the username. If the username is in
     * the database the password is checked for being correct. A corresponding
     * message for the different cases is send to the user. If the username and
     * password are correct the Server returns the users information (JSON).
     *
     * @param evt            A client event consisting of two Strings: event, argument.
     *                       The argument can contain the username and password.
     * @param responseStream A data ouput stream to write data to the client.
     * @param server         The server for database access
     */
    @Override
    public void onClientEvent(ClientEvent evt, DataOutputStream responseStream, Server server) throws IOException {

        if (evt.getEvent().equals("Info")) {
            System.out.println("Info required");
            if (server.getDatabase().containsKey(evt.getArguments())) {
                final Gebruiker gebruiker = server.getDatabase().get(evt.getArguments());
                responseStream.writeUTF(gebruiker.getJSONString());
                responseStream.flush();
            } else {
                responseStream.writeUTF("Email bestaat niet");
            }
        }

    }
}
