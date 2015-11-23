package nl.tudelft.twobrains.server.model.listeners.client;

/**
 * Created by jasperketelaar on 11/23/15.
 */
public class ClientEvent {

    private final String event;
    private final String argument;

    public ClientEvent(final String event, final String argument) {
        this.event = event;
        this.argument = argument;
    }

    public String getEvent() {
        return this.event;
    }

    public String getArguments() {
        return this.argument;
    }
}