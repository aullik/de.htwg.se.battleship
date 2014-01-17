/**
 * 
 */
package de.htwg.se.battleship.aview.tui;


import java.util.Map;

import com.google.inject.Inject;

import de.htwg.se.battleship.controller.IController;
import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.controller.event.CloseProgamm;
import de.htwg.se.battleship.controller.event.InitGame;

/**
 * @author Philipp
 *
 */
public class MainMenuUI extends UserInterface {

    public static final String MSG_INPUT_NOTE   = "Please choice a menu: ";
    public static final String MSG_DEFAULT_MENU = "Sorry bro, but '%s' has no menu entry!%n";
    public static final String MSG_EXIT         = "This is the end my friend!";


    private final Map<String, IMenuEntry> menu;
    private final UserInterface initGameUI;
    private final IInitGameController igc;
    private boolean process;
    private UserInterface ui;

    /**
     * @param scanner
     */
    @Inject
    public MainMenuUI(IMenu menu, IController controller, InitGameUI initGameUI, IInitGameController igc) {
        controller.addObserver(this);

        this.menu = menu.get();
        this.initGameUI = initGameUI;
        this.process = true;
        this.ui = this;
        this.igc = igc;
    }

    /**
     * Shutdown TUI process input
     * @param e CloseProgamm
     */
    public void update(CloseProgamm e) {
        process = false;
        getLogger().info(header());
        getLogger().info(MSG_EXIT);
    }

    /**
     * Start TUI for game initialization
     * @param e InitGame
     */
    public void update(InitGame e) {
        ui = initGameUI;
        igc.init();
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

    @Override
    public boolean executeInput(String input) {

        IMenuEntry e = menu.get(input);

        if (e == null) {
            getLogger().info(header());
            getLogger().info(String.format(MSG_DEFAULT_MENU, input));
        } else {
            e.action();
        }
        return process;
    }

    @Override
    public void showText() {
        getLogger().info(header()  + "\n" +  menu() + "\n" + MSG_INPUT_NOTE);
    }

    @Override
    public UserInterface getUI() {
        return ui;
    }

}
