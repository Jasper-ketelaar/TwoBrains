package nl.tudelft.twobrains.server.controller.client.handlers;

import nl.tudelft.twobrains.client.TwoBrains;
import nl.tudelft.twobrains.client.model.socket.TwoBrainsSocket;
import nl.tudelft.twobrains.server.model.Database;
import nl.tudelft.twobrains.server.model.Gebruiker;
import nl.tudelft.twobrains.server.model.listeners.client.ClientEvent;
import nl.tudelft.twobrains.server.model.listeners.client.ClientListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * Created by Bernard on 13-1-2016.
 */
public class ChatHandler implements ClientListener{


    @Override
    public void onClientEvent(ClientEvent evt, DataOutputStream responseStream, Database database) {
        try {
            if (evt.getEvent().equals("Chat")) {
                final String[] split = evt.getArguments().split(":");

                if (database.containsKey(split[0])) {
                    final Gebruiker gebruiker = database.get(split[0]);
                    final String message = split[1];
                    if(!message.equals("")){
                        responseStream.writeUTF("Succes:" + message);
                        //TODO:stuur message naar (andere) gebruiker
                    }
                } else {
                    responseStream.writeUTF("Email/Gebruiker bestaat niet");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
