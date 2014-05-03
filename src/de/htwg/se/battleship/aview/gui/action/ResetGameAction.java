/**
 * 
 */
package de.htwg.se.battleship.aview.gui.action;

import javax.swing.JOptionPane;

import de.htwg.se.battleship.aview.gui.Action;
import de.htwg.se.battleship.aview.gui.impl.MainFrame;
import de.htwg.se.battleship.aview.gui.impl.Menu;
import de.htwg.se.battleship.controller.IController;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class ResetGameAction extends Action {

    private static final String DIALOG_QUESTION = "Are you sure you want to abort current Game?";

    /**
     * Actions is responsible to confirm and execute a game-reset.
     * 
     * @param mainFrame
     * @param controller
     */
    public ResetGameAction(MainFrame mainFrame, IController controller) {
        super(mainFrame, controller);
    }

    /* (non-Javadoc)
     * @see de.htwg.se.battleship.aview.gui.Action#perform()
     */
    @Override
    public void perform() {
        int result = executeDialog();
        if (isDialogConfirmed(result)) {
            getController().reset();
        }
    }

    private int executeDialog() {
        int dialogMode  = JOptionPane.YES_NO_OPTION;
        String title    = Menu.BUTTON_TEXT_RESET_PLAYER;
        MainFrame mf    = getMainFrame();
        return JOptionPane
                .showConfirmDialog(mf, DIALOG_QUESTION, title, dialogMode);
    }

    private boolean isDialogConfirmed(int result) {
        return (result == JOptionPane.YES_OPTION);
    }

}