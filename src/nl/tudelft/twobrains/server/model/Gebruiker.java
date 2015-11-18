package nl.tudelft.twobrains.server.model;

import org.json.simple.JSONObject;

/**
 * Created by jasperketelaar on 11/18/15.
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

    public JSONObject getJSONObject() {
        return gebruiker;
    }


}
