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

    /**
     * Deze methode zal als eerst kijken of er een file is in de map TwoBrains.
     * Zo niet dan wordt er een nieuw aangemaakt.
     *
     * Daarna wordt er gekeken of er een database.json file in de map zit.
     * Zo niet dan wordt er een nieuwe aangemaakt.
     *
     * De database.json file wordt geparsed naar het private Database element
     * van de Server.
     * @param port wordt een nieuwe ServerSocket aangemaakt.
     * @throws IOException
     */
    public Server(final int port) throws IOException {
        this.server = new ServerSocket(port);

        try {
            final File dir = new File(System.getProperty("user.home") + "/.TwoBrains/");
            final File iDir = new File(dir.getPath() + "/images/");
            if (!iDir.exists()) {
                iDir.mkdir();
            } else {
                System.out.println(dir.getAbsolutePath());
            }
            final File file = new File(dir.getAbsolutePath() + "/database.json");
            if (!file.exists()) {
                file.createNewFile();
            }
            this.database = Database.parse(file.getPath());
            this.database.write(file.getPath());
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return De huidige Database wordt geretouneerd.
     */
    public Database getDatabase() {
        return this.database;
    }

    /**
     *  Setter voor Test
     * @param database wordt de nieuwe Database.
     */
    public void setDatabase(Database database) {
        this.database = database;
    }

    /**
     * Als er geen port wordt ingevoerd zal de server port 4444 gebruiken
     * @param args Als eerste arg kan een poort worden ingevoerd waar de Server op runt
     * @throws IOException
     */
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

    /**
     *
     * @return de huidige ClientHandler wordt geretouneerd
     */
    public ClientHandler getHandler() {
        return this.handler;
    }

    /**
     * Setter voor Test
     * @param handler wordt de nieuwe ClientHandler.
     */
    public void setHandler(ClientHandler handler) {
        this.handler = handler;
    }

    /**
     * Probeert de ClientHandler te starten als er connectie is.
     */
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

    /**
     * Sluit de Server
     * @throws IOException
     */
    public void close() throws IOException {
        server.close();
    }
}
