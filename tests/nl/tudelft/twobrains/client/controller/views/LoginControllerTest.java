package nl.tudelft.twobrains.client.controller.views;

import javafx.event.ActionEvent;
import nl.tudelft.twobrains.client.TwoBrains;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Budi on 09/12/2015.
 */
public class LoginControllerTest {

    LoginController loginController = new LoginController(new TwoBrains());

    @Test
    public void testLogin() throws Exception {
        loginController.login(new ActionEvent());

    }

    @Test
    public void testRegister() throws Exception {

    }

    @Test
    public void testInitialize() throws Exception {

    }
}