package de.htwg.se.battleship.aview.tui;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


import de.htwg.se.battleship.aview.tui.menuEntry.Close;
import de.htwg.se.battleship.aview.tui.menuEntry.NewGame;
import de.htwg.se.battleship.controller.IController;
import de.htwg.se.battleship.controller.event.CloseProgamm;
import de.htwg.se.battleship.controller.event.InitGame;

/**
 * Text User Interface is an Observer
 * 
 * @author aullik
 */
public class TextUI extends UserInterface  {

    public static final String MSG_INPUT_NOTE   = "Please choice a menu: ";
    public static final String MSG_DEFAULT_MENU = "Sorry bro, but '%s' has no menu entry!%n";
    public static final String MSG_EXIT         = "This is the end my friend!";


    private final IController controller;
    private final Map<String, IMenuEntry> menu;

    private boolean process = true;

    /**
     * @param controller controller to observe
     */
    public TextUI(IController controller, InputStream stream) {
        super(stream);

        this.controller = controller;
        controller.addObserver(this);

        menu = new HashMap<String, IMenuEntry>();
        initMenuEntry();

        logger.info(header());
        processInput();
    }

    private void initMenuEntry() {
        add(new NewGame(controller));
        add(new Close(controller));
    }

    private void add(IMenuEntry entry) {
        menu.put(entry.command(), entry);
    }

    private void processInput() {
        String input;
        Scanner scanner = new Scanner(stream);

        while (process) {
            logger.info(menu());
            logger.info(MSG_INPUT_NOTE);
            input = scanner.nextLine();

            executeInput(input);
        }

        scanner.close();
    }

    private void executeInput(String input) {
        IMenuEntry e = menu.get(input);

        if (e == null) {
            logger.info(header());
            logger.info(String.format(MSG_DEFAULT_MENU, input));
        } else {
            e.action();
        }

    }

    /**
     * Shutdown TUI process input
     * @param e CloseProgamm
     */
    public void update(CloseProgamm e) {
        logger.info(header());
        process = false;
        logger.info(MSG_EXIT);
    }

    /**
     * Start TUI for game initialization
     * @param e InitGame
     */
    public void update(InitGame e) {
        new InitGameUI(e.getController(), stream);
        e.getController().init();
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
