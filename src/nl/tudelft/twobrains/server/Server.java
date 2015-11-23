package nl.tudelft.twobrains.server;

import nl.tudelft.twobrains.server.controller.EventHandler;
import nl.tudelft.twobrains.server.controller.client.GebruikerHandler;
import nl.tudelft.twobrains.server.model.Database;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

//TODO: Javadoc comments
public class Server {

    private final ServerSocket server;

    private Database database;
    private EventHandler handler;

    public Server(final int port) throws IOException {
        this.server = new ServerSocket(port);
        try {
            final URL resource = Server.class.getResource("resources/database.json");
            this.database = Database.parse(resource.getFile());
            this.database.write(resource.getFile());
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Database getDatabase() {
        return this.database;
    }

    public static void main(final String[] args) throws IOException {
        int defaultPort = 4444;
        if (args.length > 0) {
            final String arg1 = args[0];
            try {
                defaultPort = Integer.parseInt(arg1);
            } catch (final Exception e) {
                System.err.println("Er is geen poort ingevoerd, default poort 4444 wordt gebruikt!");
            }
        }

        final Server server = new Server(defaultPort);

        server.run();
    }

    public void run() {
        while (true) {
            try {
                final Socket accept = server.accept();
                this.handler = new EventHandler(accept, this);
                handler.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void close() throws IOException {
        server.close();
    }
}
