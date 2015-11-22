package nl.tudelft.twobrains.client.model.socket;

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

    private DataInputStream input;
    private DataOutputStream output;

    public TwoBrainsSocket() {
        try {
            this.input = new DataInputStream(super.getInputStream());
            this.output = new DataOutputStream(super.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                output.writeUTF("IMAGE:" + file);
                final byte[] sizeB = new byte[4];
                input.read(sizeB);

                final int size = ByteBuffer.wrap(sizeB).asIntBuffer().get();

                final byte[] imageB = new byte[size];
                input.read(imageB);
                final BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageB));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return images.get(file);
    }
}
