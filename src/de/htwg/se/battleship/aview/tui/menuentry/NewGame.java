/**
 *
 */
package de.htwg.se.battleship.aview.tui.menuentry;

import de.htwg.se.battleship.aview.tui.IMenuEntry;
import de.htwg.se.battleship.controller.old.IOLDController;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public class NewGame implements IMenuEntry {

   private final IOLDController controller;
   public static final String CMD = "-newgame";
   public static final String DESC = "Start a new game";

   /**
    * The NewGame is the menu entry, that start a new game
    * initiation at the controller
    *
    * @param controller
    */
   public NewGame(IOLDController controller) {
      this.controller = controller;
   }

   @Override
   public void action() {
      controller.newGame();
   }

   @Override
   public String command() {
      return CMD;
   }

   @Override
   public String description() {
      return DESC;
   }

}
