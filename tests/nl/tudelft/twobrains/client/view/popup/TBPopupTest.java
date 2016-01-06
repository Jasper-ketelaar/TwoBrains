package nl.tudelft.twobrains.client.view.popup;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nl.tudelft.twobrains.client.view.popup.TBPopup;
import org.junit.Test;

/**
 * Created by Leroy on 2-12-2015.
 */

//TODO: weet niet of hier een test voor geschreven moet worden
public class TBPopupTest {
    Stage testStage = new Stage();
    TBPopup testTBPopup = new TBPopup(testStage, "Warning", 400, 400, 3);


//    @Test
//    public void testWarning() {
//
//        TBPopup testTBPopup = new TBPopup(testStage, "Warning", 400, 400, 0);
//        testTBPopup.decodeType(0).equals(Color.RED);  //private methode, public maken?
//
//    }
}
