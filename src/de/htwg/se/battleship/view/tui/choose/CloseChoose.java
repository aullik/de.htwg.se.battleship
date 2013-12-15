/**
 * 
 */
package de.htwg.se.battleship.view.tui.choose;

import de.htwg.se.battleship.controller.IntController;
import de.htwg.se.battleship.view.tui.Choose;

/**
 * @author Philipp
 *
 */
public class CloseChoose implements Choose {

    private final IntController controller;

    /**
     * The CloseChoose calls the exit method from IntController.
     * @param controller
     */
    public CloseChoose(IntController controller) {
        this.controller = controller;
    }

    @Override
    public void action() {
        controller.exit();
    }

}
