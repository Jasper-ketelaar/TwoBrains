package nl.tudelft.twobrains.server.model;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by jasperketelaar on 11/18/15.
 */
public class Database {


    public static Database parse(final String file) throws FileNotFoundException {
        final JSONParser parser = new JSONParser();
        final FileReader reader = new FileReader(file);
        try {
            final JSONArray data = (JSONArray) parser.parse(reader);


            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return null;
    }
}
