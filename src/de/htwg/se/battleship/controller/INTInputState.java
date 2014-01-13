package de.htwg.se.battleship.controller;

import java.util.Map.Entry;
import java.util.Set;

import de.htwg.se.battleship.util.observer.Event;

/**
 * Interface for different controller states
 * 
 * @author aullik
 */

public interface INTInputState {

    /**
     * get KeySet of possible Commands in this State
     * 
     * @return KeySet of possible Commands
     */
    Set<String> getKeySet();

    /**
     * get EntrySet of possible Commands in this State
     * 
     * @return EntrySet of possible Commands
     */
    Set<Entry<String, INTCommands>> getEntrySet();

    /**
     * Standard event of Class, contains Standard message
     * 
     * @return Standard event of Class, contains Standard message
     */
    Event getEvent();

    /**
     * Processes input, checks for commands and executes them
     * 
     * @param controller: Controller in which the currentStates is saved
     * @param line: input line
     * @return array of Events
     */
    Event[] processInput(InputController controller, String line);

    /**
     * String with topic for current Menu
     * 
     * @return String with topic for current Menu
     */
    String getMenuName();

}
