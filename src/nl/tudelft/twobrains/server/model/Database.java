package nl.tudelft.twobrains.server.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by jasperketelaar on 11/18/15.
 */
public class Database {

    private final HashMap<String, Gebruiker> gebruikers = new HashMap<>();

    private final JSONObject database;


    public Database(final JSONObject database) {
        this.database = database;
    }

    public static Database parse(final String file) throws IOException, ParseException {
        final JSONParser parser = new JSONParser();
        final FileReader reader = new FileReader(file);

        return new Database((JSONObject) parser.parse(reader));
    }

    public Gebruiker getGebruiker(final String email) {
        if (gebruikers.containsKey(email)) {
            return gebruikers.get(email);
        } else {
            return new Gebruiker(email, (JSONObject) database.get(email));
        }
    }

    public void add(final Gebruiker gebruiker) {
        database.put(gebruiker.getEmail(), gebruiker.getJSONObject());
    }

    public void write(final String file) throws IOException {
        final FileWriter writer = new FileWriter(file);
        writer.write(database.toJSONString().replaceAll("\\{", "{\n").replaceAll("}", "}\n"));
        writer.close();
    }
}
