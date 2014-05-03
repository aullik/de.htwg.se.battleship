/**
 * 
 */
package de.htwg.se.battleship.aview.gui.action;

import de.htwg.se.battleship.aview.gui.Action;
import de.htwg.se.battleship.aview.gui.MainFrame;
import de.htwg.se.battleship.aview.gui.Menu;
import de.htwg.se.battleship.controller.IController;
import de.htwg.se.battleship.controller.IInitGameController;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class StartAction extends Action {

    private Integer gameState;
    private final StringBuilder player1;
    private final StringBuilder player2;
    private final IInitGameController initC;

    /**
     * @param mainFrame
     * @param controller
     */
    public StartAction(MainFrame mainFrame, IController controller, Integer gameState, StringBuilder player1, StringBuilder player2, IInitGameController initC) {
        super(mainFrame, controller);
        this.gameState = gameState;
        this.player1 = player1;
        this.player2 = player2;
        this.initC   = initC;
    }

    /* (non-Javadoc)
     * @see de.htwg.se.battleship.aview.gui.Action#perform()
     */
    @Override
    public void perform() {
        if (gameState.equals(Menu.GAMESTATE_SPGAME)) {
            player2.setLength(0);
            gameState = Menu.GAMESTATE_SPSETSHIP;
        } else {
            gameState = Menu.GAMESTATE_MPSETSHIP;
        }
        initC.player(player1.toString(), player2.toString());
        getMainFrame().resetButtons();
    }

}
