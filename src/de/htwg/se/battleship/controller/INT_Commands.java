package de.htwg.se.battleship.controller;

import de.htwg.se.battleship.util.observer.Event;

public interface INT_Commands {

    Event[] action(String[] in, INT_InputState state, InputController controller);

    String getCommand();

    String getDescription();

}
