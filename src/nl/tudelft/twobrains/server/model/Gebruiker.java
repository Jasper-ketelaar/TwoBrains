package nl.tudelft.twobrains.server.model;

import org.json.simple.JSONObject;

import java.text.ParseException;
import java.util.InputMismatchException;

/**
 * TODO: Voor alle attributen een getter maken.
 */
public class Gebruiker {

    private final JSONObject gebruiker;
    private final String email;


    public Gebruiker(final String email, final JSONObject gebruiker) {
        this.gebruiker = gebruiker;
        this.email = email;
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

    private String getAttribuut(final String attribuut) {
        return gebruiker.get(attribuut).toString();
    }

    protected JSONObject getJSONObject() {
        return gebruiker;
    }

    public static Gebruiker parse(final Object[] data) {
        if (data.length != 10) {
            throw new InputMismatchException("Een gebruiker heeft 10 attributen nodig");
        } else {
            final String email = data[0].toString();
            
            final JSONObject gebruiker = new JSONObject();
            gebruiker.put("Voornaam", data[1]);
            gebruiker.put("Achternaam", data[2]);
            gebruiker.put("Geslacht", data[3]);
            gebruiker.put("Leeftijd", data[4]);
            gebruiker.put("Wachtwoord", data[5]);
            gebruiker.put("Opleiding", data[6]);
            gebruiker.put("Vakken", data[7]);
            gebruiker.put("Locatie", data[8]);
            gebruiker.put("Foto", data[9]);

            return new Gebruiker(email, gebruiker);
        }
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
