package de.htwg.se.battleship.util.observer.events;

import de.htwg.se.battleship.util.observer.Event;

/**
 * Standard event with an outputMessage
 * 
 * @author aullik
 */
public class StandardEvent implements Event {

    private final String message;

    /**
     * @param msg sets message
     */
    public StandardEvent(String msg) {
        this.message = msg;

    }

    @Override
    public String getMessage() {
        return message;
    }

}
