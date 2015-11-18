package nl.tudelft.twobrains.server.model;

import org.json.simple.JSONObject;

/**
 * Created by jasperketelaar on 11/18/15.
 */
public class Gebruiker {

    private final String voornaam;
    private final String achternaam;
    private final String email;
    private final String wacthwoord;
    private final String leeftijd;
    private final String geslacht;
    private final String foto;
    private final String locatie;
    private final String[] vakken;
    private final String opleiding;

    public Gebruiker(final String voornaam, final String achternaam, final String email, final String wachtwoord, final String leeftijd,
                     final String geslacht, final String foto, final String locatie, final String[] vakken, final String opleiding) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.email = email;
        this.wacthwoord = wachtwoord;
        this.leeftijd = leeftijd;
        this.geslacht = geslacht;
        this.foto = foto;
        this.locatie = locatie;
        this.vakken = vakken;
        this.opleiding = opleiding;
    }

    //TODO: Write implementation
    public static Gebruiker parse(final JSONObject object, final String email;) {
        return null;
    }
}
