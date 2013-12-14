package de.htwg.se.battleship.controller.input;

import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.events.CloseEvent;

public class Close implements InputState {

    private final static String command = "-close";

    @Override
    public Event[] processInput(InputController input, String word) {

        return null;
    }

    protected static String getCommand() {
        return command;
    }

    @Override
    public Event getEvent() {
        return new CloseEvent();
    }

}
