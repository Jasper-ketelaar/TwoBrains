package nl.tudelft.twobrains.server.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Database extends HashMap<String, Gebruiker> {

    private final JSONObject database;

    /**
     * Class constructer specifying the JSON databuse to use. The JSON database
     * is read. Users are created and added to the 'other' database.
     *
     * @param database A JSON object containing all the database information.
     */
    public Database(final JSONObject database) {
        this.database = database;
        for (final Object key : database.keySet()) {
            final Gebruiker gebruiker = new Gebruiker(key.toString(), (JSONObject) database.get(key));
            this.put(key.toString(), gebruiker);
        }
    }

    /**
     * Method for creating a database from a File. A parser and a reader
     * are created. The File is checked for containing information. If the
     * File is empty an empty database is created.
     *
     * @param file The name of the File where the database is created from.
     * @return A database containing all the information read from the file.
     * @throws IOException Checks if the File with name 'file' exists.
     * @throws ParseException Checks if the JSON parser reached an error.
     */
    public static Database parse(final String file) throws IOException, ParseException {
        final JSONParser parser = new JSONParser();
        final File f = new File(file);
        final FileReader reader = new FileReader(f);
        if(f.length() == 0) {
            return new Database(new JSONObject());
        }
        return new Database((JSONObject) parser.parse(reader));
    }

    /**
     * Method for getting an array with all the users.
     *
     * @return An array containing all the users.
     */
    public Gebruiker[] getAlleGebruikers() {
        final Collection<Gebruiker> gebruikers = this.values();
        return gebruikers.toArray(new Gebruiker[gebruikers.size()]);
    }

    /**
     * Method for adding a user to the database
     *
     * @param gebruiker A user that is added to the database.
     */
    public void add(final Gebruiker gebruiker) {
        this.put(gebruiker.getEmail(), gebruiker);
    }

    /**
     * Method for writing database to a File.
     *
     * @param file The name of the File that the database is written to.
     * @throws IOException
     */
    public void write(final String file) throws IOException {
        final FileWriter writer = new FileWriter(file);
        //Need to cover this branch in Test --> Jasper
        if (database.size() < this.size()) {
            for (final Object key : this.keySet()) {
                database.put(key, this.get(key).getJSONObject());
            }
        }
        writer.write(database.toJSONString().replaceAll("\\{", "{\n").replaceAll("}", "}\n"));
        writer.close();
    }

    /**
     * Method for filtering users with a predicate. Create an empty array
     * and add the users that satisfy the predicate.
     *
     * @param predicate A predicate the users are filtered with.
     * @return An array containing all the filtered users.
     */
    public ArrayList<Gebruiker> filter(final Predicate<Gebruiker> predicate) {
        final ArrayList<Gebruiker> gebruikers = new ArrayList<>();
        final Gebruiker[] concGebruikers = this.values().toArray(new Gebruiker[this.values().size()]);

        for (Gebruiker g : concGebruikers) {
            if (predicate.test(g)) {
                gebruikers.add(g);
            }
        }
        return gebruikers;
    }

    /**
     * Method for converting the database to a String.
     *
     * @return A string of the database.
     */
    @Override
    public String toString() {
        return super.toString().replace("{", "Database[").replace("}", "]");
    }
}
