package nl.tudelft.twobrains.server.model;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * TODO: Javadoc comments
 */
public class Gebruiker {

    private final JSONObject gebruiker;
    private final String email;

    /**
     * Class constructor specifying the email (=username) and information of
     * a user.
     *
     * @param email     The email (=username) of the user.
     * @param gebruiker The other information of the user.
     */
    public Gebruiker(final String email, final JSONObject gebruiker) {
        this.gebruiker = gebruiker;
        this.email = email;
    }

    /**
     * Method for creating a Gebruiker Object from two Strings containing
     * the email (=username) and data.
     *
     * @param email The email (=username) of the user.
     * @param data  The other information of the user.
     * @return Null, a new Gebruiker Object, if there are no parse exceptions.
     */
    public static Gebruiker parse(final String email, final String data) throws ParseException {
        final JSONParser parser = new JSONParser();

        final JSONObject obj = (JSONObject) parser.parse(data);
        return new Gebruiker(email, obj);
    }

    /**
     * Methods for getting all the different properties of the Gebruiker.
     *
     * @return A property of the Gebruiker.
     */
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

    public String getGeslacht() {
        return getAttribuut("Geslacht");
    }

    public String getLeeftijd() {
        return getAttribuut("Leeftijd");
    }

    public String getOpleiding() {
        return getAttribuut("Opleiding");
    }

    public String[] getVakken() {
        return getAttribuut("Vakken").split(",");
    }

    public String toStringVakken(String[] vakken) {
        String alleVakken = "";

        for (int i = 0; i < vakken.length; i++) {
            alleVakken = alleVakken + getVakken()[i] + ", ";
        }
        alleVakken = alleVakken.substring(0, alleVakken.length() - 2);
        return alleVakken;
    }

    public String getLocatie() {
        return getAttribuut("Locatie");
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

    /**
     * Method for converting the Gebruiker to a String.
     *
     * @return A string containg all the information of the Gebruiker.
     */
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

        builder.append(", Geslacht=");
        builder.append(getGeslacht());

        builder.append(", Leeftijd=");
        builder.append(getLeeftijd());

        builder.append(", Opleiding=");
        builder.append(getOpleiding());

        builder.append(", Vakken=");
        builder.append(toStringVakken(getVakken()));

        builder.append(", Locatie=");
        builder.append(getLocatie());

        builder.append("]");
        return builder.toString();
    }

    //TODO: Implementatie schrijven
    public boolean matches(final Gebruiker other) {
        return false;
    }


    /**
     * Equals methode
     * Kijkt of JSONobject en email gelijk zijn of niet
     *
     * @param other Het object waarmee wordt vergeleken
     * @return boolean met true als gelijk en false als niet gelijk
     */
    @Override
    public boolean equals(final Object other) {
        if (other instanceof Gebruiker) {
            final Gebruiker otherGebruiker = (Gebruiker) other;
            return otherGebruiker.getJSONString().equals(this.getJSONString()) && otherGebruiker.getEmail().equals(this.getEmail());
        }
        return false;
    }

}
