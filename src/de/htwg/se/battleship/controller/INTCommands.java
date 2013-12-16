package de.htwg.se.battleship.controller;

import de.htwg.se.battleship.util.observer.Event;

public interface INTCommands {

    Event[] action(String[] in, INTInputState state, InputController controller);

    String getCommand();

    String getDescription();

}
