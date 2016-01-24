package nl.tudelft.twobrains.server.controller.client.handlers;

import nl.tudelft.twobrains.server.Server;
import nl.tudelft.twobrains.server.model.listeners.client.ClientEvent;
import nl.tudelft.twobrains.server.model.listeners.client.ClientListener;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by jasperketelaar on 1/24/16.
 */
public class OproepHandler implements ClientListener {

    @Override
    public void onClientEvent(ClientEvent evt, DataOutputStream responseStream, Server server) throws Exception {
        if (evt.getEvent().equals("Oproep")) {
            final String args = evt.getArguments();
            final JSONParser parser = new JSONParser();
            final File oproepen = new File(Server.RESOURCES + "/oproep.json");
            final JSONObject object = (JSONObject) parser.parse(new FileReader(oproepen));
            if (args.equals("")) {
                responseStream.writeUTF(object.toJSONString());
            } else {
                final JSONObject nieuw = new JSONObject();
                final String strings[] = evt.getArguments().split(":");
                nieuw.put("Vak", strings[0]);
                nieuw.put("Naam", strings[2]);
                object.put(strings[1], nieuw);
                System.out.println(object.toJSONString());
                final FileWriter writer = new FileWriter(oproepen);
                writer.write(object.toJSONString());
                writer.close();
                responseStream.writeUTF(object.toJSONString());
            }

        }
    }
}
