package nl.tudelft.twobrains.server.controller;

import nl.tudelft.twobrains.server.Server;
import nl.tudelft.twobrains.server.model.Database;
import nl.tudelft.twobrains.server.model.Gebruiker;
import nl.tudelft.twobrains.server.model.Match;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Created by jasperketelaar on 11/24/15.
 */
public class MatchFinder extends Thread {

    private final Database database;

    private final ArrayList<Match> matches = new ArrayList<>();


    /**
     * Class constructor specifying the database to use.
     *
     * @param database The database that is used.
     */
    public MatchFinder(final Database database) throws IOException, ParseException {
        this.database = database;

        final JSONParser parser = new JSONParser();
        final JSONArray array = (JSONArray) ((JSONObject) parser.parse(new FileReader(Server.RESOURCES + "/matches.json"))).get("matches");
        if (array != null) {
            for (final Object obj : array) {
                if (obj instanceof JSONObject) {
                    matches.add(Match.parse((JSONObject) obj));
                }
            }
        }
    }

    public MatchFinder(final Database database, final String file) throws IOException, ParseException {
        this.database = database;

        final JSONParser parser = new JSONParser();
        final JSONArray array = (JSONArray) ((JSONObject) parser.parse(new FileReader(file))).get("matches");
        if (array != null) {
            for (final Object obj : array) {
                if (obj instanceof JSONObject) {
                    matches.add(Match.parse((JSONObject) obj));
                }
            }
        }
    }

    /**
     * Method for matching a user with other users.
     */
    @Override
    public void run() {
        while (true) {
            for (final Gebruiker gebruiker : database.getAlleGebruikers()) {
                for (final Gebruiker gebruiker2 : database.filter(g -> !gebruiker.equals(g))) {
                    final int score = gebruiker.matchScore(gebruiker2);
                    if (score > 2) {
                        final Match match = new Match(gebruiker.getEmail(), gebruiker2.getEmail(), score);
                        if (!matches.contains(match)) {
                            matches.add(match);
                            System.out.println(gebruiker.getEmail() + ", " + gebruiker2.getEmail() + ", " + score);
                        }
                    }
                }
            }
        }
    }

    public Database getDatabase(){
        return this.database;
    }

    public ArrayList<Match> getMatches() {
        return this.matches;
    }

    public ArrayList<Match> getMatchesForUser(final String email) {
        final ArrayList<Match> result = new ArrayList<>();
        for (final Match match : matches) {
            if (match.getGebruiker1().equals(email) || match.getGebruiker2().equals(email)) {
                result.add(match);
            }
        }
        return result;
    }

}
