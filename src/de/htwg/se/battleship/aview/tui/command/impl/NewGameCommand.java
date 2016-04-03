package de.htwg.se.battleship.aview.tui.command.impl;

import de.htwg.se.battleship.aview.tui.command.CommandBase;
import de.htwg.se.battleship.controller.GameStateController;

/**
 * @author aullik on 18.01.2016.
 */
public class NewGameCommand extends CommandBase {

   private final static String COMMAND = "newGame";
   private final static String DESC = "Starts a new Game.";

   private final GameStateController gameStateController;

   public NewGameCommand(GameStateController gameStateController) {
      super(COMMAND, DESC);
      this.gameStateController = gameStateController;
   }


   @Override
   public void doAction() {
      gameStateController.startNewGame();
   }
}
