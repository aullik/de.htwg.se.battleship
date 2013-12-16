package de.htwg.se.battleship.controller.commands;

import de.htwg.se.battleship.controller.INTCommands;
import de.htwg.se.battleship.controller.INTInputState;
import de.htwg.se.battleship.controller.InputController;
import de.htwg.se.battleship.controller.input.InitGame;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.events.ContinueEvent;
import de.htwg.se.battleship.util.observer.events.ErrorEvent;
import de.htwg.se.battleship.util.observer.events.StandardEvent;

public class RenamePlayer implements INTCommands {

    private final int           playerNo;
    private static final String PRE_COM = "-p";
    private final InitGame      init;

    public RenamePlayer(int no, InitGame state) {
        this.playerNo = no;
        this.init = state;
    }

    @Override
    public String getCommand() {
        return PRE_COM + playerNo;
    }

    @Override
    public String getDescription() {
        return "set name of " + init.getPlayerName(playerNo) + " to argument";
    }

    @Override
    public Event[] action(String[] in, INTInputState state,
            InputController controller) {
        Event[] array = new Event[2];
        String[] input = InputController.splitInput(in[2], state.getKeySet());
        if (input[0].isEmpty()) {
            array[0] = new ErrorEvent("couldn't rename "
                    + init.getPlayerName(playerNo));
        } else {
            array[0] = new StandardEvent(init.getPlayerName(playerNo)
                    + " changed to " + input[0]);
            init.setPlayerName(playerNo, input[0]);
        }
        if (!input[1].isEmpty()) {

            array[1] = new ContinueEvent(input[1] + input[2]);
        }

        return array;
    }

}
