package de.htwg.se.battleship.util.observer.events;

import de.htwg.se.battleship.util.observer.Event;

/**
 * Close Event, tells observers to Close the game
 * 
 * @author aullik
 */
public class CloseEvent implements Event {

    private static final String MESSAGE = "terminated";

    @Override
    public String getMessage() {
        return MESSAGE;
    }

}
