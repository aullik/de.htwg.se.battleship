package de.htwg.se.battleship.util.observer.events;

import de.htwg.se.battleship.util.observer.Event;

public class ContinueEvent implements Event {

    private final String message;

    public ContinueEvent(String msg) {
        this.message = msg;

    }

    @Override
    public String getMessage() {
        return message;
    }

}
