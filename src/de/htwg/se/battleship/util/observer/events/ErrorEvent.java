package de.htwg.se.battleship.util.observer.events;

import de.htwg.se.battleship.util.observer.Event;

public class ErrorEvent implements Event {

    private final String message;

    public ErrorEvent(String msg) {
        this.message = msg;

    }

    @Override
    public String getMessage() {
        return message;
    }

}
