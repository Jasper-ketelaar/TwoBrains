package nl.tudelft.twobrains.server.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jasperketelaar on 11/18/15.
 */
public class Database {

    private final ArrayList<Gebruiker> gebruikers;

    public Database(final ArrayList<Gebruiker> gebruikers) {
        this.gebruikers = gebruikers;
    }

    public static Database parse(final String file) throws FileNotFoundException {
        final JSONParser parser = new JSONParser();
        final FileReader reader = new FileReader(file);
        final ArrayList<Gebruiker> gebruikers = new ArrayList<>();
        try {
            final JSONObject obj = (JSONObject) parser.parse(reader);
            for (final Object key : obj.keySet()) {
                gebruikers.add(Gebruiker.parse((JSONObject) obj.get(key), (String) key));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return null;
    }
}
