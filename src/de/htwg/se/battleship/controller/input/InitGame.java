package de.htwg.se.battleship.controller.input;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import de.htwg.se.battleship.controller.INTCommands;
import de.htwg.se.battleship.controller.INTInputState;
import de.htwg.se.battleship.controller.InputController;
import de.htwg.se.battleship.controller.commands.ChangeFieldsize;
import de.htwg.se.battleship.controller.commands.RenamePlayer;
import de.htwg.se.battleship.controller.commands.StartGame;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.events.ErrorEvent;

/**
 * Gamestate Initiate Game
 * 
 * @author aullik
 */
public class InitGame implements INTInputState {

    private static final String      OPENCOMMAND  = "-newgame";
    private static final String      MENU_NAME    = "Pre-Game Menu";
    private static final int         STDFIELDSIZE = 10;
    private static final int         PLAYER1      = 1;
    private static final int         PLAYER2      = 2;
    private final Event              event;
    private String[]                 player;
    private int                      fieldsize;

    private Map<String, INTCommands> commands;

    /**
     * sets Standard values
     */
    public InitGame() {
        player = new String[] { "Player 1", "Player 2" };
        fieldsize = STDFIELDSIZE;
        initCommands();

        event = InputController.welcomeEvent(this);

    }

    private void initCommands() {
        commands = new TreeMap<String, INTCommands>();
        INTCommands com;
        com = new RenamePlayer(PLAYER1, this);
        commands.put(com.getCommand(), com);
        com = new RenamePlayer(PLAYER2, this);
        commands.put(com.getCommand(), com);
        com = new ChangeFieldsize(this);
        commands.put(com.getCommand(), com);
        com = new StartGame(this);
        commands.put(com.getCommand(), com);
    }

    @Override
    public Event[] processInput(InputController controller, String line) {
        Event[] array;
        String[] in = InputController.splitInput(line, this);
        try {
            array = commands.get(in[1]).action(in, this, controller);
        } catch (NullPointerException n) {
            array = new Event[] { new ErrorEvent("input didnt match: " + line) };
        }

        return array;
    }

    /**
     * return command to open;
     * 
     * @return Opencommand
     */
    public static String getCommand() {
        return OPENCOMMAND;
    }

    @Override
    public Event getEvent() {
        return event;
    }

    /**
     * returns the current player name of the player with the specified no.
     * 
     * @param playerNo specified no.
     * @return current player name
     */
    public String getPlayerName(int playerNo) {
        if (playerNo > player.length || playerNo <= 0) {
            throw new IndexOutOfBoundsException();
        }
        return player[playerNo - 1];
    }

    /**
     * set name of player with specific number to 'name'
     * 
     * @param playerNo specific number for player
     * @param name new name
     */
    public void setPlayerName(int playerNo, String name) {
        if (playerNo > player.length || playerNo <= 0) {
            throw new IndexOutOfBoundsException();
        }
        player[playerNo - 1] = name;
    }

    /**
     * return fieldsize for the gamefield
     * 
     * @return fieldsize
     */
    public int getFieldSize() {
        return fieldsize;
    }

    /**
     * set fieldsize
     * 
     * @param size, size to set the field to
     */
    public void setFieldSize(int size) {
        if (size < 1) {
            throw new IllegalArgumentException();
        }
        this.fieldsize = size;
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
