package nl.tudelft.twobrains.server;

import nl.tudelft.twobrains.server.controller.client.ClientHandler;
import nl.tudelft.twobrains.server.model.Database;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

//TODO: Javadoc comments
public class Server {

    private final ServerSocket server;


    private Database database;
    private ClientHandler handler;


    public Server(final int port) throws IOException {
        this.server = new ServerSocket(port);

        try {
            final File dir = new File(System.getProperty("user.home") + "/TwoBrains/");
            if (!dir.exists()) {
                dir.mkdir();
            } else {
                System.out.println(dir.getAbsolutePath());
            }

            final File file = new File(dir.getAbsolutePath() + "/database.json");
            if (!file.exists()) {
                file.createNewFile();
            }
            this.database = Database.parse(file.getPath());
            this.database.write(file.getPath()); //only for Writer test
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

    public ClientHandler getHandler() {
        return this.handler;
    }

    public void run() {
        while (true) {
            try {
                final Socket accept = server.accept();
                this.handler = new ClientHandler(accept, database);
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
