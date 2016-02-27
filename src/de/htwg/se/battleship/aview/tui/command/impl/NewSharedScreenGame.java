package de.htwg.se.battleship.aview.tui.command.impl;

import de.htwg.se.battleship.aview.tui.command.CommandBase;
import de.htwg.se.battleship.controller.GameStateController;

/**
 * @author aullik on 18.01.2016.
 */
public class NewSharedScreenGame extends CommandBase {

   private final static String COMMAND = "newSharedGame";
   private final static String DESC = "Starts a new Game with both Players playing on the same screen";

   private final GameStateController gameStateController;

   public NewSharedScreenGame(GameStateController gameStateController) {
      super(COMMAND, DESC);
      this.gameStateController = gameStateController;
   }


   @Override
   public void doAction() {
      gameStateController.startNewSharedScreenGame();
   }
}
