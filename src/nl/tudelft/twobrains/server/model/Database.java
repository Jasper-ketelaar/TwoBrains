package nl.tudelft.twobrains.server.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.function.Predicate;

public class Database extends HashMap<String, Gebruiker> {

    private final JSONObject database;

    public Database(final JSONObject database) {
        this.database = database;
        for (final Object key : database.keySet()) {
            final Gebruiker gebruiker = new Gebruiker(key.toString(), (JSONObject) database.get(key));
            this.put(key.toString(), gebruiker);
        }
    }

    public static Database parse(final String file) throws IOException, ParseException {
        final JSONParser parser = new JSONParser();
        final FileReader reader = new FileReader(file);
        return new Database((JSONObject) parser.parse(reader));
    }

    public Gebruiker[] getAlleGebruikers() {
        final Collection<Gebruiker> gebruikers = this.values();
        return gebruikers.toArray(new Gebruiker[gebruikers.size()]);
    }

    public void add(final Gebruiker gebruiker) {
        this.put(gebruiker.getEmail(), gebruiker);
    }

    public void write(final String file) throws IOException {
        final FileWriter writer = new FileWriter(file);
        if (database.size() < this.size()) {
            for (final Object key : this.keySet()) {
                database.put(key, this.get(key).getJSONObject());
            }
        }
        writer.write(database.toJSONString().replaceAll("\\{", "{\n").replaceAll("}", "}\n"));
        writer.close();
    }

    public ArrayList<Gebruiker> filter(final Predicate<Gebruiker> predicate) {
        final ArrayList<Gebruiker> gebruikers = new ArrayList<>();
        for (final Gebruiker gebruiker : this.values()) {
            if (predicate.test(gebruiker)) {
                gebruikers.add(gebruiker);
            }
        }
        return gebruikers;
    }


    @Override
    public String toString() {
        return super.toString().replace("{", "Database[").replace("}", "]");
    }
}
