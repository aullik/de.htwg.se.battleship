package de.htwg.se.battleship.controller;

import java.util.Map.Entry;
import java.util.Set;

import de.htwg.se.battleship.util.observer.Event;

public interface INT_InputState {

    Set<String> getKeySet();

    Set<Entry<String, INT_Commands>> getEntrySet();

    Event getEvent();

    Event[] processInput(InputController controller, String line);

    String getMenuName();

}
