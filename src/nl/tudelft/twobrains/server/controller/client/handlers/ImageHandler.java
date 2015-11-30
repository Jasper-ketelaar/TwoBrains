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

    /**
     * Method for reacting to client image events (overrides interface method).
     * The method checks if the evt argument is of type 'Image'. The content
     * (argument) of the evt argument is put into a string. Then the method
     * calls the sendImage method with this string and the responseStream argument.
     *
     * @param evt A client event consisting of two Strings: event, argument.
     * @param responseStream A data ouput stream to write data to the client.
     * @param database The database is not used in this method.
     */
    @Override
    public void onClientEvent(ClientEvent evt, DataOutputStream responseStream, Database database) {
        if (evt.getEvent().equals("Image")) {
            final String fileName = evt.getArguments();
            System.out.println(fileName);
            sendImage(fileName, responseStream);
        }
    }

    /**
     * Method for getting the Image File of a Client. A file with all URI of the
     * images is created by getting it from the Server. The pathnames are put
     * in an array. The array is checked for containing a file with to the
     * email argument as name.
     *
     * @param email The email of the Client, email is used as Client username.
     * @return The Image File, Null. Depending on the email (username) existing
     * on the Server.
     */
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

    /**
     * Method for sending an Image to the Client. The image File is obtained by
     * calling the getFile() method with the fileName argument. The image is loaded
     * from the file. The extension of the image is put in a String.
     * <p>
     * The image is written into a byte array. The size of the image is written into a
     * different byte array. The output stream first reserves space for the image and
     * then writes it to the client. The output is finally flushed, forcing any remaining
     * bytes to be written out. Every 5000 ms the method tries to close the output stream.
     *
     * @param fileName The name of the Image File.
     * @param output A data ouput stream to write data to the client.
     */
    private void sendImage(final String fileName, final DataOutputStream output) {
        try {
            final File file = getFile(fileName);
            final BufferedImage image = ImageIO.read(file);
            final String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length());

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
