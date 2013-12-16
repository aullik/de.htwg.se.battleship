package de.htwg.se.battleship.controller.commands;

import de.htwg.se.battleship.controller.INT_Commands;
import de.htwg.se.battleship.controller.INT_InputState;
import de.htwg.se.battleship.controller.InputController;
import de.htwg.se.battleship.controller.input.InitGame;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.events.ContinueEvent;

public class NewGame implements INT_Commands {

    private static final String COMMAND     = InitGame.getCommand();
    private static final String DESCRIPTION = "start a new Game";

    @Override
    public Event[] action(String[] in, INT_InputState state,
            InputController controller) {
        Event[] array = new Event[2];

        array[0] = controller.setState(new InitGame());
        if (!in[2].isEmpty()) {
            array[1] = new ContinueEvent(in[2]);
        }
        return array;
    }

    @Override
    public String getCommand() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

}
