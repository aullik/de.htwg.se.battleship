package de.htwg.se.battleship.aview.tui;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

import de.htwg.se.battleship.aview.tui.menuEntry.Close;
import de.htwg.se.battleship.aview.tui.menuEntry.NewGame;
import de.htwg.se.battleship.controller.IController;
import de.htwg.se.battleship.controller.event.CloseProgamm;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.IObserver;

/**
 * Text User Interface is an Observer
 * 
 * @author aullik
 */
public class TextUI implements IObserver {

    public static final String MSG_INPUT_NOTE   = "Please choice a menu: ";
    public static final String MSG_DEFAULT_MENU = "Sorry bro, but '%s' has no menu entry!%n";
    public static final String MSG_EXIT         = "This is the end my friend!";

    private final Logger        logger = Logger.getLogger("de.htwg.se.battleship.aview.tui");
    private final IController controller;
    private final Map<String, IMenuEntry> menu;

    private boolean process = true;

    /**
     * @param controller controller to observe
     */
    public TextUI(IController controller, InputStream stream) {
        this.controller = controller;
        controller.addObserver(this);

        menu = new HashMap<String, IMenuEntry>();
        initMenuEntry();

        logger.info(header());
        processInput(stream);
    }

    private void initMenuEntry() {
        add(new NewGame(controller));
        add(new Close(controller));
    }

    private void add(IMenuEntry entry) {
        menu.put(entry.command(), entry);
    }

    private void processInput(InputStream stream) {
        String input;
        Scanner scanner = new Scanner(stream);

        while (process) {
            logger.info(menu());
            logger.info(MSG_INPUT_NOTE);
            input = scanner.nextLine();

            logger.info(header());

            executeInput(input);
        }

        scanner.close();
    }

    private void executeInput(String input) {
        IMenuEntry e = menu.get(input);

        if (e == null) {
            logger.info(String.format(MSG_DEFAULT_MENU, input));
        } else {
            logger.debug(e.getClass());
            e.action();
        }

    }

    @Override
    public void update(Event e) {
        throw new IllegalArgumentException("This class has no listener for " + e.getClass().toString());
    }

    /**
     * Shutdown TUI process input
     * @param e CloseProgamm
     */
    public void update(CloseProgamm e) {
        process = false;
        logger.info(MSG_EXIT);
    }

    /**
     * Game name as ASCII-Art:
     * http://patorjk.com/software/taag/#p=display&f=Big&t=Battleship
     */
    private String header() {

        StringBuilder sb = new StringBuilder();
        sb.append("***********************************************************").append("\n");
        sb.append("*      ____        _   _   _           _     _            *").append("\n");
        sb.append("*     |  _ \\      | | | | | |         | |   (_)           *").append("\n");
        sb.append("*     | |_) | __ _| |_| |_| | ___  ___| |__  _ _ __       *").append("\n");
        sb.append("*     |  _ < / _` | __| __| |/ _ \\/ __| '_ \\| | '_ \\      *").append("\n");
        sb.append("*     | |_) | (_| | |_| |_| |  __/\\__ \\ | | | | |_) |     *").append("\n");
        sb.append("*     |____/ \\__,_|\\__|\\__|_|\\___||___/_| |_|_| .__/      *").append("\n");
        sb.append("*                                             | |         *").append("\n");
        sb.append("*                                             |_|         *").append("\n");
        sb.append("***********************************************************").append("\n");
        return sb.toString();
    }


    private String menu() {
        StringBuilder sb = new StringBuilder();
        sb.append("Main Menu:").append("\n");

        for (IMenuEntry entry : menu.values()) {
            sb.append(String.format("%-20s - ", entry.command()));
            sb.append(entry.description()).append("\n");
        }
        return sb.toString();
    }
}
