/**
 * 
 */
package de.htwg.se.battleship.aview.gui.action;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import de.htwg.se.battleship.aview.gui.Action;
import de.htwg.se.battleship.aview.gui.MainFrame;
import de.htwg.se.battleship.controller.IController;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class RenamePlayerAction extends Action {

    private static final String DIALOG_QUESTION = "Enter new Player name:";
    private static final String BUTTON_PREFIX = "Rename ";

    private final StringBuilder playerName;
    private final JButton button;

    /**
     * Action is responsible to rename a player.
     * 
     * @param panel
     * @param controller
     */
    public RenamePlayerAction(MainFrame mainFrame, IController controller, StringBuilder playerName, JButton button) {
        super(mainFrame, controller);
        this.playerName = playerName;
        this.button     = button;
    }

    /* (non-Javadoc)
     * @see de.htwg.se.battleship.aview.gui.Action#perform()
     */
    @Override
    public void perform() {
        String newName = executeDialog();
        if (!newName.isEmpty()) {
            playerName.setLength(0);
            playerName.append(newName);
            button.setText(BUTTON_PREFIX + newName);
            getMainFrame().repaint();
        }
    }

    private String executeDialog() {
        MainFrame mf     = getMainFrame();
        int dialogMode  = JOptionPane.QUESTION_MESSAGE;
        String title    = button.getText();
        return JOptionPane
                .showInputDialog(mf, DIALOG_QUESTION, title, dialogMode, null, null, null)
                .toString();
    }

}
