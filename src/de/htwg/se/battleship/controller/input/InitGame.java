package de.htwg.se.battleship.controller.input;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import de.htwg.se.battleship.controller.INT_Commands;
import de.htwg.se.battleship.controller.INT_InputState;
import de.htwg.se.battleship.controller.InputController;
import de.htwg.se.battleship.controller.commands.ChangeFieldsize;
import de.htwg.se.battleship.controller.commands.RenamePlayer;
import de.htwg.se.battleship.controller.commands.StartGame;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.events.ErrorEvent;

public class InitGame implements INT_InputState {

    private static final String       OPENCOMMAND  = "-newgame";
    private static final String       MENU_NAME    = "Pre-Game Menu";
    private static final int          STDFIELDSIZE = 10;
    private static final int          PLAYER1      = 1;
    private static final int          PLAYER2      = 2;
    private final Event               event;
    private String[]                  player;
    private int                       fieldsize;

    private Map<String, INT_Commands> commands;

    public InitGame() {
        player = new String[] { "Player 1", "Player 2" };
        fieldsize = STDFIELDSIZE;
        initCommands();

        event = InputController.welcomeEvent(this);

    }

    private void initCommands() {
        commands = new TreeMap<String, INT_Commands>();
        INT_Commands com;
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
    public Event getEvent() {
        return event;
    }

    public String getPlayerName(int player_no) {
        if (player_no > player.length || player_no <= 0)
            throw new IndexOutOfBoundsException();
        return player[player_no - 1];
    }

    public void setPlayerName(int player_no, String name) {
        if (player_no > player.length || player_no <= 0)
            throw new IndexOutOfBoundsException();
        player[player_no - 1] = name;
    }

    public int getFieldSize() {
        return fieldsize;
    }

    public void setFieldSize(int size) {
        if (size < 1)
            throw new IllegalArgumentException();
        this.fieldsize = size;
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
