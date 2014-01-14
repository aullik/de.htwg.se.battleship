/**
 * 
 */
package de.htwg.se.battleship.aview.tui.menuentry;

import de.htwg.se.battleship.aview.tui.IMenuEntry;
import de.htwg.se.battleship.controller.IController;

/**
 * 
 * 
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public class NewGame implements IMenuEntry {

    private final IController controller;

    /**
     * The NewGame is the menu entry, that start a new game
     * initiation at the controller
     * @param controller
     */
    public NewGame(IController controller) {
        this.controller = controller;
    }

    @Override
    public void action() {
        controller.newGame();
    }

    @Override
    public String command() {
        return "-newgame";
    }

    @Override
    public String description() {
        return "Start a new game";
    }

}
