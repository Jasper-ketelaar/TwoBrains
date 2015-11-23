package nl.tudelft.twobrains.server.controller.client.handlers;

import nl.tudelft.twobrains.server.Server;
import nl.tudelft.twobrains.server.model.Database;
import nl.tudelft.twobrains.server.model.listeners.client.ClientEvent;
import nl.tudelft.twobrains.server.model.listeners.client.ClientListener;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jasperketelaar on 11/23/15.
 */
public class ImageHandler implements ClientListener {

    @Override
    public void onClientEvent(ClientEvent evt, DataOutputStream responseStream, Database database) {
        if (evt.getEvent().equals("Image")) {
            final String fileName = evt.getArguments();
            System.out.println(fileName);
            sendImage(fileName, responseStream);
        }
    }

    private File getFile(final String email) {
        try {
            final File file = new File(Server.class.getResource("resources/images").toURI());
            for (final File child : file.listFiles()) {
                if (child.getName().contains(email)) {
                    return child;
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void sendImage(final String fileName, final DataOutputStream output) {
        try {
            final File file = getFile(fileName);
            final BufferedImage image = ImageIO.read(file);
            final String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length());
            System.out.println(ext);
            System.out.println("image = " + image);
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, ext, baos);

            final byte[] size = ByteBuffer.allocate(4).putInt(baos.size()).array();
            output.write(size);
            output.write(baos.toByteArray());
            output.flush();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        baos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, 5000);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
