package de.htwg.se.battleship.util.observer.events;

import de.htwg.se.battleship.util.observer.Event;

public class CloseEvent implements Event {

    private final String message;

    public CloseEvent() {
        message = "terminated";
    }

    @Override
    public String getMessage() {
        return message;
    }

}
