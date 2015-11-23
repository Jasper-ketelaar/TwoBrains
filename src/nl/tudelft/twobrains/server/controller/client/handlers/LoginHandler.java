package nl.tudelft.twobrains.server.controller.client.handlers;

import nl.tudelft.twobrains.server.model.Database;
import nl.tudelft.twobrains.server.model.Gebruiker;
import nl.tudelft.twobrains.server.model.listeners.client.ClientEvent;
import nl.tudelft.twobrains.server.model.listeners.client.ClientListener;

import java.io.DataOutputStream;

/**
 * Created by jasperketelaar on 11/23/15.
 */
public class LoginHandler implements ClientListener {

    @Override
    public void onClientEvent(ClientEvent evt, DataOutputStream responseStream, Database database) {
        try {
            if (evt.getEvent().equals("Login")) {
                final String[] split = evt.getArguments().split(":");

                if (database.containsKey(split[0])) {
                    final Gebruiker gebruiker = database.get(split[0]);
                    final String wachtwoord = gebruiker.getWachtwoord();
                    if (wachtwoord.equals(split[1])) {
                        responseStream.writeUTF("Succes:" + gebruiker.getJSONString());
                    } else {
                        responseStream.writeUTF("Wachtwoord is verkeerd");
                    }
                } else {
                    System.err.println("Deze user bestaat niet");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
