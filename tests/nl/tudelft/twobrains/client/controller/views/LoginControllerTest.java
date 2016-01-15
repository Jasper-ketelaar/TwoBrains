package nl.tudelft.twobrains.client.controller.views;

import javafx.scene.Scene;
import nl.tudelft.twobrains.client.TwoBrains;
import nl.tudelft.twobrains.client.view.SceneWrapper;
import nl.tudelft.twobrains.server.model.listeners.client.ClientEvent;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Budi on 09/12/2015.
 */
public class LoginControllerTest {

    @Test
    public void loginControllerTest(){
        LoginController loginController = new LoginController(new TwoBrains());
    }

    @Test
    public void testLogin() throws Exception {
        TwoBrains twoBrains = new TwoBrains();
        LoginController loginController = new LoginController(twoBrains);
        //twoBrains.getLoginScene().

    }

    @Test
    public void testRegister() throws Exception {

    }

    @Test
    public void testInitialize() throws Exception {

    }
}