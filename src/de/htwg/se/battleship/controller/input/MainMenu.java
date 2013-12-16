package de.htwg.se.battleship.controller.input;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import de.htwg.se.battleship.controller.INT_Commands;
import de.htwg.se.battleship.controller.INT_InputState;
import de.htwg.se.battleship.controller.InputController;
import de.htwg.se.battleship.controller.commands.CloseGame;
import de.htwg.se.battleship.controller.commands.NewGame;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.events.ErrorEvent;

public class MainMenu implements INT_InputState {

    private static final String       OPENCOMMAND = "mainmenu";
    private static final String       MENU_NAME   = "Main Menu";
    private final Event               event;

    private Map<String, INT_Commands> commands;

    public MainMenu() {
        commands = new TreeMap<String, INT_Commands>();
        INT_Commands com;
        com = new NewGame();
        commands.put(com.getCommand(), com);
        com = new CloseGame();
        commands.put(com.getCommand(), com);
        event = InputController.welcomeEvent(this);

    }

    @Override
    public Event[] processInput(InputController controller, String line) {
        Event[] array;
        String[] in = InputController.splitInput(line, commands.keySet());

        array = commands.get(in[1]).action(in, this, controller);
        if (array == null)
            array = new Event[] { new ErrorEvent("input didnt match: " + line) };

        return array;
    }

    protected static String getCommand() {
        return OPENCOMMAND;
    }

    @Override
    public Event getEvent() {
        return event;
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
