package nl.tudelft.twobrains.server.controller.client.handlers;

import nl.tudelft.twobrains.server.Server;
import nl.tudelft.twobrains.server.model.Database;
import nl.tudelft.twobrains.server.model.Gebruiker;
import nl.tudelft.twobrains.server.model.listeners.client.ClientEvent;
import nl.tudelft.twobrains.server.model.listeners.client.ClientListener;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by jasperketelaar on 11/23/15.
 */
public class RegistreerHandler implements ClientListener {

    /**
     * Method for reacting to client 'Registreer'/register events (overrides
     * interface method). The method checks if the evt argument is of type (name)
     * 'Registreer'. The content(argument) of the evt argument is put into a
     * String array by splitting. The database is checked for already containing
     * this email (=username). If not the users information is added to the database.
     *
     * @param evt A client event consisting of two Strings: event, argument.
     * @param responseStream A data ouput stream to write data to the client.
     * @param server The server for database access
     */
    @Override
    public void onClientEvent(ClientEvent evt, DataOutputStream responseStream, Server server) {

        if (evt.getEvent().equals("Registreer")) {
            try {
                final BufferedImage image = ImageIO.read(new ByteArrayInputStream(evt.getData()));
                final String split[] = evt.getArguments().split(":");
                final String email = split[0];
                final File file = new File(Server.IMAGES + email + ".jpg");
                ImageIO.write(image, "jpg", file);

                final String input = evt.getArguments().replace(email + ":", "");
                if (server.getDatabase().containsKey(email)) {
                    responseStream.writeUTF("Email bestaat al");
                } else {
                    final Gebruiker gebruiker;
                    try {
                        gebruiker = Gebruiker.parse(email, input);
                        server.getDatabase().add(gebruiker);
                        responseStream.writeUTF("Succes");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
