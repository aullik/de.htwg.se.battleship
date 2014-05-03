/**
 * 
 */
package de.htwg.se.battleship.aview.gui.action;

import de.htwg.se.battleship.aview.gui.Action;
import de.htwg.se.battleship.aview.gui.impl.MainFrame;
import de.htwg.se.battleship.controller.IController;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class GameModeAction extends Action {

    @SuppressWarnings("unused")
    private Integer gameState;
    private final int newGameState;

    /**
     * Set and initialize game mode.
     * 
     * @param mainFrame
     * @param controller
     */
    public GameModeAction(MainFrame mainFrame, IController controller, Integer gameState, int newGameState) {
        super(mainFrame, controller);
        this.gameState      = gameState;
        this.newGameState   = newGameState;
    }

    /* (non-Javadoc)
     * @see de.htwg.se.battleship.aview.gui.Action#perform()
     */
    @Override
    public void perform() {
        IController c   = getController();
        MainFrame mf    = getMainFrame();

        updateGameState();
        mf.resetButtons();
        c.newGame();
        mf.initGamefield();
    }

    private void updateGameState() {
        this.gameState = newGameState;
    }
}