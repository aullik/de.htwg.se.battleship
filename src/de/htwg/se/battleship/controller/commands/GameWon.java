package de.htwg.se.battleship.controller.commands;

import de.htwg.se.battleship.controller.INTCommands;
import de.htwg.se.battleship.controller.INTInputState;
import de.htwg.se.battleship.controller.InputController;
import de.htwg.se.battleship.controller.input.MainMenu;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.events.StandardEvent;

/**
 * command for GameWon, sets State to MainMenu
 * 
 * @author aullik
 */
public class GameWon implements INTCommands {

    private static final String COMMAND     = "-win";
    private static final String DESCRIPTION = "win the game";

    @Override
    public Event[] action(String[] in, INTInputState state,
            InputController controller) {
        Event array[] = new Event[2];
        array[0] = new StandardEvent("you won!");
        array[1] = controller.setState(new MainMenu());

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
