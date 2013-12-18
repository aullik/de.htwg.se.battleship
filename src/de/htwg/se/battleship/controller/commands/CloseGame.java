package de.htwg.se.battleship.controller.commands;

import de.htwg.se.battleship.controller.INTCommands;
import de.htwg.se.battleship.controller.INTInputState;
import de.htwg.se.battleship.controller.InputController;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.events.CloseEvent;

/**
 * Closes instance of Game
 * 
 * @author aullik
 */
public class CloseGame implements INTCommands {

    private static final String COMMAND     = "-close";
    private static final String DESCRIPTION = "terminates the instance";

    @Override
    public String getCommand() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public Event[] action(String[] in, INTInputState state,
            InputController controller) {

        return new Event[] { new CloseEvent(), null };
    }

}
