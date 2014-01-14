/**
 * 
 */
package de.htwg.se.battleship.aview.tui.menuentry;

import de.htwg.se.battleship.aview.tui.IMenuEntry;
import de.htwg.se.battleship.controller.IController;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class Close implements IMenuEntry {

    private final IController controller;

    /**
     * Close is the menu entry, that start exit the program
     */
    public Close(IController controller) {
        this.controller = controller;
    }

    @Override
    public void action() {
        controller.close();
    }

    @Override
    public String command() {
        return "-close";
    }

    @Override
    public String description() {
        return "Terminates program";
    }

}
