package nl.tudelft.twobrains.server.controller.client;

import nl.tudelft.twobrains.server.Server;
import nl.tudelft.twobrains.server.controller.client.handlers.ChatHandler;
import nl.tudelft.twobrains.server.controller.client.handlers.ImageHandler;
import nl.tudelft.twobrains.server.controller.client.handlers.LoginHandler;
import nl.tudelft.twobrains.server.controller.client.handlers.RegistreerHandler;
import nl.tudelft.twobrains.server.model.Database;
import nl.tudelft.twobrains.server.model.listeners.client.ClientEvent;
import nl.tudelft.twobrains.server.model.listeners.client.ClientListener;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by jasperketelaar on 11/23/15.
 */
public class ClientHandler extends Thread {


    private final ArrayList<ClientListener> listeners = new ArrayList<>(Arrays.asList(new LoginHandler(), new RegistreerHandler(), new ImageHandler(), new ChatHandler()));

    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final Server server;

    /**
     * Class constructor specifying the socket and database. A new input/output
     * stream are connected to the Socket specified by the socket argument.
     *
     * @param socket The socket that will be used by the ClientHandler.
     * @param server the server for database access
     * @throws IOException Checks for I/O exceptions.
     */
    public ClientHandler(final Socket socket, final Server server) throws IOException {
        this.server = server;
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
    }

    /**
     * Method for listening and responding to all client events. The input
     * from the client is put in a String array by splitting. The first and
     * second value are used to create a client event. The correct handler is
     * selected by a listener. The socket closes when the end of the input stream
     * has been reached unexpectedly or an I/O operation creates an exception.
     * If the event is of type 'Registreer' the data of the event is set by
     * first creating a byte array with the correct size and then using the
     * method setData.
     */
    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {

                final String input = this.input.readUTF();
                final String[] split = input.split(":;");
                final ClientEvent evt = new ClientEvent(split[0], split[1]);
                if(evt.getEvent().equals("Registreer")) {

                    final int size = this.input.readInt();
                    final byte[] dataB = new byte[size];

                    for(int i = 0; i < size; i++) {
                        dataB[i] = (byte) this.input.read();
                    }
                    evt.setData(dataB);
                }
                for (final ClientListener listener : listeners) {
                    listener.onClientEvent(evt, output, server);
                }
            } catch (EOFException | SocketException e) {
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.out.println("Client disconnected");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}