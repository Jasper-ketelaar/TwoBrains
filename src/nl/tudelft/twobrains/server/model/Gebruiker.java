package nl.tudelft.twobrains.server.model;


import javafx.scene.Parent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.InputMismatchException;

/**
 * TODO: Voor alle attributen een getter maken.
 * TODO: Javadoc comments
 */
public class Gebruiker {

    private final JSONObject gebruiker;
    private final String email;


    public Gebruiker(final String email, final JSONObject gebruiker) {
        this.gebruiker = gebruiker;
        this.email = email;
    }

    public static Gebruiker parse(final String email, final String data) {
        final JSONParser parser = new JSONParser();
        try {
            final JSONObject obj = (JSONObject) parser.parse(data);
            return new Gebruiker(email, obj);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getEmail() {
        return email;
    }

    public String getVoornaam() {
        return getAttribuut("Voornaam");
    }

    public String getAchternaam() {
        return getAttribuut("Achternaam");
    }

    public String getWachtwoord() {
        return getAttribuut("Wachtwoord");
    }

    public String getJSONString() {
        return getJSONObject().toJSONString();
    }

    private String getAttribuut(final String attribuut) {
        return gebruiker.get(attribuut).toString();
    }

    protected JSONObject getJSONObject() {
        return gebruiker;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("Gebruiker[Voornaam=");
        builder.append(getVoornaam());

        builder.append(", Achternaam=");
        builder.append(getAchternaam());

        builder.append(", E-mail=");
        builder.append(getEmail());

        builder.append(", Wachtwoord=");
        builder.append(getWachtwoord());

        builder.append("]");
        return builder.toString();
    }

}
