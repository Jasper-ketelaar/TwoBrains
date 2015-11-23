package nl.tudelft.twobrains.server.controller;

import nl.tudelft.twobrains.server.Server;
import nl.tudelft.twobrains.server.model.Gebruiker;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
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
        try {
            final String[] split = input.split(":");
            switch (split[0]) {
                case "LOGIN":
                    if (server.getDatabase().containsKey(split[1])) {
                        final Gebruiker gebruiker = server.getDatabase().get(split[1]);
                        final String wachtwoord = gebruiker.getWachtwoord();
                        if (wachtwoord.equals(split[2])) {
                            output.writeUTF("Succes:" + gebruiker.getJSONString());
                        } else {
                            output.writeUTF("Wachtwoord is verkeerd");
                        }
                    } else {
                        System.err.println("Deze user bestaat niet");
                    }
                    break;

                case "REGISTREER":
                    final String email = split[1].replaceAll("\"", "");
                    if (server.getDatabase().containsKey(email)) {
                    /*try {
                        output.writeUTF("Dit email adres is al in gebruik");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                    } else {
                        final String data = input.replace("REGISTREER:" + email + ":", "");
                        final Gebruiker gebruiker = Gebruiker.parse(email, data);
                        server.getDatabase().add(gebruiker);
                        System.out.println(gebruiker);
                    }
                    break;

                case "IMAGE":
                    sendImage(split[1]);
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendImage(final String fileName) {
        try {
            final File file = getFile(fileName);
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
            } catch (SocketException e) {
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.out.println("Client disconnected");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
