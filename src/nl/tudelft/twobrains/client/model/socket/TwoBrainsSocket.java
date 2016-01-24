package nl.tudelft.twobrains.client.model.socket;

import nl.tudelft.twobrains.client.model.Gebruiker;
import org.json.simple.JSONObject;
import org.omg.IOP.IOR;

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
     *
     * @attrib Hashmap
     * @Attrib datainputsteam
     * @attrib Dataoutputstream
     */
    private final HashMap<String, BufferedImage> images = new HashMap<>();
    private final DataInputStream input;
    private final DataOutputStream output;

    /**
     * Constructor TwoBrainsSocket
     * Creert "Socket-object"?
     *
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
     *
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
     *
     * @param file
     * @return image
     * @throws IOException
     */

    public BufferedImage getImage(final String file) throws IOException {
        if (!images.containsKey(file)) {

            output.writeUTF("Image:;" + file);
            final byte[] sizeB = new byte[4];
            input.read(sizeB);

            final int size = ByteBuffer.wrap(sizeB).asIntBuffer().get();


            final byte[] imageB = new byte[size];
            input.read(imageB);
            final BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageB));
            images.put(file, image);

        }
        return images.get(file);
    }

    /**
     * Methode om in te loggen op de server
     * Krijgt  2 inputs
     *
     * @param email
     * @param password
     * @return
     * @throws IOException
     */
    public Gebruiker login(final String email, final String password) throws Exception {
        final Gebruiker info = verkrijgInfo(email);
        if (info != null && verkrijgInfo(email).getWachtwoord().equals(password)) {
            return info;
        }
        return null;
    }

    public Gebruiker verkrijgInfo(final String email) throws Exception {
        getOutputStream().writeUTF("Info:;" + email);
        final String response = getInputStream().readUTF();
        if (!response.equals("Email bestaat niet")) {
            final Gebruiker info = Gebruiker.parse(email, response);
            info.setConnection(this);
            return info;
        }
        return null;

    }


    /**
     * Methode om een chatmessage te zenden
     *
     * @param email   email van de Gebruiker waar het bericht naar verstuurd wordt
     * @param bericht bericht dat wordt verzonden
     * @return De UTF die terug van de Server komt.
     */

    public String message(final String email, final String bericht) throws IOException {

        getOutputStream().writeUTF("Chat:;" + email + ":" + bericht);
        return getInputStream().readUTF();


    }


    /**
     * Methode om een account te registeren op de server
     * Verwacht 3 parameters
     *
     * @param email = userinput emailadress
     * @param data  = userinput wachtwoord
     * @param image = userinput toegevoegde profielfoto
     * @return
     * @throws IOException
     */
    public String register(final String email, final JSONObject data, final BufferedImage image) throws IOException {

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
    }

    public String oproep(final String vak, final String email, final String naam) {
        try {
            if (vak.equals("")) {
                getOutputStream().writeUTF("Oproep:; ");
            } else {
                getOutputStream().writeUTF("Oproep:;" + vak + ":" + email + ":" + naam);
            }
            final String output = getInputStream().readUTF();
            System.out.println(output);
            return output;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public ArrayList<String> getMatches(final String user) throws IOException {
        final ArrayList<String> matches = new ArrayList<>();


        getOutputStream().writeUTF("Match:;" + user);
        while (getInputStream().available() != 0) {
            matches.add(getInputStream().readUTF());
        }
        clear();
        return matches;
    }

    private void clear() throws IOException {
        input.skipBytes(input.available());
    }
}
