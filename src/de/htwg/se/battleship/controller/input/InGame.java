package de.htwg.se.battleship.controller.input;

import de.htwg.se.battleship.model.Grid;
import de.htwg.se.battleship.model.Player;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.events.CloseEvent;
import de.htwg.se.battleship.util.observer.events.ErrorEvent;
import de.htwg.se.battleship.util.observer.events.StandardEvent;

public class InGame implements InputState {

    private final static String opencommand = "-start";

    private final String[]      command;
    private final Event         event;
    private final Player        player1;
    private final Player        player2;
    private final int           fieldsize;
    private final Grid          grid1;
    private final Grid          grid2;

    protected InGame(int size, String playername1, String playername2) {
        this.fieldsize = size;
        this.player1 = new Player(playername1);
        this.player2 = new Player(playername2);
        grid1 = new Grid(fieldsize, player1);
        grid2 = new Grid(fieldsize, player2);
        command = new String[] { "-win" };
        event = new StandardEvent("Type:" + "\n -win to win");

    }

    @Override
    public Event getEvent() {
        return event;

    }

    @Override
    public Event[] processInput(InputController controller, String line) {
        Event array[] = new Event[2];
        String[] in = controller.splitInput(line, command);

        if (in[1].equals(command[0])) {
            array[0] = new StandardEvent("you won!");
            array[1] = new CloseEvent();
        } else {
            array[0] = new ErrorEvent("input didnt match: " + line);
        }

        return array;
    }

    protected static String getCommand() {
        return opencommand;
    }

}
