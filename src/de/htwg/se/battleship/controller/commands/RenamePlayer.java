package de.htwg.se.battleship.controller.commands;

import de.htwg.se.battleship.controller.INT_Commands;
import de.htwg.se.battleship.controller.INT_InputState;
import de.htwg.se.battleship.controller.InputController;
import de.htwg.se.battleship.controller.input.InitGame;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.events.ContinueEvent;
import de.htwg.se.battleship.util.observer.events.ErrorEvent;
import de.htwg.se.battleship.util.observer.events.StandardEvent;

public class RenamePlayer implements INT_Commands {

    private final int           player_no;
    private static final String PRE_COM = "-p";
    private final InitGame      init;

    public RenamePlayer(int no, InitGame state) {
        this.player_no = no;
        this.init = state;
    }

    @Override
    public String getCommand() {
        return PRE_COM + player_no;
    }

    @Override
    public String getDescription() {// set name of player to argument
        return "set name of " + init.getPlayerName(player_no) + " to argument";
    }

    @Override
    public Event[] action(String[] in, INT_InputState state,
            InputController controller) {
        Event[] array = new Event[2];
        String[] input = InputController.splitInput(in[2], state.getKeySet());
        if (input[0].isEmpty()) {
            array[0] = new ErrorEvent("couldn't rename "
                    + init.getPlayerName(player_no));
        } else {
            array[0] = new StandardEvent(init.getPlayerName(player_no)
                    + " changed to " + input[0]);
            init.setPlayerName(player_no, input[0]);
        }
        if (!input[1].isEmpty()) {

            array[1] = new ContinueEvent(input[1] + input[2]);
        }

        return array;
    }

}
