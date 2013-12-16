package de.htwg.se.battleship.controller.commands;

import de.htwg.se.battleship.controller.INTCommands;
import de.htwg.se.battleship.controller.INTInputState;
import de.htwg.se.battleship.controller.InputController;
import de.htwg.se.battleship.controller.input.InGame;
import de.htwg.se.battleship.controller.input.InitGame;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.events.ContinueEvent;

public class StartGame implements INTCommands {

    private final InitGame      init;
    private static final String COMMAND     = InGame.getCommand();
    private static final String DESCRIPTION = "starts the game";
    private static final int    PLAYER1     = 1;
    private static final int    PLAYER2     = 2;

    public StartGame(InitGame init) {

        this.init = init;
    }

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
        Event[] array = new Event[2];

        array[0] = controller.setState(new InGame(init.getFieldSize(), init
                .getPlayerName(PLAYER1), init.getPlayerName(PLAYER2)));
        if (!in[2].isEmpty()) {
            array[1] = new ContinueEvent(in[2]);
        }

        return array;
    }

}
