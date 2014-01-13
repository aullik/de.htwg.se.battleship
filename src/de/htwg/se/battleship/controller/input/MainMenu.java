package de.htwg.se.battleship.controller.input;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import de.htwg.se.battleship.controller.INTCommands;
import de.htwg.se.battleship.controller.INTInputState;
import de.htwg.se.battleship.controller.InputController;
import de.htwg.se.battleship.controller.commands.CloseGame;
import de.htwg.se.battleship.controller.commands.NewGame;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.events.ErrorEvent;

/**
 * the GameState MainMenu
 * 
 * @author aullik
 */
public class MainMenu implements INTInputState {

    private static final String      OPENCOMMAND = "-mainmenu";
    private static final String      MENU_NAME   = "Main Menu";
    private final Event              stdEvent;

    private Map<String, INTCommands> commands;

    /**
     * initiates commands and event
     */
    public MainMenu() {
        initCommands();
        commands = new TreeMap<String, INTCommands>();
        INTCommands com;
        com = new NewGame();
        commands.put(com.getCommand(), com);
        com = new CloseGame();
        commands.put(com.getCommand(), com);
        stdEvent = InputController.welcomeEvent(this);

    }

    private void initCommands() {
        commands = new TreeMap<String, INTCommands>();
        INTCommands com;
        com = new NewGame();
        commands.put(com.getCommand(), com);
        com = new CloseGame();
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
     * get open command
     * 
     * @return command to open this State
     */
    public static String getCommand() {
        return OPENCOMMAND;
    }

    @Override
    public Event getEvent() {
        return stdEvent;
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
        return getHeader() + MENU_NAME;
    }

    /*
     * Game name as ASCII-Art:
     * http://patorjk.com/software/taag/#p=display&f=Big&t=Battleship
     */
    private String getHeader() {

        StringBuilder sb = new StringBuilder();
        sb.append("***********************************************************")
                .append("\n");
        sb.append("*      ____        _   _   _           _     _            *")
                .append("\n");
        sb.append(
                "*     |  _ \\      | | | | | |         | |   (_)           *")
                .append("\n");
        sb.append("*     | |_) | __ _| |_| |_| | ___  ___| |__  _ _ __       *")
                .append("\n");
        sb.append(
                "*     |  _ < / _` | __| __| |/ _ \\/ __| '_ \\| | '_ \\      *")
                .append("\n");
        sb.append(
                "*     | |_) | (_| | |_| |_| |  __/\\__ \\ | | | | |_) |     *")
                .append("\n");
        sb.append(
                "*     |____/ \\__,_|\\__|\\__|_|\\___||___/_| |_|_| .__/      *")
                .append("\n");
        sb.append("*                                             | |         *")
                .append("\n");
        sb.append("*                                             |_|         *")
                .append("\n");
        sb.append("***********************************************************")
                .append("\n");
        sb.append("\n");
        return sb.toString();
    }

}
