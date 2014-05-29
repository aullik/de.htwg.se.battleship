/**
 * 
 */
package de.htwg.se.battleship.aview.tui.impl;

import com.google.inject.Inject;

import de.htwg.se.battleship.aview.tui.IMenu;
import de.htwg.se.battleship.aview.tui.IMenuEntry;
import de.htwg.se.battleship.aview.tui.InitGameUI;
import de.htwg.se.battleship.controller.IController;
import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.controller.event.CloseProgamm;
import de.htwg.se.battleship.controller.event.InitGame;

/**
 * @author Philipp Daniels <philipp.daniels@gmail.com>
 */
public class MainMenuUi extends UserInterface {

    public static final String MSG_INPUT_NOTE   = "%s\n%s\nPlease choice a menu: ";
    public static final String MSG_DEFAULT_MENU = "Sorry bro, but '%s' has no menu entry!%n";
    public static final String MSG_EXIT         = "This is the end my friend!";
    public static final String MENU_FORMAT      = "%-20s - %s\n";
    public static final String MENU_HEAD        = "Main Menu:\n";

    private final IMenu menu;
    private final UserInterface initGameUI;
    private final IInitGameController igc;
    private final ConsoleInput input;
    private UserInterface ui;

    /**
     * @param scanner
     */
    @Inject
    public MainMenuUi(ConsoleInput input, IMenu menu, IController controller, InitGameUI initGameUI, IInitGameController igc) {
        controller.addObserver(this);

        this.input = input;
        this.menu = menu;
        this.initGameUI = initGameUI;
        this.ui = this;
        this.igc = igc;
    }

    /**
     * Shutdown TUI process input
     * 
     * @param e CloseProgamm
     */
    public void update(CloseProgamm e) {
        deactivateProcess();
        output(header() + MSG_EXIT);
        input.close();
    }

    /**
     * Start TUI for game initialization
     * @param e InitGame
     */
    public void update(InitGame e) {
        ui = initGameUI;
        igc.init();
    }

    @Override
    public UserInterface executeInput(String input) {
        if (getProcess()) {
            executeAction(input);
        }
        return ui;
    }

    private void executeAction(String input) {
        IMenuEntry e = menu.get().get(input);
        if (e == null) {
            output(header() + String.format(MSG_DEFAULT_MENU, input));
        } else {
            e.action();
        }
    }

    @Override
    public void showText() {
        output(String.format(MSG_INPUT_NOTE, header(), buildMenu()));
    }

    private String buildMenu() {
        StringBuilder sb = new StringBuilder();
        for (IMenuEntry entry: menu.get().values()) {
            buildMenuEntry(sb, entry);
        }
        return MENU_HEAD + sb.toString();
    }

    private void buildMenuEntry(StringBuilder sb, IMenuEntry entry) {
        String string = String.format(MENU_FORMAT, entry.command(), entry.description());
        sb.append(string);
    }
}
