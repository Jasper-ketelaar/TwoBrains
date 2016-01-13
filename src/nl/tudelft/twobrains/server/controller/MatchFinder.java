package nl.tudelft.twobrains.server.controller;

import nl.tudelft.twobrains.server.model.Database;
import nl.tudelft.twobrains.server.model.Gebruiker;

import java.util.function.Predicate;

/**
 * Created by jasperketelaar on 11/24/15.
 */
public class MatchFinder extends Thread {

    private final Database database;

    /**
     * Class constructor specifying the database to use.
     *
     * @param database The database that is used.
     */
    public MatchFinder(final Database database) {
        this.database = database;
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
                        //TODO:
                    }
                }
            }
        }
    }
}
