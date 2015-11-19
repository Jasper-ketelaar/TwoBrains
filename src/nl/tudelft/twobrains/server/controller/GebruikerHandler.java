package nl.tudelft.twobrains.server.controller;

import nl.tudelft.twobrains.server.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GebruikerHandler extends Thread {

    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final Server server;

    public GebruikerHandler(final Socket socket, final Server server) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.server = server;
    }

    //TODO: Implementatie schrijven
    private void handle(final String input) {
        final String[] split = input.split(":");
        switch (split[0]) {
            case "LOGIN":
                if (server.getDatabase().containsKey(split[1])) {
                    System.out.println("Login request: " + input + " succesvol");
                } else {
                    System.err.println("Deze user bestaat niet");
                }
                break;

            case "REGISTREER":
                final String email = split[1];
                if (server.getDatabase().containsKey(email)) {
                    System.err.print("Dit email adress bestaat al");
                } else {
                    //TODO: Nieuwe gebruiker aanmaken
                }
        }

    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                handle(input.readUTF());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
