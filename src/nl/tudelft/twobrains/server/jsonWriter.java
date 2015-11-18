package nl.tudelft.twobrains.server;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Leroy on 18-11-2015.
 */
public class JSONWriter {


    String databaseLoc = "resources/database.json";


    JSONObject Attributen;
    JSONObject Eigenaar;
    JSONArray userArray;


    String email;

    String voornaam;
    String achternaam;
    String geslacht;
    String leeftijd;
    String wachtwoord;
    String opleiding;
    String vakken;
    String locatie;
    String foto;


    public JSONWriter(String eMail, String Surname, String Familyname, String Gender,
                      String Age, String Password, String Study, String Course, String Location, String Picture ) {
        Attributen = new JSONObject();
        Eigenaar = new JSONObject();
        email = eMail;
        voornaam = Surname;
        achternaam = Familyname;
        geslacht = Gender;
        leeftijd = Age;
        wachtwoord = Password;
        opleiding = Study;
        vakken = Course;
        locatie = Location;
        foto = Picture;

        JSONObject jsonObject = new JSONObject();

        Attributen.put("Voornaam:", voornaam);
        Attributen.put("Achternaam:", achternaam);
        Attributen.put("Geslacht:", geslacht);
        Attributen.put("Leeftijd:", leeftijd);
        Attributen.put("Wachtwoord:", wachtwoord);
        Attributen.put("Opleiding:", opleiding);
        Attributen.put("Vakken:", vakken);
        Attributen.put("Locatie:", locatie);
        Attributen.put("Foto:", foto);

        Eigenaar.put(email + ':', Attributen);




        final JSONParser parser = new JSONParser();

        try {
            FileReader jsonReader = new FileReader(databaseLoc);
            JSONObject userObject = (JSONObject) parser.parse(jsonReader);
            userArray = (JSONArray)userObject.get("user");
            userArray.add(userArray.size(), Eigenaar);
            System.out.println(userArray.toJSONString());



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            JSONObject writeObject = new JSONObject();

            FileWriter jsonWriter = new FileWriter(databaseLoc);
            writeObject.put("user" , userArray);
            System.out.print(writeObject.toJSONString());
            jsonWriter.write(writeObject.toJSONString());
            jsonWriter.flush();
            jsonWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static void main (String[] args) {
    new JSONWriter("leroyvelzel@gmail.com", "Leroy", "Velzel", "M", "19", "123456", "Geneeskunde", "Penetreren", "Amsterdanm", "bkabka.jpg" );

    }}













