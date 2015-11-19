package nl.tudelft.twobrains.server;

import nl.tudelft.twobrains.server.model.Database;
import nl.tudelft.twobrains.server.model.Gebruiker;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;

public class Server {

    private final ServerSocket server;

    public Server(final int port) throws IOException {
        this.server = new ServerSocket(port);
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


        try {
            final URL resource = Server.class.getResource("resources/database.json");
            final Database db = Database.parse(resource.getFile());

            server.close();
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        server.close();
    }
}
