package nl.tudelft.twobrains.server.model.listeners.client;

/**
 * Created by jasperketelaar on 11/23/15.
 */
public class ClientEvent {

    private byte[] data;

    private final String event;
    private final String argument;

    /**
     * Class constructor specifying the type of event and the content.
     *
     * @param event The type of event.
     * @param argument The content/argument of the event.
     */
    public ClientEvent(final String event, final String argument) {
        this.event = event;
        this.argument = argument;
    }

    /**
     * Method for getting the event argument of the Clientevent.
     *
     * @return The event argument.
     */
    public String getEvent() {
        return this.event;
    }

    /**
     * Method for getting the 'argument' argument of the Clientevent.
     *
     * @return The 'argument' argument.
     */
    public String getArguments() {
        return this.argument;
    }

    public void setData(final byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return this.data;
    }
}
