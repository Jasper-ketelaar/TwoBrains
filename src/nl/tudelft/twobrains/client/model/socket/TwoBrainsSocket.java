package nl.tudelft.twobrains.client.model.socket;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

//TODO: Bepaalde server utils schrijven zoals gebruikers gegevens aanvragen
public class TwoBrainsSocket extends Socket {

    /**
     * Maakt 3 attributen aan
     * @attrib Hashmap
     * @Attrib datainputsteam
     * @attrib Dataoutputstream
     */
    private final HashMap<String, BufferedImage> images = new HashMap<>();
    private final DataInputStream input;
    private final DataOutputStream output;

    /**
     * Constructor TwoBrainsSocket
     *      Creert "Socket-object"? 
     * @param ip
     * @param port
     * @throws IOException
     */
    public TwoBrainsSocket(final String ip, final int port) throws IOException {
        super(ip, port);
        this.input = new DataInputStream(super.getInputStream());
        this.output = new DataOutputStream(super.getOutputStream());
    }

    /**
     * 2 get methoden
     * @method getInputsteam
     * @method getOutputstream
     */
    @Override
    public DataInputStream getInputStream() {
        return this.input;
    }

    @Override
    public DataOutputStream getOutputStream() {
        return this.output;
    }


    /**
     * Methode voor verkrijgen van image
     * @param file
     * @return image
     * @throws IOException
     */

    public BufferedImage getImage(final String file) {
        if (!images.containsKey(file)) {
            try {
                output.writeUTF("Image:;" + file);
                final byte[] sizeB = new byte[4];
                input.read(sizeB);

                final int size = ByteBuffer.wrap(sizeB).asIntBuffer().get();

                final byte[] imageB = new byte[size];
                input.read(imageB);
                final BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageB));

                images.put(file, image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return images.get(file);
    }

    /**
     * Methode om in te loggen op de server
     * Krijgt  2 inputs
     * @param email
     * @param password
     * @return
     * @throws IOException
     */
    public String login(final String email, final String password) {
        try {
            getOutputStream().writeUTF("Login:;" + email + ":" + password);
            return getInputStream().readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Methode om een chatmessage te zenden
     *
     * @param email email van de Gebruiker waar het bericht naar verstuurd wordt
     * @param bericht bericht dat wordt verzonden
     * @return De UTF die terug van de Server komt.
     */
    public String message(final String email, final String bericht){
        try {
            getOutputStream().writeUTF("Chat:;" + email + ":" + bericht);
            return getInputStream().readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * Methode om een account te registeren op de server
     * Verwacht 3 parameters
     * @param email = userinput emailadress
     * @param data = userinput wachtwoord
     * @param image = userinput toegevoegde profielfoto
     * @return
     * @throws IOException
     */
    public String register(final String email, final JSONObject data, final BufferedImage image) {
        try {
            getOutputStream().writeUTF("Registreer:;" + email + ":" + data);


            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);

            final byte[] file = baos.toByteArray();
            getOutputStream().writeInt(baos.size());
            getOutputStream().write(file);
            getOutputStream().flush();

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        baos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, 5000);

            return getInputStream().readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public ArrayList<String> getMatches(final String user) {

        try {

            getOutputStream().writeUTF("Match:;" + user);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
