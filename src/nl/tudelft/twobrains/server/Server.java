package nl.tudelft.twobrains.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by jasperketelaar on 11/18/15.
 */
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
    }
}
