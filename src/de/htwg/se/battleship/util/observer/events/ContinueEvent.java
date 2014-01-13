package de.htwg.se.battleship.util.observer.events;

import de.htwg.se.battleship.util.observer.Event;

/**
 * Continue Event if there is more commands in the input
 * 
 * @author aullik
 */
public class ContinueEvent implements Event {

    private final String message;

    /**
     * @param msg sets message to input
     */
    public ContinueEvent(String msg) {
        this.message = msg;

    }

    @Override
    public String getMessage() {
        return message;
    }

}
