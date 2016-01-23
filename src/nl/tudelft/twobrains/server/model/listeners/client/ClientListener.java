package nl.tudelft.twobrains.server.model.listeners.client;

import nl.tudelft.twobrains.server.Server;
import nl.tudelft.twobrains.server.model.Database;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by jasperketelaar on 11/23/15.
 */
public interface ClientListener {
    /**
     * Interface specifying the method for reacting to client events for
     * different handlers.
     *
     * @param evt The client event that needs to be handled.
     * @param responseStream A data ouput stream to write data to the client.
     * @param server the server for database access
     */
    void onClientEvent(final ClientEvent evt, final DataOutputStream responseStream, Server server) throws Exception;

}
