/**
 * 
 */
package de.htwg.se.battleship.aview.gui.action;

import javax.swing.JOptionPane;

import de.htwg.se.battleship.aview.gui.Action;
import de.htwg.se.battleship.aview.gui.MainFrame;
import de.htwg.se.battleship.controller.IController;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class CloseAction extends Action {

    private static final String DIALOG_QUESTION = "Are you sure you want to quit?";
    private static final String DIALOG_TITLE    = "Confirm Dialog";

    /**
     * Action is responsible to confirm the request to exit the game.
     * 
     * @param panel
     * @param controller
     */
    public CloseAction(MainFrame mainFrame, IController controller) {
        super(mainFrame, controller);
    }

    /* (non-Javadoc)
     * @see de.htwg.se.battleship.aview.gui.Action#perform()
     */
    @Override
    public void perform() {
        int result = executeDialog();
        if (isDialogConfirmed(result)) {
            getController().close();
        }
    }

    private int executeDialog() {
        MainFrame mf    = getMainFrame();
        int dialogMode  = JOptionPane.YES_NO_OPTION;
        return JOptionPane
                .showConfirmDialog(mf, DIALOG_QUESTION, DIALOG_TITLE, dialogMode);
    }

    private boolean isDialogConfirmed(int result) {
        return (result == JOptionPane.YES_OPTION);
    }

}
