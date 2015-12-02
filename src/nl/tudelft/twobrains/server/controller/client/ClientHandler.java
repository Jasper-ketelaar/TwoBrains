package nl.tudelft.twobrains.server.controller.client;

import nl.tudelft.twobrains.server.Server;
import nl.tudelft.twobrains.server.controller.client.handlers.ImageHandler;
import nl.tudelft.twobrains.server.controller.client.handlers.LoginHandler;
import nl.tudelft.twobrains.server.controller.client.handlers.RegistreerHandler;
import nl.tudelft.twobrains.server.model.Database;
import nl.tudelft.twobrains.server.model.listeners.client.ClientEvent;
import nl.tudelft.twobrains.server.model.listeners.client.ClientListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by jasperketelaar on 11/23/15.
 */
public class ClientHandler extends Thread {


    private final ArrayList<ClientListener> listeners = new ArrayList<>(Arrays.asList(new LoginHandler(), new RegistreerHandler(), new ImageHandler()));

    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final Database database;

    /**
     * Class constructor specifying the socket and database. A new input/output
     * stream are connected to the Socket specified by the socket argument.
     *
     * @param socket The socket that will be used by the ClientHandler.
     * @param database The database that will be used by the ClientHandler.
     * @throws IOException Checks for I/O exceptions.
     */
    public ClientHandler(final Socket socket, final Database database) throws IOException {
        this.database = database;
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
     */
    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                final String input = this.input.readUTF();
                //System.out.println(input); debug check
                final String[] split = input.split(":;");
                //System.out.println(split.length); debug check
                final ClientEvent evt = new ClientEvent(split[0], split[1]);
                for (final ClientListener listener : listeners) {
                    listener.onClientEvent(evt, output, database);
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
