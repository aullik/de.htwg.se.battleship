package de.htwg.se.battleship.view.tui;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import de.htwg.se.battleship.controller.IntController;
import de.htwg.se.battleship.controller.event.CloseEvent;
import de.htwg.se.battleship.controller.event.NewGameEvent;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.IObserver;
import de.htwg.se.battleship.view.tui.choose.CloseChoose;
import de.htwg.se.battleship.view.tui.choose.NewGameChoose;

/**
 * First contact point of the TUI
 * 
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public class MainMenu extends TextUI implements IObserver {

    public static final String MSG_INPUT_NOTE   = "Please choice a number: ";
    public static final String MSG_INPUT_ERROR  = "This was not a number";
    public static final String MSG_DEFAULT_MENU = "Sorry bro, but %d has no menu entry!";
    public static final String MSG_EXIT         = "            This is the end my friend!";

    public static final int NEW_GAME = 1;
    public static final int CLOSE    = 0;

    private final IntController controller;
    private boolean             process=true;

    private final Map<Integer, Choose> choose;

    /**
     * Entry point for the TUI.
     * 
     * @param c   Controller to execute user action and synchronize user-interface
     * @param in  InputStream
     * @param out OutputStream
     */
    public MainMenu(IntController c, InputStream in, OutputStream out) {
        controller = c;
        controller.addObserver(this);
        initInOut(in, out);

        choose = new HashMap<Integer, Choose>();
        initMenuChoose();

        outputHeader();
        processInput();
    }

    private void initMenuChoose() {
        choose.put(NEW_GAME, new NewGameChoose(controller));
        choose.put(CLOSE, new CloseChoose(controller));
    }

    private void processInput() {
        Integer input;

        while (process) {
            outputMenu();

            output(MSG_INPUT_NOTE);
            input = nextInt();

            outputHeader();

            /* Input error validation */
            if (input == null) {
                outputError(MSG_INPUT_ERROR);
                continue;
            }

            executeChoose(input);
        }
    }

    private void executeChoose(int choose) {
        if (this.choose.containsKey(choose)) {
            this.choose.get(choose).action();
        } else {
            outputError(String.format(MSG_DEFAULT_MENU, choose));
        }
    }

    private void outputMenu() {
        outputln("MAIN MENU:");
        outputln(" " + NEW_GAME + " - Start new game");
        outputln(" " + CLOSE + " - Exit programm");
        outputln("");
    }

    @Override
    public void update(Event e) {
        throw new IllegalArgumentException("The class has no listener for " + e.getClass().toString());
    }

    /**
     * Observer update for CloseEvent
     * 
     * @param e CloseEvent
     */
    public void update(CloseEvent e) {
        process = false;
        outputln(MSG_EXIT);
    }

    /**
     * Observer update for NewGameEvent
     * 
     * @param e NewGameEvent
     */
    public void update(NewGameEvent e) {
        outputln("This feature is not yet implemented!\n");
    }
}
