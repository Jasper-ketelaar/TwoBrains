package nl.tudelft.twobrains.server;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Leroy on 18-11-2015.
 */
public class jsonWriter {


    public static void main (String[] args){

        String databaseLoc = "resources/database.json";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Voornaam:", "Leroy ");
        jsonObject.put("Achternaam:", "Velzel");
        jsonObject.put("Geslacht:", "M");
        jsonObject.put("Leeftijd:", "19");
        jsonObject.put("Wachtwoord:", "123456");
        jsonObject.put("Opleiding:", "Geneeskunde");
        jsonObject.put("Vakken:", "Penetratie");
        jsonObject.put("Locatie:", "Amsterdam");
        jsonObject.put("Foto:", "example.jpg");

        try{
            FileWriter jsonFileWriter = new FileWriter(databaseLoc);
            jsonFileWriter.write(jsonObject.toString());
            jsonFileWriter.flush();
            jsonFileWriter.close();
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println(jsonObject.toJSONString());
            System.out.println(jsonObject.toString());

        }

    }





}
