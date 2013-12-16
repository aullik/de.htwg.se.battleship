package de.htwg.se.battleship.controller.commands;

import de.htwg.se.battleship.controller.INT_Commands;
import de.htwg.se.battleship.controller.INT_InputState;
import de.htwg.se.battleship.controller.InputController;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.events.CloseEvent;

public class CloseGame implements INT_Commands {

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
    public Event[] action(String[] in, INT_InputState state,
            InputController controller) {

        return new Event[] { new CloseEvent() };
    }

}
