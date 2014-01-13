package de.htwg.se.battleship.controller;

import de.htwg.se.battleship.util.observer.Event;

/**
 * Interface for all Commands
 * 
 * @author aullik
 */
public interface INTCommands {

    /**
     * main method for every command
     * 
     * @param in input
     * @param state class who executed this
     * @param controller to change states
     * @return array of events
     */
    Event[] action(String[] in, INTInputState state, InputController controller);

    /**
     * get open Command
     * 
     * @return Command
     */
    String getCommand();

    /**
     * get CommandDescription
     * 
     * @return despcription
     */
    String getDescription();

}
