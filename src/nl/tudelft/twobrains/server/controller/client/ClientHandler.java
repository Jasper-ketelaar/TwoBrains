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

    public ClientHandler(final Socket socket, final Database database) throws IOException {
        this.database = database;
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                final String input = this.input.readUTF();
                System.out.println(input);
                final String[] split = input.split(":;");
                System.out.println(split.length);
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

    public void addListener(final ClientListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(final ClientListener listener) {
        this.listeners.remove(listener);
    }
}
