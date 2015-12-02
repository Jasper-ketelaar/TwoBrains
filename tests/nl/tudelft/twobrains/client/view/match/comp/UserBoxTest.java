package nl.tudelft.twobrains.client.view.match.comp;

import nl.tudelft.twobrains.client.model.Gebruiker;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Leroy on 2-12-2015.
 */

//TODO: Image error
public class UserBoxTest {
    JSONObject testJSONObject = new JSONObject();


    Gebruiker testGebruiker = new Gebruiker("test@email.com", testJSONObject);

    UserBox testUserBox = new UserBox(testGebruiker);

    @Test
    public void getGebruiker(){
        assertEquals(testGebruiker, testUserBox.getGebruiker());
    }
}
