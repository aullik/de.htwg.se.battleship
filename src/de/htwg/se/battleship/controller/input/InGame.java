package de.htwg.se.battleship.controller.input;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import de.htwg.se.battleship.controller.INT_Commands;
import de.htwg.se.battleship.controller.INT_InputState;
import de.htwg.se.battleship.controller.InputController;
import de.htwg.se.battleship.controller.commands.GameWon;
import de.htwg.se.battleship.model.Grid;
import de.htwg.se.battleship.model.Player;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.events.ErrorEvent;

public class InGame implements INT_InputState {

    private static final String       OPENCOMMAND = "-start";
    private static final String       MENU_NAME   = "Ingame Menu";

    private Map<String, INT_Commands> commands;
    private final Event               event;
    private final Player              player1;
    private final Player              player2;
    private final int                 fieldsize;
    private final Grid                grid1;
    private final Grid                grid2;

    public InGame(int size, String playername1, String playername2) {
        this.fieldsize = size;
        this.player1 = new Player(playername1);
        this.player2 = new Player(playername2);
        grid1 = new Grid(fieldsize, player1);
        grid2 = new Grid(fieldsize, player2);
        initCommands();
        event = InputController.welcomeEvent(this);

    }

    private void initCommands() {
        commands = new TreeMap<String, INT_Commands>();
        INT_Commands com;
        com = new GameWon();
        commands.put(com.getCommand(), com);

    }

    @Override
    public Event getEvent() {
        return event;

    }

    @Override
    public Event[] processInput(InputController controller, String line) {
        Event array[];
        String[] in = InputController.splitInput(line, commands.keySet());
        array = commands.get(in[1]).action(in, this, controller);

        if (array == null) {
            array = new Event[] { new ErrorEvent("input didnt match: " + line) };
        }
        return array;
    }

    public static String getCommand() {
        return OPENCOMMAND;
    }

    @Override
    public Set<String> getKeySet() {
        return commands.keySet();
    }

    @Override
    public Set<Entry<String, INT_Commands>> getEntrySet() {
        return commands.entrySet();
    }

    @Override
    public String getMenuName() {
        return MENU_NAME;
    }

}
