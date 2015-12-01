package nl.tudelft.twobrains.client.controller.views;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import nl.tudelft.twobrains.client.TwoBrains;
import nl.tudelft.twobrains.client.view.popup.TBPopup;
import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;

/**
 * Created by jasperketelaar on 11/25/15.
 */
public class RegisterController {

    private final TwoBrains twoBrains;
    @FXML
    private TextField email;
    @FXML
    private PasswordField wachtwoord;
    @FXML
    private TextField voornaam;
    @FXML
    private TextField achternaam;
    @FXML
    private DatePicker geboorte;
    @FXML
    private TextField locatie;
    @FXML
    private TextField opleiding;
    @FXML
    private TextField vakken;
    @FXML
    private ImageView profielfoto;
    @FXML
    private RadioButton man;
    @FXML
    private RadioButton vrouw;
    @FXML
    private Label geslachtLabel;
    @FXML
    private Button upload;

    private BufferedImage profileImage;

    public RegisterController(final TwoBrains twoBrains) {
        this.twoBrains = twoBrains;
    }

    public void upload(final ActionEvent evt) {
        final FileChooser chooser = new FileChooser();
        chooser.setTitle("Kies een plaatje");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images (*.jpg, *.png)", "*.jpg", "*.png"));
        final File file = chooser.showOpenDialog(twoBrains.getStage());

        if (file != null) {
            if (file.length() / 1024 > 500) {
                final TBPopup popup = new TBPopup(twoBrains.getStage(), "Maximaal 500KB", 200, 100, TBPopup.WARNING);
                popup.show();
            } else {
                try {
                    final Image toolkitImage = ImageIO.read(file).getScaledInstance(165, 190, Image.SCALE_SMOOTH);
                    this.profileImage = new BufferedImage(toolkitImage.getWidth(null), toolkitImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                    final Graphics g = this.profileImage.getGraphics();
                    g.drawImage(toolkitImage, 0, 0, null);
                    g.dispose();

                    final WritableImage wImage = null;
                    this.profielfoto.setImage(SwingFXUtils.toFXImage(this.profileImage, wImage));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void register(final ActionEvent evt) {
        final String emailText = email.getText();
        final String voornaamText = voornaam.getText();
        final String achternaamText = achternaam.getText();
        final String leeftijdText = geboorte.getValue() == null ? "" : calculateAge(geboorte.getValue());
        final String geslachtText = man.isSelected() ? "M" : (vrouw.isSelected() ? "V" : "");
        final String wachtwoordText = wachtwoord.getText();
        final String locatieText = locatie.getText();
        final String opleidingText = opleiding.getText();
        final String vakkenText = vakken.getText();
        final javafx.scene.image.Image foto = this.profielfoto.getImage();

        final boolean fotoVal;
        if (foto == null) {
            upload.getStyleClass().add("error");
            fotoVal = false;
        } else {
            fotoVal = true;
            upload.getStyleClass().remove("error");
        }

        if (fotoVal & validate(email, emailText) & validate(voornaam, voornaamText) & validate(achternaam, achternaamText)
                & validate(geboorte, leeftijdText) & validate(geslachtLabel, geslachtText)
                & validate(wachtwoord, wachtwoordText) & validate(locatie, locatieText)
                & validate(opleiding, opleidingText) & validate(vakken, vakkenText)) {
            final JSONObject user = new JSONObject();
            user.put("Voornaam", voornaamText);
            user.put("Achternaam", achternaamText);
            user.put("Leeftijd", leeftijdText);
            user.put("Geslacht", geslachtText);
            user.put("Wachtwoord", wachtwoordText);
            user.put("Locatie", locatieText);
            user.put("Opleiding", opleidingText);
            user.put("Vakken", vakkenText);

            final String response = twoBrains.getSocket().register(emailText, user, this.profileImage);
            if (response.equals("Succes")) {
                twoBrains.show(twoBrains.getLoginScene());
            } else {

            }
        }

    }

    private String calculateAge(final LocalDate geboorte) {
        final Calendar birth = Calendar.getInstance();
        System.out.println(geboorte.getYear());
        birth.set(Calendar.YEAR, geboorte.getYear());
        birth.set(Calendar.MONTH, geboorte.getMonthValue());
        birth.set(Calendar.DATE, geboorte.getDayOfMonth());

        final long time = System.currentTimeMillis();
        final Calendar today = Calendar.getInstance();
        today.setTimeInMillis(time);

        int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        if (today.get(Calendar.MONTH) < birth.get(Calendar.MONTH)) {
            age--;
        } else if (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH)) {
            if (today.get(Calendar.DAY_OF_MONTH) < birth.get(Calendar.DAY_OF_MONTH)) {
                age--;
            }
        }
        return age + "";
    }

    public void annuleer(final ActionEvent evt) {
        twoBrains.show(twoBrains.getLoginScene());
    }

    private boolean validate(final Node node, final String value) {
        if (value.equals("")) {
            System.out.println(node);
            node.getStyleClass().add("error");
            return false;
        } else {
            node.getStyleClass().remove("error");
            return true;
        }
    }
}
