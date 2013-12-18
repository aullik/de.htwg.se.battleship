package de.htwg.se.battleship.controller.commands;

import de.htwg.se.battleship.controller.INTCommands;
import de.htwg.se.battleship.controller.INTInputState;
import de.htwg.se.battleship.controller.InputController;
import de.htwg.se.battleship.controller.input.InitGame;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.events.ContinueEvent;
import de.htwg.se.battleship.util.observer.events.ErrorEvent;
import de.htwg.se.battleship.util.observer.events.StandardEvent;

/**
 * chagne the fieldsize
 * 
 * @author aullik
 */
public class ChangeFieldsize implements INTCommands {

    private static final String COMMAND     = "-field";
    private static final String DESCRIPTION = "set fieldsize to argument, currently: ";
    private final InitGame      init;

    /**
     * @param init State in which to change size
     */
    public ChangeFieldsize(InitGame init) {
        this.init = init;
    }

    @Override
    public String getCommand() {
        return COMMAND;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION + init.getFieldSize();
    }

    @Override
    public Event[] action(String[] in, INTInputState state,
            InputController controller) {
        Event[] array = new Event[2];
        String[] input = InputController.splitInput(in[2], state.getKeySet());
        if (input[0].isEmpty()) {
            array[0] = new ErrorEvent("couldn't change fieldsize, currently: "
                    + init.getFieldSize());
        } else if (input[0].matches("[0-9]+$")) {
            init.setFieldSize(Integer.parseInt(input[0]));
            array[0] = new StandardEvent("changed fieldsize to "
                    + init.getFieldSize());
        } else {
            array[0] = new ErrorEvent(input[0] + " is no number");
        }
        if (input[1] != null && !input[1].isEmpty()) {
            array[1] = new ContinueEvent(input[1] + input[2]);
        }
        return array;
    }

}
