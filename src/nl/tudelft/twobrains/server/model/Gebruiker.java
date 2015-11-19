package nl.tudelft.twobrains.server.model;

import org.json.simple.JSONObject;

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
