package nl.tudelft.twobrains.server.controller.client.handlers;

import nl.tudelft.twobrains.server.Server;
import nl.tudelft.twobrains.server.model.Gebruiker;
import nl.tudelft.twobrains.server.model.Match;
import nl.tudelft.twobrains.server.model.listeners.client.ClientEvent;
import nl.tudelft.twobrains.server.model.listeners.client.ClientListener;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jasperketelaar on 1/15/16.
 */
public class MatchHandler implements ClientListener {

    @Override
    public void onClientEvent(ClientEvent evt, DataOutputStream responseStream, Server server) throws IOException {
        if (evt.getEvent().equals("Match")) {
            final ArrayList<Match> matchArrayList = server.getMatchFinder().getMatchesForUser(evt.getArguments());
            for (final Match match : matchArrayList) {
                if (match.getGebruiker1().equals(evt.getArguments())) {
                    responseStream.writeUTF(match.getGebruiker2() + ":" + server.getDatabase().get(match.getGebruiker2()).getJSONString());
                } else {
                    responseStream.writeUTF(match.getGebruiker1() + ":" + server.getDatabase().get(match.getGebruiker1()).getJSONString());
                }


            }
            responseStream.flush();
        }
    }
}
