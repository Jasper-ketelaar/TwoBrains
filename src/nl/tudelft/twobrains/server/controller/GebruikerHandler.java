package nl.tudelft.twobrains.server.controller;

import nl.tudelft.twobrains.server.Server;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.Timer;
import java.util.TimerTask;

//TODO: Javadoc comments
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
                break;

            case "IMAGE":
                sendImage(split[1]);

                break;

        }

    }

    public void sendImage(final String email) {
        try {
            final File file = getFile(email);
            final BufferedImage image = ImageIO.read(file);
            final String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length());
            System.out.println(ext);
            System.out.println("image = " + image);
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, ext, baos);

            final byte[] size = ByteBuffer.allocate(4).putInt(baos.size()).array();
            socket.getOutputStream().write(size);
            socket.getOutputStream().write(baos.toByteArray());
            socket.getOutputStream().flush();
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getFile(final String email) {
        try {
            final File file = new File(getClass().getResource("../resources/images").toURI());
            for (final File child : file.listFiles()) {
                if (child.getName().contains(email)) {
                    return child;
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
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
