package de.htwg.se.battleship.controller.input;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import de.htwg.se.battleship.controller.INTCommands;
import de.htwg.se.battleship.controller.INTInputState;
import de.htwg.se.battleship.controller.InputController;
import de.htwg.se.battleship.controller.commands.GameWon;
import de.htwg.se.battleship.model.Grid;
import de.htwg.se.battleship.model.Player;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.events.ErrorEvent;

/**
 * Gamestate InGame
 * 
 * @author aullik
 */
public class InGame implements INTInputState {

    private static final String      OPENCOMMAND = "-start";
    private static final String      MENU_NAME   = "Ingame Menu";

    private Map<String, INTCommands> commands;
    private final Event              event;
    private final Player             player1;
    private final Player             player2;
    private final int                fieldsize;
    private final Grid               grid1;
    private final Grid               grid2;

    /**
     * @param size fieldsize
     * @param playername1 name for Player1
     * @param playername2 name for Player2
     */
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
        commands = new TreeMap<String, INTCommands>();
        INTCommands com;
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

    /**
     * get open command
     * 
     * @return command to open this State
     */
    public static String getCommand() {
        return OPENCOMMAND;
    }

    @Override
    public Set<String> getKeySet() {
        return commands.keySet();
    }

    @Override
    public Set<Entry<String, INTCommands>> getEntrySet() {
        return commands.entrySet();
    }

    @Override
    public String getMenuName() {
        return MENU_NAME;
    }

}
