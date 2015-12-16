package nl.tudelft.twobrains.client.model.interfaces;

/**
 * Created by jasperketelaar on 12/7/15.
 */
public interface Validation {

    /**
     * Interface die aangeeft dat er een validate methode
     * moet worden geimplementeerd.
     *
     * @return true/false, true als goed gevalideerd
     * false als niet.
     */
    boolean validate();

}
