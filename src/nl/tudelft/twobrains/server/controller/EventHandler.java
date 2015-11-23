package nl.tudelft.twobrains.server.controller;

import nl.tudelft.twobrains.server.Server;
import nl.tudelft.twobrains.server.model.listeners.client.ClientEvent;
import nl.tudelft.twobrains.server.model.listeners.client.ClientListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * Created by jasperketelaar on 11/23/15.
 */
public class EventHandler extends Thread {


    private final ArrayList<ClientListener> listeners = new ArrayList<>();

    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final Server server;

    public EventHandler(final Socket socket, final Server server) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.server = server;
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                final String input = this.input.readUTF();
                final String[] split = input.split(";:");
                final ClientEvent evt = new ClientEvent(split[0], split[1]);
                for (final ClientListener listener : listeners) {
                    listener.onClientEvent(evt);
                }
            } catch (SocketException e) {
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
