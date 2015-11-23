package nl.tudelft.twobrains.server.model.listeners.client;

import nl.tudelft.twobrains.server.model.Database;

import java.io.DataOutputStream;

/**
 * Created by jasperketelaar on 11/23/15.
 */
public interface ClientListener {

    void onClientEvent(final ClientEvent evt, final DataOutputStream responseStream, Database database);

}
