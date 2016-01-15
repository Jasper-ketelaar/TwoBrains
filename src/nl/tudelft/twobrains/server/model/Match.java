package nl.tudelft.twobrains.server.model;

import org.json.simple.JSONObject;

/**
 * Created by jasperketelaar on 1/15/16.
 */
public class Match {

    private final String gebruiker1;
    private final String gebruiker2;
    private final double score;

    public Match(final String gebruiker1, final String gebruiker2, final double score) {
        this.gebruiker1 = gebruiker1;
        this.gebruiker2 = gebruiker2;
        this.score = score;
    }

    public static Match parse(final JSONObject object) {
        return new Match(object.get("user1").toString(), object.get("user2").toString(), Double.parseDouble(object.get("score").toString()));
    }

    @Override
    public boolean equals(final Object other) {
        if (other instanceof Match) {
            final Match otherMatch = (Match) other;
            return ((this.gebruiker1.equals(otherMatch.gebruiker1) && this.gebruiker2.equals(otherMatch.gebruiker2))
                    || (this.gebruiker2.equals(otherMatch.gebruiker1) && this.gebruiker1.equals(otherMatch.gebruiker2)))
                    && otherMatch.score == this.score;
        }
        return false;
    }

    public String getGebruiker1() {
        return gebruiker1;
    }

    public String getGebruiker2() {
        return gebruiker2;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return gebruiker1 + ":" + gebruiker2 + ":" + score;
    }
}
