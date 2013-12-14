package de.htwg.se.battleship.controller.input;

import de.htwg.se.battleship.util.observer.Event;

public interface InputState {

    String[] getCommands();

    Event getEvent();

    Event[] processInput(InputController controller, String line);

}
