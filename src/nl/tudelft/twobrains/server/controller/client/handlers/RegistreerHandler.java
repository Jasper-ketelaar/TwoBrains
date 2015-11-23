package nl.tudelft.twobrains.server.controller.client.handlers;

import nl.tudelft.twobrains.server.model.Database;
import nl.tudelft.twobrains.server.model.Gebruiker;
import nl.tudelft.twobrains.server.model.listeners.client.ClientEvent;
import nl.tudelft.twobrains.server.model.listeners.client.ClientListener;

import java.io.DataOutputStream;

/**
 * Created by jasperketelaar on 11/23/15.
 */
public class RegistreerHandler implements ClientListener {

    @Override
    public void onClientEvent(ClientEvent evt, DataOutputStream responseStream, Database database) {
        if (evt.getEvent().equals("Registreer")) {
            final String split[] = evt.getArguments().split(":");
            final String email = split[0];
            final String input = evt.getArguments().replace(email + ":", "");
            if (database.containsKey(email)) {
                //TODO: Faulty case
            } else {
                final Gebruiker gebruiker = Gebruiker.parse(email, input);
                database.add(gebruiker);
                System.out.println(gebruiker);
            }
        }
    }
}
