/**
 *
 */
package de.htwg.se.battleship.aview.gui.action;

import de.htwg.se.battleship.aview.gui.Action;
import de.htwg.se.battleship.aview.gui.MainFrame;
import de.htwg.se.battleship.controller.IMenuController;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public class ContinueAction extends Action {

   /**
    * Action is responsible to continue the game.
    *
    * @param mainFrame
    * @param controller
    */
   public ContinueAction(MainFrame mainFrame, IMenuController controller) {
      super(mainFrame, controller);
   }

   /* (non-Javadoc)
    * @see de.htwg.se.battleship.aview.gui.Action#perform()
    */
   @Override
   public void perform() {
      getMainFrame().swapPanel();
   }

}
