package de.htwg.se.battleship.aview.tui.command.impl;

import de.htwg.se.battleship.aview.tui.command.CommandBase;

/**
 * @author aullik on 01.03.2016.
 */
public class SurrenderGame extends CommandBase {

   private final static String COMMAND = "surrender";
   private final static String DESC = "Surrender the current game.";
   private final Runnable onAction;

   public SurrenderGame(Runnable onAction) {
      super(COMMAND, DESC);
      this.onAction = onAction;
   }

   @Override
   public void doAction() {
      onAction.run();
   }
}
