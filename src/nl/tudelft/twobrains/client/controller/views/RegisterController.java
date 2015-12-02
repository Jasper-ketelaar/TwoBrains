package nl.tudelft.twobrains.client.controller.views;

import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import nl.tudelft.twobrains.client.TwoBrains;
import nl.tudelft.twobrains.client.controller.AbstractController;
import nl.tudelft.twobrains.client.view.popup.TBPopup;
import nl.tudelft.twobrains.client.view.registreer.comp.TooltipTextField;
import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.tools.Tool;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Created by jasperketelaar on 11/25/15.
 */
public class RegisterController extends AbstractController {

    public static final String EMAIL_REGEX = "^[-a-z0-9A-Z~!$%^&*_=+}{'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-A-Z-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(com|edu|net|org|nl)|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$";
    final Pattern pattern = Pattern.compile(EMAIL_REGEX);


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
    private ComboBox geslacht;
    @FXML
    private Button upload;
    @FXML
    private Pane gegevens;

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
                    this.profileImage = ImageIO.read(file);

                    final Image toolkitImage = profileImage.getScaledInstance(165, 190, Image.SCALE_SMOOTH);
                    final BufferedImage scaled = new BufferedImage(toolkitImage.getWidth(null), toolkitImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                    final Graphics g = scaled.getGraphics();
                    g.drawImage(toolkitImage, 0, 0, null);
                    g.dispose();

                    final WritableImage wImage = null;
                    this.profielfoto.setImage(SwingFXUtils.toFXImage(scaled, wImage));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @FXML
    public void register(final ActionEvent evt) {
        final String emailText = email.getText();
        final String voornaamText = voornaam.getText();
        final String achternaamText = achternaam.getText();
        final String leeftijdText = geboorte.getValue() == null ? "" : calculateAge(geboorte.getValue());
        final String geslachtText = geslacht.getSelectionModel().getSelectedItem().toString();
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
                & validate(geboorte, leeftijdText) & validate(geslacht, geslachtText)
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final TooltipTextField email = new TooltipTextField("Test", false);
        email.getTextField().setPromptText("email@adres.com");
        gegevens.getChildren().add(new TooltipTextField("Test", false));
    }

    @Override
    public void initItems() {
        this.geslacht.getItems().clear();
        this.geslacht.getItems().addAll("Man", "Vrouw");
    }


    @FXML
    public void emailKeyTyped(final KeyEvent evt) {
        validate(email, s -> pattern.matcher(s).matches(), email.getText());
    }

    @FXML
    public void voornaamKeyPressed(final KeyEvent evt) {

    }

    @FXML
    public void achternaamKeyPressed(final KeyEvent evt) {

    }

    @FXML
    public void wachtwoordKeyPressed(final KeyEvent evt) {

    }


    @FXML
    public void locatieKeyPressed(final KeyEvent evt) {

    }

    @FXML
    public void opleidingKeyPressed(final KeyEvent evt) {

    }

    @FXML
    public void vakkenKeyPressed(final KeyEvent evt) {

    }

    public <T> void validate(final Node node, final Predicate<T> predicate, final T value) {
        final ObservableList<String> classes = node.getStyleClass();
        if (predicate.test(value)) {
           if(classes.contains("error"))
               classes.remove("error");
        } else {
            if(!classes.contains("error"))
                classes.add("error");
        }
    }


}
