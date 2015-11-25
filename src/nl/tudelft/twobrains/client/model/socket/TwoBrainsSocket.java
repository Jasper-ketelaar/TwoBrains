package nl.tudelft.twobrains.client.model.socket;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.HashMap;

//TODO: Bepaalde server utils schrijven zoals gebruikers gegevens aanvragen
public class TwoBrainsSocket extends Socket {

    private final HashMap<String, BufferedImage> images = new HashMap<>();

    private final DataInputStream input;
    private final DataOutputStream output;

    public TwoBrainsSocket(final String ip, final int port) throws IOException {
        super(ip, port);
        this.input = new DataInputStream(super.getInputStream());
        this.output = new DataOutputStream(super.getOutputStream());
    }

    @Override
    public DataInputStream getInputStream() {
        return this.input;
    }

    @Override
    public DataOutputStream getOutputStream() {
        return this.output;
    }

    public BufferedImage getImage(final String file) {
        if (!images.containsKey(file)) {
            try {
                output.writeUTF("Image:;" + file);
                final byte[] sizeB = new byte[4];
                input.read(sizeB);

                final int size = ByteBuffer.wrap(sizeB).asIntBuffer().get();

                final byte[] imageB = new byte[size];
                System.out.println(input.read(imageB));
                final BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageB));

                images.put(file, image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return images.get(file);
    }

    public String login(final String email, final String password) {
        try {
            getOutputStream().writeUTF("Login:;" + email + ":" + password);
            return getInputStream().readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String register(final String email, final JSONObject data) {
        try {
            getOutputStream().writeUTF("Registreer:;" + email + ":" + data);
            return getInputStream().readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
