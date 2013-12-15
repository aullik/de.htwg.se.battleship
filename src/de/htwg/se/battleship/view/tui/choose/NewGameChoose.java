/**
 * 
 */
package de.htwg.se.battleship.view.tui.choose;

import de.htwg.se.battleship.controller.IntController;
import de.htwg.se.battleship.view.tui.Choose;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class NewGameChoose implements Choose {

    private final IntController controller;

    /**
     * The NewGameChoose calls the newGame method from IntController.
     * @param controller IntController
     */
    public NewGameChoose(IntController controller) {
        this.controller = controller;
    }

    @Override
    public void action() {
        controller.newGame();
    }

}
