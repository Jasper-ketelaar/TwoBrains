package nl.tudelft.twobrains.client.controller.views;

import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import nl.tudelft.twobrains.client.TwoBrains;
import nl.tudelft.twobrains.client.controller.AbstractController;
import nl.tudelft.twobrains.client.model.interfaces.Validation;
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
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * Created by jasperketelaar on 11/25/15.
 */
public class RegisterController extends AbstractController implements EventHandler<KeyEvent> {

    public static final String EMAIL_REGEX = "^[-a-z0-9A-Z~!$%^&*_=+}{'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-A-Z-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(com|edu|net|org|nl)|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,30}))(:[0-9]{1,5})?$";
    public static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);

    private final HashMap<String, TooltipTextField> textFields = new HashMap<>();


    private final TwoBrains twoBrains;

    @FXML
    private DatePicker geboorte;

    @FXML
    private ImageView profielfoto;

    @FXML
    private ComboBox geslacht;

    @FXML
    private Button upload;

    @FXML
    private Pane gegevens;

    private BufferedImage profileImage;

    /**
     * Hier wordt dce contructor voor de Register controller aangemaakt
     * Ook hier wordt het twobrains object meegegeven als parameter, en vervolgens geinitialiseerd
     *
     * @param twoBrains
     */
    public RegisterController(final TwoBrains twoBrains) {
        this.twoBrains = twoBrains;
    }

    /**
     * Een void methode waarin het mogelijk is om een profielfoto te uploaden
     * Krijgt een ActionEvent als parameter mee
     *
     * @param evt Methode checkt eerst of er file wordt meegegeven, (!= null)
     *            Vervolgens wordt er gecheckt of de file groter is dan 500kb, indien dit het geval krijgt de gebruiker een pop-up met een warning
     *            Als de file kleiner is dan 500kb, gaat hij proberen het bestand te lezen
     *            Als laatste wordt de foto in de userbox geplaatst dmv van scaling
     */

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


    /**
     * Void methode waarbij de gebruiker zich registreert
     * Als allereerst worden de attributen van de registercontroller class gecast naar Strings
     * Vervolgens wordt gecontroleerd met behulp van een boolean of er al een foto is geupload.
     * Indien geen foto, krijgt de gebruiker een error
     * Ook wordt de userinput gecheckt, op het moment dat dit allemaal in orde is dan wordt er nieuw final object gecreeerd
     * In het final object wordt alle userinput geinitialiseerd in het JSONobject
     * Indien het object geinitialiseerd is, krijgt de user te zien dat het gelukt is dmv de string "succes"
     */
    @FXML
    public void register(final ActionEvent evt) throws IOException {
        final String emailText = textFields.get("Email").getTextField().getText();
        final String voornaamText = textFields.get("Voornaam").getTextField().getText();
        final String achternaamText = textFields.get("Achternaam").getTextField().getText();
        final String leeftijdText = geboorte.getValue() == null ? "" : calculateAge(geboorte.getValue());
        final String geslachtText = geslacht.getSelectionModel().getSelectedItem().toString();
        final String wachtwoordText = textFields.get("Wachtwoord").getTextField().getText();
        final String locatieText = textFields.get("Locatie").getTextField().getText();
        final String opleidingText = textFields.get("Opleiding").getTextField().getText();
        final String vakkenText = textFields.get("Vakken").getTextField().getText();
        final javafx.scene.image.Image foto = this.profielfoto.getImage();

        final boolean fotoVal;
        if (foto == null) {
            upload.getStyleClass().add("error");
        } else {
            upload.getStyleClass().remove("error");
        }

        for (final TooltipTextField field : textFields.values()) {
            if (!field.getValidation().validate()) {
                return;
            }
        }

        if (leeftijdText == "" || geslachtText == "" || foto == null) {
            return;

        }
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

    /**
     * Methode waarin de leeftijd van de gebruiker wordt berekend
     *
     * @param geboorte Wordt gedaan dmv van de standaard java class Calender
     *                 Waarden worden eerst uit het attribuut de Dateset Geboorte gehaald en geinitialiseerd op Jaar/Maand/Dag
     *                 vervolgens wordt de leeftijd int gedefineerd.
     *                 Maar er moet rekening worden gehouden met de maand waarin, iemand jarig is. Want Huidige jaar - Geboortejaar werkt niet altijd
     *                 Dan een dubbel if statement om deze 'error' er uit te halen.
     *                 Als Huidige maand eerder is dan de maand waarin gebruiker jarig is ( hij moet nog jarig worden dit jaar) dan leeftijd naar beneden
     *                 Als de Huidige maand gelijk is aan de maand waarin de gebruiker jarig is (hij kán nog jarig worden) dan vergelijkt hij de dagen met elkaar
     * @return age
     */

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

    /**
     * Gebruiker krijgt de mogelijkheid om registratie te annuleren
     * Indien even annuleren geactiveerd wordt de gebruiker terug gestuurd naar loginscherm
     *
     * @param evt
     */
    public void annuleer(final ActionEvent evt) {
        twoBrains.show(twoBrains.getLoginScene());
    }

    /**
     * Een methode die automatisch wordt aangeroepen daar JavaFX,
     * De methode controlleert voor ieder userinput of de userinput correct is
     * Voor iedere ToolTip wordt gecheckt aan de voorwaarde
     * Indien een ToolTip niet in orde is staat achter de tooltip een informatiebalk
     * In de informatiebalk staat waardoor de userinput niet in orde is
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final TooltipTextField email = new TooltipTextField("Email adres; moet geldig zijn", false, 94.0, 174.0, "email@adres.com");
        email.setValidation(() -> PATTERN.matcher(email.getTextField().getText()).matches());
        email.setOnKeyReleased(this);
        textFields.put("Email", email);

        final TooltipTextField wachtwoord = new TooltipTextField("Wachtwoord; moet minimaal 6 karakters hebben waarvan " +
                "minimaal 1 hoofdletter en minimaal 1 cijfer", true, 94.0, 214.0, "Wachtwoord");
        wachtwoord.setValidation(() -> {
            final String text = wachtwoord.getTextField().getText();
            if (text.length() < 6) return false;

            boolean upperCase = false;
            boolean number = false;
            for (final char c : text.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    upperCase = true;
                } else if (Character.isDigit(c)) {
                    number = true;
                }
            }
            return upperCase && number;
        });
        wachtwoord.setOnKeyReleased(this);
        textFields.put("Wachtwoord", wachtwoord);


        final TooltipTextField voornaam = new TooltipTextField("Jouw voornaam", false, 94.0, 319, "Voornaam");
        voornaam.setOnKeyReleased(this);
        textFields.put("Voornaam", voornaam);

        final TooltipTextField achternaam = new TooltipTextField("Jouw achternaam", false, 94.0, 359, "Achternaam");
        achternaam.setOnKeyReleased(this);
        textFields.put("Achternaam", achternaam);

        final TooltipTextField locatie = new TooltipTextField("Jouw locatie", false, 94.0, 479, "Locatie");
        locatie.setOnKeyReleased(this);
        textFields.put("Locatie", locatie);

        final TooltipTextField opleiding = new TooltipTextField("Jouw opleiding", false, 94.0, 519, "Opleiding");
        opleiding.setOnKeyReleased(this);
        textFields.put("Opleiding", opleiding);


        final TooltipTextField vakken = new TooltipTextField("Jouw vakken; meerdere vakken met komma's scheiden"
                , false, 94.0, 559, "Vakken");
        vakken.setOnKeyReleased(this);
        textFields.put("Vakken", vakken);

        /*final TooltipTextField vakken = new TooltipTextField("Wachtwoord; moet minimaal 6 karakters hebben waarvan " +
                "minimaal 1 hoofdletter en minimaal 1 cijfer", false, 94.0, 559, "Wachtwoord");*/

        gegevens.getChildren().addAll(email, wachtwoord, voornaam, achternaam, locatie, opleiding, vakken);
    }

    @Override
    /**
     * De tooltipbox waarbij de gebruiker kan kiezen tussen het geslacht man, en het geslacht vrouw
     */
    public void initItems() {
        this.geslacht.getItems().clear();
        this.geslacht.getItems().addAll("Man", "Vrouw");
    }

    public void validate(final TooltipTextField node, final Validation validation) {
        final ObservableList<String> classes = node.getTextField().getStyleClass();
        if (validation.validate()) {
            if (classes.contains("error"))
                classes.remove("error");
        } else {
            if (!classes.contains("error"))
                classes.add("error");
        }
    }


    @Override
    public void handle(KeyEvent event) {

        final TooltipTextField tooltipTextField = (TooltipTextField) event.getSource();
        validate(tooltipTextField, tooltipTextField.getValidation());
    }
}
